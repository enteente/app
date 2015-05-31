package ente.algebra;

/** Klasse zum Speichern und Arbeiten mit Matrizen und Vektoren
 *      die Rationale Zahlen als Elemente enthalten
 *
 *      @author enteente
 *      @date 17/05/2015
 */ 
public class Matrix{

    private Rational[][] field;             //field with Rational elements
	private final int[] dim = new int[2];   //dimensions of field

    /** constructor  
     *      initialize Objects of type Matrix (also Vectors) with given
     *      dimensions
     *      default dimension is (1,1)
     *      @param n
     *      @param m
     *      @throws IllegalArgumentException if n or m are not Integer 
     */
	public Matrix(int n,int m){
        if(n%1!= 0 || m%1!=0 ){
            throw new IllegalArgumentException("Dimension must be of type Integer!");
        }
		field = new Rational[n][m];
		dim[0] = n;
        dim[1] = m;
	}

    public Matrix(int[] dim){
        this(dim[0],dim[1]);
    }

	public Matrix(){
		this(1,1);
	}

	/** getter dimension
     *      returns dimension of field
     *      @param i specifies if rows(0) or columns(1) is required
     *      @return int
	 */
	public int getDim(int i){
		return dim[i];
	}
	
    /** setter 
     *      set the Rational Object ate field with (i,j)
     *      @param i is the row number
     *      @param j is the column number
     *      @param r,n is either a Rational object or a long-number
     *      @return void
     */
	public void setElement(int i, int j ,Rational r){
		field[i][j] = r;
	}

    public void setElement(int i,int j, long n){
        field[i][j] = new Rational(n,1);
    }

    /** getter element
     *      returns the Rational object at (i,j) of the field
     *      @param i is the row number
     *      @param j is the column number
     *      @return Rational 
     */
	public Rational getElement(int i, int j){
		return field[i][j];
	}

	/** equals overrides method equals in class Object
     *      Two fields are equal if each of there entries are parwise equal
     *      @param Object o
     *      @return boolean: true if fields are equal else false
     *      @throws IllegalArgumentException
	 */
	@Override
	public boolean equals(Object o){
		if(!(o instanceof Matrix))
            throw new IllegalArgumentException("parameter is of wrong type!");
        Matrix A = (Matrix) o;
        if(dim[0] != A.dim[0] || dim[1] != A.dim[1]){
            System.out.println("Different dimensions!");
            return false;
        }
		for(int i = 0; i < dim[0]; i++){
			for(int j = 0; i < dim[1]; i++){
				if(!(field[i][j].equals(A.field[i][j]))){
                    return false;
                }
			}
		}
		return true;	
	}

    /** hashcode overrides method hashcode of class Object
     *      returns a integer-value which represents the whole field
     *      @param non
     *      @return int
     */
	@Override
	public int hashCode(){
        int hash = 0;
        for(int i = 0; i < dim[0]; i++){
            for(int j = 0; j < dim[1]; j++){
                hash += (i+j+1)*(field[i][j].getNum()/field[i][j].getDenom());
            }
        }
		return hash;
	}

    /** clone overrides method clone of class Object
     *      returns a clone of the given field
     *      @param non
     *      @return Object
     */
	@Override
	protected Object clone(){
		Matrix A = new Matrix(dim);
		for(int i = 0; i < dim[0]; i++){
			for(int j = 0; j < dim[1]; j++){
                A.setElement(i,j,new Rational(field[i][j]));
			}
		}
		return A;
	}

    /** toString overrides method toString of class Object
     *      returns a String representing the given field
     *      @param non
     *      @return String 
     */
	@Override
	public String toString(){
		String s= "\n";
		for(int i = 0; i < dim[0]; i++){
			s += " | ";
			for(int j = 0; j < dim[1]; j++){
				s += field[i][j]+" ";
			}
			s += "|\n";
		}
        return s;
	}

	/** matrix specific methods
     */

    /** swapping
     *      swaps two rows of a matrix by swaping the references
     *      @param i is first row
     *      @param j is second row
     *      @return void because the method is working in place
     *      @throws ArrayIndexOutOfBoundsException if i or j are not rows of the field
	 */
	public void swapRow(int i, int j){//i and j are row index
        if(i<0 || j < 0 || i >= dim[0] || j >= dim[0]){
            throw new ArrayIndexOutOfBoundsException("Index out of bounds: "+i+"  "+j+"  "+dim[0]);
        }
		Rational[] line = field[i];
		field[i] = field[j];
		field[j] = line;
	}

	/** field multiplication
	 * 	    A * B static and non-static
	 * 	    @param Matrix A or in case of static its two Matrix objects
     * 	    @return Matrix C = A * B
     * 	    @throws IllegalArgumentException if the dimensions of A and B do not match to multiplicate
	 */
	public Matrix multMatrix(final Matrix A){
		if(dim[1] != A.dim[0])//spalten links != zeilen rechts ?
			throw new IllegalArgumentException("Die Matrixmultiplikation ist wegen falscher Dimensionen nicht möglich!");
		Matrix C = new Matrix(dim[0],A.dim[1]); //C mit dim: zeilenlinks und spalten rechts
		for(int i = 0; i < C.dim[0]; i++){ //i durchlaeuft zeilen
			for(int j = 0; j < C.dim[1]; j++){ //j durchlaeuft spalten
                //DEBUG System.out.println("j: "+j);
				C.setElement(i,j,0); //zeilespalte auf 0 setzen
				for(int k = 0; k < dim[1]; k++){//k durchlaeuft spalten von links
                    //DEBUG System.out.println("k: "+k);
					C.field[i][j].addEq(field[i][k].mult(A.field[k][j])); //
				}
			}
		}
		return C;
	}

	public static Matrix multMatrix(final Matrix A, final Matrix B){
		return A.multMatrix(B);
	}


} 
