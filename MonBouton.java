import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class MonBouton extends JButton /* implements ActionListener */ {
  
      int ligne, colonne;
  
     public MonBouton(int ligne, int colonne) {
        this.ligne = ligne;
        this.colonne = colonne;
        // addActionListener(this);
	}

    /* public void ActionPerform(ActionEvent e) {
		
    } */
}
