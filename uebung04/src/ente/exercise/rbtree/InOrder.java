package ente.exercise.rbtree;

import java.util.*;

public class InOrder<E extends Comparable<E>> implements Iterator<E>{
    private NodeRB<E> here;
    private RedBlackTree<E> tree; 

    public InOrder(NodeRB<E> n,RedBlackTree<E> tree){
        here = n;
        this.tree = tree;
    }
    
    public boolean hasNext(){
        //System.out.println("asked for hasNext");
        if(here.hasRightchild()){
            return true;
        }
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

    /*returns next Element and sets intern next Node*/
    public E next(){
        /*look for leftest rightson*/
        if(here.hasRightchild()){
            //System.out.println("\tcurrent has rightchild!");
            NodeRB<E> n = here.getRightchild();
            while(n.hasLeftchild()){
                n = n.getLeftchild();
            }
            here = n;
            return here.getData();
        }
        /*look for parent... where this child is a leftson*/
        else{
            NodeRB<E> n = here.getParent();
            NodeRB<E> m = here;
            NodeRB<E> tmp;
            try{
                while(n != null && ((n.hasLeftchild() && n.getLeftchild().compareTo(m) != 0) || !n.hasLeftchild()) ){
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
                //System.out.println("null pointer!\n Iterator ende!");
                throw new NoSuchElementException();
            }
        }
    }

    public void remove(){
        //System.out.println("removing element: "+here);
        NodeRB<E> n = here; /*remember actual node */
        if(this.hasNext()){
            next();/* next*/
        }
        else{
            here = null;
        }
        /*remove old node*/
        tree.remove(n);

    }

    public NodeRB<E> getHere(){
        if(here != null){
            return here;
        }
        return null;
    }

}

