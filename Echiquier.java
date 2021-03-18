
public class Echiquier extends JPanel{
		
		// final Case[8][8] tabCase;
		
		Echiquier(){
			super(new GridLayout(8,8));
			// this.tabCase = new Case [][];
			for(int i = 0; i<8; i++){
				for(int j = 0; j<8; j++){
					final Case tabcase = new Case(this, i);
					this.tabCase.add(Case);
					add(Case);
				}
			}
		 validate();	
		}
	}


