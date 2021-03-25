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
		System.out.println("Case ligne : " + x + " colonne : " + y);
	 
	}
}
	

    



