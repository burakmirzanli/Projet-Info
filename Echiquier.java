import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;


public class Echiquier {
	
	private final JPanel interfaceJeu = new JPanel(new BorderLayout(3, 3));

	public JButton[][] plateauBouton = new JButton [8][8];
	public JPanel plateauPanel = new JPanel();
	private static final String COLS = "ABCDEFGH"; 
	
	Echiquier() {
        initialisation();
    }
		
	public final void initialisation(){
		
		interfaceJeu.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		plateauPanel = new JPanel(new GridLayout(0, 9));
		
		plateauPanel.setBorder(new LineBorder(Color.BLACK));
        interfaceJeu.add(plateauPanel);
        
        
        Insets buttonMargin = new Insets(0,0,0,0);
        for (int ii = 0; ii < plateauBouton.length; ii++) {
            for (int jj = 0; jj < plateauBouton[ii].length; jj++) {
                JButton b = new JButton();
                b.setMargin(buttonMargin);
                // our chess pieces are 64x64 px in size, so we'll
                // 'fill this in' using a transparent icon..
                ImageIcon icon = new ImageIcon(
                        new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
                b.setIcon(icon);
                if ((jj % 2 == 1 && ii % 2 == 1)
                        //) {
                        || (jj % 2 == 0 && ii % 2 == 0)) {
                    b.setBackground(Color.WHITE);
                } else {
                    b.setBackground(Color.BLACK);
                }
                plateauBouton[jj][ii] = b;
            }
       }
       
       //fill the chess board
		plateauPanel.add(new JLabel(""));
        // fill the top row
        for (int ii = 0; ii < 8; ii++) {
            plateauPanel.add(
                    new JLabel(COLS.substring(ii, ii + 1),SwingConstants.CENTER));
        }
        // fill the black non-pawn piece row
        for (int ii = 0; ii < 8; ii++) {
            for (int jj = 0; jj < 8; jj++) {
                switch (jj) {
                    case 0:
                        plateauPanel.add(new JLabel("" + (ii + 1),SwingConstants.CENTER));
                    default:
                        plateauPanel.add(plateauBouton[jj][ii]);
                }
            }
        }
		
	
		
		
		
	}
	
	
	public final JComponent getInterfaceJeu() {
        return interfaceJeu;
    }
	
	
	
	public static void main(String[] args) {
        Runnable r = new Runnable() {
        
			@Override
				public void run() {
					Echiquier e = new Echiquier();

					JFrame f = new JFrame("Jeu d'echecs");
					f.add(e.getInterfaceJeu());
					f.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					f.setLocationByPlatform(true);
					f.setSize(750,750);
					
					f.setMinimumSize(f.getSize());
					f.setVisible(true);
				}
		};
		SwingUtilities.invokeLater(r);
		
	}		
		
				
   

   
   
}

