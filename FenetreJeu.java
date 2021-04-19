
import javax.swing.*;
import java.awt.*;


public class FenetreJeu extends JFrame {
	
	private final JFrame fenetreJeu;
	private JLabel[][] tab;
	private Echiquier e;
		
	public FenetreJeu(){
		this.fenetreJeu = new JFrame ("Jeu d'echecs");
		
		this.fenetreJeu.setLayout(new BorderLayout());
		
		this.fenetreJeu.setSize(600,600);
		
		this.fenetreJeu.add(Echiquier.BorderLayout.CENTER);
		
		this.fenetreJeu.setVisible(true);
	}
	
	
	
	
	
	
	public static void main(String[] args) {
		FenetreJeu f = new FenetreJeu();
	}
}

