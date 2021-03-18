public class Echequier{
	
	private Case [][] echequier; //Case est la classe qui fournit les cases de l'echequier.
	
	public Echequier(){
		echequier = new Case [8][8];
	}
	
	public void debut(){
		//
	}
	
	public boolean estOccupe(int l, int c){
		boolean b = false;
		if (echequier [l][c] != null){
			b = true;
		}
		return b;
	}
	public String getPieceCouleur(int l, int c){
		if (estOccupe(l, c)){
			return //couleur de la piece Pierlouis au secours mdr
		}
	}
}
