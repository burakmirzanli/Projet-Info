import java.awt.event.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class EcouteurBoutonHisto implements ActionListener{
	
	private Echiquier e1;
	private boolean f;
	private int numero;

	public EcouteurBoutonHisto(Echiquier e1, int numero){
		this.e1 = e1;
		this.numero = numero;

	}
		
    public void actionPerformed(ActionEvent e){
	   e1.retourArriereJeu(numero);
	}

}
