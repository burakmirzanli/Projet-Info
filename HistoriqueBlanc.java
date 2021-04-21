 public class HistoriqueBlanc {
    private String colonneBlanc;
    private int ligneBlanc;
    
    public HistoriqueBlanc(int l, String c) {
    colonneBlanc=c;
    ligneBlanc=l;
    }
    public String toString (){
		String s = ligneBlanc+""+colonneBlanc+" ";
		return s;
	}
}
