package Juego;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Partida{
	static Mapa mapa;
	static JFrame main;
	static MenuPrincipal menu;
	static ArrayList<String> nombreJugadores;
	
	public static void main ( String[] args ) {
		nuevo();
	}
	
	public static void nuevo() {
		main = new JFrame();
		menu = new MenuPrincipal("Bomb");
		main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		main.setMinimumSize(new Dimension(730,760));
		main.setPreferredSize(new Dimension(730,760));
		main.setMaximumSize(new Dimension(730,760));
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
		main.add(mapa);
		main.setResizable(false);
		main.pack(); 
	}
	
	public static void acabar(Jugador ganador) {
		JPanel fin = new JPanel();
		JLabel fin_text = new JLabel();
		JButton reiniciar = new JButton();
		
		try {
			fin_text.setText("El jugador " + ganador.getNombre() + " ha ganado.");
			fin_text.setFont(new Font("serif", Font.PLAIN, 50));
		} catch ( NullPointerException e ) {
			e.printStackTrace();
		}
		
		reiniciar.setText("Volver a jugar");
		reiniciar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				main.dispose();
				nuevo();				
			}
			
		});
		fin.add(Box.createVerticalStrut(500));
		fin.add(fin_text);
		fin.add(reiniciar);
		main.remove(mapa);
		main.add(fin);
		main.pack();
	}
}
