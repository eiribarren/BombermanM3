package Juego;

import java.util.ArrayList;

public class Bloque extends Sprite {
	public Bloque( int fila, int columna, int ancho, int altura ) {
		this.fila = fila;
		this.columna = columna;
		this.ancho = ancho;
		this.alto = altura;
		ArrayList<String> urlFrames = new ArrayList<String>();
		urlFrames.add("/Juego/bloque.png");
		this.cambiarTamano(ancho, altura, urlFrames);
		this.setVisible(true);
	}
	public String toString() {
		return "bloque";
	}
}
