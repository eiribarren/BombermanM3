package Juego;

import javax.swing.*;

import Juego.Mapa.Casilla;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class Jugador extends Sprite implements ActionListener, KeyListener {
	
	private String nombre;
	private int posicionX = 0, posicionY = 0, velocidad = 0;
	private int indexFrame;
	private int contadorFrames;
	private int rango;
	private int limite_bombas;
	private int bombas_puestas;
	private movimiento direccion;
	
	private enum movimiento {
		arriba, abajo, derecha, izquierda, quieto
	}

	public Jugador(String nombre, int fila, int columna, int velocidad, ArrayList<String> urlFrames, int alto, int ancho) {
		this.nombre = nombre;
		this.posicionX = fila * alto;
		this.posicionY = columna * ancho;
		this.velocidad = velocidad;
		this.rango = 2;
		this.direccion = movimiento.quieto;
		this.fila = fila;
		this.columna = columna;
		this.limite_bombas = 1;
		this.bombas_puestas = 0;
		cambiarTamano(alto,ancho,urlFrames);
		addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
		setVisible(true);
	}
	
	public int getLimite_bombas() {
		return limite_bombas;
	}

	public void setLimite_bombas(int limite_bombas) {
		this.limite_bombas = limite_bombas;
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

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getRango() {
		return this.rango;
	}
	public boolean aumentarContadorFrames() {
		this.contadorFrames += this.velocidad;
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
		this.setPosicionY(this.posicionY - this.velocidad * 8);
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
		this.setPosicionY(this.posicionY + this.velocidad * 8);
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
		this.setPosicionX(this.posicionX - this.velocidad * 8);
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
		this.setPosicionX(this.posicionX + this.velocidad * 8);
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
	
	public int getBombas_puestas() {
		return this.bombas_puestas;
	}
	
	public int getLimiteBombas() {
		return this.limite_bombas;
	}

	public void setBombas_puestas(int bombas_puestas) {
		this.bombas_puestas = bombas_puestas;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int tecla = e.getKeyCode();
		if ( direccion == movimiento.quieto ) {
			if ( tecla == KeyEvent.VK_UP ) {
				Casilla c = Partida.mapa.getCasilla(this.fila-1, this.columna);
				if ( c != null ) {
					if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
						direccion = movimiento.arriba;
						this.fila--;
						moverArriba();	
					}
				}		
			}
			if ( tecla == KeyEvent.VK_DOWN ) {
				Casilla c = Partida.mapa.getCasilla(this.fila+1, this.columna);
				if ( c != null ) {
					if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
						direccion = movimiento.abajo;
						this.fila++;
						moverAbajo();
					}
				}
			}
			if ( tecla == KeyEvent.VK_LEFT ) {
				Casilla c = Partida.mapa.getCasilla(this.fila, this.columna-1);
				if ( c != null ) {
					if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
						direccion = movimiento.izquierda;
						this.columna--;
						moverIzquierda();
					}
				}
			}
			if ( tecla == KeyEvent.VK_RIGHT ) {
				Casilla c = Partida.mapa.getCasilla(this.fila, this.columna+1);
				if ( c != null ) {
					if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {						
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
	
	public void morir() {
		
	}
	
	public void ponerMejoras(Mejora m) {
		switch(m.tipo) {
			case SPEED_UP:
				this.velocidad += 1;
				break;
			case BOMB_UP:
				this.limite_bombas += 1;
				break;
			case RANGO_UP:
				this.rango += 1;
				break;
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
