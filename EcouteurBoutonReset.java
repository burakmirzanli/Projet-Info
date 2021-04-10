import java.awt.event.*;
  
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class EcouteurBoutonReset implements ActionListener{
	
	private Echiquier e1;
	private JButton b;
	//private Joueur j
	

	
	
	
	public EcouteurBoutonReset(Echiquier e1, JButton b){
		this.e1=e1;
		this.b=b;
	}
		
    public void actionPerformed(ActionEvent e){
	   
	   for(int i=0; i<8;i++){
		   for(int j=0; j<8; j++){
			   Echiquier.plateauBouton[i][j].setPiece(null);
		   }
	   }
	   
	   
	   
	   Echiquier.plateauBouton[0][0].setPiece(new Tour("Noir",0,0));
	   Echiquier.plateauBouton[1][0].setPiece(new Cavalier("Noir",0,1));
       Echiquier.plateauBouton[2][0].setPiece(new Fou("Noir",0,2));
       Echiquier.plateauBouton[3][0].setPiece(new Dame("Noir",0,3));
       Echiquier.plateauBouton[4][0].setPiece(new Roi("Noir",0,4));
       Echiquier.plateauBouton[5][0].setPiece(new Fou("Noir",0,5));
       Echiquier.plateauBouton[6][0].setPiece(new Cavalier("Noir",0,6));
       Echiquier.plateauBouton[7][0].setPiece(new Tour("Noir",0,7));
       
       for(int i =0; i<8;i++){
		Echiquier.plateauBouton[i][1].setPiece(new Pion("Noir",0,i));
       } 
       
       Echiquier.plateauBouton[0][7].setPiece(new Tour("Blanc",7,0));
       Echiquier.plateauBouton[1][7].setPiece(new Cavalier("Blanc",7,1));
       Echiquier.plateauBouton[2][7].setPiece(new Fou("Blanc",7,2));
       Echiquier.plateauBouton[3][7].setPiece(new Dame("Blanc",7,3));
       Echiquier.plateauBouton[4][7].setPiece(new Roi("Blanc",7,4));
       Echiquier.plateauBouton[5][7].setPiece(new Fou("Blanc",7,5));
       Echiquier.plateauBouton[6][7].setPiece(new Cavalier("Blanc",7,6));
       Echiquier.plateauBouton[7][7].setPiece(new Tour("Blanc",7,7));
       
       for(int i =0; i<8;i++){
		Echiquier.plateauBouton[i][6].setPiece(new Pion("Blanc",6,i));
       }

	}
}
	

    

