import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Pion extends Piece implements interfaceValidite{
	
	private String couleur;
	private int ligne;
	private int colonne;
	private ImageIcon icon;
	
	public Pion (String couleur, int ligne, int colonne){
		super ("Pion", couleur);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		// - CREATION IMAGE DES PIECES - //
		if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/PN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/PB.png"));
			this.icon = icon;
		}
	}
	public Pion (String couleur){
		super ("Pion", couleur);
		this.couleur = couleur;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/PN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/PB.png"));
			this.icon = icon;
		}
	}
	
	public ImageIcon getIcon(){
		return this.icon;
	}
	
	
	public String getCouleur(){
		return this.couleur;
	}
	
	public void setCouleur (String c){
		this.couleur = c;
	}
	public int getLigne(){
		return this.ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}
	
	public void setLigne(int l){
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
		
      // if (occupe.estOccupe(l, c) && !couleur.equals(occupe.getPieceCouleur())){ //methode de la classe Echequier qui envoie un boolean et l'autre qui renvoie un String couleur
			if (Math.abs(l-p.getLigne())==1 && Math.abs(c-p.getColonne())==1){
				valid = true;
			}
		// }
		else {
			if (Math.abs(l-p.getLigne())==1 && Math.abs(c-p.getColonne())==0){
				valid = true;
			}
		} 
		return valid; 
	}
}

