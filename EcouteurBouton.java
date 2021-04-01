import java.awt.event.*;


public class EcouteurBouton implements ActionListener{
	
	private Echiquier e1;
	private MonBouton b;
	private Deplacement d;
	//private Joueur j
	

	
	
	
	public EcouteurBouton(Echiquier e1, MonBouton b){
		this.e1=e1;
		this.b=b;
	}
		
    public void actionPerformed(ActionEvent e){
		int x = b.getL();
		int y = b.getC();
		Piece p = b.getPiece();
		
		if(p!=null){
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Cette piece est " + p.toString());
			System.out.println(".......");
			
		}else{
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Il n'y a pas de pièce");
			System.out.println(".......");
		}
		
		// MISE EN MEMOIRE POSITION DEPART //
		if(e1.compteurBouton % 2 == 0){
			e1.memoirePositionDepart(x,y);
			
			e1.memoirePieceDepart(p);
			
			System.out.println("Position Depart = "+e1.getDepartL()+" ; "+e1.getDepartC()); 
			
			e1.compteurBouton++;
			
		}else if(e1.compteurBouton % 2 == 1){
			e1.memoirePositionArrivee(x,y);
			
			e1.memoirePieceArrivee(p);
			
			System.out.println("Position Arrivee = "+e1.getArriveeL()+" ; "+e1.getArriveeC());
			
			e1.deplacementPiece(Echiquier.departL, Echiquier.departC, x, y);
			
			e1.compteurBouton++;
		}
		System.out.println("Compteur :"+e1.compteurBouton);
		
	}
	

}
	

    



