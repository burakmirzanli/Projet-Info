public class Deplacement{
	private int departL;
	private int departC;
	private int arriveeL;
	private int arriveeC;
	private Piece p;
	
	public Deplacement(int departL,int departC, int arriveeL, int arriveeC){
		this.departL = departL;
		this.departC = departC;
		this.arriveeL = arriveeL;
		this.arriveeC = arriveeC;
	}
	
	public void deplacementFinal(Piece p){
		
		Piece p1 = (Echiquier.plateauBouton[arriveeC][arriveeL]).getPiece();

	
			if(((interfaceValidite) p).deplacementValid(departL, departC, arriveeL,arriveeC)==true && p1==null){
				(Echiquier.plateauBouton[arriveeC][arriveeL]).setPiece(p);
				(Echiquier.plateauBouton[departC][departL]).setPiece(null);
			
			
			}
		
			else if(((interfaceValidite) p).deplacementValid(departL, departC, arriveeL,arriveeC)==true && p1!=null  && p.getCouleur()!=p1.getCouleur()){
				(Echiquier.plateauBouton[arriveeC][arriveeL]).setPiece(p);
				(Echiquier.plateauBouton[departC][departL]).setPiece(null);
				
				
			}
			
			
			else if(((interfaceValidite) p).deplacementValid(departL, departC, arriveeL,arriveeC)==true && p1!=null  && p.getCouleur()==p1.getCouleur()){
				System.out.println("Déplacement impossible ! Choisis une autre case");
				
			}
			
			
			else if(((interfaceValidite) p).deplacementValid(departL, departC, arriveeL,arriveeC)==false){
				System.out.println("Déplacement impossible ! Choisis une autre case");
				
			} 
	}
}
	

	
