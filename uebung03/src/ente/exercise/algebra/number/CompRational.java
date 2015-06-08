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
        if(this.equals(c)){
            return 0;
        }
        else if(this.sub(c).getNum() > 0){
            return 1;
        }
        else{
            return -1;
        }
    }
}
