import java.awt.event.*;


public class EcouteurBouton implements ActionListener{
	
	private Echiquier e;
	private MonBouton b;
	//private Joueur j;
	
	
	
	public EcouteurBouton(Echiquier e, MonBouton b){
		this.e=e;
		this.b=b;
	}
		
    public void actionPerformed(ActionEvent e){
		int x = b.getL();
		int y = b.getC();
		Piece p = b.getPiece();
		if(p!=null){
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Cette pièce est " + p.toString());
			System.out.println(".......");
			
		}else{
			System.out.println("Case ligne : " + x + " colonne : " + y );
			System.out.println("Il n'y a pas de pièce");
			System.out.println(".......");
		}
	 
	}
}
	

    



