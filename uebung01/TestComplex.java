/** Testklasse fuer die Klasse Complex
*	ausfuerbare Klasse
* 	
*	@author: EnteEnte
*	@last modification: 10/05/2015
*/

public class TestComplex{
	
	public static void main(String[] args){
		
		/*Complex-Variablen*/
		Complex a = new Complex(Complex.ZERO);
		Complex b = new Complex(Complex.I);
		Complex c = new Complex(Complex.ONE);
		Complex d = new Complex(1,-1);
		Complex e = new Complex(-1,1);
		Complex f = new Complex(1,1);
		Complex g = new Complex(2,1);

		for(int i = 0; i < 1;i++){
		
		/* toString */
		System.out.println("toString-Methode testen:");
		System.out.println("Die Complexen Zahlen a bis g:");
		System.out.println(a+" | "+b+" | "+c+" |\n"+d+" | "+e+" | "+f+" | "+g);
		
		/*clone, equals, hashcode testen*/
		System.out.println("\nclone und equals-Methoden testen:");
		System.out.print("e ="+e +" und d "+d +"\n clone von d ist e:");
		e = d.clone();
		System.out.println(e);
		System.out.print("e ="+e +" und f "+f +"\n clone von f ist e:");
		e = f.clone();
		System.out.println(e);
		System.out.print("e ="+e +" und g "+g +"\n clone von g ist e:");
		e = g.clone();
		System.out.println(e+"\n");
		System.out.println(a+" equals "+b+" ?:"+a.equals(b));
		System.out.println(g+" equals "+e+" ?:"+g.equals(e));
		System.out.println(c+" equals "+d+" ?:"+c.equals(d));
		System.out.println(a+" equals "+b+" ?:"+Complex.equals(a,b));
		System.out.println(g+" equals "+e+" ?:"+Complex.equals(g,e));
		System.out.println(c+" equals "+d+" ?:"+Complex.equals(c,d));
		System.out.println("haschcode: "+a.hashcode() + " = " + b.hashcode() + " ?");
		System.out.println("haschcode: "+g.hashcode() + " = " + e.hashcode() + " ?");
		System.out.println("haschcode: "+c.hashcode() + " = " + d.hashcode() + " ?");

		/*setter testen*/
		System.out.println("\nSetter tesetn:");
		System.out.print(e+".setReal(0) = ");
		e.setReal(0);
		System.out.println(e);
		System.out.print(g+".setReal(1) = ");
		g.setReal(1);
		System.out.println(g);
		System.out.print(f+".setReal(4.1) = ");
		f.setReal(4.1);
		System.out.println(f);
		System.out.print(f+".setIm(2.5) = ");
		f.setIm(2.5);
		System.out.println(f);
		System.out.print(g+".setIm(2) = ");
		g.setIm(2);
		System.out.println(g);
		System.out.print(f+".setIm(-1) = ");
		e.setIm(-1);
		System.out.println(e);
		/*getter testen*/
		System.out.println("\nGetter testen:");
		System.out.print("get "+e+": e.getReal() und e.getIm()  = ");
		System.out.println(e.getReal()+" , "+e.getIm());
		System.out.print("get "+f+": f.getReal() und f.getIm()  = ");
		System.out.println(f.getReal()+" , "+f.getIm());
		System.out.print("get "+g+": g.getReal() und g.getIm()  = ");
		System.out.println(g.getReal()+" , "+g.getIm());

		/*arithmetische Operationen*/
		/*add*/
		System.out.println("\nAddition testen:");
		System.out.print(a+" + "+b+" = ");
		a.add(b);
		System.out.println(a);
		System.out.print(b+" + "+d+" = ");
		b.add(d);
		System.out.println(b);
		System.out.print(c+" + "+d+" = ");
		c.add(d);
		System.out.println(c);
		System.out.print(a+" + "+b+" = ");
		a = Complex.add(a,b);
		System.out.println(a);
		System.out.print(b+" + "+d+" = ");
		b = Complex.add(b,d);
		System.out.println(b);
		System.out.print(c+ " + "+d+" = ");
		c = Complex.add(c,d);
		System.out.println(c);


		/*mult*/
		System.out.println("\nMultiplikation testen:");
		System.out.print(a +" * "+ b);
		a.mult(b);
		System.out.println(" = "+a);
		System.out.print(c +" * "+ d);
		c.mult(d);
		System.out.println(" = "+c);
		System.out.print(Complex.I +" * "+ b);
		c.mult(b);
		System.out.println(" = "+c);
		System.out.print(a +" * "+ b);
		a = Complex.mult(a,b);
		System.out.println(" = "+a);
		System.out.print(c +" * "+ d);
		c = Complex.mult(a,d);
		System.out.println(" = "+c);
		System.out.print(Complex.I +" * "+ b);
		c = Complex.mult(Complex.I, b);
		System.out.println(" = "+c);
		
		/*abs*/
		System.out.println("\n Betragsfunktion testen:");
		System.out.println("|" + a + "| ="+ a.abs());
		System.out.println("|" + f + "| ="+ f.abs());
		System.out.println("|" + g + "| ="+ g.abs());
		System.out.println("|" + a + "| ="+ Complex.abs(a));
		System.out.println("|" + f + "| ="+ Complex.abs(f));
		System.out.println("|" + g + "| ="+ Complex.abs(g));

		/*Skalarmultiplikation*/
		System.out.println("\n Skalarmultiplikation testen:");
		System.out.print(a+" * 3 = ");
		a.mult(3);
	        System.out.println(a);	
		System.out.print(b+" * 4 = ");
		b.mult(4);
	        System.out.println(b);	
		System.out.print(c+" * 5 = ");
		c.mult(5);
	        System.out.println(c);	

		/*random testen:*/
		System.out.println("\n random testen:");
		System.out.print(a + " wird mit random ");
		a = Complex.random();
		System.out.println("\""+ a +"\" ueberschrieben.");
		System.out.print(b + " wird mit random ");
		b = Complex.random();
		System.out.println("\""+ b +"\" ueberschrieben.");
		System.out.print(a + " wird mit random ");
		a = Complex.random();
		System.out.println("\""+ a +"\" ueberschrieben.");
	
		/*conjugate testen*/
		System.out.println("\n Methode fuer das Complex konjugierte testen:");
		System.out.print("a = ("+ a +") konjugiert: ");
		a.conjugate();
		System.out.println(a);
		System.out.print("b = ("+ b +") konjugiert: ");
		b.conjugate();
		System.out.println(b);
		System.out.print("c = ("+ c +") konjugiert: ");
		c.conjugate();
		System.out.println(c);
		}
	}
}
