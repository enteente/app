/**
 * Testet die Methoden aus dem package algebra
 * @author Julian Stock
 * @date 12.05.2015
 */

import java.io.*;

import algebra.*;

public class AlgebraTest {

	public static void main(String[] args) throws Exception{
		
		BufferedReader bufR = null;
		int index = 0;
		
		try {
			FileReader filR = new FileReader(args[0]);
			bufR = new BufferedReader(filR);
		} catch (FileNotFoundException e) {
			System.out.println("Datei nicht gefunden: Try again");
		} catch (ArrayIndexOutOfBoundsException e) {
			System.out.println("Gib eine Datei zum Lesen: Try again");
		}
		
		String Zeile;
		String[] zahlenString = null;
		Zeile = bufR.readLine();
		zahlenString = Zeile.split("\\s+");
		
		int groesse = zahlenString.length;
		Matrix a = new Matrix(groesse, groesse);
		Matrix b = new Matrix(groesse, 1);
		
		for(int i = 0; i < groesse; i++){
			Rational eintrag = new Rational(Long.parseLong(zahlenString[i]));
			b.setZS(i,0, eintrag);
	    }
	        System.out.print("b: " + b);
		
	    while((Zeile = bufR.readLine()) != null && groesse > index ){
	    	if(groesse <= index++){
	    		throw new IndexOutOfBoundsException("The matrix is no" + groesse +" x "+ index + " Matrix!");
	        }
	        zahlenString = Zeile.split("\\s+");
	        if(zahlenString.length != groesse){
	        	throw new IllegalArgumentException("Array is not a square array!");
	        }
	        for(int i = 0; i < groesse; i++){
	        	Rational eintrag = new Rational(Long.parseLong(zahlenString[i]));
	        	a.setZS(index-1,i,eintrag);
	        }
	    }
	    if(index != groesse){
	    	throw new IllegalArgumentException("The matrix is not a square Matrix!");
	    }
	    System.out.println("a: " + a);
		bufR.close(); 
	        
		Matrix x = Algebra.gaussJordanVerfahren(a, b);
		System.out.print("b: " + b);
		System.out.println("a: " + a);
		System.out.println("x: " + x);
	}

}
