public class Piece {
	
	private String type;
	private String couleur;
	
	public Piece (String type, String couleur){
		this.type = type;
		this.couleur = couleur;
	}
	
	public String toString (){ //On utilise plutot les donnees "privates" plutot que les redemander ici pour appeler la methode depuis "main"
		String s = "La piece "+this.type+" de couleur "+this.couleur+" ..."; //a completer
		return s;
	}
	
	public String getCouleur(){
		return this.couleur;
	}
	
	public void setCouleur (String c){
		this.couleur = c;
	}
}
