package ente.exercise.algebra;

/** class represents Rational numbers and has methods to calculate and 
 *      work with those
 *
 * @author enteente
 * @date 17/05/2015
 */

public class Rational{
	private long num;   //numerator
	private long denom; //denominator
    public static final Rational NULL = new Rational(0,1);

    /** constructor
     *      initialize objects of type Rational giving
     *      numerator and denominator or another Rational number
     *      default Rational is number "1/1"
     *      @throws ArithmeticException ("\ by Zero") if trying to 
     *      make a new Object with denomenator zero.
     */
    public Rational(long num, long denom) throws ArithmeticException{
        if(denom == 0) {
            throw new ArithmeticException("/ by Zero");
        }
        this.num = num;
        this.denom = denom;
        this.form();
        this.fraction();
    }
    
    public Rational(Rational r) {
		this(r.num,r.denom);
	}

    public Rational(){
        this(1,1);
    }

    /** setter 
     *      to set explicitly the numerator and denomenator
     *      denomenator is not set to zero, because its not defined
     *      gives an output advising that denom wasnt set!
     *      @param naturalnumber 
     *      @return void
     */
    public void setNum(long n){
        num = n;
        this.fraction();
    }

    public void setDenom(long d){
        if(d != 0){
            denom = d;
            this.form();
            this.fraction();
        }else{
            System.out.println("denominator cannot be zero! denom was not set!");
        }
    }

    /** getter
     *      to get explicitly the numerator and denominator
     *      @param non
     *      @return natural number numerator or denominator
     */
    public long getNum(){
        return num;
    }

    public long getDenom(){
        return denom;
    }

	/** equals overrides method equals in class Object
	 * 	    Two Rational numbers are equal if they're difference is zero.
     * 	    @param Object o which should be instance of Rational
     * 	    @return boolean: true if Object equals the given parameter
     * 	    @throws IllegalArgumentException if Parameter o is not a Rational number
	 */
	@Override 
	public boolean equals(Object o){
		if(!(o instanceof Rational))
            throw new IllegalArgumentException("data is of wrong type in method \"equals\"");
		Rational r = (Rational) o;
		/*through difference of numerator*/
		if(this.sub(r).num == 0)
			return true;
		return false;
	}
	
    /** toString overrides method toString in class Object
     *      @param non
     *      @return a String presenting the Rational number
     */ 
	@Override 
	public String toString(){
        this.form();
        this.fraction();
        Rational r = new Rational(this); //fraction Rational number
        /* Rational number is of type long if denomenator is 1 after fraction */
        if(r.denom == 1){                           
            return " "+(r.num)+" ";
        }
        return ""+ r.num + "/" + r.denom;	
	} 

    /** compare to Rational Numbers 
     *      could implement Comparable<T> but that way would be generic :(
     *      Compares to Rational numbers, because Rational numbers have an order
     *      @param Object o of instance Rational
     *      @return -1 if this number is lower than param
     *               0 if this number is equal to param
     *               1 if this number is higher than param
     *      @throws IllegalArgumentException if param o is not instance of Rational 
     */
    public int compare(Object o){
        if(!(o instanceof Rational))
            throw new IllegalArgumentException("data is of wrong type in method \"compareTo\"");
        /*through difference of numerator*/
        Rational r = (Rational) o;
        if(this.equals(o)){
            return 0;
        }else 
        if(this.sub(r).num > 0){
            return 1;
        }else
            return -1;
    }

	/** arithmetical operations
     *      to be able to calculate with Rational numbers
     *      represented by an Object of type Rational
     *      @param Rational(2), static methods have two parameters of type Rational
     *              non-static methods habe one parameter of type Rational
     *      @return Rational representing result of calculation
     */

    /** addition
     *      adds two Rational numbers 
     *      @first non-static, returns new Rational
     *      @second static, returns new Rational
     *      @third non-static, works in place and returns changed Rational
     * */
	public Rational add(final Rational r){
		Rational a = new Rational(this).addEq(r);
        a.form();
        return a;
	}

	public static Rational add(final Rational r1, final Rational r2) {
		Rational a = new Rational(r1).addEq(r2);
        a.form();
        return a;
	}

	public Rational addEq(Rational r) {
		num = num * r.denom + r.num * denom;
		denom = denom * r.denom;
        this.form();
        this.fraction();
		return this;
	}
     /** subtraktion
     *      sub two Rational numbers 
     *      @first non-static, returns new Rational
     *      @second static, returns new Rational
     *      @third non-static, works in place and returns changed Rational
     * */
   
    public Rational sub(final Rational r){
        Rational a = new Rational(this).subEq(r);
        a.form();
        return a;
    }

    public static Rational sub(final Rational r1, final Rational r2){
        Rational a = new Rational(r1).subEq(r2);
        a.form();
        return a;
    }   

    public Rational subEq(Rational r){
        //System.out.print(this +" - "+r);
        num = num * r.denom - r.num * denom;
        denom = denom * r.denom;
        this.form();
        this.fraction();
        //System.out.println(" = "+this);
        return this;
    }
    /** multiplication
     *      multiplicates two Rational numbers 
     *      @first non-static, returns new Rational
     *      @second static, returns new Rational
     *      @third non-static, works in place and returns changed Rational
     * */

	public Rational mult(final Rational r){
	    Rational a = new Rational(this).multEq(r);
        a.form();
        return a;
	}

	public static Rational mult(final Rational r1, final Rational r2){
		Rational a= new Rational(r1).multEq(r2);
        a.form();
        return a;
	}

	public Rational multEq(Rational r){
		num *= r.num;
		denom *= r.denom;
        this.form();
        this.fraction();
		return this;
	}
    /** division
     *      divides two Rational numbers 
     *      @first non-static, returns new Rational
     *      @second static, returns new Rational
     *      @third non-static, works in place and returns changed Rational
     * */
	public Rational div(final Rational r){
	    Rational a = new Rational(this).divEq(r);
        a.form();
        return a;
	}

	public static Rational div(final Rational r1, final Rational r2){
		Rational a = new Rational(r1.divEq(r2));
        a.form();
        return a;
	}

	public Rational divEq(Rational r){
		if(r.num == 0){
            throw new ArithmeticException("ERROR: / by ZERO");
        }
        num *= r.denom;
		denom *= r.num;
		this.form();
        this.fraction();
        return this;
	}


    /** absolut
     *      calculates the absolut value of the Rational number object
     *      @param non
     *      @return the Rational number with positiv num and denom
     *              does not override Rational object
     */
	public Rational abs(){
		return new Rational(Math.abs(this.num), Math.abs(this.denom));
	}

    /** fraction
     *      calculates the fraction of a Rational number
     *      using modernEuklid to calc the ggt(num, denom)
     *      and divides by that
     *      @param non
     *      @return void does override Object
     */
    public void fraction(){
        long tmp, tmpnum, tmpdenom;
        tmpnum = num;
        tmpdenom = denom;
        do{
            tmp = tmpnum % tmpdenom;
            tmpnum = tmpdenom;
            tmpdenom = tmp;
        }while(tmpdenom != 0);
        num /= tmpnum;
        denom /= tmpnum;
    }


    /** form
     *      does not effect the Rational number, it takes care of the coeffizient
     *      so its always present in the numerator!
     *      @param non
     *      @return void, changes object if necessary
     */
    public void form(){
        if(denom >= 0){
            return;
        }
        denom *= (-1);
        num *= (-1);
    }
}
