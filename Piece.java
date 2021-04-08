import java.lang.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.swing.*;
import javax.swing.border.*;

public class Piece {
	
	public String type;
	private String couleur;
	private int ligne;
	private int colonne;
	private ImageIcon icon;
	
	public Piece (String type, String couleur, int ligne, int colonne){
		this.type = type;
		this.couleur = couleur;
		this.ligne = ligne;
		this.colonne = colonne;
		icon = new ImageIcon(new BufferedImage(64, 64, BufferedImage.TYPE_INT_ARGB));
		this.icon = icon;
	}
	public Piece (String type, String couleur){
		this.type = type;
		this.couleur = couleur;
	}
	
	public ImageIcon getIcon(){
		return this.icon;
	}
	
	public String getCouleur(){
		return this.couleur;
	}
	
	public void setCouleur (String c){
		this.couleur = c;
	}
	
	public int getLigne(){
		return this.ligne;
	}
	
	public int getColonne(){
		return this.colonne;
	}
	
	public void setLigne(int l){
		this.ligne=l;
	}
	
	public void setColonne(int c){
		this.colonne=c;
	}
	
	public String toString (){
		String s = type+" "+couleur+" en "+ligne+";"+colonne; //a completer
		return s;
	}

}
