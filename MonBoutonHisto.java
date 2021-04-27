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


public class MonBoutonHisto extends JButton /* implements ActionListener */ {

      
    private int numero;
  
    public MonBoutonHisto(String s ,int numero) {
        super(s);
        this.numero=numero;

	}
	
	public int getNumero(){
		return this.numero;
	}
}
