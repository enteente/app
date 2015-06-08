package ente.exercise.adt;

import java.util.*;

public class Node<E extends Comparable<E>> implements Comparable<Node<E>>{
   private Node<E> leftchild;
   private Node<E> rightchild;
   private E data;
   private Node<E> parent;

   /*constructor*/
   public Node(Node<E> p, E data){
       parent = p;
       this.data = data;        
   } 

   /*root constructor*/
   public Node(E data){
       parent = null;
       this.data = data;
   }
    
   public Node(){
        this(null);
   }

   /*setter*/ 
   public void setLeftchild(Node<E> l){
       leftchild = l;
   }

   public void setRightchild(Node<E> r){
       rightchild = r;
   }

   public void setParent(Node<E> p){
      parent = p;     
   }

   public void setData(E data){
      this.data = data;
   }

   /*getter*/
   public Node<E> getLeftchild(){
      return leftchild;
   }
   
   public Node<E> getRightchild(){
      return rightchild;
   }
   
   public Node<E> getParent(){
      return parent;
   }
   
   public E getData(){
       return data;
   }

   @Override
   public int compareTo(Node<E> n) throws NullPointerException{
       //DEBUG System.out.println("tries comparing");
      return this.getData().compareTo(n.getData());
   }

   public boolean hasLeftchild(){
      if(leftchild == null){
         return false;
      }
      return true;

   }

   public boolean hasRightchild(){
      if(rightchild == null){
         return false;
      }
      return true;
   }

    @Override
    public String toString(){
        return this.getData().toString();
    }
}
