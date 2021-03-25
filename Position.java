public class Position{
	private int colonne;
	private int ligne;
	
	public Position(int ligne, int colonne){
		this.ligne=ligne;
		this.colonne=colonne;
	}
	
	public int getLigne(){
		return this.ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}
	
	public void setLigne(int l){
		this.ligne=l;
	}
	
	public void setColonne(int c){
		this.colonne=c;
	}
	
}
	
