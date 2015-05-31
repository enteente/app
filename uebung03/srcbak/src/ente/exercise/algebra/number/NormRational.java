package ente.exercise.algebra.number;

/** class represents norminated Rational numbers and has methods to calculate and work with those
 *
 * @author enteente
 * @date 20/05/2015
 */

import ente.exercise.algebra.*;

public class NormRational extends Rational{
    private long num;//numerator
    private long denom;//denominator
    public static final NormRational NULL = new NormRational(0,1);

    /** constructor 
     *      initialize objects of type NormRational giving
     *      numerator and denominator or another Rational number
     *      default NormRational is number "1/1"
     *      @throws ArithmeticException ("\ by ZERO") if trying to make a new Objectwith denominator zero.
     */
    public NormRational(long num, long denom){
        super(num, denom);
    }

    public NormRational(Rational r){
        super(r.getNum(),r.getDenom());
    }

    public NormRational(){
        super();
    }


}
