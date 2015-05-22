/**
 * Klasse zur Verarbeitung von rationalen Zahlen
 * @author Julian Stock
 * @date 12.05.2015
 */

package algebra;

public class Rational {
	
	private long zaehler;
	private long nenner;
	
	/**
	 * spezielle Zahlen
	 */
	public static Rational EINS = new Rational(1, 1);
	public static Rational NULL = new Rational();
	
	/**
	 * default-Konstruktor
	 */
	public Rational(){
		zaehler = 0;
		nenner = 1;
	}
	
	/**
	 * specific-Konstruktor1
	 */
	public Rational(long zaehler, long nenner) throws IllegalArgumentException {
		if(nenner == 0){
			throw new IllegalArgumentException("Nenner muss ungleich 0 sein");
		}
		this.zaehler = zaehler;
		this.nenner = nenner;
		this.kuerzen();
	}
	
	/**
	 * specific-Konstruktor2
	 */
	public Rational(long zaehler) throws IllegalArgumentException {
		this.zaehler = zaehler;
		nenner = 1;
	}
	
	/**
	 * setzt den Zaehler
	 * @param zaehler
	 */
	public void setZaehler(long zaehler){
		this.zaehler = zaehler;
		this.kuerzen();
	}
	/**
	 * liefert den Zaehler
	 * @return
	 */
	public long getZaehler(){
		return zaehler;
	}
	/**
	 * setzt den Nenner
	 * @param nenner
	 */
	public void setNenner(long nenner){
		if(nenner != 0){
			this.nenner = nenner;
		}else{
			throw new IllegalArgumentException("Nenner darf nicht 0 sein");
		}
		this.kuerzen();
	}
	/**
	 * liefert den Nenner
	 * @return
	 */
	public long getNenner(){
		return nenner;
	}
	
	@Override
	public String toString(){
		return zaehler + "/" + nenner;
	}
	
	@Override
	public boolean equals(Object zahl){
		Rational num = new Rational();
		if(zahl instanceof Rational){
			num = (Rational) zahl;
		}
		if((this.zaehler == num.zaehler && this.nenner == num.nenner) || 
				((double) num.zaehler * this.nenner / this.zaehler == num.nenner) ||
				((double) this.zaehler * num.nenner / num.zaehler == this.nenner)){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public int hashCode(){
		final int prime = 31;
		int hash = 1;
		hash *= prime + zaehler;
		hash *= prime + nenner;
		return hash;
	}
	
	/**
	 * multipliziert die Faktoren 0 und 1 miteinander und liefert das Ergebnis zurueck
	 * @param fac0
	 * @param fac1
	 * @return
	 */
	public static Rational mult(Rational fac0, Rational fac1){
		Rational result = new Rational();
		result.zaehler = fac0.zaehler * fac1.zaehler;
		result.nenner = fac0.nenner * fac1.nenner;
		return (result.kuerzen());
	}
	
	/**
	 * multipliziert zwei rationale Zahlen miteinander, indem die static Methode mult
	 * aufgerufen wird und liefert das Ergebnis zurueck
	 * @param fac
	 * @return
	 */
	public Rational mult(Rational fac){
		Rational result = mult(this, fac);
		return result;
	}
	
	/**
	 * addiert zwei rationale Zahlen, falls sie ungleiche Nenner haben, 
	 * bringt man sie zunaechst auf den gleichen Nenner und addiert danach
	 * @param sum0
	 * @param sum1
	 * @return
	 */
	public static Rational add(Rational sum0, Rational sum1){
		Rational sum = new Rational();
		if(sum0.nenner == sum1.nenner){
			sum.zaehler = sum0.zaehler + sum1.zaehler;
			sum.nenner = sum0.nenner;
		}else{
			double quotient = sum0.nenner / sum1.nenner;
			boolean vielfache = sum0.nenner % sum1.nenner == 0;
			
			if(quotient < 1 && vielfache){
				sum.zaehler = (sum1.nenner/sum0.nenner)*sum0.zaehler + sum1.zaehler;
				sum.nenner = sum1.nenner;
			}else if(quotient > 1 && vielfache){
				sum.zaehler = (sum0.nenner/sum1.nenner)*sum1.zaehler + sum0.zaehler;
				sum.nenner = sum0.nenner;
			}else{
				sum.zaehler = sum0.zaehler*sum1.nenner + sum1.zaehler*sum0.nenner;
				sum.nenner = sum0.nenner*sum1.nenner;
			}
		}
		return (sum.kuerzen());
	}
	
	public Rational add(Rational summand){
		Rational summe = add(this, summand);
		return summe;
	}

	public static Rational sub(Rational minuend, Rational subtrahend){
		Rational differenz = new Rational();
		if(minuend.nenner == subtrahend.nenner){
			differenz.zaehler = minuend.zaehler - subtrahend.zaehler;
			differenz.nenner = minuend.nenner;
		}else{
			double quotient = minuend.nenner / subtrahend.nenner;
			boolean vielfache = minuend.nenner % subtrahend.nenner == 0;
			
			if(quotient < 1 && vielfache){
				differenz.zaehler = (subtrahend.nenner/minuend.nenner)*minuend.zaehler - subtrahend.zaehler;
				differenz.nenner = subtrahend.nenner;
			}else if(quotient > 1 && vielfache){
				differenz.zaehler = (minuend.nenner/subtrahend.nenner)*subtrahend.zaehler - minuend.zaehler;
				differenz.nenner = minuend.nenner;
			}else{
				differenz.zaehler = minuend.zaehler*subtrahend.nenner - subtrahend.zaehler*minuend.nenner;
				differenz.nenner = minuend.nenner*subtrahend.nenner;
			}
		}
		return (differenz.kuerzen());
	}
	
	public Rational sub(Rational subtrahend){
		Rational differenz = sub(this, subtrahend);
		return differenz;
	}
	
	public static Rational divide(Rational divident, Rational quotient){
		long temp = quotient.zaehler;
		quotient.zaehler = quotient.nenner;
		quotient.nenner = temp;
		return (divident.mult(quotient)).kuerzen();
	}
	
	public Rational divide(Rational quotient){
		return divide(this, quotient);
	}
	
	public boolean smaller(Rational vergleich){
		double quotient0 = this.zaehler/this.nenner;
		double quotient1 = vergleich.zaehler/vergleich.nenner;
		double result = quotient0/quotient1;
		if(result < 1){
			return true;
		}
		return false;
	}

	public Rational kuerzen(){
		long teiler = 1;
		if(this.zaehler != 0 && this.nenner != 0){
			teiler = ggT(this.zaehler, this.nenner);
		}
		Rational gekuerzt = new Rational();
		gekuerzt.zaehler /= teiler;
		gekuerzt.nenner /= teiler;
		return gekuerzt;
	}
	
	public static long ggT(long a, long b)
	{
		if (a == b){
			return a;
		}
	    else
	    {
	    	if (a>b){
	    		return(ggT(a-b,b));
	    	}else{
	    		return(ggT(b-a,a));
	    	}
	    }
	}
}
