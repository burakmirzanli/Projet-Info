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
	
	public boolean deplacementValid(){		 //cette methode pourra bien etre transformee en interface
		Deplacement val = new Deplacement(); //Deplacement est une classe (voir la diagramme de cahier des charges qu'on a fait)
		return val.getValidite();			 //getValidite() est la methode de la classe Deplacement qui retourne un boolean vrai en cas d'un deplacement valide d'une piece
	}
}
