/** class which implements complex numbers 
*	a + ib 
*
*	@author: EnteEnte
*	@last modification: 10/05/2015
*	methods:
*		*protected Complex clone()
*		*boolean equals(Complex z)
*		*boolean equals(Complex a, COmplex b)
*		*int hashcode()
*		*void setReal(double d)
*		*void setIm(double d)
*		*double getReal()
*		*double getIm()
*		*String toString()
*		*void add(Complex z)
*		*static Complex add(Complex a, Complex b)
*		*void mult(Complex z)
*		*static Complex add(Complex a, Complex b)
*		*void mult(double d)
*		*double abs()
*		*static double abs(Complex z)
*		*static Complex random()
*		*void conjugate()
*
*
*/

public class Complex{
	private double real;
	private double im;
	public final static Complex ZERO;
	public final static Complex ONE;
	public final static Complex I;
	public final static Complex RESULT;
	/* constructors *************************************************/
	public Complex(){
		real = 0;
		im = 0;
	}	
	
	public Complex(double real, double im){
		this.real = real;
		this.im = im;
	}

	public Complex(Complex z){
		this(z.getReal(),z.getIm());
	}	
	/* static constructors ******************************************/
	static{
		ZERO = new Complex(0,0);
		ONE = new Complex(1,0);
		I = new Complex(0,1);
		RESULT = new Complex(42,0);
	}	

	/* getter und setter ********************************************/
	public void setReal(double real){
		this.real = real;
	}
	public void setIm(double im){
		this.im = im;
	}
	public double getReal(){
		return real;
	}
	public double getIm(){
		return im;	
	}	

	/* vergleichsmethoden *******************************************/
	@Override protected Complex clone(){
		return new Complex(real,im);
	}//oder mit protected??

	/** Vergleicht zwei Complexe Zahlen miteinander und liefert true zurueck,
	 *	wenn Real und Imteil gleich sind
	 * 	Eigenschaften von equals: reflexiv, symm, trans und consistent sind sichergestellt
	 */
	@Override public boolean equals(Object z){
		if(!(z instanceof Complex))
			throw new IllegalArgumentException("Wie soll ich denn Enten mit Nudeln vergleichen?");
		Complex c = (Complex) z;
		if(this.getReal() != c.getReal() || this.getIm() != c.getIm())
			return false;
		return true;
	}
	public static boolean equals(Complex a, Complex b){
		return a.equals(b);
	}

	/*Hashcode einer komplexen Zahl
	 * kann nur eine injektive Abbildung sein, da mehr complexe Zahlen definierbar sind als es int gibt*/
	@Override public int hashCode(){
		return ((int)(7*real + 11*im)) % Integer.MAX_VALUE ;
	}
	

	/** Ueberschriebene toString Methode, die Complex con Objekt erbt
	 */
	@Override public String toString(){
		String s = "+";
		if(im < 0){
			s = "";
		}

		if((real%1) == 0 ){
			if((im%1) == 0)
				return "" + (int)real + s + (int)im + "i";
	       		return "" + (int)real + s + im + "i";
		}
		if((im%1) == 0)
			return "" + real + s + (int)im + "i";	
		return "" + real + s + im + "i";	
	}

	/* arithmetische Funktionen************************************/

	/** Addition
	 * 	(a+bi)+(c+di) = (a+c)+(b+d)i
	 */
	public void add(Complex z){
		Complex a = add(this,z);
		real = a.getReal();
		im = a.getIm();
	}
	public static Complex add(Complex a, Complex b){
		return new Complex(a.getReal()+b.getReal() , a.getIm()+b.getIm() );
	}

	/** Multiplikation
	 * 	(a+bi)*(c+di) = (ac-bd)+(ad+bc)i
	 */
	public void mult(Complex z){				/*Objektmethode fuer Multiplikation*/
		Complex a = mult(this,z);
		real = a.getReal();
		im = a.getIm();
	}
	public static Complex mult(Complex a, Complex b){	/*Klassenmethode fuer Multiplikation*/
		double real = a.getReal()*b.getReal()-a.getIm()*b.getIm();
		double im = a.getReal()*b.getIm()+a.getIm()*b.getReal();
		return new Complex(real,im);
	}
	public void mult(double d){				/*skalarmultiplikation*/
		real *= d;
		im *= d;
	}

	/** Betrag dieser Complexen Zahl berechnen
	 * 	abs(a+bi) = sqrt(a^2+b^2)
	 */
	public double abs(){
		return abs(this);	
	}
	public static double abs(Complex a){
		return Math.sqrt(a.getReal()*a.getReal()+a.getIm()*a.getIm());
	}

	/** Erzeugt eine zufällige Komplexe Zahl mit real, im zwischen 0 und 1
	 *  und gibt diese zurück
	 */
	public static Complex random(){
		return new Complex((int)(10*Math.random()),(int)(10*Math.random()));
	}

	/** die Komplexe Zahl komplex konjugieren
	 */
	public void conjugate(){
		im = -im;
	}

}
