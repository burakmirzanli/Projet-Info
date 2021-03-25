public class Pion extends Piece implements interfaceValidite {
	
	private String couleur;
	private int ligne;
	private int colonne;
	
	public Pion (String couleur, int ligne, int colonne){
		super ("Pion", couleur);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
	}
	public Pion (String couleur){
		super ("Pion", couleur);
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
		Echequier occupe = new Echequier (couleur);
		Position p = new Position(l, c);
		
		if (occupe.estOccupe(l, c) && !couleur.equals(occupe.getPieceCouleur())){ //methode de la classe Echequier qui envoie un boolean et l'autre qui renvoie un String couleur
			if (abs(l-p.getLigne())==1 && abs(c-p.getColonne())==1){
				valid = true;
			}
		}
		else {
			if (abs(l-p.getLigne()==1) && abs(c-p.getColonne())==0){
				valid = true;
			}
		}
		return valid;
	}
}
}
