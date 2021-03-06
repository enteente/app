package ente.exercise.run;

import ente.exercise.rbtree.*;
import ente.exercise.algebra.number.*;

public class RationalRBT{

   public static void main(String[] args){
        try{
            int n = args.length;
            RedBlackTree<CompRational> rbt = new RedBlackTree<CompRational>();
            readList(args,rbt);
        
            System.out.println("Black-depth:"+rbt.getBlack());
            
            System.out.print("Final RedBlackTree (sorted): ");
            InOrder<CompRational> i = rbt.iterator();
            System.out.print(" "+i.getHere());
            while(i.hasNext()){
                i.next();
                System.out.print(" "+i.getHere());
            }
           
            System.out.println("\nFinal RedBlackTree reverse : ");
            RevInOrder<CompRational> j = rbt.revIterator();
            System.out.print(" "+j.getHere());
            while(j.hasNext()){
                j.next();
                System.out.print(" "+j.getHere());
            }
            //System.out.println("calling spares with 1");
            spare(1,rbt.iterator());

            System.out.println("\nFinal RedBlackTree (sorted): ");
            InOrder<CompRational> k = rbt.iterator();
            if(k != null){
                System.out.print(" "+k.getHere());
                while(k.hasNext()){
                    k.next();
                    System.out.print(" "+k.getHere());
                }
            }
            System.out.println();

        }catch(IndexOutOfBoundsException e){
            System.out.println("MISSING LISTS! You have to give me a list of Rational numbers!");
        }finally{
            System.out.println();
        }  
   }

   public static void readList(String[] list, RedBlackTree<CompRational> rbt) throws IndexOutOfBoundsException{
      long num, denom;
      int i = 0;
      CompRational c;
      try{
         while(i < list.length){//geht mit string so nich
            if(i % 2 == 0){
               num = Long.parseLong(list[i]);
               denom = Long.parseLong(list[i+1]);
               //DEBUG System.out.println("number one: "+num+" and two: "+denom);
                c = new CompRational(num,denom);
                //DEBUG System.out.println("next Rational: "+c);
                /* depending on switch inserts or removes element in tree*/
                rbt.add(c);
            }
            i += 2;
         }
      }catch (NumberFormatException n){
         System.out.println("There should be only numbers used in the lists!");
      }
   }

    public static void spare(int n,InOrder<CompRational> i){
        //iterate and remove all elements with abs() < n 
        while(i.getHere() != null && i.getHere().getData().abs().lower() < n){
            i.remove();
        }
    }
}

