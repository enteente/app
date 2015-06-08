package ente.exercise.rbtree;

import ente.exercise.algebra.*;
import java.util.*;
import ente.exercise.adt.*;

/** class RedBlackTree is a modifiable collection
 */

public class RedBlackTree<E extends Comparable<E>> extends AbstractCollection<E>{
    private int black = 1;//schwarztiefe
    private int size = 0;
    private NodeRB<E> root;//elements

    /** adds a new Node with color-characteristic to the RedBlackTree
     *  @param E
     *  @return boolean 
     *  true if a new Node was inserted
     *  false if the Node already existed
     */
    @Override
    public boolean add(E e){
        System.out.println("aufruf von add mit: "+e);
            /*if tree is empty insert element as new root*/
            if(root == null){
                root = new NodeRB<E>(true,null,e);
                size++;
                return true;
            }
            return rekAdd(root,new NodeRB<E>(true,e));
    }

    /** gets current root*/
    public NodeRB<E> getRoot(){
        return root;
    }

    /** recursivley finding Node
     */
    public boolean rekAdd(NodeRB<E> n, NodeRB<E> m){
        /*compare node with node m(inserting)*/
        int c = n.compareTo(m);
        NodeRB<E> neu;
        /*cases*/
        if(c == 0){
            return false;
        }
        else if(c > 0){
            /*if has no leftchild found parent of m*/
            if(n.hasLeftchild()){
                rekAdd(n.getLeftchild(),m);
            }
            else{
                if(m.hasLeftchild() || m.hasRightchild()){
                    neu = m;
                    m.setParent(n);
                }
                else{
                    neu = new NodeRB<E>(true,n,m.getData());
                }
                n.setLeftchild(neu);
                recolor(neu,true);
                size++;
                return true;
            }
            return true;
        }
        else if(c < 0){
            /*if has no rightchild found parent of m*/
            if(n.hasRightchild()){
                rekAdd(n.getRightchild(),m);
            }
            else{
                if(m.hasLeftchild() || m.hasRightchild()){
                    neu = m;
                    m.setParent(n);
                }
                else{
                    neu = new NodeRB<E>(true,n,m.getData());
                }
                n.setRightchild(neu);
                recolor(neu,true);
                size++;
                return true;
            }
            return true;
        }
        return false;
    }

    /** removes given node (object) from tree */
    @Override
    public boolean remove(Object o){
        NodeRB<E> m = null;
        /*checking if Object is valid*/
        if(!(o instanceof NodeRB)){
            throw new IllegalArgumentException("element is not even of type Node");
        }
        else{
            m = (NodeRB<E>) o;
        }

        /*base case of empty tree*/
        if(root == null){
            return false;
        }

        //System.out.println("removing: "+m);
        /*case specification on the number of childs*/
        if(!m.hasLeftchild() && !m.hasRightchild()){
            /*no childs: just remove Node and set child from father to null*/
            NodeRB<E> BLATT = new NodeRB<E>(m.getParent());
            if(!m.setChild(new NodeRB<E>(false,m.getParent(),null))){
                root = null;//empty
            }
            /*recolor if removed node was black*/
            if(!m.getColor()){
                recolor(BLATT,false);
            }
        }
        else if(m.hasLeftchild() && m.hasRightchild()){
            /*two childs: NextNode is inserted in space of m and deleted with another case (has no leftchild)*/
            NodeRB<E> R = m.getNext();//next
            /*resetting pointers*/
            if(!m.setChild(R)){
                root = R;
            }
            if(R.hasRightchild()){
                R.getParent().setLeftchild(R.getRightchild());
                /*if R was black, recolor with old rightchild of R*/
                if(!R.getColor()){
                    recolor(R.getRightchild(),false);
                }
            }
            R.setRightchild(m.getRightchild());
            R.setParent(m.getParent());
            R.setColor(m.getColor());
        }
        else if(m.hasLeftchild()){
            /*leftchild is new node ... */
            if(!m.setChild(m.getLeftchild())){
                root = m.getLeftchild();
            }
            m.getLeftchild().setParent(m.getParent());
            /*recolor if m was black*/
            if(!m.getColor()){
                recolor(m.getLeftchild(),false);
            }
        }
        else{
            /*rightchild is new node ...*/
            moveNode(m);
            /*recolor if m was black*/
            if(!m.getColor()){
                recolor(m.getRightchild(),false);
            }
        }
        size--;
        return true;
    }

    /** moveNode() overrides node  with rightchild 
     *    @param non
     *    @return void
     */
    private NodeRB<E> moveNode(NodeRB<E> n){/*always rightnode caused by structure of binarytree*/
        if(!n.setChild(n.getRightchild())){
            root = n.getRightchild();
        }
        n.getRightchild().setParent(n.getParent());//rightsons parent set to parent
        return n;
    }
         
    /** returns number of Nodes in RedBlackTree
     */
    @Override
    public int size(){
        return size;
    }


    /** returns the black-depth 
     */
    public int getBlack(){
        return black;
    }

    /**returns new reverse in-order iterator (starting with biggest element
     */
    public RevInOrder<E> revIterator(){
        try{
            /*find start-element*/
            NodeRB<E> n = root;
            while(n.hasRightchild()){
                n = n.getRightchild();
            }
            //System.out.println("new Iterator starts with: "+n);
            return new RevInOrder<E>(n,this);
        }catch(NullPointerException p){
            System.out.print("RedBlackTree is empty!");
            return null;
        }
    }

    /**returns new in-order iterator (starting with smallest element)
     */
    @Override
    public InOrder<E> iterator(){
        try{
            /*find start-element*/
            NodeRB<E> n = root;
            while(n.hasLeftchild()){
                n = n.getLeftchild();
            }
            //System.out.println("new Iterator starts with: "+n);
            return new InOrder<E>(n,this);
        }catch(NullPointerException p){
            System.out.print("RedBlackTree is empty!");
            return null;
        }
    }

    /** in case of adding or removing a Node reshaping RedBlackTree 
     *  to continue be confirm with red-black rules
     *  @param NodeRB<E> n
     *  @param boolean io 
     */
    private void recolor(NodeRB<E> n,boolean io){
        if(io){//insert case
            //n root or black
            if(n.isRoot() || !n.getParent().getColor()){
                //insertFix1 >> nothing happens
            }
            //parent red root
            else if(n.getParent().isRoot() && n.getParent().getColor()){
                insertFix2(n);
            }
            //parent and uncle red
            else if(n.hasUncle() && n.getParent().getColor() && n.getUncle().getColor()){
                insertFix3(n);
            }
            //parent red, uncle black
            else if(n.hasUncle() && n.getParent().getColor() && !n.getUncle().getColor()){
                insertFix4(n);
            }
        }
        else{//removce case
            if(n.getColor()){
                n.setColor(false);
            }
            else if(n.isRoot()){
                //ready
            }
            else if(n.whichChild()){
                removeFix1(n);
            }
            else{
                removeFix2(n);
            }
        }
        
    }

    /** color specific methods of a red black tree
     */

    /** rotate left
     * swaps dependencies
     */
    private boolean rotateLeft(NodeRB<E> x){
        NodeRB<E> two = x.getRightchild().getLeftchild();
        if(x == null){
            throw new NoSuchElementException("blubb");
        }
        x.getRightchild().setLeftchild(x);
        if(x.isRoot()){
            x.getRightchild().setParent(null);
            root = x.getRightchild();
        }
        else if(x.whichChild()){//set grannies child
            x.getParent().setRightchild(x.getRightchild());
        }
        else{
            x.getParent().setLeftchild(x.getRightchild());
        }
        x.getLeftchild().setParent(x.getParent());//new granny
        x.setParent(x.getRightchild());//x father new
        two.setParent(x);//two parent new
        x.setRightchild(two);//x leftchild new
        return true;
    }

    /** rotate right
     * reverse swapping of rotate left
     */
    private boolean rotateRight(NodeRB<E> y){
        NodeRB<E> two = y.getLeftchild().getRightchild();
        if(y == null){
            throw new NoSuchElementException("blubb");
        }
        y.getLeftchild().setRightchild(y);//y is new rightchild of x
        if(y.isRoot()){
            y.getLeftchild().setParent(null);
            root = y.getLeftchild();
        }
        else if(y.whichChild()){//set grannies child
            y.getParent().setLeftchild(y.getLeftchild());
        }
        else{
            y.getParent().setRightchild(y.getLeftchild());
        }
        y.getLeftchild().setParent(y.getParent());//new granny
        y.setParent(y.getLeftchild());//y father new
        two.setParent(y);//new parent of two is y
        y.setLeftchild(two);//y leftchild new
        return true;
    }

    /*fix case 2 from exercise*/
    private boolean insertFix2(NodeRB<E> n){
        n.getParent().setColor(false);
        black++;
        return true;
    }

    /*fix case 3 from exercise*/
    private boolean insertFix3(NodeRB<E> n){
        n.getParent().setColor(false);
        n.getUncle().setColor(false);
        n.getGranny().setColor(true);
        recolor(n.getGranny(),true);
        return true;
    }
    
    /*fix case 4 from exercise*/
    private boolean insertFix4(NodeRB<E> n){
        if(!n.isRoot() && n.getParent().whichChild() && !n.whichChild()){
            rotateLeft(n.getParent());
            insertFix4(n.getLeftchild());
        }
        else if(!n.isRoot() && !n.getParent().whichChild() && n.whichChild()){
            rotateRight(n.getParent());
            insertFix4(n.getRightchild());
        }
        else{
            boolean tmp = n.getParent().getColor();
            n.getParent().setColor(n.getGranny().getColor());
            n.getGranny().setColor(tmp);
            if(n.getParent().whichChild() && n.whichChild()){
                rotateRight(n.getGranny());
            }
            else{
                rotateLeft(n.getGranny());
            }
        }
        return true;
    }

    /* fix on removing case 1 from exercise*/
    private boolean removeFix1(NodeRB<E> n){
        /*number 1*/
        if(n.getSibling().getColor()){
            //System.out.println("\t11:sibling is red");
            n.getParent().setColor(true);
            n.getSibling().setColor(false);
            rotateLeft(n.getParent());
        }
        /*number 2*/
        if(n.getSibling().hasRightchild() && n.getSibling().getRightchild().getColor()){
            /*number 4*/
            //System.out.println("\t14:siblings rightson is red");
            boolean tmp = n.getParent().getColor();
            n.getParent().setColor(n.getSibling().getColor());
            n.getSibling().setColor(tmp);
            n.getSibling().getRightchild().setColor(false);
            rotateLeft(n.getParent());
        }
        else if(n.getSibling().hasLeftchild() && n.getSibling().getLeftchild().getColor()){
            /*number 3*/
            //System.out.println("\t13:siblings leftchild is red");
            n.getSibling().setColor(true);
            n.getSibling().getLeftchild().setColor(false);
            rotateRight(n.getSibling());
            removeFix1(n);
        }
        else{
            //System.out.println("\t1x:siblings childs are black");
            n.getSibling().setColor(true);
            black--;
            recolor(n.getParent(),false);
        }
        return true;
    }

    /*fix on remove case 2 from exercise*/
    private boolean removeFix2(NodeRB<E> n){
         /*number 1*/
        if(n.getSibling().getColor()){
            //System.out.println("\t21:sibling is red!");
            n.getParent().setColor(true);
            n.getSibling().setColor(false);
            rotateRight(n.getParent());
        }
        /*number 2*/
        if(n.getSibling().hasLeftchild() && n.getSibling().getLeftchild().getColor()){
            /*number 4*/
            //System.out.println("\t24:siblings leftchild is red");
            boolean tmp = n.getParent().getColor();
            n.getParent().setColor(n.getSibling().getColor());
            n.getSibling().setColor(tmp);
            n.getSibling().getLeftchild().setColor(false);
            rotateRight(n.getParent());
        }
        else if(n.getSibling().hasRightchild() && n.getSibling().getRightchild().getColor()){
            //System.out.println("\t23:siblings rightchild is red");
            /*number 3*/
            n.getSibling().setColor(true);
            n.getSibling().getRightchild().setColor(false);
            rotateLeft(n.getSibling());
            removeFix2(n);
        }
        else{
            //System.out.println("\t2x:siblings childs are black!");
            n.getSibling().setColor(true);
            black--;
            recolor(n.getParent(),false);
        }
        return true;
    }

}
