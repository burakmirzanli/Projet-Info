import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.border.*;
import java.lang.*;
import javax.swing.border.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.ArrayList;



public class Echiquier {
	
	// - ATTRIBUTS INTERFACE - //
	
	private JPanel interfaceJeu = new JPanel(new BorderLayout(3, 3));

	private static MonBouton[][] plateauBouton = new MonBouton [8][8];
	private JPanel plateauPanel = new JPanel();
	private static JPanel bandeauBas = new JPanel(new GridLayout());
	
	private static final String COLS = "ABCDEFGH";
	
	private static EcouteurBoutonLancer eL;
	
	private static int heureB=0, minuteB=20, secondeB=0;
    private static int heureN=0, minuteN=20, secondeN=0;
    
    private static final JLabel tpsB = new JLabel(heureB+":"+minuteB+":"+secondeB);
	private static final JLabel tpsN = new JLabel(heureN+":"+minuteN+":"+secondeN);
	
	private static JLabel infoVictoire = new JLabel("                    ");
	
	private static Color couleurB = new Color(255,206,158);
	
	private static Color couleurN = new Color(209,139,71);
	
	// - ATTRIBUTS PIECE ET MOUVEMENT - //
	
	private static int departL;
	private static int departC;
    private static int arriveeL;
	private static int arriveeC;
	
	private Piece pieceDepart;
	private Piece pieceArrivee;
   
	private int compteurBouton;
	
	private int compteurBoutonJoueur=0;

	private Piece piece;
	
	private boolean roqueValid=false;
			
	// - ATTRIBUTS LIST HISTORIQUE - //
	
	private ArrayList<Piece[][]> listeHistoriquePlateau = new ArrayList<Piece[][]>();
	
	private ArrayList<Integer> listeHistoriqueTimerSecB = new ArrayList<Integer>() ;
	
	private ArrayList<Integer> listeHistoriqueTimerMinB = new ArrayList<Integer>() ;
	
	private ArrayList<Integer>	listeHistoriqueTimerHeuB = new ArrayList<Integer>() ;
	
	private ArrayList<Integer> listeHistoriqueTimerSecN = new ArrayList<Integer>() ;
	
	private ArrayList<Integer> listeHistoriqueTimerMinN = new ArrayList<Integer>() ;
	
	private ArrayList<Integer>	listeHistoriqueTimerHeuN = new ArrayList<Integer>() ;
	
	private ArrayList<String> listeHistoriqueString = new ArrayList<String>();
	
	private int compteurHistorique = 0;
	
	// - ATTRIBUTS CREATION CHRONOMETRE - //
	
	public ActionListener tache_timerB = new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeB--;
				if(secondeB==-1)
				{
					secondeB=59;
					minuteB--;
				}
				if(minuteB==-1)
				{
					minuteB=0;
					//heureB++;
					secondeB--;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsB.setText(heureB+":"+minuteB+":"+secondeB);
				
			}
		};
		
	public ActionListener tache_timerN= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeN--;
				if(secondeN==-1)
				{
					secondeN=59;
					minuteN--;
				}
				if(minuteN==-1)
				{
					minuteN=0;
					//heureB++;
					secondeN--;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsN.setText(heureN+":"+minuteN+":"+secondeN);
				
			}
		};
	 
	private int delais=1000;
		
	private Timer timerB= new Timer(delais,tache_timerB);
	private Timer timerN= new Timer(delais,tache_timerN);
	
	// - ECHIQUIER - //

	Echiquier() {
        initialisation();
    }
		
	public final void initialisation(){
		
		// - INITIALISATION EMPLACEMENT PLATEAU - //
		
		interfaceJeu.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		plateauPanel = new JPanel(new GridLayout(0, 9));
		
        interfaceJeu.add(plateauPanel);
        
        // - INITIALISATION DES BOUTONS - //
        
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < plateauBouton.length; ii++) {
            for (int jj = 0; jj < plateauBouton[ii].length; jj++) {
				Piece p = null;
                MonBouton b = new MonBouton(ii,jj,p);
                b.addActionListener(new EcouteurBouton(this,b)) ;
                b.setMargin(buttonMargin);
                
                // - CREATION DES CASES DU PLATEAU PAR DES CASES VIDES - //
                
                ImageIcon icon = new ImageIcon(new BufferedImage(64,64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                
                // - REMPLISSAGE COULEUR DES CASES - //
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(couleurB);
                } else {
                    b.setBackground(couleurN);
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
       
       
       // - CREATION DES COLONNES 1-8 ET A-H ET DU PLATEAU - //
       
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
					
					// - CREATION DE LA FENETRE - //
					
					JFrame f = new JFrame("Jeu d'echecs");
					
					f.setLayout(new BorderLayout());
					f.setBackground(Color.BLACK);
					f.add(e.getInterfaceJeu(),BorderLayout.CENTER);
					
					// - CREATION BANDEAU HAUT AVEC BOUTON LANCER-RESET ET TIMER  - //
					
					JPanel bandeauHaut = new JPanel(new FlowLayout());
					
					bandeauHaut.setBackground(couleurN);
					
					JButton boutonReset = new JButton("Reinitialisation");
					JButton boutonLancer = new JButton ("Lancer Jeu");
					
				
					boutonReset.setBorder(new EmptyBorder(5,5,5,5));
					boutonLancer.setBorder(new EmptyBorder(5,5,5,5));
					
					
					boutonReset.addActionListener(new EcouteurBoutonReset(e)) ;
					eL=new EcouteurBoutonLancer(e);
					boutonLancer.addActionListener(eL);
					
					JLabel tpsRestB = new JLabel("Temps restant blancs");
					JLabel tpsRestN = new JLabel("Temps restant noirs");
					
					tpsRestB.setForeground(Color.WHITE);
					tpsRestN.setForeground(Color.WHITE);

					
					bandeauHaut.setBorder(new EmptyBorder(20,20,20,20));
					bandeauHaut.add(new JLabel("                       "));
					
					bandeauHaut.add(boutonLancer);
					bandeauHaut.add(boutonReset);
					
					bandeauHaut.add(new JLabel("                             "));
					
					bandeauHaut.add(tpsRestB);
					bandeauHaut.add(tpsB);
					bandeauHaut.add(tpsRestN);
					bandeauHaut.add(tpsN);
					
					JLabel infoVictoireTexte = new JLabel("Information sur la partie : ");
					infoVictoireTexte.setForeground(Color.WHITE);
					
					bandeauHaut.add(infoVictoireTexte);
					bandeauHaut.add(infoVictoire);
					
					boutonReset.setBackground(Color.WHITE);
					boutonLancer.setBackground(Color.WHITE);
					
					// - AJOUT DU BANDEAU DU BAS QUI SERVIRA DE PANEL POUR LES BOUTONS DE L'HISTORIQUE - //
					
					bandeauBas.setBorder(new EmptyBorder(10, 10, 10, 10));
					
					f.add(bandeauHaut, BorderLayout.NORTH);
					f.add(bandeauBas, BorderLayout.SOUTH);

					
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
		
		// - ON COMMENCE PAR VIDER L'ECHIQUIER - //
		
		for(int i=0; i<8;i++){
		   for(int j=0; j<8; j++){
			   plateauBouton[i][j].setPiece(null);
		   }
	   }
		
	   // - ON INITIALISE LES PIECES - //
	   
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
       
       // - ON RENITIALISE LES COMPTEURS ET LES TEMPS - //
       
       compteurBouton=0;
       compteurBoutonJoueur=0;
       
       timerB.stop();
       timerN.stop();
       
       heureB=0;
       minuteB=20;
       secondeB=0;
       
       heureN=0;
       minuteN=20;
       secondeN=0;
       
       tpsB.setText(heureB+":"+minuteB+":"+secondeB);
       tpsN.setText(heureN+":"+minuteN+":"+secondeN);
		
	   // - ON RENITIALISE LES BOUTONS HISTORIQUE - //
	   
	   bandeauBas.removeAll();
       eL.setF(false);
       
       this.setCouleurComplet();
       
	    
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
	
	// - SETTERS - //
	
	public void setCouleurCaseVert(int l, int c){
		this.plateauBouton[c][l].setBackground(new Color(135, 233, 144));
	}
	
	public void setCouleurComplet(){
		
		// - METHODE QUI PERMET DE RE-PEINDRE LE PLATEAU EN "NOIR" ET "BLANC" - //
		
		for (int ii = 0; ii < plateauBouton.length; ii++) {
			for (int jj = 0; jj < plateauBouton[ii].length; jj++) {
				if ((jj % 2 == 1 && ii % 2 == 1)
                       //) {
						|| (jj % 2 == 0 && ii % 2 == 0)) {
						plateauBouton[jj][ii].setBackground(couleurB);
				} else {
					plateauBouton[jj][ii].setBackground(couleurN);
				}
			}
		}
	}
	
	public void setCompteurBoutonJoueur(int i){
		this.compteurBoutonJoueur=i;
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
	
	public EcouteurBoutonLancer getEcout(){
		return eL;
	}
	
	public int getCompteurHisto(){
		return this.compteurHistorique;
	}
	
	public Piece getPieceDep(){
		return this.pieceDepart;
	}
	
	public Piece getPieceArr(){
		return this.pieceArrivee;
	}
	
	// - TIMER - //
	
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
	
	
	// - METHODE DEPLACETEMENT PIECE AVEC TEST DE LA VALIDITE ET ECHEC - //
	// - DEPLACEMENT DE LA PIECE A PARTIR DU BOUTON DE DEPART VERS CELUI DONT ON A CLIQUER UNE SECONDE FOIS  - // 
	
				
   	public void deplacementPiece(int departL, int departC, int arriveeL, int arriveeC){		

		boolean couleurValid = false;
		
		// - ON COMMENCE PAR TESTER SI C'EST BIEN LE TOUR DE LA COULEUR - //
		
		if(pieceDepart.getCouleur() == "Blanc" && compteurBoutonJoueur % 2 == 0){		
			couleurValid = true;
		}else if(pieceDepart.getCouleur() == "Noir" && compteurBoutonJoueur % 2 == 1){
			couleurValid = true;
			
		}else{
			couleurValid = false;
			System.out.println("Ce n'est pas le tour de cette couleur");
            
		}
		
		// - TEST DE POSSIBILITE DE DEPLACEMENT D'UNE PIECE DANS UNE CASE VIDE - //
		// - SI TEST VALIDE, DEPLACEMENT DE LA PIECE - //
		
		if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee==null && couleurValid == true){
			if ((pieceDepart.getType().equals("Roi") && pieceDepart.getCouleur().equals("Noir") && !testEchecNoir(arriveeC, arriveeL)) || (pieceDepart.getType().equals("Roi") && pieceDepart.getCouleur().equals("Blanc") && !testEchecBlanc(arriveeC, arriveeL)) || !pieceDepart.getType().equals("Roi")){
				(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
				(plateauBouton[departC][departL]).setPiece(null);
				
				if(pieceDepart.getType()=="Roi"){
					((Roi)pieceDepart).setRoiPositionDepart(false);
				}
				if(pieceDepart.getType()=="Tour"){
					((Tour)pieceDepart).setTourPositionDepart(false);
				}
				
				
				if (compteurBoutonJoueur % 2 == 1 && !testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir"))){ //condition de ne pas faire un auto-echec par une piece qui n'est pas le roi

			// - LE DEPLACEMENT EST VALIDE CHANGEMENT DE JOUEUR - PAIR = BLANC IMPAIR = NOIR - //
			
					compteurBoutonJoueur++; 
					augmentationListeHisto();
			
					if(compteurBoutonJoueur % 2 == 1){
						timerN.start();
						creationBoutonHistoriqueBlanc();
						compteurHistorique++;
						timerB.stop();
					}else if(compteurBoutonJoueur % 2 == 0){
						timerB.start();
						timerN.stop();
						creationBoutonHistoriqueNoir();
						compteurHistorique++;
					}			
			
					if(pieceDepart.getType() == "Pion" ){
						promotionPion(plateauBouton[arriveeC][arriveeL]);
					}
				
				}
				
				else if (compteurBoutonJoueur % 2 == 0 && !testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					
					compteurBoutonJoueur++;
					augmentationListeHisto();
			
					if(compteurBoutonJoueur % 2 == 1){
						timerN.start();
						timerB.stop();
						creationBoutonHistoriqueBlanc();
						compteurHistorique++;
					}else if(compteurBoutonJoueur % 2 == 0){
						timerB.start();
						timerN.stop();
						creationBoutonHistoriqueNoir();
						compteurHistorique++;
					}			
				
					if(pieceDepart.getType() == "Pion" ){
						promotionPion(plateauBouton[arriveeC][arriveeL]);
					}
				
				}
				
				else if ((compteurBoutonJoueur % 2 == 0 && testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))) || (compteurBoutonJoueur % 2 == 1 && testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					plateauBouton[departC][departL].setPiece(pieceDepart);
					plateauBouton[arriveeC][arriveeL].setPiece(null);
				}
				
				
			
			}
		}
		
		// - TEST DE POSSIBILITE DE DEPLACEMENT D'UNE PIECE DANS UNE CASE OCCUPEE PAR UNE PIECE DE L'AUTRE COULEUR - //
		// - SI TEST VALIDE, DEPLACEMENT DE LA PIECE - //
		
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee!=null  && pieceDepart.getCouleur()!=pieceArrivee.getCouleur() && couleurValid == true){
			if ((pieceDepart.getType().equals("Roi") && pieceDepart.getCouleur().equals("Noir") && !testEchecNoir(arriveeC, arriveeL)) || (pieceDepart.getType().equals("Roi") && pieceDepart.getCouleur().equals("Blanc") && !testEchecBlanc(arriveeC, arriveeL)) || !pieceDepart.getType().equals("Roi")){
				(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDepart);
				(plateauBouton[departC][departL]).setPiece(null);
				
				if(pieceDepart.getType()=="Roi"){
					((Roi)pieceDepart).setRoiPositionDepart(false);
				}
				if(pieceDepart.getType()=="Tour"){
					((Tour)pieceDepart).setTourPositionDepart(false);
				}
				
				
				if (compteurBoutonJoueur % 2 == 1 && !testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir"))){ //condition de ne pas faire un auto-echec par une piece qui n'est pas le roi

			// - LE DEPLACEMENT EST VALIDE CHANGEMENT DE JOUEUR - PAIR = BLANC IMPAIR = NOIR - //
			
					compteurBoutonJoueur++;
					augmentationListeHisto();
					
					if(compteurBoutonJoueur % 2 == 1){
						timerN.start();
						timerB.stop();
						creationBoutonHistoriqueBlanc();
						compteurHistorique++;
					}else if(compteurBoutonJoueur % 2 == 0){
						timerB.start();
						timerN.stop();
						creationBoutonHistoriqueNoir();
						compteurHistorique++;
					}			
			
					if(pieceDepart.getType() == "Pion" ){
						promotionPion(plateauBouton[arriveeC][arriveeL]);
					}
					
				
				}
				
				else if (compteurBoutonJoueur % 2 == 0 && !testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					
					compteurBoutonJoueur++;
					augmentationListeHisto();
			
					if(compteurBoutonJoueur % 2 == 1){
						timerN.start();
						timerB.stop();
						creationBoutonHistoriqueBlanc();
						compteurHistorique++;
					}else if(compteurBoutonJoueur % 2 == 0){
						timerB.start();
						timerN.stop();
						creationBoutonHistoriqueNoir();
						compteurHistorique++;
					}			
				
					if(pieceDepart.getType() == "Pion" ){
						promotionPion(plateauBouton[arriveeC][arriveeL]);
					}
				
				}
				
				else if ((compteurBoutonJoueur % 2 == 0 && testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))) || (compteurBoutonJoueur % 2 == 1 && testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					plateauBouton[departC][departL].setPiece(pieceDepart);
					plateauBouton[arriveeC][arriveeL].setPiece(pieceArrivee);
				}
				
				
			

			}
			
		}
		
		// - TEST DE POSSIBILITE DE DEPLACEMENT D'UNE PIECE DANS UNE CASE OCCUPEE PAR UNE PIECE DE LA MEME COULEUR - //
		// - SI TEST VALIDE, PAS DE DEPLACEMENT DE LA PIECE - //
		
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceArrivee!=null  && pieceDepart.getCouleur()==pieceArrivee.getCouleur() && couleurValid == true){
			System.out.println("Déplacement impossible ! Choisis une autre case");
		}
		
		// - TEST DE DEPLACEMENT IMPOSSIBLE DE LA PIECE - //
		// - SI TEST VALIDE, PAS DE DEPLACEMENT DE LA PIECE	- //
			
		else if(((interfaceValidite) pieceDepart).deplacementValid(departL, departC, arriveeL,arriveeC)==false && couleurValid == true){
			System.out.println("Déplacement impossible ! Choisis une autre case");
		}
		
		// - PAINT PLATEAU NOIR ET BLANC - //
		
		this.setCouleurComplet();	
		
		// - PAINT CASE ECHEC ROI EN ROUGE - //
		
		System.out.println("Roi Noir:   C - "+positionRoi("colonneRoiNoir")+"	L - "+positionRoi("ligneRoiNoir"));	
		System.out.println("Roi Noir:   C - "+positionRoi("colonneRoiBlanc")+"	L - "+positionRoi("ligneRoiBlanc"));	
		
		if (testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir"))){
			getBoutonPlateau(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")).setBackground(new Color(238, 16, 16));
			infoVictoire.setText("ECHEC DU ROI NOIR");
			if (testMatNoir()){
				getBoutonPlateau(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")).setBackground(new Color(115, 194, 251));
				infoVictoire.setText("ECHEC ET MAT DU ROI NOIR, VICTOIRE DES BLANCS");
			}
		}


		if (testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))){
			getBoutonPlateau(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc")).setBackground(new Color(238, 16, 16));
			infoVictoire.setText("ECHEC DU ROI BLANC");
			if (testMatBlanc()){
				getBoutonPlateau(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc")).setBackground(new Color(115, 194, 251));
				System.out.println("MAT");
				infoVictoire.setText("ECHEC ET MAT DU ROI BLANC, VICTOIRE DES NOIRS");
			}
		} 
		
			
		
	}	
	
	public boolean deplacementMat(int departL, int departC, int arriveeL, int arriveeC){		
		
		Piece pieceDMat = getBoutonPlateau(departC, departL).getPiece();
		Piece pieceAMat = getBoutonPlateau(arriveeC, arriveeL).getPiece();
		
		boolean deplacementAutorise = false;

		if(((interfaceValidite) pieceDMat).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceAMat==null){
			if ((pieceDMat.getType().equals("Roi") && pieceDMat.getCouleur().equals("Noir") && !testEchecNoir(arriveeC, arriveeL)) || (pieceDMat.getType().equals("Roi") && pieceDMat.getCouleur().equals("Blanc") && !testEchecBlanc(arriveeC, arriveeL)) || !pieceDMat.getType().equals("Roi")){
				(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDMat);
				(plateauBouton[departC][departL]).setPiece(null);
				if (compteurBoutonJoueur % 2 == 1 && !testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir"))){ //condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
							
					deplacementAutorise = true;
				
				}
				else if (compteurBoutonJoueur % 2 == 0 && !testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					
					deplacementAutorise = true;	
								
				}
				
				else if ((compteurBoutonJoueur % 2 == 0 && testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))) || (compteurBoutonJoueur % 2 == 1 && testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi

					deplacementAutorise = false;
					
				}
				
					plateauBouton[departC][departL].setPiece(pieceDMat);
					plateauBouton[arriveeC][arriveeL].setPiece(null);		
				
			
			}
		}
		else if(((interfaceValidite) pieceDMat).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceAMat!=null  && pieceDMat.getCouleur()!=pieceAMat.getCouleur()){
			if ((pieceDepart.getType().equals("Roi") && pieceDepart.getCouleur().equals("Noir") && !testEchecNoir(arriveeC, arriveeL)) || (pieceDMat.getType().equals("Roi") && pieceDMat.getCouleur().equals("Blanc") && !testEchecBlanc(arriveeC, arriveeL)) || !pieceDMat.getType().equals("Roi")){
				(plateauBouton[arriveeC][arriveeL]).setPiece(pieceDMat);
				(plateauBouton[departC][departL]).setPiece(null);
				if (compteurBoutonJoueur % 2 == 1 && !testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir"))){ //condition de ne pas faire un auto-echec par une piece qui n'est pas le roi

			// - LE DEPLACEMENT EST VALIDE CHANGEMENT DE JOUEUR - PAIR = BLANC IMPAIR = NOIR - //
					
					deplacementAutorise = true;
				
				}
				else if (compteurBoutonJoueur % 2 == 0 && !testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					
					deplacementAutorise = true;
				
				}
				
				else if ((compteurBoutonJoueur % 2 == 0 && testEchecBlanc(positionRoi("colonneRoiBlanc"), positionRoi("ligneRoiBlanc"))) || (compteurBoutonJoueur % 2 == 1 && testEchecNoir(positionRoi("colonneRoiNoir"), positionRoi("ligneRoiNoir")))){//condition de ne pas faire un auto-echec par une piece qui n'est pas le roi
					deplacementAutorise = false;
				}
					plateauBouton[departC][departL].setPiece(pieceDMat);
					plateauBouton[arriveeC][arriveeL].setPiece(pieceAMat);
			}
			
		}
		else if(((interfaceValidite) pieceDMat).deplacementValid(departL, departC, arriveeL,arriveeC)==true && pieceAMat!=null  && pieceDepart.getCouleur()==pieceAMat.getCouleur()){
			
			deplacementAutorise = false;
		}
			
			
		else if(((interfaceValidite) pieceDMat).deplacementValid(departL, departC, arriveeL,arriveeC)==false){
			
			deplacementAutorise = false;
		}
		
		return deplacementAutorise;
		
	}
	
	public int positionRoi(String s){
		
		int position = 0;
		
		int ligneRoiNoir = 0;
		int ligneRoiBlanc = 0;
		int colonneRoiNoir = 0;
		int colonneRoiBlanc = 0;
		
		for (int i = 0; i <= 7; i++){
			for (int j = 0; j <= 7; j++){
				if (getBoutonPlateau(j, i).getPiece() != null){
							
					if (getBoutonPlateau(j, i).getPiece().getType().equals("Roi") && getBoutonPlateau(j, i).getPiece().getCouleur().equals("Noir")){
						ligneRoiNoir = i;
						colonneRoiNoir = j;
					}
					if (getBoutonPlateau(j, i).getPiece().getType().equals("Roi") && getBoutonPlateau(j, i).getPiece().getCouleur().equals("Blanc")){
						ligneRoiBlanc = i;
						colonneRoiBlanc = j;
					}
				}
			}
		}
		if (s.equals("ligneRoiNoir")){
			position = ligneRoiNoir;
		}
		else if (s.equals("ligneRoiBlanc")){
			position = ligneRoiBlanc;
		}
		else if (s.equals("colonneRoiNoir")){
			position = colonneRoiNoir;
		}
		else if (s.equals("colonneRoiBlanc")){
			position = colonneRoiBlanc;
		}
		
		return position;
	}
	
	public boolean testEchecNoir(int c, int l){
		boolean echec = false;
		int a = 0; //condition de while
		int i = 1;
		
		while (l+i<=7 && a==0){ //echec par le bas pour Tour et Dame Blanc
			if(getBoutonPlateau(c, l+i).getPiece() != null){
				if((getBoutonPlateau(c, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c, l+i).getPiece().getType().equals("Tour")) && getBoutonPlateau(c, l+i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if((!getBoutonPlateau(c, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c, l+i).getPiece().getType().equals("Tour")) || !getBoutonPlateau(c, l+i).getPiece().getType().equals("Roi")){
					a = 1;
				}
				if (getBoutonPlateau(c, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c, l+i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
		i = 1;
		a = 0;
		
		while (l-i>=0 && a==0){ //echec par le haut pour Tour et Dame Blanc
			if(getBoutonPlateau(c, l-i).getPiece() != null){
				if((getBoutonPlateau(c, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c, l-i).getPiece().getType().equals("Tour")) && getBoutonPlateau(c, l-i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c, l-i).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c, l-i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
		while (c-i>=0 && a==0){ //echec par le gauche pour Tour et Dame Blanc
			if(getBoutonPlateau(c-i, l).getPiece() != null){
				if((getBoutonPlateau(c-i, l).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i, l).getPiece().getType().equals("Tour")) && getBoutonPlateau(c-i, l).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c-i, l).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
		while (c+i<=7 && a==0){ //echec par le droite pour Tour et Dame Blanc
			if(getBoutonPlateau(c+i, l).getPiece() != null){
				if((getBoutonPlateau(c+i, l).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l).getPiece().getType().equals("Tour")) && getBoutonPlateau(c+i, l).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c+i, l).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
         while (c+i<=7 && l+i<=7 && a==0){  //echec par le diagonale a droite en bas pour Fou et Dame Blanc
			if(getBoutonPlateau(c+i, l+i).getPiece() != null){
				if((getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c+i, l+i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l+i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
         while (c-i>=0 && l+i<=7 && a==0){  //echec par le diagonale a droite en haut pour Fou et Dame Blanc
			if(getBoutonPlateau(c-i, l+i).getPiece() != null){
				if((getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c-i, l+i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l+i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
         while (c-i>=0 && l-i>=0 && a==0){  //echec par le diagonale a gauche en bas pour Fou et Dame Blanc
			if(getBoutonPlateau(c-i, l-i).getPiece() != null){
				if((getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Tour")) && getBoutonPlateau(c-i, l-i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l-i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
         while (c+i<=7 && l-i>=0 && a==0){  //echec par le diagonale a droite en haut pour Fou et Dame Blanc
			if(getBoutonPlateau(c+i, l-i).getPiece() != null){
				if((getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c+i, l-i).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
				if(!getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l-i).getPiece().getCouleur().equals("Noir")){
					a = 0;
				}
			}
			i++;
		}
        
        if(c+2<=7 && l-1>=0){// echec par Cavalier Blanc droite en haut horizontal
            if(getBoutonPlateau(c+2, l-1).getPiece() != null){
				if(getBoutonPlateau(c+2, l-1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+2, l-1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        if(c+1<=7 && l-2>=0){// echec par Cavalier Blanc droite en haut vertical
            if(getBoutonPlateau(c+1, l-2).getPiece() != null){
				if(getBoutonPlateau(c+1, l-2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+1, l-2).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        if(c-1>=0 && l-2>=0){// echec par Cavalier Blanc gauche en haut vertical
            if(getBoutonPlateau(c-1, l-2).getPiece() != null){
				if(getBoutonPlateau(c-1, l-2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-1, l-2).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c-2>=0 && l-1>=0){// echec par Cavalier Blanc gauche en haut horizontal
            if(getBoutonPlateau(c-2, l-1).getPiece() != null){
				if(getBoutonPlateau(c-2, l-1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-2, l-1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        if(c-2>=0 && l+1<=7){// echec par Cavalier Blanc gauche en bas horizontal
            if(getBoutonPlateau(c-2, l+1).getPiece() != null){
				if(getBoutonPlateau(c-2, l+1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-2, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        if(c-1>=0 && l+2<=7){// echec par Cavalier Blanc gauche en bas vertical
            if(getBoutonPlateau(c-1, l+2).getPiece() != null){
				if(getBoutonPlateau(c-1, l+2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-1, l+2).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c+1<=7 && l+2<=7){// echec par Cavalier Blanc droite en bas vertical
            if(getBoutonPlateau(c+1, l+2).getPiece() != null){
				if(getBoutonPlateau(c+1, l+2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+1, l+2).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c+2<=7 && l+1<=7){// echec par Cavalier Blanc droite en bas horizontal
            if(getBoutonPlateau(c+2, l+1).getPiece() != null){
				if(getBoutonPlateau(c+2, l+1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+2, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c+1<=7 && l+1<=7){// echec par Pion Blanc diagonale droite 
            if(getBoutonPlateau(c+1, l+1).getPiece() != null){
				if(getBoutonPlateau(c+1, l+1).getPiece().getType().equals("Pion")  && getBoutonPlateau(c+1, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c-1>=0 && l+1<=7){// echec par Pion Blanc diagonale gauche 
            if(getBoutonPlateau(c-1, l+1).getPiece() != null){
				if(getBoutonPlateau(c-1, l+1).getPiece().getType().equals("Pion")  && getBoutonPlateau(c-1, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        if(c+1<=7 && l-1>=0){// echec par Roi Blanc par diagonale droite vers le haut
            if(getBoutonPlateau(c+1, l-1).getPiece() != null){
				if(getBoutonPlateau(c+1, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l-1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c+1<=7 && l>=0 && l<=7){// echec par Roi Blanc par droite
            if(getBoutonPlateau(c+1, l).getPiece() != null){
				if(getBoutonPlateau(c+1, l).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c+1<=7 && l+1<=7){// echec par Roi Blanc par diagonale droite vers le bas
            if(getBoutonPlateau(c+1, l+1).getPiece() != null){
				if(getBoutonPlateau(c+1, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }

        if(c-1>=0 && l-1>=0){// echec par Roi Blanc par diagonale gauche vers le haut
            if(getBoutonPlateau(c-1, l-1).getPiece() != null){
				if(getBoutonPlateau(c-1, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l-1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c-1>=0 && l>=0 && l<=7){// echec par Roi Blanc par gauche
            if(getBoutonPlateau(c-1, l).getPiece() != null){
				if(getBoutonPlateau(c-1, l).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c-1>=0 && l+1<=7){// echec par Roi Blanc par diagonale gauche vers le bas
            if(getBoutonPlateau(c-1, l+1).getPiece() != null){
				if(getBoutonPlateau(c-1, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c>=0 && c <= 7 && l+1<=7){// echec par Roi Blanc en bas
            if(getBoutonPlateau(c, l+1).getPiece() != null){
				if(getBoutonPlateau(c, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c, l+1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
         if(c>=0 && c <= 7 && l-1>=0){// echec par Roi Blanc en haut
            if(getBoutonPlateau(c, l-1).getPiece() != null){
				if(getBoutonPlateau(c, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c, l-1).getPiece().getCouleur().equals("Blanc")){
					echec = true;
					System.out.println("ECHEC NOIR TRUE");
				}
            }
        }
        
       
		return echec;
	}
	
	public boolean testEchecBlanc(int c, int l){
		boolean echec = false;
		int a = 0; //condition de while
		int i = 1;
		
		while (l+i<=7 && a==0){ //echec par le bas pour Tour et Dame Noir
			if(getBoutonPlateau(c, l+i).getPiece() != null){
				if((getBoutonPlateau(c, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c, l+i).getPiece().getType().equals("Tour")) && getBoutonPlateau(c, l+i).getPiece().getCouleur().equals("Noir")){
					echec = true;
				}
				if(!getBoutonPlateau(c, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c, l+i).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c, l+i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
		while (l-i>=0 && a==0){ //echec par le bas pour Tour et Dame Noir
			if(getBoutonPlateau(c, l-i).getPiece() != null){
				if((getBoutonPlateau(c, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c, l-i).getPiece().getType().equals("Tour")) && getBoutonPlateau(c, l-i).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c, l-i).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c, l-i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}  i = 1;
		a = 0;
		
		while (c-i>=0 && a==0){  //echec par le gauche pour Tour et Dame Noir
			if(getBoutonPlateau(c-i, l).getPiece() != null){
				if((getBoutonPlateau(c-i,l).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i,l).getPiece().getType().equals("Tour")) && getBoutonPlateau(c-i,l).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c-i, l).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
		while (c+i<=7 && a==0){  //echec par le droite pour Tour et Dame Noir
			if(getBoutonPlateau(c+i, l).getPiece() != null){
				if((getBoutonPlateau(c+i, l).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l).getPiece().getType().equals("Tour")) && getBoutonPlateau(c+i, l).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c+i, l).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l).getPiece().getType().equals("Tour")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
        

         while (c+i<=7 && l+i<=7 && a==0){  //echec par le diagonale a droite en bas pour Fou et Dame Noir
			if(getBoutonPlateau(c+i, l+i).getPiece() != null){
				if((getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c+i, l+i).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l+i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
		
         while (c-i>=0 && l+i<=7 && a==0){  //echec par le diagonale a droite en haut pour Fou et Dame Blanc
			if(getBoutonPlateau(c-i, l+i).getPiece() != null){
				if((getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c-i, l+i).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l+i).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l+i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
         while (c-i>=0 && l-i>=0 && a==0){  //echec par le diagonale a gauche en bas pour Fou et Dame Blanc
			if(getBoutonPlateau(c-i, l-i).getPiece() != null){
				if((getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c-i, l-i).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c-i, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c-i, l-i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
        i = 1;
		a = 0;
         while (c+i<=7 && l-i>=0 && a==0){  //echec par le diagonale a droite en haut pour Fou et Dame Blanc
			if(getBoutonPlateau(c+i, l-i).getPiece() != null){
				if((getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Dame") || getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Fou")) && getBoutonPlateau(c+i, l-i).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
				if(!getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Dame") || !getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Fou")){
					a = 1;
				}
				if (getBoutonPlateau(c+i, l-i).getPiece().getType().equals("Roi") && getBoutonPlateau(c+i, l-i).getPiece().getCouleur().equals("Blanc")){
					a = 0;
				}
			}
			i++;
		}
             if(c+2<=7 && l-1>=0){// echec par Cavalier Noir droite en haut horizontal
            if(getBoutonPlateau(c+2, l-1).getPiece() != null){
				if(getBoutonPlateau(c+2, l-1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+2, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        if(c+1<=7 && l-2>=0){// echec par Cavalier droite en haut vertical
            if(getBoutonPlateau(c+1, l-2).getPiece() != null){
				if(getBoutonPlateau(c+1, l-2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+1, l-2).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        if(c-1>=0 && l-2>=0){// echec par Cavalier Noir gauche en haut vertical
            if(getBoutonPlateau(c-1, l-2).getPiece() != null){
				if(getBoutonPlateau(c-1, l-2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-1, l-2).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c-2>=0 && l-1>=0){// echec par Cavalier Noir gauche en haut horizontal
            if(getBoutonPlateau(c-2, l-1).getPiece() != null){
				if(getBoutonPlateau(c-2, l-1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-2, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        if(c-2>=0 && l+1<=7){// echec par Cavalier Noir gauche en bas horizontal
            if(getBoutonPlateau(c-2, l+1).getPiece() != null){
				if(getBoutonPlateau(c-2, l+1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-2, l+1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        if(c-1>=0 && l+2<=7){// echec par Cavalier Noir gauche en bas vertical
            if(getBoutonPlateau(c-1, l+2).getPiece() != null){
				if(getBoutonPlateau(c-1, l+2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c-1, l+2).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+1<=7 && l+2<=7){// echec par Cavalier Noir droite en bas vertical
            if(getBoutonPlateau(c+1, l+2).getPiece() != null){
				if(getBoutonPlateau(c+1, l+2).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+1, l+2).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+2<=7 && l+1<=7){// echec par Cavalier Noir droite en bas horizontal
            if(getBoutonPlateau(c+2, l+1).getPiece() != null){
				if(getBoutonPlateau(c+2, l+1).getPiece().getType().equals("Cavalier")  && getBoutonPlateau(c+2, l+1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        
         if(c-1>=0 && l-1>=0){// echec par Pion Noir diagonale gauche 
            if(getBoutonPlateau(c-1, l-1).getPiece() != null){
				if(getBoutonPlateau(c-1, l-1).getPiece().getType().equals("Pion")  && getBoutonPlateau(c-1, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+1<=7 && l-1>=0){// echec par Pion Noir diagonale droite 
            if(getBoutonPlateau(c+1, l-1).getPiece() != null){
				if(getBoutonPlateau(c+1, l-1).getPiece().getType().equals("Pion")  && getBoutonPlateau(c+1, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+1<=7 && l-1>=0){// echec par Roi Noir droite haut
            if(getBoutonPlateau(c+1, l-1).getPiece() != null){
				if(getBoutonPlateau(c+1, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+1<=7 && l>=0 && l<=7){// echec par Roi Noir droite
            if(getBoutonPlateau(c+1, l).getPiece() != null){
				if(getBoutonPlateau(c+1, l).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c+1<=7 && l+1<=7){// echec par Roi Noir diagonale droite bas
            if(getBoutonPlateau(c+1, l+1).getPiece() != null){
				if(getBoutonPlateau(c+1, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c+1, l+1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c-1>=0 && l-1>=0){// echec par Roi Noir par diagonale gauche vers le haut
            if(getBoutonPlateau(c-1, l-1).getPiece() != null){
				if(getBoutonPlateau(c-1, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c-1>=0 && l>=0 && l<=7){// echec par Roi Noir par gauche
            if(getBoutonPlateau(c-1, l).getPiece() != null){
				if(getBoutonPlateau(c-1, l).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c-1>=0 && l+1<=7){// echec par Roi Noir par diagonale gauche vers le bas
            if(getBoutonPlateau(c-1, l+1).getPiece() != null){
				if(getBoutonPlateau(c-1, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c-1, l+1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        if(c>=0 && c <= 7 && l+1<=7){// echec par Roi Noir en bas
            if(getBoutonPlateau(c, l+1).getPiece() != null){
				if(getBoutonPlateau(c, l+1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c, l+1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
         if(c>=0 && c <= 7 && l-1>=0){// echec par Roi Noir en haut
            if(getBoutonPlateau(c, l-1).getPiece() != null){
				if(getBoutonPlateau(c, l-1).getPiece().getType().equals("Roi")  && getBoutonPlateau(c, l-1).getPiece().getCouleur().equals("Noir")){
					echec = true;
					System.out.println("ECHEC BLANC TRUE");
				}
            }
        }
        
		return echec;
	}
	
	// - TEST D'UN ECHEC ET MAT NOIR - //
	// - LA METHODE EST APPELEE QUAND IL Y A UN ECHEC, POUR TESTER LE MAT ELLE VA ENSUITE PRENDRE CHACUNE DES PIECES NOIRES ET TESTER SI CHACUN D'ENTRE ELLES NE PEUX PAS SE DEPLACER SUR UNE CASE DE L'ECHIQUIER - //
	// - DANS LE CAS OU UNE SEULE PIECE PEUT SE DEPLACER (SACHANT QU'UN DEPLACEMENT N'EST POSSIBLE QUE S'IL ENLEVE LA SITUATION D'ECHEC) CELA VEUT DONC DIRE QU'IL N'Y A PAS ECHEC ET MAT - //
	
	public boolean testMatNoir(){
		boolean matNoir = true;
		
		for (int j = 0; j <= 7; j++){
			for (int i = 0; i <= 7; i++){
				if (getBoutonPlateau(j, i).getPiece() != null){ // ON  CHERCHE CHACUNE DES PIECES NOIRES EN PARCOURANT TOUT L'ECHIQUIER
					
					if (getBoutonPlateau(j, i).getPiece().getCouleur().equals("Noir")){
						for (int n = 0; n <= 7; n++){   
							for (int k = 0; k <= 7; k++){
								if (deplacementMat(i, j, k, n)){ // ON TESTE LE DEPLACEMENT SUR CHACUNE DES CASES DE L'ECHIQUIER
									matNoir = false;
								}
							}
						}
					}
				}
			}
		}
		
		return matNoir;
	}
	
	// - TEST D'UN ECHEC ET MAT BLANC - //
	// - LA METHODE EST APPELEE QUAND IL Y A UN ECHEC, POUR TESTER LE MAT ELLE VA ENSUITE PRENDRE CHACUNE DES PIECES BLANCHES ET TESTER SI CHACUN D'ENTRE ELLES NE PEUX PAS SE DEPLACER SUR UNE CASE DE L'ECHIQUIER - //
	// - DANS LE CAS OU UNE SEULE PIECE PEUT SE DEPLACER (SACHANT QU'UN DEPLACEMENT N'EST POSSIBLE QUE S'IL ENLEVE LA SITUATION D'ECHEC) CELA VEUT DONC DIRE QU'IL N'Y A PAS ECHEC ET MAT - //
	
	
	public boolean testMatBlanc(){
		boolean matBlanc = true;
		
		for (int j = 0; j <= 7; j++){
			for (int i = 0; i <= 7; i++){
				if (getBoutonPlateau(j, i).getPiece() != null){ // ON  CHERCHE CHACUNE DES PIECES BLANCHES EN PARCOURANT TOUT L'ECHIQUIER
					
					if (getBoutonPlateau(j, i).getPiece().getCouleur().equals("Blanc")){
						for (int n = 0; n <= 7; n++){   
							for (int k = 0; k <= 7; k++){
								if (deplacementMat(i, j, k, n)){ // ON TESTE LE DEPLACEMENT SUR CHACUNE DES CASES DE L'ECHIQUIER
									matBlanc = false;
								}
							}
						}
					}
				}
			}
		}
		return matBlanc;
		
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
	
	// - AFFICHAGE DES COUPS POSSIBLES - //
	
	public void affichageCasePossible(Piece p){
		for(int i = 0;i<this.getPlateau().length;i++){
			for(int j = 0;j<this.getPlateau().length;j++){
				if(p instanceof interfaceValidite){
					if(((interfaceValidite)p).deplacementValid(departL,departC,i,j)==true){
						if(plateauBouton[j][i].getPiece()==null){
							this.plateauBouton[j][i].setBackground(new Color(0, 204, 203));
						}
						else if(((plateauBouton[j][i].getPiece()).getCouleur())!=p.getCouleur()&&(plateauBouton[j][i].getPiece())!=null){
							this.plateauBouton[j][i].setBackground(new Color(0, 204, 203));
						}
						
					}
				}
			}
		}	
		
	}
	
	// - CREATION BOUTON BANDEAU BAS HISTORIQUE - //
	// - A SA CREATION LE BOUTON ENTRE EN MEMOIRE DANS UNE LISTE LES CARACTERISTIQUES DE LA PARTIE A CET INSTANT - //
	
	public void creationBoutonHistoriqueBlanc(){
		
		MonBoutonHisto b = new MonBoutonHisto(listeHistoriqueString.get(compteurHistorique),compteurHistorique);
		b.addActionListener(new EcouteurBoutonHisto(this,b.getNumero()));
		
		augmentationListeHistoPlateau(); // ENREGISTREMENT PLATEAU
		augmentationListeHistoTimer(); // ENREGISTREMENT DES TIMERS
		
		b.setBackground(couleurB);
		bandeauBas.add(b);
		bandeauBas.validate();
		bandeauBas.repaint();
		
	}
	
	public void creationBoutonHistoriqueNoir(){
		
		MonBoutonHisto b = new MonBoutonHisto(listeHistoriqueString.get(compteurHistorique),compteurHistorique);
		b.addActionListener(new EcouteurBoutonHisto(this,b.getNumero()));
		
		augmentationListeHistoPlateau(); // ENREGISTREMENT PLATEAU
		augmentationListeHistoTimer(); // ENREGISTREMENT DES TIMERS
		
		b.setBackground(couleurN);
		bandeauBas.add(b);
		bandeauBas.validate();
		bandeauBas.repaint();
		
	}
	
	// - AUGMENTATION LISTE HISTORIQUE - //
	
	public void augmentationListeHisto(){	
		listeHistoriqueString.add("Tour : "+(compteurHistorique+1));
	}
	
	// - ON PREND LA POSITION DE CHAQUE PIECE DU PLATEAU POUR CONNAITRE LA DISPOSITION DE CELUI-CI - //
	
	public void augmentationListeHistoPlateau(){
		Piece[][] tempPlateau = new Piece[8][8];
		for(int i=0; i<8;i++){
		   for(int j=0; j<8; j++){
			   tempPlateau[i][j]= (plateauBouton[j][i]).getPiece();
		   }
		}
		
		listeHistoriquePlateau.add(tempPlateau);
	}
	
	// - ENREGISTREMENT DE LA POSITION DES TIMERS A L'INSTANT DE LA CREATION DU BOUTON - //
	
	public void augmentationListeHistoTimer(){

		listeHistoriqueTimerSecB.add(secondeB);
		listeHistoriqueTimerMinB.add(minuteB);
		listeHistoriqueTimerHeuB.add(heureB);
		
		listeHistoriqueTimerSecN.add(secondeN);
		listeHistoriqueTimerMinN.add(minuteN);
		listeHistoriqueTimerHeuN.add(heureN);
	}
	
	// - ACTION REVENIR EN ARRIERE DANS HISTORIQUE AU TOUR CORRESPOND A "NUMERO" - //
	
	public void retourArriereJeu(int numero){
		Piece[][] tempTab;
		compteurHistorique=numero;
		tempTab = listeHistoriquePlateau.get(numero);
		
		for(int i=0; i<8;i++){
		   for(int j=0; j<8; j++){
			   plateauBouton[j][i].setPiece(tempTab[i][j]);
		   }
		}
		
		heureB = listeHistoriqueTimerHeuB.get(numero);
		minuteB = listeHistoriqueTimerMinB.get(numero);
		secondeB = listeHistoriqueTimerSecB.get(numero);
		
		heureN = listeHistoriqueTimerHeuN.get(numero);
		minuteN = listeHistoriqueTimerMinN.get(numero);
		secondeN = listeHistoriqueTimerSecN.get(numero);
		
		tpsB.setText(heureB+":"+minuteB+":"+secondeB);
		tpsN.setText(heureN+":"+minuteN+":"+secondeN);
		
		if(compteurBoutonJoueur % 2 == 1){
			timerN.start();
			timerB.stop();
						
	    }else if(compteurBoutonJoueur % 2 == 0){
			timerB.start();
			timerN.stop();
		}			
		
		setCouleurComplet();
		
	}
	
	// - ROQUE - //
	
	public void roque(int ligneR, int colonneR, int ligneT, int colonneT){
		
		// - RECUPERATION DES PIECES PRESENTE AUX POSITIONS EN PARAMETRE - //
	
		Piece p1 = plateauBouton[colonneR][ligneR].getPiece();
		Piece p2 = plateauBouton[colonneT][ligneT].getPiece();
		
		boolean couleurValid = false;
		
		// - TEST POUR SAVOIR A QUELLE COUlEUR EST LE TOUR DE JOUER - //
		
		if(pieceDepart.getCouleur() == "Blanc" && compteurBoutonJoueur % 2 == 0){		
			couleurValid = true;
		}else if(pieceDepart.getCouleur() == "Noir" && compteurBoutonJoueur % 2 == 1){
			couleurValid = true;
			
		}else{
			couleurValid = false;
			System.out.println("Ce n'est pas le tour de cette couleur");
            
		}
		
		// - TEST DE POSSIBILITE DU ROQUE - //

		if(p1.getCouleur()==p2.getCouleur()&& p1.getType()=="Roi" && p2.getType()=="Tour" && couleurValid==true ){
					
				//TEST DU PETIT ROQUE : test la présence d'une pièce entre le roi et la tour droite	
					
				if(departC<arriveeC){
		
					for(int i=departC+1 ; i<arriveeC; i++){
						if(((this.getBoutonPlateau(i, departL))).getPiece() != null){
							roqueValid = false;
						}
						else if (((this.getBoutonPlateau(i, departL))).getPiece() == null){
							roqueValid=true;
						}
					}
				}
				
				//TEST DU GRAND ROQUE : test la présence d'une pièce entre le roi et la tour gauche
					
				else if(departC>arriveeC){
						
					for(int i=departC-1 ; i>arriveeC; i--){
						if(((this.getBoutonPlateau(i, departL))).getPiece() != null){
							roqueValid = false;
						}
						else if (((this.getBoutonPlateau(i, departL))).getPiece() == null){
							roqueValid=true;
						}
					}
				}
				
				//ROQUE S'IL EST VALIDE ET QUE LE ROI ET A TOUR N'ONT PAS BOUGE
				
				if(roqueValid==true && ((Roi) p1).getRoiPositionDepart() == true && ((Tour) p2).getTourPositionDepart()){

					if(Math.abs(departC-arriveeC)==3){
					    (plateauBouton[arriveeC][arriveeL]).setPiece(null);
					    (plateauBouton[departC][departL]).setPiece(null);
						plateauBouton[departC+2][departL].setPiece(pieceDepart);
						plateauBouton[arriveeC-2][arriveeL].setPiece(pieceArrivee);
						compteurBoutonJoueur++;
					}
						
					if(Math.abs(departC-arriveeC)==4){
						(plateauBouton[arriveeC][arriveeL]).setPiece(null);
					    (plateauBouton[departC][departL]).setPiece(null);
						plateauBouton[departC-2][departL].setPiece(pieceDepart);
						plateauBouton[arriveeC+3][arriveeL].setPiece(pieceArrivee);
						compteurBoutonJoueur++;
					}		
				}
		}
		this.setCouleurComplet();
	
	}

   
}
