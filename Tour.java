public class Tour extends Piece implements interfaceValidite {
	
	private String couleur;
	
	public Tour (String couleur){
		super ("Tour", couleur);
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
		Position p = new Position(int l, int c);
		if ((abs(l-p.getLigne())>=0 && abs(c-getColonne())==0) || (abs(l-p.getLigne())==0 && abs(c-getColonne())>=0)){
			valid = true;
		}
		return valid;
	}
}
