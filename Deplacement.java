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
	
	public boolean getValidite(Piece p){
		
		boolean v = false;
		

	
			if(((interfaceValidite) p).deplacementValid(arriveeL,arriveeC)==true && p==null){
				
				v=true;
				return v;
			
			} else { 
				
				return v;
				
				}
		
			/* else if((interfaceValidite) p).deplacementValid(arriveeL,arriveeC)==true && p!=null  && p.getCouleur()!=p.getCouleur())){
				
				v=true;
				return v;
			}
			
			
			else if((interfaceValidite) p).deplacementValid(arriveeL,arriveeC)==true && ((interfaceValidite) p)!=null  && (interfaceValidite) p).getCouleur()==(interfaceValidite) p).getCouleur())){
				
				v=false;
				System.out.println("Déplacement impossible ! Choisis une autre case");
				return v;
			}
			
			
			else if((interfaceValidite) p).deplacementValid(arriveeL,arriveeC)==false){
				
				v=false;
				System.out.println("Déplacement impossible ! Choisis une autre case");
				return v;
			} */
	}
}
	

	
	

