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
	
	public boolean deplacementValid(){		 //cette methode pourra bien etre transformee en interface
		Deplacement val = new Deplacement(); //Deplacement est une classe (voir la diagramme de cahier des charges qu'on a fait)
		return val.getValidite();			 //getValidite() est la methode de la classe Deplacement qui retourne un boolean vrai en cas d'un deplacement valide d'une piece
	}
}
