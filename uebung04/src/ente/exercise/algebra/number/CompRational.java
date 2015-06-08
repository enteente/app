package ente.exercise.algebra.number;

import java.lang.*;
import ente.exercise.algebra.*;

/** comparable normrational
 */
public class CompRational extends NormRational implements Comparable<CompRational>{
    
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
        CompRational tis = new CompRational(this);
        if(tis.equals(c)){
            //System.out.println("\t\tthis.equals(c): "+this.equals(c));
            return 0;
        }
        else if(tis.sub(c).getNum() > 0){
            //System.out.println("\t\tthis.sub(c): "+this.sub(c)+" und getNum(): "+this.sub(c).getNum());
            return 1;
        }
        else{
            //System.out.println("oder this ist eben kleiner als c");
            return -1;
        }
    }

    @Override
    public CompRational abs(){
        return new CompRational(super.abs());
    }

    public long lower(){
        //System.out.println("\t\tlower(): num="+this.getNum()+" / denom="+this.getDenom()+" = ");
        return getNum()/getDenom();
    }
}
