public class Cavalier extends Piece implements interfaceValidite {
	
	private String couleur;
	
	public Cavalier (String couleur){
		super ("Cavalier", couleur);
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
	
	public boolean deplacementValid(){		
		boolean valid = false;
		Position p = new Position(int l, int c); // l (ligne) et c (colonne) designe la case ciblee par le joueur
		if ((abs(l-p.getLigne())==2 && abs(c-getColonne())==1) || (abs(l-p.getLigne())==1 && abs(c-getColonne())==2)){
			valid = true;
		}
		return valid;
	}
}
