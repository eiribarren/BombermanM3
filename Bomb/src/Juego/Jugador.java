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
	private boolean muerto;
	private movimiento direccion;
	private ArrayList<CapturaTecla> controles;
	
	private enum movimiento {
		arriba, abajo, derecha, izquierda, quieto
	}

	public Jugador(String nombre, int fila, int columna, int velocidad, ArrayList<String> urlFrames, int alto, int ancho, ArrayList<CapturaTecla> controles) {
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
		this.controles = controles;
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
			if ( this.contadorFrames == 4 ) {
				this.fila--;
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
			if ( this.contadorFrames == 4 ) {
				this.fila++;
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
			if ( this.contadorFrames == 4 ) {
				this.columna--;
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
			if ( this.contadorFrames == 4 ) {
				this.columna++;
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
		if ( !this.estaMuerto() ) {
			int tecla = e.getKeyCode();
			if ( direccion == movimiento.quieto ) {
				if ( tecla == controles.get(0).getTecla() ) {
					Casilla c = Partida.mapa.getCasilla(this.fila-1, this.columna);
					if ( c != null ) {
						if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
							direccion = movimiento.arriba;
							moverArriba();	
						}
					}		
				}
				if ( tecla == controles.get(3).getTecla() ) {
					Casilla c = Partida.mapa.getCasilla(this.fila+1, this.columna);
					if ( c != null ) {
						if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
							direccion = movimiento.abajo;
							moverAbajo();
						}
					}
				}
				if ( tecla == controles.get(2).getTecla() ) {
					Casilla c = Partida.mapa.getCasilla(this.fila, this.columna-1);
					if ( c != null ) {
						if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {
							direccion = movimiento.izquierda;
							moverIzquierda();
						}
					}
				}
				if ( tecla == controles.get(1).getTecla() ) {
					Casilla c = Partida.mapa.getCasilla(this.fila, this.columna+1);
					if ( c != null ) {
						if ( !c.tieneObjeto() || c.getObjeto() instanceof Explosion || c.getObjeto() instanceof Mejora ) {						
							direccion = movimiento.derecha;
							moverDerecha();
						}
					}
				}
				if ( tecla == controles.get(4).getTecla()) {
					if ( !Partida.mapa.getCasilla(this.fila,this.columna).tieneObjeto() ) {
						ponerBomba();
					}
				}
			}
		}
	}
	
	public void morir() {
		this.muerto = true;
		this.setFrameActual(frames.get(frames.size()-1));
		this.direccion = movimiento.quieto;
	}
	
	public boolean estaMuerto() {
		return this.muerto;
	}
	
	public void ponerMejoras(Mejora m) {
		switch(m.tipo) {
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
