package ente.exercise.run;

import ente.exercise.algebra.*;
import ente.exercise.algebra.number.*;
import java.util.*;
import ente.exercise.adt.*;

/** Reverse Polish Notation
 *
 *  @author enteente
 *  @date 20/05/2015
 */
public class RPN{
    public static void main(String[] args){
        /*inits*/
        String line = "";
        String[] rpnTerm;
        Stack rpnStack = new Stack();
        int i = 0;
        NormRational rat1, rat2;

        /*output... how to work with this app*/
        String HELP = "Options:\n > exit        : quits RPN.java \n"
                            +   " > help        : shows this manual \n"
                            +   " > \"rpnTerm\" : if rpnTerm has correct syntax tries to calculate solution \n";
        System.out.println(HELP);
        
        do/*input is not finished by exit)*/{
            System.out.println("Please insert term in RPN notation:\n");
            /*save input in String*/
            line = StdIn.readLine();
            i = 0;
            /*reading and verifying term ...is RPN?*/
            System.out.println("reading and handling...");
            if(line.equals("exit")){
               System.out.println("closing RPN.java..."); 
            } 
            else if(line.equals("help")){
                System.out.println(HELP);
            }
            else{
                System.out.println("trying to interpret as RPN-term...");
                rpnTerm = line.split("\\s+");
                if(rpnTerm[i].equals("")){
                    i++;
                }
                //n = rpnTerm.length;
                //needs try and catch for indexarrayoutofboundsex
                while(rpnTerm[i] != "\n"){
                    if(isOperand(rpnTerm[i])){
                        //DEBUG
                        System.out.println("rpnTerm["+i+"] is: "+rpnTerm[i]);
                        rpnStack.push(new NormRational(Long.parseLong(rpnTerm[i++]),1));
                    }
                    else if(isOperator(rpnTerm[i])){
                       //DEBUG
                        System.out.println("rpnTerm["+i+"] is: "+rpnTerm[i]);
                        char c = rpnTerm[i++].charAt(0);
                        rat2 = (NormRational) rpnStack.pop();//second operand
                        rat1 = (NormRational) rpnStack.pop();//first operand
                        switch(c){
                        case '+': rat1.addEq(rat2);break;
                        case '-': rat1.subEq(rat2);break;
                        case '*': rat1.multEq(rat2);break;
                        case '/': rat1.divEq(rat2);break;
                        default: System.out.println("The input-Term isn't in valid RPN-notation");i = i+ rpnTerm.length;break;
                        }
                        rpnStack.push(rat1);
                    }
                    else{
                       System.out.println("unknown character for RPN");
                       i = i + rpnTerm.length;
                    } 
                    System.out.println("round end:" +rpnStack);
                }
                if(rpnTerm[i] != "\n"){
                    System.out.println("There has been an error in your RPN-term! Include given help and try again!");
                }
                else if(rpnStack.search((rpnStack.peek())) > 1){
                    System.out.println("Your RPN-term was incorrect. There are unhandled numbers left beneath the result!");
                }
                else{
                    System.out.println("The result of your input is: "+rpnStack.pop());
                }
            }
            System.out.println("\nCalculate another RPN-term or use any other command unterstuetzt");

        }while(!(line.equals("exit")));
    }

    public static boolean isOperand(String s){
        int n = s.length();
        boolean b = true;
        for(int i = 0; i < n; i++){
            if(s.charAt(i) < 48 || s.charAt(i) > 57)
                return false;
        }
        return true;
    }

    public static boolean isOperator(String s){
        if (s.length() != 1){
            return false;
        }
        if(s.equals("+") || s.equals("-") || s.equals("*") || s.equals("/")){
            return true;
        }else{
        return false;
        }
    }
}

