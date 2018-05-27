package Juego;

import java.util.ArrayList;

public class Caja extends Sprite {
	private Mejora mejora;
	
	public Caja(int fila, int columna, int ancho, int altura) {
		this.fila = fila;
		this.columna = columna;
		this.ancho = ancho;
		this.alto = altura;
		ArrayList<String> urlFrames = new ArrayList<String>();
		urlFrames.add("/Juego/caja.png");
		this.cambiarTamano(ancho, altura, urlFrames);
		this.setVisible(true);
		this.ponerMejora();
	}
	public void ponerMejora() {
		
	}
	public String toString() {
		return "caja";
	}
}
