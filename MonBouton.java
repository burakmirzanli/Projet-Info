import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class MonBouton extends JButton /* implements ActionListener */ {
  
      int ligne, colonne;
      private Piece p;
      private ImageIcon icon;
  
     public MonBouton(int ligne, int colonne,Piece p) {
        this.ligne = ligne;
        this.colonne = colonne;
		this.p=p;
		if (this.p != null){
			this.setIcon(p.getIcon());
		}
        // addActionListener(this);
	}
	
	public int getL(){
		return this.ligne;
	}
	
	public int getC(){
		return this.colonne;
	}
	
	public Piece getPiece(){
		return this.p;
	}
	
	public void setPiece (Piece p1){
		this.p=p1;
		if (this.p != null){
			this.setIcon(p.getIcon());
		}
	}

    /* public void ActionPerform(ActionEvent e) {
		
    } */
}
