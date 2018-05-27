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
		Jugador j = new Jugador(nombreJugadores.get(0), 1, 1, 8, 8, urlFrames, 72, 72 );
		jugadores.add(j);
		main.remove(menu);
		mapa = new Mapa(10,10,720,720);
		jugadores.forEach( jugador -> mapa.ponerJugador(jugador) );
		ArrayList<String> mapeado = new ArrayList<String>();
		mapeado.add("--------");
		mapeado.add("--------");
		mapeado.add("--C--C--");
		mapeado.add("--------");
		mapeado.add("---C----");
		mapeado.add("--------");
		mapeado.add("--------");
		mapeado.add("--------");
		try {
			mapa.mapear(mapeado);
		} catch ( Exception e ) {
			e.printStackTrace();
		}
		main.setMinimumSize(new Dimension(730,760));
		main.setPreferredSize(new Dimension(730,760));
		main.setMaximumSize(new Dimension(730,760));
		main.add(mapa);
		main.pack(); 
	}
}
