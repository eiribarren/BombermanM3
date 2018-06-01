package Juego;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class Mapa extends JPanel implements KeyListener, ActionListener {
	private int filas;
	private int columnas;
	private HashSet<Jugador> jugadores;
	private HashMap<Integer, HashMap<Integer, Casilla>> casillas;
	private Timer timer;	
	private HashSet<Jugador> jugadoresMuertos;
	
	public enum objetos {
		BOMBA, CAJA, BLOQUE, EXPLOSION
	}
	public Mapa(int filas, int columnas, int ancho, int alto) {
		this.timer = new Timer(100,this);
		this.filas = filas;
		this.columnas = columnas;
		this.jugadores = new HashSet<Jugador>();
		this.casillas = new HashMap<Integer, HashMap<Integer, Casilla>>();
		this.jugadoresMuertos = new HashSet<Jugador>();
		JPanel mapa = new JPanel();
		mapa.setVisible(true);
		mapa.setLayout(new GridLayout(filas,columnas));
		for ( int i = 0 ; i < this.getFilas() ; i++ ) {
			casillas.put(i,new HashMap<Integer, Casilla>());
			for ( int j = 0 ; j < this.getColumnas() ; j++ ) {
				casillas.get(i).put(j,new Casilla(i, j, ancho/columnas, alto/filas));
			}
		}
		for ( int i = 0 ; i < filas ; i++ ) {
			for ( int j = 0 ; j < columnas ; j++ ) {
				Casilla c = casillas.get(i).get(j);
				if ( c.getFila() == 0 || c.getFila() == this.filas - 1 || c.getColumna() == 0 || c.getColumna() == this.columnas - 1 ) {
					c.ponerObjeto(new Bloque(c.getFila(),c.getColumna(),ancho/columnas,alto/filas));
				}
				mapa.add(c);
			}
		}	
		this.add(mapa);
		this.addKeyListener(this);
		setFocusable(true);
		timer.start();
		this.setVisible(true);
	}
	
	public int getFilas() {
		return filas;
	}

	public int getColumnas() {
		return columnas;
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		for ( Jugador jugador : jugadores ) {
			if (!jugador.eliminar()) {
				jugador.getFrameActual().paintIcon(this, g, jugador.getPosicionX(), jugador.getPosicionY());
			}
		}
		g.dispose();
	}
	
	public void ponerJugador( Jugador jugador ) {
		jugadores.add(jugador);
		this.add(jugador);
	}
	
	public Jugador getJugador( String nombre ) {
		for ( Jugador jugador : jugadores ) {
			if ( jugador.getNombre().equals(nombre) ) {
				return jugador;
			}
		}
		return null;
	}
	
	public class Casilla extends JPanel implements ActionListener, Comparable {
		private int columna;
		private int fila;
		private BufferedImage imagen;
		private Sprite objeto = null;
		private int alto;
		private int ancho;
	
		public Casilla(int fila, int columna, int ancho, int alto) {
			try {
				this.imagen = ImageIO.read(getClass().getResourceAsStream("/Juego/suelo.png"));
			} catch ( Exception e ) {
				e.printStackTrace();
			} 
			this.alto = alto;
			this.ancho = ancho;
			this.setPreferredSize(new Dimension(ancho,alto));
			this.setMaximumSize(new Dimension(ancho,alto));
			this.setPosicion(fila, columna);
			this.setBackground(Color.WHITE);
			this.repaint();
			this.setVisible(true);
		}
		public int getColumna() {
			return columna;
		}
		public void setColumna(int columna) {
			this.columna = columna;
		}
		public int getFila() {
			return fila;
		}
		public void setFila(int fila) {
			this.fila = fila;
		}
		public void setPosicion(int fila, int columna) {
			this.setFila(fila);
			this.setColumna(columna);
		}
		public int getAlto() {
			return this.alto;
		}
		public int getAncho() {
			return this.ancho;
		}
		public Sprite getObjeto() {
			return objeto;
		}
		public Sprite ponerObjeto(Sprite objeto) {
			Sprite objeto_aux = null;
			if ( this.objeto != null ) {
				objeto_aux = this.objeto;
				this.remove(this.objeto);
			}
			this.objeto = objeto;
			this.add(this.objeto);
			this.repaint();
			return objeto_aux;
		}
		public Sprite quitarObjeto() {
			Sprite objeto_aux = null;
			if (this.objeto != null) {
				objeto_aux = this.objeto;
				this.remove(this.objeto);
			}
			this.objeto = null;
			this.repaint();
			return objeto_aux;
		}
		public boolean tieneObjeto() {
			if ( this.objeto != null ) {
				return true;
			}
			return false;
		}
		public void paintComponent(Graphics g) {
			super.paintComponent(g);
		    g.drawImage(this.imagen,0,0,null);
		} 
		
		public void paint(Graphics g) {
			super.paint(g);
			if ( this.tieneObjeto() ) {
				this.objeto.getFrameActual().paintIcon(this, g, 0, 0);
			}
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if ( this.tieneObjeto() ) {
				this.objeto.actionPerformed(e);
			}
			this.repaint();
		}
		@Override
		public int compareTo(Object o) {
			// TODO Auto-generated method stub
			if ( o instanceof Casilla ) {
				if ( this.getFila() > ((Casilla)o).getFila() ) {
					return 1;
				} else if ( this.getFila() < ((Casilla)o).getFila() ){
					return -1;
				} else {
					if ( this.getColumna() > ((Casilla)o).getColumna() ) {
						return 1;
					} else if ( this.getColumna() < ((Casilla)o).getColumna()) {
						return -1;
					}
				}
			}
			return 0;
		}
		
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		jugadores.forEach(jugador -> jugador.keyPressed(e));
		this.repaint();
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if ( jugadoresMuertos.size() < jugadores.size() - 1 ) {
			Casilla c;
			for ( Jugador j : jugadores ) {
				if ( j.eliminar() ) {
					if ( !jugadoresMuertos.contains(j) ) {
						jugadoresMuertos.add(j);
					}
					if ( jugadoresMuertos.size() == jugadores.size() -1 ) {
						Jugador ganador = null;
						for ( Jugador jug : jugadores ) {
							if ( !jug.estaMuerto() ) {
								ganador = jug;
								break;
							}
						}
						Partida.acabar(ganador);
						break;
					}
				}
				if ( (c = casillas.get(j.getFila()).get(j.getColumna())).tieneObjeto() ) {
					Sprite obj = c.getObjeto();
					if ( obj instanceof Explosion) {
						j.morir();
					} else if ( obj instanceof Mejora ) {
						c.quitarObjeto();
						j.ponerMejoras((Mejora)obj);
					}
				}
			}
			jugadores.forEach(jugador -> jugador.actionPerformed(e));
			for ( int i = 0 ; i < filas ; i++ ) {
				for ( int j = 0 ; j < columnas ; j++ ) {
					casillas.get(i).get(j).actionPerformed(e);
				}
			}
		}
		this.repaint();
	}
	
	public Casilla getCasilla(int fila, int columna) {
		Casilla c = null;
		try {
			c = this.casillas.get(fila).get(columna);
		} catch ( IndexOutOfBoundsException e ) {
			c = null;
		}
		return c;
	}
	
	public void mapear(ArrayList<String> mapeado) throws MapeadoIncorrectoException{
		if ( mapeado.size() != this.getFilas() - 2 ) {
			throw new MapeadoIncorrectoException();
		}
		for ( int i = 0 ; i < mapeado.size() ; i++ ) {
			if ( mapeado.get(i).length() > this.getColumnas() - 2 ) {
				throw new MapeadoIncorrectoException();
			} else {
				String fila = mapeado.get(i);
				for ( int j = 0 ; j < fila.length() ; j++ ) {
					if ( fila.charAt(j) == 'C' ) {
						Casilla c = casillas.get(i+1).get(j+1);
						c.ponerObjeto(new Caja(i+1,j+1,c.getAncho(),c.getAlto()));
					} else if ( fila.charAt(j) == 'B' ) {
						Casilla c = casillas.get(i+1).get(j+1);
						c.ponerObjeto(new Bloque(i+1,j+1,c.getAncho(),c.getAlto()));
					}
				}
			}
		}
	}
	
	public void ponerObjeto(int fila, int columna, objetos objeto, Sprite contexto) {
		try {
			Casilla c = casillas.get(fila).get(columna);
			switch (objeto) {
				case BOMBA:
					if ( ((Jugador)contexto).getLimiteBombas() > ((Jugador)contexto).getBombas_puestas() ) {
						c.ponerObjeto(new Bomba(fila, columna, c.getAlto(), c.getAncho(), ((Jugador) contexto).getRango(),(Jugador)contexto));
						((Jugador)contexto).setBombas_puestas(((Jugador)contexto).getBombas_puestas()+1);
					}
					break;
				case CAJA:
					c.ponerObjeto(new Caja(fila, columna, c.getAlto(), c.getAncho()));
					break;
				case EXPLOSION:
					Jugador j = ((Bomba)contexto).getPropietario();
					j.setBombas_puestas(j.getBombas_puestas()-1);
					c.ponerObjeto(new Explosion(fila, columna, c.getAlto(), c.getAncho(),Explosion.tipo.CENTRAL));
					Sprite auxiliar; 
					boolean bloqueo_norte = false;
					boolean bloqueo_sur = false;
					boolean bloqueo_este = false;
					boolean bloqueo_oeste = false;
					for ( int i = 1 ; i < ((Bomba)contexto).getRango() ; i++ ) {
						if ( !bloqueo_sur ) { 
						    if ( fila + i < casillas.size()-2 ) { 
						        c = casillas.get(fila+i).get(columna); 
						        auxiliar = c.ponerObjeto(new Explosion(fila+i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.VERTICAL)); 
						        bloqueo_sur = comprobarAuxiliar(auxiliar); 
						    } else  if ( fila + i < casillas.size()-1 ) { 
						    	c = casillas.get(fila+i).get(columna); 
						        auxiliar = c.ponerObjeto(new Explosion(fila+i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.AB_FINAL)); 
						        bloqueo_sur = comprobarAuxiliar(auxiliar); 
						    } 
						} 
						if ( !bloqueo_norte ) { 
					        if ( fila - i > 1 ) { 
					            c = casillas.get(fila-i).get(columna); 
					            auxiliar = c.ponerObjeto(new Explosion(fila-i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.VERTICAL)); 
					            bloqueo_norte = comprobarAuxiliar(auxiliar); 
					        } else if ( fila - i > 0 ) { 
					            c = casillas.get(fila-i).get(columna); 
					            auxiliar = c.ponerObjeto(new Explosion(fila-i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.AR_FINAL)); 
					            bloqueo_norte = comprobarAuxiliar(auxiliar); 
					        } 
					    } 
					     
					    if ( !bloqueo_este ) { 
					        if ( columna + i < casillas.get(i).size()-2 ) { 
					            c = casillas.get(fila).get(columna + i); 
					            auxiliar = c.ponerObjeto(new Explosion(fila, columna +i, c.getAlto(), c.getAncho(), Explosion.tipo.HORIZONTAL)); 
					            bloqueo_este = comprobarAuxiliar(auxiliar); 
					        } else if ( columna + i < casillas.get(i).size()-1 ){ 
					            c = casillas.get(fila).get(columna + i); 
					            auxiliar = c.ponerObjeto(new Explosion(fila, columna + i, c.getAlto(), c.getAncho(), Explosion.tipo.D_FINAL)); 
					            bloqueo_este = comprobarAuxiliar(auxiliar); 
					        } 
					    } 
					     
					    if ( !bloqueo_oeste ) { 
					        if ( columna - i > 1 ) { 
					            c = casillas.get(fila).get(columna - i); 
					            auxiliar = c.ponerObjeto(new Explosion(fila, columna-i, c.getAlto(), c.getAncho(), Explosion.tipo.HORIZONTAL)); 
					            bloqueo_oeste = comprobarAuxiliar(auxiliar); 
					        } else if ( columna - i > 0 ) { 
					            c = casillas.get(fila).get(columna - i); 
					            auxiliar = c.ponerObjeto(new Explosion(fila, columna-i, c.getAlto(), c.getAncho(), Explosion.tipo.I_FINAL)); 
					            bloqueo_oeste = comprobarAuxiliar(auxiliar); 
					        } 
						}
					}
                    if ( !bloqueo_sur ) { 
                        if ( fila + ((Bomba)contexto).getRango() < casillas.size() -1 ) { 
                            casillas.get(fila + ((Bomba)contexto).getRango()).get(columna).ponerObjeto(new Explosion(fila+((Bomba)contexto).getRango(), columna, c.getAlto(), c.getAncho(), Explosion.tipo.AB_FINAL)); 
                        } 
                    } 
                    if ( !bloqueo_norte ) { 
                        if ( fila - ((Bomba)contexto).getRango() > 0 ) { 
                            casillas.get(fila - ((Bomba)contexto).getRango()).get(columna).ponerObjeto(new Explosion(fila-((Bomba)contexto).getRango(), columna, c.getAlto(), c.getAncho(), Explosion.tipo.AR_FINAL)); 
                        } 
                    } 
                    if ( !bloqueo_este ) { 
                        if ( columna + ((Bomba)contexto).getRango() < casillas.get(fila).size()-1 ) { 
                            casillas.get(fila).get(columna + ((Bomba)contexto).getRango()).ponerObjeto(new Explosion(fila, columna+((Bomba)contexto).getRango(), c.getAlto(), c.getAncho(), Explosion.tipo.D_FINAL)); 
                        } 
                    } 
                    if ( !bloqueo_oeste ) { 
                        if ( columna - ((Bomba)contexto).getRango() > 0 ) { 
                            casillas.get(fila).get(columna - ((Bomba)contexto).getRango()).ponerObjeto(new Explosion(fila, columna-((Bomba)contexto).getRango(), c.getAlto(), c.getAncho(), Explosion.tipo.I_FINAL)); 
                        } 
                    } 
					break;
						
				
			}
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		} catch ( ArrayIndexOutOfBoundsException e ) {
			e.printStackTrace();
		} catch ( Exception e ) {
			e.printStackTrace();
		}
	}
	
	private boolean comprobarAuxiliar(Sprite auxiliar) { 
        if ( auxiliar instanceof Caja ) { 
        	Mejora.tipoMejora tipo;
        	int rng = (int)(Math.random() * 5);
        	switch(rng) {
	        	case 1:
	        		tipo = Mejora.tipoMejora.RANGO_UP;
	        		break;
	        	case 2:
	        		tipo = Mejora.tipoMejora.BOMB_UP;
	        		break;
	        	default:
	        		return true;
        	}
            casillas.get(auxiliar.getFila()).get(auxiliar.getColumna()).quitarObjeto(); 
            casillas.get(auxiliar.getFila()).get(auxiliar.getColumna()).ponerObjeto(new Mejora(auxiliar.getFila(),auxiliar.getColumna(),auxiliar.getAncho(), auxiliar.getAlto(), tipo)); 
            return true; 
        } else if ( auxiliar instanceof Bomba ) {
        	((Bomba)auxiliar).explotar();
        }
        return false; 
    } 
}
