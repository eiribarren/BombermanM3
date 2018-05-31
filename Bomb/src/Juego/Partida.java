package Juego;

import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Partida{
	static Mapa mapa;
	static JFrame main;
	static MenuPrincipal menu;
	static ArrayList<String> nombreJugadores;
	
	public static void main ( String[] args ) {
		main = new JFrame();
		menu = new MenuPrincipal("Bomb");
		main.add(menu);
		main.setVisible(true);
		main.pack(); 
	}
	
	public static void comenzar() {
		
		nombreJugadores = menu.getJugadores();
		ArrayList<Jugador> jugadores = new ArrayList<Jugador>(); 
		ArrayList<String> urlFrames = new ArrayList<String>();
		for ( int i = 0 ; i < 12 ; i++ ) {
			urlFrames.add( "/Juego/Sprites_Rojo/frame" + i + ".png");
		}
		urlFrames.add("/Juego/Sprites_Rojo/rojo_muerto.png");
		Jugador j = new Jugador(nombreJugadores.get(0), 1, 1, 1, urlFrames, 72, 72, menu.getControles(1) );
		jugadores.add(j);
		urlFrames = new ArrayList<String>();
		for ( int i = 0 ; i < 12 ; i++ ) {
			urlFrames.add( "/Juego/Sprites_Azul/frame" + i + ".png");
		}
		urlFrames.add("/Juego/Sprites_Azul/azul_muerto.png");
		j = new Jugador(nombreJugadores.get(1), 8, 8, 1, urlFrames, 72, 72, menu.getControles(2) );
		jugadores.add(j);
		main.remove(menu);
		mapa = new Mapa(10,10,720,720);
		jugadores.forEach( jugador -> mapa.ponerJugador(jugador) );
		ArrayList<String> mapeado = new ArrayList<String>();
		mapeado.add("--C-CC--");
		mapeado.add("-C-C--C-");
		mapeado.add("C-C--C-C");
		mapeado.add("--C--C-C");
		mapeado.add("C--C-C--");
		mapeado.add("C-C-C--C");
		mapeado.add("-C-CC-C-");
		mapeado.add("--C--C--");
		try {
			mapa.mapear(mapeado);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		main.setMinimumSize(new Dimension(730,760));
		main.setPreferredSize(new Dimension(730,760));
		main.setMaximumSize(new Dimension(730,760));
		main.add(mapa);
		main.setResizable(false);
		main.pack(); 
	}
}
