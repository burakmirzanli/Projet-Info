import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class EcouteurBoutonLancer implements ActionListener{
	
	private Echiquier e1;
	private boolean f;

	public EcouteurBoutonLancer(Echiquier e1){
		this.e1=e1;
	}
		
    public void actionPerformed(ActionEvent e){
	   
	   e1.startTpsB();
	   f=true;
	   

	}
	
	public boolean getF(){
		return f;
	}
	
	public void setF(boolean k){
		f=k;
	}
}
