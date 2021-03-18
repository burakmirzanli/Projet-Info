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
		
		if(p.deplacementValid(arrL,arrC)==true && plateau[arrL][arrC]==null){
			
			v=true;
			plateau[arrL[arrC]=p;
			return v;
		
		}
		
		else if(p.deplacementValid(arrL,arrC)==true && (plateau[arrL][arrC]!=null  && plateau[arrL][arrC].getCouleur()!=p.getCouleur())){
			
			v=true;
			plateau[arrL[arrC]=p;
			return v;
		}
		
		
		else if(p.deplacementValid(arrL,arrC)==true && (plateau[arrL][arrC]!=null  && plateau[arrL][arrC].getCouleur()==p.getCouleur())){
			
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
