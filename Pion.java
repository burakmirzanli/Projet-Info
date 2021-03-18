public class Pion extends Piece implements interfaceValidite {
	
	private String couleur;
	
	public Pion (String couleur){
		super ("Pion", couleur);
		this.couleur = couleur;
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
