public class Case{
	private Piece p;
	
	
	public Case(Piece p){
		this.p=p;
	}
	
	public Piece getPiece(){
		return this.p;
	}
	
	
	public void setPiece(Piece p1){
		this.p=p1;
	}
	
	
	public boolean estOccupe(){
		boolean occ = false;
		if(this.p==null){
			occ=false;
			return occ;
		}else{
			occ=true;
			System.out.println(this.p.toString());
			return occ;
		}
	}
}

