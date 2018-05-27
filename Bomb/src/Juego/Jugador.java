package Juego;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Jugador extends Sprite implements ActionListener, KeyListener {
	
	private String nombre;
	private int posicionX = 0, posicionY = 0, velocidadX = 0, velocidadY = 0;
	private int indexFrame;
	private int contadorFrames;
	private int rango;
	private movimiento direccion;
	
	private enum movimiento {
		arriba, abajo, derecha, izquierda, quieto
	}

	public Jugador(String nombre, int fila, int columna, int velocidadX, int velocidadY, ArrayList<String> urlFrames, int alto, int ancho) {
		this.nombre = nombre;
		this.posicionX = fila * alto;
		this.posicionY = columna * ancho;
		this.velocidadX = velocidadX;
		this.velocidadY = velocidadY;
		this.rango = 2;
		this.direccion = movimiento.quieto;
		this.fila = fila;
		this.columna = columna;
		cambiarTamano(alto,ancho,urlFrames);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setVisible(true);
	}
	
	public String getNombre() {
		return nombre;
	}

	public int getPosicionX() {
		return posicionX;
	}

	public void setPosicionX(int posicionX) {
		this.posicionX = posicionX;
	}

	public int getPosicionY() {
		return posicionY;
	}

	public void setPosicionY(int posicionY) {
		this.posicionY = posicionY;
	}

	public int getVelocidadX() {
		return velocidadX;
	}

	public void setVelocidadX(int velocidadX) {
		this.velocidadX = velocidadX;
	}

	public int getVelocidadY() {
		return velocidadY;
	}
	
	public void setVelocidadY(int velocidadY) {
		this.velocidadY = velocidadY;
	}
	
	public int getRango() {
		return this.rango;
	}
	public boolean aumentarContadorFrames() {
		this.contadorFrames++;
		if ( contadorFrames == 9 ) {
			this.contadorFrames = 0;
			this.direccion = movimiento.quieto;
			return false;
		}
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if ( this.direccion != movimiento.quieto ) {
			switch(direccion) {
				case arriba:
					moverArriba();
					break;
				case abajo:
					moverAbajo();
					break;
				case izquierda:
					moverIzquierda();
					break;
				case derecha:
					moverDerecha();
					break;
			}
		}
	}
	public void moverArriba() {
		this.setPosicionY(this.posicionY - this.velocidadY);
		if (aumentarContadorFrames()) {
			indexFrame++;
			if ( indexFrame > 5 || indexFrame < 3 ) {
				indexFrame = 4;
			}
		} else {
			indexFrame = 3;
		}
		this.setFrameActual(this.frames.get(indexFrame));
	}
	
	public void moverAbajo() {
		this.setPosicionY(this.posicionY + this.velocidadY);
		if (aumentarContadorFrames()) {
			indexFrame++;
			if ( indexFrame > 2 || indexFrame < 0 ) {
				indexFrame = 1;
			}
		} else {
			indexFrame = 0;
		}
		this.setFrameActual(this.frames.get(indexFrame));
	}
	
	public void moverIzquierda() {
		this.setPosicionX(this.posicionX - this.velocidadX);
		if (aumentarContadorFrames()) {
			indexFrame++;
			if ( indexFrame > 8 || indexFrame < 6 ) {
				indexFrame = 7;
			}
		} else {
			indexFrame = 6;
		}
		this.setFrameActual(this.frames.get(indexFrame));
	}
	
	public void moverDerecha() {
		this.setPosicionX(this.posicionX + this.velocidadX);
		if (aumentarContadorFrames()) {
			indexFrame++;
			if ( indexFrame > 11 || indexFrame < 9) {
				indexFrame = 10;
			}
		} else {
			indexFrame = 9;
		}
		this.setFrameActual(this.frames.get(indexFrame));
	}
	
	public void ponerBomba() {
		Partida.mapa.ponerObjeto(this.fila, this.columna, Mapa.objetos.BOMBA, this);
	}
	
	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		if ( direccion == movimiento.quieto ) {
			if ( tecla == KeyEvent.VK_UP ) {
				if ( Partida.mapa.getCasilla(this.fila-1, this.columna) != null ) {
					if ( !Partida.mapa.getCasilla(this.fila-1, this.columna).tieneObjeto() ) {
						direccion = movimiento.arriba;
						this.fila--;
						moverArriba();	
					}
				}		
			}
			if ( tecla == KeyEvent.VK_DOWN ) {
				if ( Partida.mapa.getCasilla(this.fila+1, this.columna) != null ) {
					if ( !Partida.mapa.getCasilla(this.fila+1, this.columna).tieneObjeto() ) {
						direccion = movimiento.abajo;
						this.fila++;
						moverAbajo();
					}
				}
			}
			if ( tecla == KeyEvent.VK_LEFT ) {
				if ( Partida.mapa.getCasilla(this.fila, this.columna-1) != null ) {
					if ( !Partida.mapa.getCasilla(this.fila, this.columna-1).tieneObjeto() ) {
						direccion = movimiento.izquierda;
						this.columna--;
						moverIzquierda();
					}
				}
			}
			if ( tecla == KeyEvent.VK_RIGHT ) {
				if ( Partida.mapa.getCasilla(this.fila, this.columna+1) != null ) {
					if ( !Partida.mapa.getCasilla(this.fila, this.columna+1).tieneObjeto() ) {						
						direccion = movimiento.derecha;
						this.columna++;
						moverDerecha();
					}
				}
			}
			if ( tecla == KeyEvent.VK_SPACE ) {
				if ( !Partida.mapa.getCasilla(this.fila,this.columna).tieneObjeto() ) {
					ponerBomba();
				}
			}
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
}
