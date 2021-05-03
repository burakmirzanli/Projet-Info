import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;



public class Tour extends Piece implements interfaceValidite{
	
	//ATTRIBUTS DE LA CLASSE
	
	private String couleur;
	private int colonne;
	private int ligne;
	private ImageIcon icon;
	private Echiquier e1;
	private boolean tourPositionDepart = true;
	
	//CONSTRUCTEUR : associe à la pièce une couleur, une position, un échiquier et une image
	
	public Tour (String couleur, int ligne, int colonne, Echiquier e1){
		super ("Tour", couleur, ligne, colonne, e1);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		this.e1 = e1;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/TN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/TB.png"));
			this.icon = icon;
		}
        
	}
	
	//GETTERS ET SETTERS
	
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
	
	public boolean getTourPositionDepart(){
		return this.tourPositionDepart;
	}
	
	public void setTourPositionDepart(boolean res){
		this.tourPositionDepart=res;
	}
	
	//AFFICHAGE TOSTRING
		
	public String toString (){
		String s = super.toString();
		return s;
	}
	
	
	//METHODE DEFINISSANT LES DEPLACEMENTS POSSIBLES DE LA PIECE
	
	public boolean deplacementValid(int departL, int departC, int arriveeL, int arriveeC){	
		boolean valid = false;
		
		if (Math.abs(departL-arriveeL)>=0 && Math.abs(departC-arriveeC)==0){ // DEPLACEMENT VERTICAL //
			valid = true;
			
			if (departL < arriveeL) { // DEPLACEMENT VERS LE BAS //	
			
				for (int i = departL + 1; i < arriveeL; i++)
					if (((e1.getBoutonPlateau(departC,i))).getPiece() != null)
						valid = false;
			
			} else { // DEPLACEMENT VERS LE HAUT //	
			
				for (int i = departL - 1; i > arriveeL; i--)
					if (((e1.getBoutonPlateau(departC, i))).getPiece() != null)
						valid = false;
			}
		}
		
		if((Math.abs(departL-arriveeL)==0) && Math.abs(departC-arriveeC)>=0){ // DEPLACEMENT HORIZONTAL//
			valid = true;
			
			if(departC < arriveeC){ // DEPLACEMENT SUR LA DROITE //
				
				for(int i=departC+1 ; i<arriveeC; i++){
					if(((e1.getBoutonPlateau(i, departL))).getPiece() != null){
						valid = false;
					}
				}
			
			} else { // DEPLACEMENT SUR LA GAUCHE //
				
				for(int i=departC-1 ; i>arriveeC; i--){
					if(((e1.getBoutonPlateau(i, departL))).getPiece() != null){
						valid = false;
					}
				}
			}
		
		}
		return valid;
	}
}
