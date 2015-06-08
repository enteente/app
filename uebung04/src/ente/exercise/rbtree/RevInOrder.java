package ente.exercise.rbtree;

import java.util.*;

public class RevInOrder<E extends Comparable<E>> implements Iterator<E>{
    private NodeRB<E> here;
    private RedBlackTree<E> tree;
    
    public RevInOrder(NodeRB<E> n, RedBlackTree<E> tree){
        here = n;
        this.tree = tree;
    }

    public boolean hasNext(){
        //System.out.println("asked for hasnext, current is: "+here);
        /*if has leftchild has next!*/
        if(here.hasLeftchild()){
            return true;
        }
        /*otherwise has next if its a rightson in any instance*/ 
        else{
            NodeRB<E> tmp = here;
            try{
                this.next();
            }catch(NoSuchElementException a){
                return false;
            }
            here = tmp;
        }
        return true;    
    }

    /* returns next Element and sets intern next Node*/
    public E next(){
        /* look for rightest leftson*/
        if(here.hasLeftchild()){
            NodeRB<E> n = here.getLeftchild();
            while(n.hasRightchild()){
                n = n.getRightchild();
            }
            here = n;
            return here.getData();
        }
        /* look for parent ...where childpath is from lefftson */
        else{
            NodeRB<E> n = here.getParent();
            NodeRB<E> m = here;
            NodeRB<E> tmp;
            try{
                while(n != null && ((n.hasRightchild() && n.getRightchild().compareTo(m) != 0) || !n.hasRightchild())){
                    tmp = n;
                    n = n.getParent();
                    m = tmp;
                    if(n == null){
                        throw new NoSuchElementException("last element");
                    }
                }
                here = n;
                return here.getData();
            }catch(NullPointerException e){
                throw new NoSuchElementException();
            }
        }
    }

    /** removes current node from rbt
     */
    public void remove(){
        NodeRB<E> n = here; /*aktuellen knoten merken*/
        if(this.hasNext()){
            next();/*next*/
        }
        else{
            here = null;
        }
        /*remove old node*/
        tree.remove(n);
    }

    /* returns current node*/
    public NodeRB<E> getHere(){
        if(here != null){
            return here;
        }
        return null;
    }

}

