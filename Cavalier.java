import java.lang.*;
public class Cavalier extends Piece implements interfaceValidite {
	
	private String couleur;
	private int ligne;
	private int colonne;
	
	public Cavalier (String couleur, int ligne, int colonne){
		super ("Cavalier", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.ccolonne = colonne;
	}
	public Cavalier (String couleur){
		super ("Cavalier", couleur);
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
		Position p = new Position(l, c); // l (ligne) et c (colonne) designe la case ciblee par le joueur
		if ((Math.abs(l-p.getLigne())==2 && Math.abs(c-getColonne())==1) || (Math.abs(l-p.getLigne())==1 && Math.abs(c-getColonne())==2)){
			valid = true;
		}
		return valid;
	}
}
