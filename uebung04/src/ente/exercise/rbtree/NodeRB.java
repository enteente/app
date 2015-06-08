package ente.exercise.rbtree;

import java.util.*;

public class NodeRB<E extends Comparable<E>> implements Comparable<NodeRB<E>>{
    public static final boolean BLACK = false;
    public static final boolean RED = true;

    private boolean color; //black is false and red is true
    private NodeRB<E> leftchild;
    private NodeRB<E> rightchild;
    private NodeRB<E> parent;
    private E e;

    /** constructors
     */
    public NodeRB(boolean b, NodeRB<E> p, E e){
        this.e = e;
        parent = p;
        //System.out.println("parent of node "+e+" is: "+parent);
        color = b;
        if(e != null){
            getLeaves();
        }
    }

    public NodeRB(boolean b, E e){//for root
        this(b,null,e);
    }

    public NodeRB(NodeRB<E> p){//for leaves
        this(BLACK,p,null);
    }

    public NodeRB(){//empty root
        this(BLACK,null,null);
    }

    private void getLeaves(){
        this.leftchild = new NodeRB<E>(BLACK,this,null);
        this.rightchild = new NodeRB<E>(BLACK,this,null);
    }

    /**getcolor
     */
    public boolean getColor(){
        return color;
    }

    public E getData(){
        return e;
    }

    public NodeRB<E> getLeftchild(){
        return leftchild;
    }

    public NodeRB<E> getRightchild(){
        return rightchild;
    }

    public NodeRB<E> getParent(){
        return parent;
    }

    public NodeRB<E> getSibling(){
        if(this.whichChild()){
            return this.getParent().getRightchild();
        }
        return this.getParent().getLeftchild();
    }

    public NodeRB<E> getUncle(){
        return this.getParent().getSibling();
    }

    public NodeRB<E> getGranny(){
        return this.getParent().getParent();
    }

    public NodeRB<E> getNext(){
        NodeRB<E> R = this.getRightchild();
        while(R.hasLeftchild()){
            R = R.getLeftchild();
        }
        return R;
    }
    
    /**setter
     */
    public void setColor(boolean b){
        color = b;
    }

    public void setData(E e){
        this.e = e;
    }

    public void setLeftchild(NodeRB<E> n){
        leftchild = n;
    }

    public void setRightchild(NodeRB<E> n){
        rightchild = n;
    }

    public void setParent(NodeRB<E> n){
        parent = n;
    }

    public boolean setChild(NodeRB<E> n){
        if(this.isRoot()){
            return false;
        }    
        else if(this.whichChild()){
            this.getParent().setLeftchild(n);
        }
        else{
            this.getParent().setRightchild(n);
        }
        return true;
    }

    public boolean hasLeftchild(){
        if(leftchild == null || leftchild.getData() == null){
            return false;
        }
        return true;
    }

    public boolean hasRightchild(){
        if(rightchild == null || rightchild.getData() == null){
            return false;
        }
        return true;
    }

    public boolean isRoot(){
        if(parent == null){
            return true;
        }
        return false;
    }

    public boolean hasUncle(){
        if(this.hasGranny()){
            return true;
        }
        else{
            return false;
        }
        /*try{
            this.getUncle();
            return true;
        }catch(Exception e){
            return false;
        }*/
    }

    public boolean hasGranny(){
        try{
            this.getGranny();
            return true;
        }catch(Exception e){
            return false;
        }
    }

    public boolean whichChild(){
        if(parent != null && this.getParent().hasLeftchild() && this.getParent().getLeftchild().compareTo(this) == 0){
            return true;
        }
        else{
            return false;
        }
    }

    @Override
    public int compareTo(NodeRB<E> n){
        //System.out.println("\t\t"+this.getData()+" compared to "+n.getData());
        return this.getData().compareTo(n.getData());
    }

    public String toString(){
        String s = "";
        if(this.getData() == null){
            s += " null ";
        }else{
            s+= this.getData();
        }
        if(color){
            return s +"(red)";
        }
        return s +"(black)";
    }
}

