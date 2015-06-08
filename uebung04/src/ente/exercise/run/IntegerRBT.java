package ente.exercise.run;

import ente.exercise.algebra.number.*;
import ente.exercise.rbtree.*;

public class IntegerRBT{

   public static void main(String[] args){
        try{
            int n = args.length;
            RedBlackTree<Integer> rbt = new RedBlackTree<Integer>();
            readList(args,rbt);
        
            System.out.println("Black-depth:"+rbt.getBlack());
            System.out.println("Final RedBlackTree (sorted): ");
            InOrder<Integer> i = rbt.iterator();
            
            System.out.print(" "+i.getHere());
            while(i.hasNext()){
                i.next();
                System.out.print(" "+i.getHere());
            }
            
            System.out.println("\nFinal RedBlackTree reverse : ");
            RevInOrder<Integer> j = rbt.revIterator();
            System.out.print(" "+j.getHere());
            while(j.hasNext()){
                j.next();
                System.out.print(" "+j.getHere());
            }

            //System.out.println("calling spares with 1");
            spare(0,rbt.iterator());

            System.out.println("\nFinal RedBlackTree (sorted): ");
            InOrder<Integer> k = rbt.iterator();
            if(k != null){
                System.out.print(" "+k.getHere());
                while(k.hasNext()){
                    k.next();
                    System.out.print(" "+k.getHere());
                }
            }
            System.out.println();
	
   //         System.out.println("\n"+rbt);
        }catch(IndexOutOfBoundsException e){
            System.out.println("MISSING LISTS! You have to give me two lists of Rational numbers!");
        }finally{
            System.out.println();
        }
   }

   public static void readList(String[] list, RedBlackTree<Integer> rbt) throws IndexOutOfBoundsException{
      int number;
      try{
            for(int i = 0; i < list.length; i++){
                number = Integer.parseInt(list[i]);
                /* depending on switch inserts or removes element in tree*/
                rbt.add(number);
                //System.out.println(bst);
            }
      }catch (NumberFormatException n){
         System.out.println("There should be only numbers used in the lists!");
      }
   }

    public static void spare(int n,InOrder<Integer> i){
        //iterate and remove all elements smaller than n
        while(i.getHere() != null && i.getHere().getData() < n){
            i.remove();
        }
    }
}

