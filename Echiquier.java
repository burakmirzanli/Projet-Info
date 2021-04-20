 
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import java.lang.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;



public class Echiquier {
	
	// Attributs //
	
	private JPanel interfaceJeu = new JPanel(new BorderLayout(3, 3));

	private static MonBouton[][] plateauBouton = new MonBouton [8][8];
	private JPanel plateauPanel = new JPanel();
	private static final String COLS = "ABCDEFGH";
	
	private static int departL;
	private static int departC;
    private static int arriveeL;
	private static int arriveeC;
	
	private Piece pieceDepart;
	private Piece pieceArrivee;
    //
	private int ligneBlanc;
    private String colonneBlanc;
    //
	private int compteurBouton;
	
	private int compteurBoutonJoueur=0;

	private Piece piece;
    
	//INITIALISATION HISTORIQUE BLANC
    public class HistoriqueBlanc {
    private String colonneBlanc;
    private int ligneBlanc;
    public HistoriqueBlanc(int l, String c) {
    colonneBlanc=c;
    ligneBlanc=l;
    }
    public String toString (){
		String s = ligneBlanc+""+colonneBlanc+" ";
		return s;
	}
    }
    ArrayList<HistoriqueBlanc> listeBlanc = new ArrayList<HistoriqueBlanc>();
    
    //INITIALISATION HISTORIQUE NOIR
    public class HistoriqueNoir {
    private String colonneNoir;
    private int ligneNoir;
    public HistoriqueNoir(int l, String c) {
    colonneNoir=c;
    ligneNoir=l;
    }
    public String toString (){
		String s = ligneNoir+""+colonneNoir+" ";
		return s;
	}
    }
    ArrayList<HistoriqueNoir> listeNoir = new ArrayList<HistoriqueNoir>();
    //
    
	private static int heureB=0, minuteB=0, secondeB=0;
    private static int heureN=0, minuteN=0, secondeN=0;
    
    private static final JLabel tpsB = new JLabel(heureB+":"+minuteB+":"+secondeB);
	private static final JLabel tpsN = new JLabel(heureN+":"+minuteN+":"+secondeN);
   
	
	
	public ActionListener tache_timerB = new ActionListener()
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
		
	public ActionListener tache_timerN= new ActionListener()
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
	 
	private int delais=1000;
		
	private Timer timerB= new Timer(delais,tache_timerB);
	private Timer timerN= new Timer(delais,tache_timerN);
	
	
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
       
       plateauBouton[0][0].setPiece(new Tour("Noir",0,0,this));
	   plateauBouton[1][0].setPiece(new Cavalier("Noir",0,1,this));
       plateauBouton[2][0].setPiece(new Fou("Noir",0,2,this));
       plateauBouton[3][0].setPiece(new Dame("Noir",0,3,this));
       plateauBouton[4][0].setPiece(new Roi("Noir",0,4,this));
       plateauBouton[5][0].setPiece(new Fou("Noir",0,5,this));
       plateauBouton[6][0].setPiece(new Cavalier("Noir",0,6,this));
       plateauBouton[7][0].setPiece(new Tour("Noir",0,7,this));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][1].setPiece(new Pion("Noir",0,i,this));
       } 
       
       plateauBouton[0][7].setPiece(new Tour("Blanc",7,0,this));
       plateauBouton[1][7].setPiece(new Cavalier("Blanc",7,1,this));
       plateauBouton[2][7].setPiece(new Fou("Blanc",7,2,this));
       plateauBouton[3][7].setPiece(new Dame("Blanc",7,3,this));
       plateauBouton[4][7].setPiece(new Roi("Blanc",7,4,this));
       plateauBouton[5][7].setPiece(new Fou("Blanc",7,5,this));
       plateauBouton[6][7].setPiece(new Cavalier("Blanc",7,6,this));
       plateauBouton[7][7].setPiece(new Tour("Blanc",7,7,this));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][6].setPiece(new Pion("Blanc",6,i,this));
       }
       
       
       // - CREATION DU PLATEAU GRAPHIQUEMENT - //
       
	   plateauPanel.add(new JLabel(""));
        for (int ii = 0; ii < 8; ii++) {
            plateauPanel.add(new JLabel(COLS.substring(ii, ii + 1),SwingConstants.CENTER));
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
					JButton boutonLancer = new JButton ("Lancer Jeu");
					boutonReset.addActionListener(new EcouteurBoutonReset(e)) ;
					boutonLancer.addActionListener(new EcouteurBoutonLancer(e));
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
					bandeauHaut.add(boutonLancer);
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

	   plateauBouton[0][0].setPiece(new Tour("Noir",0,0,this));
	   plateauBouton[1][0].setPiece(new Cavalier("Noir",0,1,this));
       plateauBouton[2][0].setPiece(new Fou("Noir",0,2,this));
       plateauBouton[3][0].setPiece(new Dame("Noir",0,3,this));
       plateauBouton[4][0].setPiece(new Roi("Noir",0,4,this));
       plateauBouton[5][0].setPiece(new Fou("Noir",0,5,this));
       plateauBouton[6][0].setPiece(new Cavalier("Noir",0,6,this));
       plateauBouton[7][0].setPiece(new Tour("Noir",0,7,this));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][1].setPiece(new Pion("Noir",0,i,this));
       } 
       
       plateauBouton[0][7].setPiece(new Tour("Blanc",7,0,this));
       plateauBouton[1][7].setPiece(new Cavalier("Blanc",7,1,this));
       plateauBouton[2][7].setPiece(new Fou("Blanc",7,2,this));
       plateauBouton[3][7].setPiece(new Dame("Blanc",7,3,this));
       plateauBouton[4][7].setPiece(new Roi("Blanc",7,4,this));
       plateauBouton[5][7].setPiece(new Fou("Blanc",7,5,this));
       plateauBouton[6][7].setPiece(new Cavalier("Blanc",7,6,this));
       plateauBouton[7][7].setPiece(new Tour("Blanc",7,7,this));
       
       for(int i =0; i<8;i++){
		plateauBouton[i][6].setPiece(new Pion("Blanc",6,i,this));
       }
       
       compteurBouton=0;
       compteurBoutonJoueur=0;
       
       timerB.stop();
       timerN.stop();
       
       heureB=0;
       minuteB=0;
       secondeB=0;
       
       heureN=0;
       minuteN=0;
       secondeN=0;
       
       tpsB.setText(heureB+":"+minuteB+":"+secondeB);
       tpsN.setText(heureN+":"+minuteN+":"+secondeN);
       
       
	    
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
	
	// - GETTERS - //
	
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
	
	public MonBouton getBoutonPlateau(int C, int L){
		return this.plateauBouton[C][L];
	}
		
	public final JComponent getInterfaceJeu() {
        return interfaceJeu;
    }
	
	public void startTpsB(){
		timerB.start();
	}
	
	// - METHODE COMPTEUR BOUTON CLIC ET BOUTON JOUEUR - //
	
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
		
		if(pieceDepart.getCouleur() == "Blanc" && compteurBoutonJoueur % 2 == 0){		
			couleurValid = true;
		}else if(pieceDepart.getCouleur() == "Noir" && compteurBoutonJoueur % 2 == 1){
			couleurValid = true;
			
		}else{
			couleurValid = false;
			System.out.println("Ce n'est pas le tour de cette couleur");
            
		}
		
    
		
		if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee==null && couleurValid == true){
			(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
			(plateauBouton[departC][departL]).setPiece(null);
			
			// - LE DEPLACEMENT EST VALIDE CHANGEMENT DE JOUEUR - PAIR = BLANC IMPAIR = NOIR - //
			
			compteurBoutonJoueur++; 
			
			if(compteurBoutonJoueur % 2 == 1){
				timerN.start();
				timerB.stop();
			}else if(compteurBoutonJoueur % 2 == 0){
				timerB.start();
				timerN.stop();
			}			
			
			if(pieceDepart.getType() == "Pion" ){
				promotionPion(plateauBouton[arriveeC][arriveeL]);
			}
				
		}
		
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee!=null  && pieceDepart.getCouleur()!=pieceArrivee.getCouleur() && couleurValid == true){
			(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
			(plateauBouton[departC][departL]).setPiece(null);
			
			// - LE DEPLACEMENT EST VALIDE CHANGEMENT DE JOUEUR - PAIR = BLANC IMPAIR = NOIR - //
			
			compteurBoutonJoueur++; 
			
			if(compteurBoutonJoueur % 2 == 1){
				timerN.start();
				timerB.stop();
			}else if(compteurBoutonJoueur % 2 == 0){
				timerB.start();
				timerN.stop();
			}			
			
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
	//HISTORIQUE
   
    if (pieceDepart.getCouleur()== "Blanc"){
        System.out.print("Historique Blanc: ");
     if (getArriveeC()==0){
         
        HistoriqueBlanc HB0=new HistoriqueBlanc(getArriveeL()+1, "A");
        listeBlanc.add(HB0);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.println(deplacement);}
        }else if(getArriveeC()==1){
        HistoriqueBlanc HB1=new HistoriqueBlanc(getArriveeL()+1, "B");
        listeBlanc.add(HB1);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if(getArriveeC()==2){
        HistoriqueBlanc HB2=new HistoriqueBlanc(getArriveeL()+1, "C");
        listeBlanc.add(HB2);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if(getArriveeC()==3){	 
        HistoriqueBlanc HB3=new HistoriqueBlanc(getArriveeL()+1, "D");
        listeBlanc.add(HB3);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==4){
         HistoriqueBlanc HB4=new HistoriqueBlanc(getArriveeL()+1, "E");
        listeBlanc.add(HB4);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==5){
        HistoriqueBlanc HB5=new HistoriqueBlanc(getArriveeL()+1, "F");
        listeBlanc.add(HB5);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==6){
         HistoriqueBlanc HB6=new HistoriqueBlanc(getArriveeL()+1, "G");
        listeBlanc.add(HB6);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==7){
         HistoriqueBlanc HB7=new HistoriqueBlanc(getArriveeL()+1, "H");
        listeBlanc.add(HB7);
        for (HistoriqueBlanc deplacement : listeBlanc) {
        System.out.print(deplacement);}

        } 
        System.out.println();  
    }
    if (pieceDepart.getCouleur()== "Noir"){
        System.out.print("Historique Noir: ");
     if (getArriveeC()==0){
         
        HistoriqueNoir HN0=new HistoriqueNoir(getArriveeL()+1, "A");
        listeNoir.add(HN0);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.println(deplacement);}
        }else if(getArriveeC()==1){
        HistoriqueNoir HN1=new HistoriqueNoir(getArriveeL()+1, "B");
        listeNoir.add(HN1);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if(getArriveeC()==2){
        HistoriqueNoir HN2=new HistoriqueNoir(getArriveeL()+1, "C");
        listeNoir.add(HN2);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if(getArriveeC()==3){	 
        HistoriqueNoir HN3=new HistoriqueNoir(getArriveeL()+1, "D");
        listeNoir.add(HN3);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==4){
         HistoriqueNoir HN4=new HistoriqueNoir(getArriveeL()+1, "E");
        listeNoir.add(HN4);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==5){
        HistoriqueNoir HN5=new HistoriqueNoir(getArriveeL()+1, "F");
        listeNoir.add(HN5);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==6){
         HistoriqueNoir HN6=new HistoriqueNoir(getArriveeL()+1, "G");
        listeNoir.add(HN6);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}
        }else if (getArriveeC()==7){
         HistoriqueNoir HN7=new HistoriqueNoir(getArriveeL()+1, "H");
        listeNoir.add(HN7);
        for (HistoriqueNoir deplacement : listeNoir) {
        System.out.print(deplacement);}

        } 
        System.out.println();  
}		
}	
	// - PROMOTION D'UN PION ARRIVANT AU BOUT DU PLATEAU EN DAME - //

	public void promotionPion(MonBouton b){
	
		if(b.getL() == 0 ||	b.getL() == 7){
		
			if((b.getPiece()).getCouleur()=="Blanc"){ // - TEST COULEUR PIECE - //
				
				b.setPiece(new Dame("Blanc",b.getL(),b.getC(),this)); // - PROMOTION EN DAME BLANCHE - //
				
			} else if((b.getPiece()).getCouleur()=="Noir"){ // - TEST COULEUR PIECE - //
				
				b.setPiece(new Dame("Noir",b.getL(),b.getC(),this)); // - PROMOTION EN DAME NOIR - //
				
			}
		}
	
	}
	
	/* public JLabel getTpsB(){
		return this.tpsB;
	}
	
	public JLabel getTpsN(){
		return this.tpsN;
	}
	
	public void startTpsB(){
		timerB.start();
	}
	
	public void startTpsN(){
		timerN.start();
	}
	
	public void stopTpsB(){
		timerB.stop();
	}
	
	public void stopTpsN(){
		timerN.start();
	} */

   
}
