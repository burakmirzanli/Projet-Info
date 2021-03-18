
import javax.swing.*;
import java.awt.*;


public class Echiquier {
	
	private final JFrame fenetreJeu;
	
	public Echiquier(){
		this.fenetreJeu = new JFrame ("Jeu d'echecs");
		
		this.fenetreJeu.setLayout(new BorderLayout());
		
		this.fenetreJeu.setSize(600,600);
		
		this.fenetreJeu.add(this.Plateau.BorderLayout.CENTER);
		
		this.fenetreJeu.setVisible(true);
	}
	
	public class Plateau extends JPanel {
		final Case[8][8] tabCase;
		
		Plateau(){
			super(new GridLayout(8,8));
			this.tabCase = new Case [][];
			for(int i = 0; i<8; i++){
				for(int j = 0; j<8; j++){
					final Case tabcase = new Case();
					this.tabCase.add(Case);
					add(Case);
				}
			}
		 validate();	
		}
	}
	
	public class Case extends JPanel {
		
	}
}

