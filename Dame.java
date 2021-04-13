import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Dame extends Piece implements interfaceValidite {
	
	public String couleur;
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
	
	public boolean deplacementValid(int departL, int departC, int arriveeL, int arriveeC){		
		
		boolean valid = false;
		boolean c=true;
		
		if (Math.abs(departL-arriveeL)>=0 && Math.abs(departC-arriveeC)==0){ // DEPLACEMENT VERTICAL //
			valid = true;
			
			if (departL < arriveeL) { // DEPLACEMENT VERS LE BAS //	
			
				for (int i = departL + 1; i < arriveeL; i++)
					if ((Echiquier.plateauBouton[departC][i]).getPiece() != null)
						valid = false;
			
			} else { // DEPLACEMENT VERS LE HAUT //	
			
				for (int i = departL - 1; i > arriveeL; i--)
					if ((Echiquier.plateauBouton[departC][i]).getPiece() != null)
						valid = false;
			}
		}
		
		if((Math.abs(departL-arriveeL)==0) && Math.abs(departC-arriveeC)>=0){ // DEPLACEMENT HORIZONTAL//
			valid = true;
			
			if(departC < arriveeC){ // DEPLACEMENT SUR LA DROITE //
				
				for(int i=departC+1 ; i<arriveeC; i++){
					if((Echiquier.plateauBouton[i][departL]).getPiece() != null){
						valid = false;
					}
				}
			
			} else { // DEPLACEMENT SUR LA GAUCHE //
				
				for(int i=departC-1 ; i>arriveeC; i--){
					if((Echiquier.plateauBouton[i][departL]).getPiece() != null){
						valid = false;
					}
				}
			}
		
		}
		 
        //TEST DE DEPLACEMENT SUR LA DIAGONALE
        
        if(Math.abs(departC-arriveeC)==Math.abs(departL-arriveeL)){
         
			if (departL-arriveeL<0 && departC-arriveeC<0){
				
				
				for(int i=1;(i<arriveeC)&&(i<arriveeL);i++){
					if((departL+i)<7 && (departC+i)<7){
						Piece p = (Echiquier.plateauBouton[departC+i][departL+i]).getPiece();
						if(p!=null&&p.getCouleur()==this.couleur){
							c=false;
						}
					}
				}
				
				if(c==true){
					valid=true;
				}else{
					valid=false;
				}
				
			 }   
			 else if (departL-arriveeL>0 && departC-arriveeC>0){  
				int j = departL-1;
				for(int i=departC-1;i>arriveeC;i--){
					if(j>arriveeL){
						if(j>=0 && i>=0){
							Piece p = (Echiquier.plateauBouton[i][j]).getPiece();
							if(p!=null && p.getCouleur()== this.couleur){
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
			
			else if(departL-arriveeL>0 && departC-arriveeC<0){
				
				int j=departL-1;;
				for(int i=departC+1;i<arriveeC;i++){
					if(j>arriveeL){
					
						if(j>=0 && i<7){
							Piece p = (Echiquier.plateauBouton[i][j]).getPiece();
							if(p!=null&&p.getCouleur()==this.couleur){
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
			
			else if(departL-arriveeL<0 && departC-arriveeC>0){
				int j=departC-1;
				for(int i=departL+1;i<arriveeL;i++){
					if(j>arriveeC){
					
						if(i<7 && j>=0){
							Piece p = (Echiquier.plateauBouton[j][i]).getPiece();
							if(p!=null&&p.getCouleur()==this.couleur){
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
