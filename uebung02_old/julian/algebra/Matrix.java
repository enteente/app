/**
 * Klasse zur Verarbeitung von Matrizen unter rationalen Zahlen
 * @author Julian Stock
 * @date 12.05.2015
 */

package algebra;

public class Matrix {
	
	/**
	 * genutzte Variablen
	 */
	private Rational[][] matrix;					// Matrix ueber den rationalen Zahlen
	private int spalten;							// Spaltenanzahl des Arrays
	private int zeilen;								// Zeilenanzahl des Arrays
	
	/**
	 * default-Konstruktor
	 */
	public Matrix(){
		spalten = 1;
		zeilen = 1;
		matrix = new Rational[zeilen][spalten];
		this.setZS(1, 1, null);
	}
	
	/**
	 * specific-Konstruktor
	 */
	public Matrix(int z, int s){
		spalten = s;
		zeilen = z;
		matrix = new Rational[z][s];
		for(int i = 0; i < z; i++){
			for(int j = 0; j < s; j++){
				Rational eintrag = new Rational();
				this.setZS(i, j, eintrag);
			}
		}
	}
	
	/**
	 * erlaubt es einzelne Eintraege der Matrix zu setzen
	 * @param s
	 * @param z
	 * @param wert
	 */
	public void setZS(int z, int s, Rational wert){
		matrix[z][s] = wert;
	}
	
	/**
	 * erlaubt es einzelne Eintrage der Matrix auszulesen
	 */
	public Rational getZS(int z, int s){
		return matrix[z][s];
	}
	
	/**
	 * liefert die Dimension des Arrays zurueck
	 * @return
	 */
	public int getDim(){
		return spalten;
	}
	
	/**
	 * vertausche zwei Zeilen der Matrix
	 * @param zeile0
	 * @param zeile1
	 */
	public void tauscheZeilen(int zeile0, int zeile1){
		Rational[] temp = new Rational[spalten];
		temp = matrix[zeile1];
		matrix[zeile1] = matrix[zeile0];
		matrix[zeile0] = temp;
	}
	
	public static Matrix mult(Matrix eins, Matrix zwei) throws IndexOutOfBoundsException{
		if(eins.spalten != zwei.zeilen){
			throw new IndexOutOfBoundsException("mult: Zeilen und Spalten passen nicht zueinander");
		}
		Matrix result = new Matrix(eins.spalten, zwei.zeilen);
		for(int i = 0; i < eins.zeilen; i++){
			for(int j = 0; j < zwei.spalten; j++){
				for(int k = 0; k < eins.spalten; k++){
					result.setZS(i, j, result.getZS(i, j).add(eins.getZS(i, k).mult(zwei.getZS(k, j))));
				}
			}
		}
		return result;
	}
	
	public Matrix mult(Matrix eins){
		return mult(this, eins);
	}
	
	@Override
	public boolean equals(Object obj) {
		Matrix other = new Matrix();
		if(obj instanceof Matrix){
			other = (Matrix) obj;
		}
		if (spalten != other.spalten) {
			return false;
		}
		if (zeilen != other.zeilen) {
			return false;
		}
		for(int i = 0; i < zeilen; i++){
			for(int j = 0; j < spalten; j++){
				if(this.getZS(i, j).equals(other.getZS(i, j))){
					return false;
				}
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int hash = 1;
		hash *= prime + spalten;
		hash *= prime + zeilen;
		return hash;
	}	

	@Override
	public String toString(){
		String s = "";
		for(int i = 0; i < zeilen; i++){
			for(int j = 0; j < spalten; j++){
				s += this.getZS(i, j) + " ";
			}
		}
		return s;
		
	}

	@Override
	protected Object clone(){
		Matrix clone = this;
		return (Object) clone;
	}
}
