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
	   
	   e1.renitialisationEchiquier();

	}
}
	

    

