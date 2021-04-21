public class HistoriqueNoir {
    private String colonneNoir;
    private int ligneNoir;
    
    public HistoriqueNoir(int l, String c) {
    colonneNoir=c;
    ligneNoir=l;
    }
    public String toString (){
		String s = ligneNoir+""+colonneNoir+" ";
		return s;
	}
}
