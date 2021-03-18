public class Roi extends Piece implements interfaceValidite {
	
	private String couleur;
	
	public Roi (String couleur){
		super ("Roi", couleur);
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
		Position p = new Position(l, c);
		if (abs(l-p.getLine())==1 && abs(c-p.getColonne())==1){
			valid = true;
		}
		return valid;
	}
}
