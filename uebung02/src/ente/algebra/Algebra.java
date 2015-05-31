package ente.algebra;

/** class Algebra has methods to do some Algebra stuff
 *      it's kind of a static class cause it only has static methods until now
 *      and no constructors
 *
 *      @author enteente
 *      @date 17/05/2015
 */
public class Algebra{

    /** GAUSS JORDAN
     *      implemented using Uebung 02 exercise
     *      solves the equation:
     *          A*x = b
     *      Gauss-Jordan can solve the equation above in at most n (dimension of field A) steps
     *      @param A is given 
     *      @param b is given
     *      @return Matrix x
     *      @throws IllegalArgumentException if the dimensions of the fields do not match to do gauss-jordan
     */
    public static Matrix gaussJordan(final Matrix AA, final Matrix bb){
		if(AA.getDim(0) != bb.getDim(0) || AA.getDim(0) != AA.getDim(1)){
		       throw new IllegalArgumentException("Die Parameter haben keine passenden Dimensionen");	
        }
        if(bb.getDim(1) != 1){
            throw new IllegalArgumentException("b ist kein einspaltiger Vektor! (dim[1] != 1)");
        }
		
        /*variablen*/
        int l = 0;                          //indice of max-entry in each step/column
		int n = AA.getDim(0);               //dimension for not using getDim() that often
        Matrix quotient = new Matrix(n,1);  //field for saving the quotient aik/akk in each step
        Matrix A = (Matrix) AA.clone();     //clone of A for not overwriting A
        Matrix b = (Matrix) bb.clone();     //clone of b for not overwriting b
        
        /*iteration for k = 0 to n-1*/
        for(int k = 0; k < n; k++){
           
            /* calculates max of Vektor A[k:n][k] */
			l = maxVectorEntry(A,k);

            /* tests if A is singular */
			if(A.getElement(l,k).equals(Rational.NULL)){
				 throw new IllegalArgumentException("A ist singulaer aka nicht invertierbar und das System Ax = b ist nicht loesbar");
            }
        
            /*swap row k and l of matrix A and vector b*/
            if(l != k){
                A.swapRow(l,k);
                b.swapRow(l,k);
            }

            /*calculate matrix A^k */
            for(int i = 0; i < n;i++){
                if(i != k){
                    quotient.setElement(i,0,A.getElement(i,k).div(A.getElement(k,k)));  
                }
                for(int j = 0; j < n;j++){
                    if(j < k){
                        //A(i,j) = A(i,j)
                    }
                    else if(i != k && j == k){
                        A.setElement(i,j,Rational.NULL);
                    }
                    else if(i == k && j >= k){
                        A.setElement(i,j,A.getElement(k,j));
                    }
                    else{
                        A.getElement(i,j).subEq(A.getElement(k,j).mult(quotient.getElement(i,0)));
                    }
                }
            }

            /*calculate b*/
            for(int i = 0; i < b.getDim(0); i++){
                if(i != k){
                    b.getElement(i,0).subEq(b.getElement(k,0).mult(quotient.getElement(i,0)));
                }
            }
        }

        /* calculate x*/
        Matrix x = new Matrix(A.getDim(0),1);
        for(int k = 0; k < x.getDim(0); k++){
            x.setElement(k,0,b.getElement(k,0).div(A.getElement(k,k)));
        }
        return x;

	}

	/** calculates index of max.entry of Vektor A[:,k]
     *      @param Matrix A the field to search
     *      @param int k is the column to search
     *      @return int 
     */
    private static int maxVectorEntry(final Matrix A,final int k){
        int l = k;
		Rational max = new Rational(A.getElement(0,k));
		for(int i = k; i < A.getDim(0); i++){
			if(A.getElement(i,k).compareTo(max) == 1){
				max = A.getElement(i,k);
				l = i;
			}
		}
        return l;
	}
	
}
