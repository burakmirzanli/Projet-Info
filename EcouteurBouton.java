import java.awt.event.*;


public class EcouteurBouton implements ActionListener{
	
	private Echiquier e1;
	private MonBouton b;

	

	
	
	
	public EcouteurBouton(Echiquier e1, MonBouton b){
		this.e1=e1;
		this.b=b;
	}
		
    public void actionPerformed(ActionEvent e){
		int x = b.getL();
		int y = b.getC();
		Piece p = b.getPiece();
		
		if(p!=null){
			System.out.println("--------------------------------------");
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Cette piece est " + p.toString());
			
		}else{
			System.out.println("--------------------------------------");
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Il n'y a pas de piece");
		}
		
		
		// MISE EN MEMOIRE POSITION DEPART //
		if(e1.getCompteurBouton() % 2 == 0){
			if(p !=null){
			e1.memoirePositionDepart(x,y);
			
			e1.memoirePieceDepart(p);
			
			System.out.println("Position Depart = "+e1.getDepartL()+" ; "+e1.getDepartC()); 
			
			e1.CompteurBoutonIncrements();
			}
		// MISE EN MEMOIRE POSITION ARRIVEE ET TEST DEPLACEMENT POSSIBLE //
		}else if(e1.getCompteurBouton() % 2 == 1){
			e1.memoirePositionArrivee(x,y);
			
			e1.memoirePieceArrivee(p);
			
			System.out.println("Position Arrivee = "+e1.getArriveeL()+" ; "+e1.getArriveeC());
			
			e1.deplacementPiece(e1.getDepartL(), e1.getDepartC(), x, y);
			
			e1.CompteurBoutonIncrements();
		}
		System.out.println("Compteur :"+e1.getCompteurBouton());
		System.out.println("CompteurJoueur :"+e1.getCompteurBoutonJoueur());
	}
	

}
	

    



