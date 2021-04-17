import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Roi extends Piece implements interfaceValidite{
	
	private String couleur;
	private int ligne;
	private int colonne;
	private ImageIcon icon;
	private Echiquier e1;
	
	public Roi (String couleur, int ligne, int colonne, Echiquier e1){
		super ("Roi", couleur, ligne, colonne, e1);
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		this.e1 = e1;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/RN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/RB.png"));
			this.icon = icon;
		}
	}
	public Roi (String couleur){
		super ("Roi", couleur);
		this.couleur = couleur;
		// - CREATION IMAGE DES PIECES - //
        if(this.couleur == "Noir"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/RN.png"));
			this.icon = icon;
		} else if (this.couleur == "Blanc"){
			ImageIcon icon = new ImageIcon(Echiquier.class.getResource("image/RB.png"));
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
public boolean deplacementValid(int departL, int departC, int arriveeL, int arriveeC){		
		
		boolean valid = false;
		boolean c=true;
		
		if (Math.abs(departL-arriveeL)>=0 && Math.abs(departC-arriveeC)==0&&(arriveeL==departL+1||arriveeL==departL-1)){ // DEPLACEMENT VERTICAL //
			valid = true;
			
			if (departL < arriveeL) { // DEPLACEMENT VERS LE BAS //	
				Piece p1= (e1.getBoutonPlateau(departC,departL+1)).getPiece();
				if (p1 != null && p1.getCouleur()==this.couleur){
					valid = false;
				}
			
			} else { // DEPLACEMENT VERS LE HAUT //	
				Piece p1= (e1.getBoutonPlateau(departC,departL-1)).getPiece();
				if (p1 != null&&p1.getCouleur()==this.couleur){
						valid = false;
				}
			}
		}
		
		if((Math.abs(departL-arriveeL)==0) && Math.abs(departC-arriveeC)>=0&& (arriveeC==departC+1||arriveeC==departC-1)){ // DEPLACEMENT HORIZONTAL//
			valid = true;
			
			if(departC < arriveeC ){ // DEPLACEMENT SUR LA DROITE //
				Piece p2= (e1.getBoutonPlateau(departC+1,departL)).getPiece();
				
				if( p2 != null&&p2.getCouleur()==this.couleur){
						valid = false;
					}
				
			
			} else { // DEPLACEMENT SUR LA GAUCHE //
				Piece p2= (e1.getBoutonPlateau(departC-1,departL)).getPiece();
				
				if(p2 != null&&p2.getCouleur()==this.couleur){
						valid = false;
				}
				
			}
		
		}
		 
        //TEST DE DEPLACEMENT SUR LA DIAGONALE
        
        if(Math.abs(departC-arriveeC)==Math.abs(departL-arriveeL)){
         
			if (departL-arriveeL<0 && departC-arriveeC<0 && arriveeL==departL+1&& arriveeC==departC+1){
				Piece p = (e1.getBoutonPlateau(departC+1,departL+1)).getPiece();
				if(p!=null&&p.getCouleur()==this.couleur){
					c=false;
				}
			
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
				
		   }   
			 else if (departL-arriveeL>0 && departC-arriveeC>0&& arriveeC==departC-1&& arriveeL==departL-1){  
				Piece p = (e1.getBoutonPlateau(departC-1,departL-1)).getPiece();
				if(p!=null && p.getCouleur()== this.couleur){
					c=false;
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}

			}
			
			else if(departL-arriveeL>0 && departC-arriveeC<0&& arriveeC==departC+1&& arriveeL==departL-1){
				
				Piece p = (e1.getBoutonPlateau(departC+1,departL-1)).getPiece();
				if(p!=null&&p.getCouleur()==this.couleur){
					c=false;
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
		
			}
			
			else if(departL-arriveeL<0 && departC-arriveeC>0&& arriveeC==departC-1&& arriveeL==departL+1){
			
				Piece p = (e1.getBoutonPlateau(departC-1,departL+1)).getPiece();
				if(p!=null&&p.getCouleur()==this.couleur){
					c=false;
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
