package ente.exercise.algebra.number;

import java.lang.*;
import ente.exercise.algebra.*;

/** comparable normrational
 */
public class CompRational extends NormRational implements Comparable<CompRational>{
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
    public int compareTo(CompRational c){
       return this.compare((Rational) c);
    }
}
