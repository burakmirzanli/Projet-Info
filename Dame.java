import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Dame extends Piece {
	
	private String couleur;
	private int ligne;
	private int colonne;
	private ImageIcon icon;
	
	public Dame (String couleur, int ligne, int colonne){
		super ("Dame", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/DN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/DB.png"));
			this.icon = icon;
		}
	}
	
	public Dame (String couleur){
		super ("Dame", couleur);
		this.couleur = couleur;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/DN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/DB.png"));
			this.icon = icon;
		}
	}
	
	public ImageIcon getIcon(){
		return this.icon;
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
	public int getligne(){
		return this.ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}
	
	public void setligne(int l){
		this.ligne=l;
	}
	
	public void setColonne(int c){
		this.colonne=c;
	}
	
	public boolean deplacementValid(int l, int c){		
		boolean valid = false;
		Position p = new Position(l, c);
		if ((Math.abs(l-p.getLigne())>=0 && Math.abs(c-getColonne())==0) || (Math.abs(l-p.getLigne())==0 && Math.abs(c-getColonne())>=0) || (Math.abs(l-p.getLigne())>=0 && Math.abs(c-p.getColonne())>=0)){
			valid = true;
		}
		return valid;
	}
}
