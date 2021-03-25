

public class TestChronoV1 {
	public static void main (String[] args) {
        ChronoV1 chrono = new ChronoV1();                 //On initialise un chrono.
        chrono.start();                               //démarrage du chrono 
        //boucle qui delay chrono.stop pour tester  
            try {
            Thread.sleep(5 * 1000);//5 secondes
            } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            }
        
        chrono.stop();                                  //arrêt du chrono 
        long tempsTotal2 = chrono.getDureeSec();        // récupération du temps mis par le joueur .     
        System.out.println(chrono.getDureeTxt());       //affichage

	}
}

