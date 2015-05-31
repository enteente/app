import java.io.*;
import src.java.ente.algebra.*;
import static java.lang.System.out;

/** test class to test the gauss-jordan algorithm which
 *      uses the classes Algebra, Rational and Matrix of 
 *      package src.java.ente.algebra
 *      It uses a BufferedReader to read data from a file
 *      first row of data in file represents the vector b transposed
 *      following rows represent the Matrix A
 *
 *      @author enteente
 *      @date 17/05/2015
 */
public class TestAlgebra{
	public static void main(String[] args) throws IOException {
        
        /*inits*/            
        BufferedReader br = null;
        String line = "";
        int index = 0;

        /*reading from file*/
        try{
            out.println("reading file ...");
		    FileReader fr = new FileReader(args[0]);
		    br = new BufferedReader(fr);

            /** Dimension von A und b auslesen
            */
		    String bvector = br.readLine();
            String[] vector = bvector.split("\\s+");
            int n = vector.length;//dimension of Matrix and Vector 
            out.println("Vektorlaenge von b ist: "+vector.length);
            out.println("n wird gesetzt: n = "+n);

            /* read input file and save in Matrix-objects
            *      initializes Matrix b and Matrix A to solve Ax=b
            *      b = vector(n,1)
            *      A = Matrix(n,n) - otherwise cannot be solved with gauss jordan
            *      x =solution vector(n,1)
            */
            out.println("fills fields A(nxn) and b(nx1) with data ...");
            Matrix b = new Matrix(n,1);
            Matrix A = new Matrix(n,n);
        
            /** save values
             *   of bvector in b(Matrix-object)*/
            for(int j = 0; j < n; j++){
                b.setElement(j,0,Long.parseLong(vector[j]));
            }
            /* output of b */
            out.print(b);

            /** saves values
             *   of each left line in file in field A(Matrix-Object
             */
            while((line = br.readLine()) != null && n > index ){
                if(n <= index++){
                    throw new IndexOutOfBoundsException("The matrix is not a square Matrix!"+n+"   "+index);
                }
                vector = line.split("\\s+");

                if(vector.length != n && !(vector[0].equals("")) ){
                    throw new IllegalArgumentException("Array is not a square array!");
                }
                for(int i = 0; i < n;i++){
                    A.setElement(index-1,i,Long.parseLong(vector[i]));
                }
		    }
            if(index != n){
                throw new IllegalArgumentException("The matrix is not a square Matrix!");
            }
            /* output A */
            out.println(A);
            
            /** use gauss jordan of class Algebra to solve the Equation Ax = b 
            */
            out.println("\n-----------------------------------------\n"
                       +"berechnet A*x = b ...");
            Matrix x = Algebra.gaussJordan(A,b);
            out.println("x: "+x);
        
            out.println("\n Probe:");
            Matrix B = A.multMatrix(x);
            out.println(" Ist A*x == b ? "
                        +B.equals(b));

            out.println("A*x ist: "+B);
            out.println("aber b ist: "+b);

        /* if there was something wrong: report and throw exception */    
        }catch (FileNotFoundException f){
            out.println("Given file not found: Please try again!\nDo: java TestAlgebra <filename>");
        }catch (ArrayIndexOutOfBoundsException a){
            out.println("Please give a file to read!\nDo: java TestAlgebra <filename>");
        }catch (NumberFormatException e){
            out.println("Number format was wrong! Please verify that there are only numbers of type Long in the file\n"
                    +"and each line starts with a number!");
        }catch (IllegalArgumentException i){
            out.println("The field A is not a square field!");
        }catch (Exception e){
            out.println("There was something wrong! Please try again!");
        }finally{
            br.close();
        }
    }
}
