import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;


public class Echiquier {
	
	// Attributs //
	
	private final JPanel interfaceJeu = new JPanel(new BorderLayout(3, 3));

	public static MonBouton[][] plateauBouton = new MonBouton [8][8];
	public JPanel plateauPanel = new JPanel();
	private static final String COLS = "ABCDEFGH";
	
	private int departL;
	private int departC;
    private int arriveeL;
	private int arriveeC;
	
	public Piece pieceDepart;
	public Piece pieceArrivee;
	
	public int compteurBouton;

	
	public Piece piece;
	
	Echiquier() {
        initialisation();
    }
		
	public final void initialisation(){
		
		interfaceJeu.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		plateauPanel = new JPanel(new GridLayout(0, 9));
		
		plateauPanel.setBorder(new LineBorder(Color.BLACK));
        interfaceJeu.add(plateauPanel);
        
        // - INITIALISATION DES BOUTONS - //
        
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < plateauBouton.length; ii++) {
            for (int jj = 0; jj < plateauBouton[ii].length; jj++) {
				Piece p = null;
                MonBouton b = new MonBouton(ii,jj,p);
                b.addActionListener(new EcouteurBouton(this,b)) ;
                b.setMargin(buttonMargin);
                // Les pièces de notre échequier feront 64*64 pixel donc on rempli
                // les cases (boutons) de notre plateau par des images vides
                ImageIcon icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.GRAY);
                }
                plateauBouton[jj][ii] = b;
            }
       }
       
       
      
       // - INITIALISATION PLATEAU AVEC PIECES - //
       
       plateauBouton[0][0].setPiece(new Tour("Noir",0,0));
	   plateauBouton[1][0].setPiece(new Cavalier("Noir",0,1));
       plateauBouton[2][0].setPiece(new Fou("Noir",0,2));
       plateauBouton[3][0].setPiece(new Dame("Noir",0,3));
       plateauBouton[4][0].setPiece(new Roi("Noir",0,4));
       plateauBouton[5][0].setPiece(new Fou("Noir",0,5));
       plateauBouton[6][0].setPiece(new Cavalier("Noir",0,6));
       plateauBouton[7][0].setPiece(new Tour("Noir",0,7));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][1].setPiece(new Pion("Noir",0,i));
       } 
       
       plateauBouton[0][7].setPiece(new Tour("Blanc",7,0));
       plateauBouton[1][7].setPiece(new Cavalier("Blanc",7,1));
       plateauBouton[2][7].setPiece(new Fou("Blanc",7,2));
       plateauBouton[3][7].setPiece(new Dame("Blanc",7,3));
       plateauBouton[4][7].setPiece(new Roi("Blanc",7,4));
       plateauBouton[5][7].setPiece(new Fou("Blanc",7,5));
       plateauBouton[6][7].setPiece(new Cavalier("Blanc",7,6));
       plateauBouton[7][7].setPiece(new Tour("Blanc",7,7));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][6].setPiece(new Pion("Blanc",6,i));
       }
       
       // On rempli le plateau
		plateauPanel.add(new JLabel(""));
        // On commence par la ligne du haut
        for (int ii = 0; ii < 8; ii++) {
            plateauPanel.add(
                    new JLabel(COLS.substring(ii, ii + 1),SwingConstants.CENTER));
        }
        // Et maitenant l'autre ligne
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        plateauPanel.add(new JLabel("" + (ii + 1),SwingConstants.CENTER));
                    default:
                        plateauPanel.add(plateauBouton[jj][ii]);
                }
            }
        }
		
	
		
		
		
	}
	
	
	
	public final JComponent getInterfaceJeu() {
        return interfaceJeu;
    }
	
	// LANCEMENT DE L'INTERFACE ET DU JEU //
	
	public static void main(String[] args) {
        Runnable r = new Runnable() {
        
			@Override
				public void run() {
					Echiquier e = new Echiquier();

					JFrame f = new JFrame("Jeu d'echecs");
					f.add(e.getInterfaceJeu());
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					f.setLocationByPlatform(true);
					f.setSize(750,750);
					
					f.setMinimumSize(f.getSize());
					f.setVisible(true);
				}
		};
		SwingUtilities.invokeLater(r);
		
	}
	
	// MISE EN MEMOIRE PREMIER CLIC SUR BOUTON //
		
	public void memoirePositionDepart(int x, int y){
			departL = x;
			departC = y;
	}
	
	// MISE EN MEMOIRE DEUXIEME CLIC SUR BOUTON //
	
	public void memoirePositionArrivee(int x, int y){
			arriveeL = x;
			arriveeC = y;
	}
	
	// MISE EN MEMOIRE PIECE PREMIER BOUTON //
	
	public void memoirePieceDepart(Piece P){
			this.pieceDepart = P;
	}
	
	// MISE EN MEMOIRE PIECE SECOND BOUTON //
	
	public void memoirePieceArrivee(Piece P){
			this.pieceArrivee = P;
	}
	
	// GETTER DEPART / ARRIVEE //
	
	public int getDepartC(){
		return this.departC;
	}		
		
	public int getDepartL(){
		return this.departL;
	}
	
	public int getArriveeC(){
		return this.arriveeC;
	}	
	
	public int getArriveeL(){
		return this.arriveeL;
	}			
	
	public MonBouton[][] getPlateau(){
		return this.plateauBouton;
	}
	
	// DEPLACEMENT DE LA PIECE A PARTIR DU BOUTON DE DEPART VERS CELUI DONT ON A CLIQUER UNE SECONDE FOIS // 
	// ATTENTION PLATEAU BOUTON [COLONNE][LIGNE] ET NON PAS [LIGNE][COLONNE] //
	
	public void deplacementPiece(int x, int y){
		
		
	if(pieceDepart instanceof interfaceValidite){
		
		if(((interfaceValidite) pieceDepart).deplacementValid(x, y)){
			this.plateauBouton[departC][departL].setPiece(null);
			this.plateauBouton[y][x].setPiece(pieceDepart);
		} 
	}	
	
		
	} 
				
   

   
   
}

