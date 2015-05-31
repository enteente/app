package ente.exercise.algebra.number;

import java.lang.*;
import ente.exercise.algebra.*;

/** comparable normrational
 */
public class CompRational extends NormRational implements Comparable{
    private long num;
    private long denom;

    /** constructors
     *
     */
    public CompRational(long num, long denom){
        super(num,denom);
    }

    public CompRational(Rational r){
        super(r.getNum(),r.getDenom());
    }

    public CompRational(){
        super();
    }

    /** compareTo
     */
    @Override
    public int compareTo(Object o){
        if(!(o instanceof CompRational)){
            throw new IllegalArgumentException("cannot compare Objects because there are not of same type");
        }
        CompRational c = (CompRational) o;
            return this.compare(c);
    }

}
