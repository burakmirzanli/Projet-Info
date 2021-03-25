public class Dame extends Piece implements interfaceValidite {
	
	private String couleur;
	private int ligne;
	private int colonne;
	
	public Dame (String couleur, int ligne, int colonne){
		super ("Dame", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
	}
	
	public String toString (){
		return super.toString();
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
	
	public boolean deplacementValid(int l, int c){		
		boolean valid = false;
		Position p = new Position(l, c);
		if ((abs(l-p.getLigne())>=0 && abs(c-getColonne())==0) || (abs(l-p.getLigne())==0 && abs(c-getColonne())>=0) || (abs(l-p.getLine())>=0 && abs(c-p.getColonne())>=0)){
			valid = true;
		}
		return valid;
	}
}
