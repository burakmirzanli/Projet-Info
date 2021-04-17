  
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;
import java.lang.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.event.*;

public class Chronometre {
	
		
	public static final int delais=1000;
	public static ActionListener tache_timerB;
	public static ActionListener tache_timerN;
    public static int heureB=0, minuteB=0, secondeB=0;
    public static int heureN=0, minuteN=0, secondeN=0;
    public static final JLabel tpsB = new JLabel(heureB+":"+minuteB+":"+secondeB);
	public static final JLabel tpsN = new JLabel(heureN+":"+minuteN+":"+secondeN);
	public static Timer timerN;
	public static Timer timerB;
	
	public static void main (String[] args) {
		
				// - CREATION DU CHRONOMETRE - //
		// - ACTION REALISER PAR LE TIMER BLANC - //
		tache_timerB= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeB++;
				if(secondeB==60)
				{
					secondeB=0;
					minuteB++;
				}
				if(minuteB==60)
				{
					minuteB=0;
					heureB++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsB.setText(heureB+":"+minuteB+":"+secondeB);
				
			}
		};
		// - ACTION REALISER PAR LE TIMER NOIR - //
		tache_timerN= new ActionListener()
		{
			public void actionPerformed(ActionEvent e1)
			{
				secondeN++;
				if(secondeN==60)
				{
					secondeN=0;
					minuteN++;
				}
				if(minuteN==60)
				{
					minuteN=0;
					heureN++;
				}
				// - RAFRAICHIR LE CHRONO - //
				tpsN.setText(heureN+":"+minuteN+":"+secondeN);
				
			}
		};
		
		// - INSTANCIATION DES TIMER - //
		
		final Timer timerB= new Timer(delais,tache_timerB);
		final Timer timerN= new Timer(delais,tache_timerN);
		
		// - FIN CODE CHRONO - //
		

		
		

	}
	
	public JLabel getTpsB(){
		return this.tpsB;
	}
	
	public JLabel getTpsN(){
		return this.tpsN;
	}
	
}

