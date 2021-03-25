public class Fou extends Piece implements interfaceValidite {
	
	private String couleur;
	private int colonne;
	private int ligne;
	
	public Fou (String couleur, int ligne, int colonne){
		super ("Fou", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
	}
	public Fou (String couleur){
		super ("Fou", couleur);
		this.couleur = couleur;
	}
	public String getCouleur(){
		return this.couleur;
	}
	
	public void setCouleur (String c){
		this.couleur = c;
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
	
	public String toString (){
		String s = super.toString();
		return s;
	}
	public boolean deplacementValid(int l, int c){		
		boolean valid = false;
		Position p = new Position(l, c);
		if (abs(l-p.getLine())>=0 && abs(c-p.getColonne())>=0){
			valid = true;
		}
		return valid;
}
}
