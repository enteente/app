package ente.exercise.run;

import ente.exercise.adt.*;
import ente.exercise.algebra.number.*;

public class TestBST{

   public static void main(String[] args){
      int n = args.length;
      if( n == 0){
         throw new IndexOutOfBoundsException("you have to give me two lists of Rational numbers!");
      }
      BinarySearchTree<CompRational> bst = new BinarySearchTree<CompRational>();
      readList(args,bst);
   
      System.out.println(bst);   
   }

   public static void readList(String[] list, BinarySearchTree<CompRational> bst){
      long num, denom;
      int i = 0;
      CompRational c;
      boolean swap= true; //true means insert and false means remove
     // try{
         while(list[i] != null){//geht mit string so nich
            if(i % 2 == 0){
               num = Long.parseLong(list[i]);
               denom = Long.parseLong(list[i+1]);
               if(swap && num == 0 && denom == 0){//tests if second List begins ... swap verifies that there are just 2 Lists
                  swap = false;
               }
               c = new CompRational(num,denom);
               /* depending on switch inserts or removes element in tree*/
               if(swap){
                  bst.insert(c);
               }
               else{
                  bst.remove(c);
               }            
            }
            i += 2;
         }
   /*   }catch (IndexOutOfBoundsException a){
         System.out.println("you have to give me two lists of Rational numbers!");
      }catch (){

      }finally{
         System.out.println("helper!");
      }
      */
   }

}

