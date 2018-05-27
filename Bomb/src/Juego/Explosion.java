package Juego;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class Explosion extends Sprite {
	
	private tipo tipo_exp;
	private int delay = 2;
	private int contador = 0;
	
	public enum tipo {
		CENTRAL, VERTICAL, HORIZONTAL, I_FINAL, D_FINAL, AR_FINAL, AB_FINAL
	}
	
	public Explosion (int fila, int columna, int ancho, int alto, tipo tipo) {
		this.fila = fila;
		this.columna = columna;
		this.ancho = ancho;
		this.alto = alto;
		this.tipo_exp = tipo;
		this.start();
	}
	
	private void start() {
		ArrayList<String> urlFrames = new ArrayList<String>();
		switch(this.tipo_exp) {
			case CENTRAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-0-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case VERTICAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-1-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case HORIZONTAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-2-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case D_FINAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-5-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case I_FINAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-6-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case AR_FINAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-3-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
			case AB_FINAL:
				for ( int i = 0; i < 4 ; i++ ) {
					urlFrames.add("/Juego/Explosion/expl-4-" + i + ".png" );
				}
				cambiarTamano(ancho, alto, urlFrames);
				break;
		}
	}
	
	public void actionPerformed(ActionEvent e) {
		contador++;
		if ( contador == delay ) {
			contador = 0;
			indexFrame++;
			if ( indexFrame < 4 ) {
				this.setFrameActual(this.frames.get(indexFrame));
			} else {
				Partida.mapa.getCasilla(this.fila, this.columna).quitarObjeto();
			}
		}
	}

}
