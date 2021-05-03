import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Cavalier extends Piece implements interfaceValidite{
	
	//ATTRIBUTS DE LA CLASSE
	
	private String couleur;
	private int ligne;
	private int colonne;
	private ImageIcon icon;
	private Echiquier e1;
	
	//CONSTRUCTEUR : associe à la pièce une couleur, une position, un échiquier et une image
	
	public Cavalier (String couleur, int ligne, int colonne, Echiquier e1){
		super ("Cavalier", couleur, ligne, colonne, e1);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		this.e1 = e1;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/CN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/CB.png"));
			this.icon = icon;
		}
	}
	
	//GETTERS ET SETTERS
	
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
	
	//AFFICHAGE TOSTRING
	
	public String toString (){
		String s = super.toString();
		return s;
	}
	
	//METHODE DEFINISSANT LES DEPLACEMENTS POSSIBLES DE LA PIECE
	
	public boolean deplacementValid(int departL, int departC, int arriveeL, int arriveeC){		
		
		boolean valid = false;
		
		if ((Math.abs(departL-arriveeL)==2 && Math.abs(departC-arriveeC)==1) || (Math.abs(departL-arriveeL)==1 && Math.abs(departC-arriveeC)==2)){
			valid = true;
		}
		
		return valid;
	}
}
