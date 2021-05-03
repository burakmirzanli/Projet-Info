import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Fou extends Piece implements interfaceValidite {
	
	//ATTRIBUTS DE LA CLASSE
	
	private String couleur;
	private int colonne;
	private int ligne;
	private ImageIcon icon;
	private Echiquier e1;
	
	//CONSTRUCTEUR : associe à la pièce une couleur, une position, un échiquier et une image
	
	public Fou (String couleur, int ligne, int colonne, Echiquier e1){
		super ("Fou", couleur, ligne, colonne, e1);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		this.e1 = e1;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/FN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/FB.png"));
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
	public int getligne(){
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
        boolean c=true;
        
        //TEST DE DEPLACEMENT SUR LA DIAGONALE
        
        if(Math.abs(departC-arriveeC)==Math.abs(departL-arriveeL)){
			
			//TEST DIAGONALE EN BAS A DROITE
			
			if (departL-arriveeL<0 && departC-arriveeC<0){
				
				
				int j = departL+1;
				for(int i=departC+1;i<arriveeC;i++){
					if(j<arriveeL){
						if(j<7 && i<7){
							Piece p = ((e1.getBoutonPlateau(i,j))).getPiece();
							if(p!=null){
								c=false;
							}
						}
						j++;
					}
				}
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
				
			 }   
			 
			 //TEST DIAGONALE EN HAUT A GAUCHE
			 
			 else if (departL-arriveeL>0 && departC-arriveeC>0){  
				int j = departL-1;
				for(int i=departC-1;i>arriveeC;i--){
					if(j>arriveeL){
						if(j>=0 && i>=0){
							Piece p = ((e1.getBoutonPlateau(i,j))).getPiece();
							if(p!=null){
								c=false;
							}
						}
						j--;
					}
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}

			}
			
			//TEST DIAGONALE EN HAUT A DROITE
			
			else if(departL-arriveeL>0 && departC-arriveeC<0){
				
				int j=departL-1;;
				for(int i=departC+1;i<arriveeC;i++){
					if(j>arriveeL){
					
						if(j>=0 && i<7){
							Piece p = ((e1.getBoutonPlateau(i,j))).getPiece();
							if(p!=null){
								c=false;
							}
						}
						j--;
					}
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
		
			}
			
			//TEST DIAGONALE EN BAS A GAUCHE
			
			else if(departL-arriveeL<0 && departC-arriveeC>0){
				int j=departC-1;
				for(int i=departL+1;i<arriveeL;i++){
					if(j>arriveeC){
					
						if(i<7 && j>=0){
							Piece p = ((e1.getBoutonPlateau(j,i))).getPiece();
							if(p!=null){
								c=false;
							}
						}
						j--;
					}
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
				
				
			}
			
			
			
		}
        return valid;
        
    }
    
}
