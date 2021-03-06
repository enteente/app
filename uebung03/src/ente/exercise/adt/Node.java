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
   
   /*setter*/ 
   void setLeftchild(Node<E> l){
       leftchild = l;
   }

   void setRightchild(Node<E> r){
       rightchild = r;
   }

   void setParent(Node<E> p){
      parent = p;     
   }

   void setData(E data){
      this.data = data;
   }

   /*getter*/
   Node<E> getLeftchild(){
      return leftchild;
   }
   
   Node<E> getRightchild(){
      return rightchild;
   }
   
   Node<E> getParent(){
      return parent;
   }
   
   E getData(){
       return data;
   }

   @Override
   public int compareTo(Node<E> n) throws NullPointerException{
       //DEBUG System.out.println("tries comparing");
      return this.getData().compareTo(n.getData());
   }

   boolean hasLeftchild(){
      if(leftchild == null){
         return false;
      }
      return true;

   }

   boolean hasRightchild(){
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
