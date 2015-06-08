package ente.exercise.run;

import ente.exercise.adt.*;
import ente.exercise.algebra.number.*;

public class TestBST{

   public static void main(String[] args){
        try{
            int n = args.length;
            BinarySearchTree<CompRational> bst = new BinarySearchTree<CompRational>();
            readList(args,bst);
        
            System.out.println("Final BinaryTree (sorted): "+bst);
        }catch(IndexOutOfBoundsException e){
            System.out.println("MISSING LISTS! You have to give me two lists of Rational numbers!");
        }   
   }

   public static void readList(String[] list, BinarySearchTree<CompRational> bst) throws IndexOutOfBoundsException{
      long num, denom;
      int i = 0;
      CompRational c;
      boolean swap= true; //true means insert and false means remove
      try{
         while(i < list.length){//geht mit string so nich
            if(i % 2 == 0){
               num = Long.parseLong(list[i]);
               denom = Long.parseLong(list[i+1]);
               //DEBUG System.out.println("number one: "+num+" and two: "+denom);
               if(swap && num == 0 && denom == 0){//tests if second List begins ... swap verifies that there are just 2 Lists
                  swap = false;
                  //DEBUG System.out.println("doppelnull");
               }
               else{
                    c = new CompRational(num,denom);
                    //DEBUG System.out.println("next Rational: "+c);
                    /* depending on switch inserts or removes element in tree*/
                    if(swap){
                        bst.insert(c);
                        //DEBUG System.out.println("insert");
                    }
                    else{
                        bst.remove(c);
                        //DEBUG System.out.println("remove");
                    }
               }            
            }
            i += 2;
            //System.out.println(bst);
         }
      }catch (NumberFormatException n){
         System.out.println("There should be only numbers used in the lists!");
      }
   }

}

