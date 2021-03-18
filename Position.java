public class Position{
	private int colonne;
	private int ligne;
	
	public Position(int ligne, int colonne){
		this.ligne=ligne;
		this.colonne=colonne;
	}
	
	public int getligne(){
		return this.ligne;
	}
	
	public int getcolonne(){
		return this.colonne;
	}
	
	public void setligne(int l){
		this.ligne=l;
	}
	
	public void setColonne(int c){
		this.colonne=c;
	}
	
}
	
