package Juego;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Mapa extends JPanel implements KeyListener, ActionListener {
	private int filas;
	private int columnas;
	private ArrayList<Jugador> jugadores;
	private ArrayList<ArrayList<Casilla>> casillas;
	private Timer timer;
	
	public enum objetos {
		BOMBA, SPEED_UP, EXP_UP, CAJA, BLOQUE, EXPLOSION
	}
	public Mapa(int filas, int columnas, int ancho, int alto) {
		this.timer = new Timer(100,this);
		this.filas = filas;
		this.columnas = columnas;
		this.jugadores = new ArrayList<Jugador>();
		this.casillas = new ArrayList<ArrayList<Casilla>>();
		JPanel mapa = new JPanel();
		mapa.setVisible(true);
		mapa.setLayout(new GridLayout(filas,columnas));
		for ( int i = 0 ; i < this.getFilas() ; i++ ) {
			ArrayList<Casilla> fila = new ArrayList<Casilla>();
			for ( int j = 0 ; j < this.getColumnas() ; j++ ) {
				fila.add(new Casilla(i, j, ancho/columnas, alto/filas));
			}
			this.casillas.add(fila);
		}
		for ( ArrayList<Casilla> fila_a : casillas ) {
			for ( Casilla casilla : fila_a ) {
				if ( casilla.getFila() == 0 || casilla.getFila() == this.filas - 1 || casilla.getColumna() == 0 || casilla.getColumna() == this.columnas - 1 ) {
					casilla.ponerObjeto(new Bloque(casilla.getFila(),casilla.getColumna(),ancho/columnas,alto/filas));
				}
				mapa.add(casilla);
			}
		}	
		this.add(mapa);
		this.addKeyListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);
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
		jugadores.forEach( jugador -> jugador.getFrameActual().paintIcon(this, g, jugador.getPosicionX(), jugador.getPosicionY()) );
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
	
	public class Casilla extends JPanel implements ActionListener {
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
		// TODO Auto-generated method stub
		jugadores.forEach(jugador -> jugador.actionPerformed(e));
		casillas.forEach(fila -> fila.forEach(casilla -> casilla.actionPerformed(e)));
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
						Casilla c = casillas.get(i).get(j);
						c.ponerObjeto(new Caja(i,j,c.getAncho(),c.getAlto()));
					} else if ( fila.charAt(j) == 'B' ) {
						Casilla c = casillas.get(i).get(j);
						c.ponerObjeto(new Bloque(i,j,c.getAncho(),c.getAlto()));
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
					c.ponerObjeto(new Bomba(fila, columna, c.getAlto(), c.getAncho(), ((Jugador) contexto).getRango()));
					break;
				case CAJA:
					c.ponerObjeto(new Caja(fila, columna, c.getAlto(), c.getAncho()));
					break;
				case EXPLOSION:
					c.ponerObjeto(new Explosion(fila, columna, c.getAlto(), c.getAncho(),Explosion.tipo.CENTRAL));
					for ( int i = 1 ; i < ((Bomba)contexto).getRango() ; i++ ) {
						if ( fila + i < casillas.size()-2 ) {
							c = casillas.get(fila+i).get(columna);
							c.ponerObjeto(new Explosion(fila+i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.VERTICAL));
						}
						if ( fila - i > 1 ) {
							c = casillas.get(fila-i).get(columna);
							c.ponerObjeto(new Explosion(fila-i, columna, c.getAlto(), c.getAncho(), Explosion.tipo.VERTICAL));
						}	
						if ( columna + i < casillas.get(i).size()-2 ) {
							c = casillas.get(fila).get(columna + i);
							c.ponerObjeto(new Explosion(fila, columna +i, c.getAlto(), c.getAncho(), Explosion.tipo.HORIZONTAL));
						}
						if ( columna - i > 1 ) {
							c = casillas.get(fila).get(columna - i);
							c.ponerObjeto(new Explosion(fila, columna-i, c.getAlto(), c.getAncho(), Explosion.tipo.HORIZONTAL));
						}
					}
					if ( fila + ((Bomba)contexto).getRango() < casillas.size() -1 ) {
						casillas.get(fila + ((Bomba)contexto).getRango()).get(columna).ponerObjeto(new Explosion(fila+((Bomba)contexto).getRango(), columna, c.getAlto(), c.getAncho(), Explosion.tipo.AB_FINAL));
					}
					if ( fila - ((Bomba)contexto).getRango() > 0 ) {
						casillas.get(fila - ((Bomba)contexto).getRango()).get(columna).ponerObjeto(new Explosion(fila-((Bomba)contexto).getRango(), columna, c.getAlto(), c.getAncho(), Explosion.tipo.AR_FINAL));
					}
					if ( columna + ((Bomba)contexto).getRango() < casillas.get(fila).size()-1 ) {
						casillas.get(fila).get(columna + ((Bomba)contexto).getRango()).ponerObjeto(new Explosion(fila, columna+((Bomba)contexto).getRango(), c.getAlto(), c.getAncho(), Explosion.tipo.D_FINAL));
					}
					if ( columna - ((Bomba)contexto).getRango() > 0 ) {
						casillas.get(fila).get(columna - ((Bomba)contexto).getRango()).ponerObjeto(new Explosion(fila, columna-((Bomba)contexto).getRango(), c.getAlto(), c.getAncho(), Explosion.tipo.I_FINAL));
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
}
