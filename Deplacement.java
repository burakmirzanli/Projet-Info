public class Deplacement{
	private Position depart;
	private Position arrivee;
	
	public Deplacement(Position dep, Position arr){
		this.depart=dep;
		this.arrivee=arr;
	}
	
	public boolean getValidite(Piece p){
		
		boolean v = false;
		
		int arrL = arrivee.getLigne();
		int arrC = arrivee.getColonne();
		
		Case c = plateau[arrL][arrC];
		Piece p1 = c.getPiece();
		
		if(p.deplacementValid(arrL,arrC)==true && p1==null){
			
			v=true;
			c.setPiece(p);
			return v;
		
		}
		
		else if(p.deplacementValid(arrL,arrC)==true && (p1!=null  && p1.getCouleur()!=p.getCouleur())){
			
			v=true;
			c.setPiece(p);
			return v;
		}
		
		
		else if(p.deplacementValid(arrL,arrC)==true && (p1!=null  && p1.getCouleur()==p.getCouleur())){
			
			v=false;
			System.out.println("Déplacement impossible ! Choisis une autre case");
			return v;
		}
		
		
		else if(p.deplacementValid(arrL,arrC)==false){
			
			v=false;
			System.out.println("Déplacement impossible ! Choisis une autre case");
			return v;
		}
		
	}
	
	
}
