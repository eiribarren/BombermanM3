package Juego;

import java.util.ArrayList;

public class Mejora extends Sprite {
	tipoMejora tipo;
			 
	public enum tipoMejora {
		 RANGO_UP, BOMB_UP
	}
			
	public Mejora(int fila, int columna, int ancho, int altura, tipoMejora tipo) {
		this.tipo = tipo;
		this.fila = fila;
		this.alto = altura;
		this.ancho = ancho;
		this.columna = columna;
		ArrayList<String> urlFrames = new ArrayList<String>();
		if ( this.tipo == tipoMejora.RANGO_UP ) {
			urlFrames.add("/Juego/Mejoras/rankup.png");
		} else if ( this.tipo == tipoMejora.BOMB_UP ) {
			urlFrames.add("/Juego/Mejoras/bomb_up.png");
		}
		cambiarTamano(this.ancho, this.alto, urlFrames);
	}
}
