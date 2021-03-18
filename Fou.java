public class Fou extends Piece implements interfaceValidite {
	
	private String couleur;
	
	public Fou (String couleur){
		super ("Fou", couleur);
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
		if (abs(l-p.getLine())>=0 && abs(c-p.getColonne())>=0){
			valid = true;
		}
		return valid;
}
}
