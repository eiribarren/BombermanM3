package Juego;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public abstract class Sprite extends JLabel implements ActionListener {
	protected int fila;
	protected int columna;
	protected int ancho;
	protected int alto;
	protected ImageIcon frameActual;
	protected int indexFrame;
	protected ArrayList<ImageIcon> frames = new ArrayList<ImageIcon>();
	
	public void cambiarTamano(int ancho, int altura, ArrayList<String> urlFrames) {
		this.setPreferredSize(new Dimension(ancho,altura));
		this.setMaximumSize(new Dimension(ancho,altura));
		for ( String urlFrame : urlFrames ) {
			Image imagen = new javax.swing.ImageIcon(getClass().getResource(urlFrame)).getImage();
			BufferedImage bi = new BufferedImage(imagen.getWidth(null), imagen.getHeight(null), BufferedImage.TYPE_INT_ARGB);
			Graphics2D g = bi.createGraphics();
			g.drawImage(imagen,0,0,ancho,altura,null);
			frames.add(new ImageIcon(bi));
		}
		this.setFrameActual(frames.get(0)); 
	}
	
	public int getPosicionX() {
		return columna * ancho;
	}
	
	public int getPosicionY() {
		return fila * alto;
	}
	
	public ImageIcon getFrameActual() {
		return frameActual;
	}

	public void setFrameActual(ImageIcon frameActual) {
		this.frameActual = frameActual;
	}
	
	public void actionPerformed(ActionEvent e) {
		
	}
}
