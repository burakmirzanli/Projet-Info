import java.awt.event.*;
  
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class EcouteurBoutonReset implements ActionListener{
	
	//ATTRIBUTS
	
	private Echiquier e1;
	
	// CONSTRUCTEUR ECOUTEUR
	
	public EcouteurBoutonReset(Echiquier e1){
		this.e1=e1;
		
	}
	
	//ACTION FAITE PAR L'ECOUTEUR
	
    public void actionPerformed(ActionEvent e){
	   
	   e1.renitialisationEchiquier();
	   

	}
}
	

    

