/**
 * Klasse beinhaltet verschiedene mathematische Methoden
 */

package algebra;

public class Algebra {
	
	public static Matrix gaussJordanVerfahren(Matrix a, Matrix b) throws Exception{
		
		int groesse = a.getDim();
		Matrix A = new Matrix(groesse, groesse);
		Matrix B = new Matrix(groesse, 1);
		for(int i = 0; i < groesse; i++){
			for(int j = 0; j < groesse; j++){
				A.setZS(i, j, a.getZS(i, j));
				
			}
			B.setZS(i, 0, b.getZS(i, 0));
		}
		Matrix aikakk = new Matrix(groesse, 1);
		for(int k = 1; k < groesse; k++){
			int temp = 0;
			for(int l = k; l < groesse-1; l++){
				
				if(A.getZS(l, k).equals(Rational.NULL)){
					throw new Exception("Die Matrix ist Singulaer");
				}
				
				if(A.getZS(l, k).smaller(A.getZS(l+1, k))){
					temp = l+1;
				}
				
			}
			A.tauscheZeilen(temp, k);
			B.tauscheZeilen(temp, k);
			
			for(int i = 0; i < groesse; i++){
				aikakk.setZS(k, 0, A.getZS(i, k).divide(A.getZS(k, k)));
				for(int j = 0; j < groesse; j++){
					if(i < groesse && j < k-1){
						A.setZS(i, j, A.getZS(i, j));
					}else if(i < groesse && j == k && i != k){
						A.setZS(i, j, Rational.NULL);
					}else if(i == k && j == k){
						A.setZS(i, j, A.getZS(k, k));
					}else if(i == k && j > k && j < groesse){
						A.setZS(i, j, A.getZS(k, j));
					}else{
						A.setZS(i, j, A.getZS(i, j).sub(A.getZS(k, j).mult(aikakk.getZS(i, 0))));
					}
					if(i == k){
						B.setZS(i, 0, B.getZS(k, 0));
					}else{
						B.setZS(i, 0, B.getZS(i, 0).sub(aikakk.getZS(i, 0)));
					}
				}
			}
		}
		Matrix x = new Matrix(groesse, 1);
		for(int i = 0; i < groesse; i++){
			x.setZS(i, 0, B.getZS(i, 0).divide(A.getZS(i, i)));
		}
		return x;
	}
}
