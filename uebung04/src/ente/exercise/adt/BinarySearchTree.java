package ente.exercise.adt;

import java.util.*;
import java.lang.*;
import ente.exercise.algebra.number.*;
import ente.exercise.algebra.*;

public class BinarySearchTree<E extends Comparable<E>> implements BinaryTreeOp<E>{
   /*new search tree by default constructor with root null*/
   Node<E> root = null;  
   
   /*using default constructor*/

   /*method insert an element in tree*/
    public void insert(Node<E> n){
    /*if tree is empty insert element as new root*/
        try{
            if(root == null){
                root = n;
                return;
            }
            /*find leave of tree to insert element*/
            rekInsert(root,n);
        }catch(NullPointerException e){
            System.out.println("irgendiwe nullpointer");
        }
    }

    public void insert(E element){
        /*if tree is empty insert element as new root*/
        try{
            if(root == null){
                //DEBUG System.out.println("bst is empty"); 
                root = new Node<E>(element);
                return;
            }
            /*find leave of tree to insert element*/
            //DEBUG System.out.println("recursive method");
            rekInsert(root,new Node<E>(element));
        }catch(NullPointerException e){
            System.out.println("irgendwie nullpointer");
        }catch(IllegalArgumentException f){
            System.out.println("element already exists in BinaryTree");
        }
    }

   /*rekursives insert private part*/
   private void rekInsert(Node<E> n, Node<E> m)throws IllegalArgumentException{
      /*compare element and n.data*/
      int c = m.compareTo(n);
      //DEBUG System.out.println("comparing actual node and CR given");
      /*cases*/
      if(c == 0){
          System.out.println("Inserting "+m+" failed:");
         //throw new EntityExistsException("element already exists in BinaryTree");
         throw new IllegalArgumentException("element already exists in BinaryTree");
      }
      else if(c < 0 ){/*weiter in linken pfasd*/
         if(n.hasLeftchild()){
            rekInsert(n.getLeftchild(),m);
         }
         else{
            n.setLeftchild(m);
         }         
      }
      else if(c > 0){/*element is bigger than n*/
         if(n.hasRightchild()){/*go deeper in right path*/
            rekInsert(n.getRightchild(),m);
         }
         else{
            n.setRightchild(m);
         }
      }
   }

   public void remove(E element){
      if(root == null){
         throw new IllegalArgumentException("was not able to remove element vom binarysearchtree because tree is empty");
      }
      Node<E> m = rekRemove(root, element);

      /*compare childs*/
      if(!m.hasLeftchild() && !m.hasRightchild()){          
         /*node doesnt have any child, just remove node and set child from father*/
         if(m.getParent().getLeftchild().getData().compareTo(element) == 0){
            m.getParent().setLeftchild(null);
         }
         else{
            m.getParent().setRightchild(null);
         }
      }
      else if(m.hasLeftchild() && m.hasRightchild()){
         /*node has two childs,compare .... compilacates*/
         /*right child is bigger*/
         Node<E> tmp = m.getLeftchild();
         moveNode(m);
         insert(tmp);

      }
      else if(m.hasLeftchild()){
         /*leftchild is new node ... */
         m.setData(m.getLeftchild().getData());
         m.setLeftchild(m.getLeftchild().getLeftchild());
         m.setRightchild(m.getLeftchild().getRightchild());        
      }
      else{
         /*rightchild is new node ...*/
         moveNode(m);
      }
   }

   /*rekursive remove private part*/
   private Node<E> rekRemove(Node<E> n, E element){
      int c = element.compareTo(n.getData());
      Node<E> m;

      if(c == 0){//found element...to be removed
         return n;
      }
      else if (c < 0 && n.hasLeftchild()){//is in left childtree
        m = rekRemove(n.getLeftchild(),element);
      }
      else if (c > 0 && n.hasRightchild()){//is in right childtree
        m = rekRemove(n.getLeftchild(),element);
      }
      else{//if node isnt an entity of the tree at all 
         throw new IndexOutOfBoundsException("was not able to remove element from BST because there was no aquivalent element");
      }
      return m;
   }

   /** moveNode() overrides node  with rightchild 
    *    @param non
    *    @return void
    */
   private void moveNode(Node<E> n){/*always rightnode caused by structure of binarytree*/
      n.setData(n.getRightchild().getData());
      n.setLeftchild(n.getRightchild().getLeftchild());
      n.setRightchild(n.getRightchild().getRightchild());
   }
   
   public String toString(){
      String s = rekString(root);
     return s; 
   }

   private String rekString(Node<E> n){
      String s = "";
      if(n == null){//if n does not exist return empty string
         return "";
      }
      return ""+ rekString(n.getLeftchild()) +" "+ n.getData() +" "+ rekString(n.getRightchild());
   }

}
