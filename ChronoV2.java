public class ChronoV2{
	
	public static void main (String[] args) {
		for (int i=1; i>=1; i++){ //démarrage
            try {
            Thread.sleep(1000);
            } catch (InterruptedException ie) {
            Thread.currentThread().interrupt();
            }
        System.out.println(i); 
        if (i==9){ //arrêt
            break;
        }
    }
	}
}

