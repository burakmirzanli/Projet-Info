  
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;



public class Echiquier {
	
	// Attributs //
	
	private JPanel interfaceJeu = new JPanel(new BorderLayout(3, 3));

	public static MonBouton[][] plateauBouton = new MonBouton [8][8];
	private JPanel plateauPanel = new JPanel();
	public static final String COLS = "ABCDEFGH";
	
	public static int departL;
	public static int departC;
    public static int arriveeL;
	public static int arriveeC;
	
	public Piece pieceDepart;
	public Piece pieceArrivee;
	
	public int compteurBouton;
	
	public int compteurBoutonJoueur=0;

	public Piece piece;
	
	
	public static final int delais=1000;
	public static ActionListener tache_timerB;
	public static ActionListener tache_timerN;
    public static int heureB=0, minuteB=0, secondeB=0;
    public static int heureN=0, minuteN=0, secondeN=0;
    public static final JLabel tpsB = new JLabel(heureB+":"+minuteB+":"+secondeB);
	public static final JLabel tpsN = new JLabel(heureN+":"+minuteN+":"+secondeN);
	private Timer timerB = new Timer(delais, tache_timerB);
	private Timer timerN = new Timer(delais, tache_timerN);
	
	
	Echiquier() {
        initialisation();
    }
		
	public final void initialisation(){
		
		// - INITIALISATION EMPLACEMENT PLATEAU - //
		
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
                ImageIcon icon = new ImageIcon(new BufferedImage(64,64, BufferedImage.TYPE_INT_ARGB));
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
       
       
       // - CREATION DU PLATEAU GRAPHIQUEMENT - //
       
	   plateauPanel.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            plateauPanel.add(
                    new JLabel(COLS.substring(ii, ii + 1),SwingConstants.CENTER));
        }
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
        
        
        /*
        // - CREATION DU CHRONOMETRE - //
		// - ACTION REALISER PAR LE TIMER BLANC - //
		tache_timerB= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeB++;
				if(secondeB==60)
				{
					secondeB=0;
					minuteB++;
				}
				if(minuteB==60)
				{
					minuteB=0;
					heureB++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsB.setText(heureB+":"+minuteB+":"+secondeB);
				
			}
		};
		// - ACTION REALISER PAR LE TIMER NOIR - //
		tache_timerN= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeN++;
				if(secondeN==60)
				{
					secondeN=0;
					minuteN++;
				}
				if(minuteN==60)
				{
					minuteN=0;
					heureN++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsN.setText(heureN+":"+minuteN+":"+secondeN);
				
			}
		};
		
	
		
		// - INSTANCIATION DES TIMER - //
		
		Timer timerB= new Timer(delais,tache_timerB);
		Timer timerN= new Timer(delais,tache_timerN);
		timerB.start();
		timerN.start();*/
		
	}
	
	// - LANCEMENT DE L'INTERFACE ET DU JEU - //
	
	public static void main(String[] args) {
        Runnable r = new Runnable() {
        
			@Override
				public void run() {
					Echiquier e = new Echiquier();
					
					JFrame f = new JFrame("Jeu d'echecs");
					f.setLayout(new BorderLayout());
					f.add(e.getInterfaceJeu(),BorderLayout.CENTER);
					
					
					JPanel bandeauHaut = new JPanel(new FlowLayout());
					
					
					//bandeauHaut.setBackground(new Color(116,78,69));
					
					JButton boutonReset = new JButton("Renitialisation");
					boutonReset.addActionListener(new EcouteurBoutonReset(e,boutonReset)) ;
					JLabel tpsRestB = new JLabel("Temps restant blancs");
					JLabel tpsRestN = new JLabel("Temps restant noirs");
					// JTextField tpsB = new JTextField(15);
					// JTextField tpsN = new JTextField(15);
					
				
					bandeauHaut.setBorder(new EmptyBorder(20,20,20,20));
					bandeauHaut.add(new JLabel("                       "));
					bandeauHaut.add(tpsRestB);
					bandeauHaut.add(tpsB);
					bandeauHaut.add(tpsRestN);
					bandeauHaut.add(tpsN);
					bandeauHaut.add(new JLabel("                             "));
					bandeauHaut.add(boutonReset);
					boutonReset.setBackground(Color.ORANGE);
					
					f.add(bandeauHaut, BorderLayout.NORTH);
					
					
					
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					f.setLocationByPlatform(true);
					f.setSize(1000,1000);
					
					f.setMinimumSize(f.getSize());
					f.setVisible(true);
				}
		};
		SwingUtilities.invokeLater(r);
		
	}
	
	// - RENITIALISATION ECHIQUIER - //
	public void renitialisationEchiquier(){
		for(int i=0; i<8;i++){
		   for(int j=0; j<8; j++){
			   plateauBouton[i][j].setPiece(null);
		   }
	   }

	   plateauBouton[0][0].setPiece(new Tour("Noir",0,0));
	   plateauBouton[1][0].setPiece(new Cavalier("Noir",0,1));
       plateauBouton[2][0].setPiece(new Fou("Noir",0,2));
       plateauBouton[3][0].setPiece(new Dame("Noir",0,3));
       plateauBouton[4][0].setPiece(new Roi("Noir",0,4));
       plateauBouton[5][0].setPiece(new Fou("Noir",0,5));
       plateauBouton[6][0].setPiece(new Cavalier("Noir",0,6));
       plateauBouton[7][0].setPiece(new Tour("Noir",0,7));
       
       for(int i =0; i<8;i++){
		Echiquier.plateauBouton[i][1].setPiece(new Pion("Noir",0,i));
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
       
       compteurBouton=0;
       compteurBoutonJoueur=0;
	    
	}
	
	// - MISE EN MEMOIRE PREMIER CLIC SUR BOUTON - //
		
	public void memoirePositionDepart(int x, int y){
			departL = x;
			departC = y;
	}
	
	// - MISE EN MEMOIRE DEUXIEME CLIC SUR BOUTON - //
	
	public void memoirePositionArrivee(int x, int y){
			arriveeL = x;
			arriveeC = y;
	}
	
	// - MISE EN MEMOIRE PIECE PREMIER BOUTON - //
	
	public void memoirePieceDepart(Piece P){
			this.pieceDepart = P;
	}
	
	// - MISE EN MEMOIRE PIECE SECOND BOUTON - //
	
	public void memoirePieceArrivee(Piece P){
			this.pieceArrivee = P;
	}
	
	// - GETTER DEPART / ARRIVEE - //
	
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
		
	public final JComponent getInterfaceJeu() {
        return interfaceJeu;
    }
	
	public int getCompteurBouton(){
		return this.compteurBouton;
	}
	
	public int getCompteurBoutonJoueur(){
		return this.compteurBoutonJoueur;
	}
	
	
	
	public void CompteurBoutonIncrements(){
		compteurBouton++;
	}	
	
	// - DEPLACEMENT DE LA PIECE A PARTIR DU BOUTON DE DEPART VERS CELUI DONT ON A CLIQUER UNE SECONDE FOIS  - // 
	// - ATTENTION PLATEAU BOUTON [COLONNE][LIGNE] ET NON PAS [LIGNE][COLONNE] - //
	
				
   	public void deplacementPiece(int departL, int departC, int arriveeL, int arriveeC){
		boolean couleurValid = false;
		tache_timerB= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeB++;
				if(secondeB==60)
				{
					secondeB=0;
					minuteB++;
				}
				if(minuteB==60)
				{
					minuteB=0;
					heureB++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsB.setText(heureB+":"+minuteB+":"+secondeB);
				
			}
		};
		// - ACTION REALISER PAR LE TIMER NOIR - //
		tache_timerN= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeN++;
				if(secondeN==60)
				{
					secondeN=0;
					minuteN++;
				}
				if(minuteN==60)
				{
					minuteN=0;
					heureN++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsN.setText(heureN+":"+minuteN+":"+secondeN);
				
			}
		};
		
		Timer timerB= new Timer(delais,tache_timerB);
		Timer timerN= new Timer(delais,tache_timerN);
		
		if(pieceDepart.getCouleur() == "Blanc" && compteurBoutonJoueur % 2 == 0){		
			couleurValid = true;
			timerB.start();
		}else if(pieceDepart.getCouleur() == "Noir" && compteurBoutonJoueur % 2 == 1){
			couleurValid = true;
			timerN.start();
			
		}else{
			couleurValid = false;
			System.out.println("Ce n'est pas le tour de cette couleur");
		}
		
		
		
		if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee==null && couleurValid == true){
			(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
			(plateauBouton[departC][departL]).setPiece(null);
			compteurBoutonJoueur++;
			if(pieceDepart.getType() == "Pion" ){
				promotionPion(plateauBouton[arriveeC][arriveeL]);
			}
				
		}
		
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee!=null  && pieceDepart.getCouleur()!=pieceArrivee.getCouleur() && couleurValid == true){
			(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
			(plateauBouton[departC][departL]).setPiece(null);
			compteurBoutonJoueur++;
			if(pieceDepart.getType() == "Pion"){
				promotionPion(plateauBouton[arriveeC][arriveeL]);
			}

		}
			
			
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee!=null  && pieceDepart.getCouleur()==pieceArrivee.getCouleur() && couleurValid == true){
			System.out.println("Déplacement impossible ! Choisis une autre case");
		}
			
			
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==false && couleurValid == true){
			System.out.println("Déplacement impossible ! Choisis une autre case");
		} 
		
	}
			
			
		
	// - PROMOTION D'UN PION ARRIVANT AU BOUT DU PLATEAU EN DAME - //

	public void promotionPion(MonBouton b){
	
		if(b.getL() == 0 ||	b.getL() == 7){
		
			if((b.getPiece()).getCouleur()=="Blanc"){ // - TEST COULEUR PIECE - //
				
				b.setPiece(new Dame("Blanc",b.getL(),b.getC())); // - PROMOTION EN DAME BLANCHE - //
				
			} else if((b.getPiece()).getCouleur()=="Noir"){ // - TEST COULEUR PIECE - //
				
				b.setPiece(new Dame("Noir",b.getL(),b.getC())); // - PROMOTION EN DAME NOIR - //
				
			}
		}
	
	}
	
    public void resetTimerB(){
	    timerB.restart();
    }
    
    public void resetTimerN(){
		timerN.restart();
	}
   
}
