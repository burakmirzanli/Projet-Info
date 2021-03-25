import java.lang.*;
public class Roi extends Piece implements interfaceValidite {
	
	private String couleur;
	private int ligne;
	private int colonne;
	
	public Roi (String couleur, int ligne, int colonne){
		super ("Roi", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
	}
	public Roi (String couleur){
		super ("Roi", couleur);
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
		Position p = new Position(l, c);
		if (Math.abs(l-p.getLine())==1 && Math.abs(c-p.getColonne())==1){
			valid = true;
		}
		return valid;
	}
}
