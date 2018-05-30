package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Timer;

public class Bomba extends Sprite implements ActionListener{
	private int contadorMecha;
	private int rango;
	private Jugador propietario;
	public Bomba(int fila, int columna, int ancho, int alto, int rango, Jugador propietario) {
		this.indexFrame = 0;
		this.contadorMecha = 30;
		this.fila = fila;
		this.columna = columna;
		this.ancho = ancho;
		this.alto = alto;
		this.rango = rango;
		this.propietario = propietario;
		ArrayList<String> urlFrames = new ArrayList<String>();
		for ( int i = 0 ; i < 2 ; i++ ) {
			urlFrames.add("/Juego/Bomba/bomba" + i + ".png");
		}
		cambiarTamano(alto,ancho,urlFrames);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.contadorMecha--;
		if ( this.indexFrame == 1 ) {
			this.indexFrame = 0;
		} else {
			this.indexFrame = 1;
		}
		this.setFrameActual(this.frames.get(indexFrame));
		if ( this.contadorMecha == 0 ) {
			explotar();
		}
	}
	
	public void explotar() {
		Partida.mapa.getCasilla(this.fila,this.columna).quitarObjeto();
		Partida.mapa.ponerObjeto(this.fila, this.columna, Mapa.objetos.EXPLOSION, this );
	}
	
	public int getRango() {
		return this.rango;
	}
	
	public Jugador getPropietario() {
		return this.propietario;
	}
}
