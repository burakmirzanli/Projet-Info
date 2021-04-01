import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;



public class Tour extends Piece implements interfaceValidite{
	
	private String couleur;
	private int colonne;
	private int ligne;
	private ImageIcon icon;
	
	public Tour (String couleur, int ligne, int colonne){
		super ("Tour", couleur, ligne, colonne);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/TN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/TB.png"));
			this.icon = icon;
		}
        
	}
	public Tour (String couleur){
		super ("Tour", couleur);
		this.couleur = couleur;
	}
	public String getCouleur(){
		return this.couleur;
	}
	
	public ImageIcon getIcon(){
		return this.icon;
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
	
	public boolean deplacementValid(int departL, int departC, int arriveeL, int arriveeC){		
		boolean valid = false;
		if ((Math.abs(departL-arriveeL)>=0 && Math.abs(departC-arriveeC)==0) || (Math.abs(departL-departC)==0 && Math.abs(departC-arriveeC)>=0)){
			valid = true;
		}
		return valid;
	}
}
