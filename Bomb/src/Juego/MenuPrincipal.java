package Juego;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPrincipal extends JPanel {
	private ArrayList<String> nombresJugadores;
	private ArrayList<CapturaTecla> j1_teclas;
	private ArrayList<CapturaTecla> j2_teclas;
	
	public MenuPrincipal(String titulo_text) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(600,600));
		this.setAlignmentY(Component.CENTER_ALIGNMENT);
		this.nombresJugadores = new ArrayList<String>();
		JLabel titulo = new JLabel();
		JLabel nombreJugador1_label = new JLabel();
		JLabel nombreJugador2_label = new JLabel();
		JLabel resolucion_label = new JLabel();
		JLabel error_label = new JLabel();
		JTextField nombreJugador_textField = new JTextField();
		JTextField nombreJugador2_textField = new JTextField();
		JButton botonJugar = new JButton();
		j1_teclas = new ArrayList<CapturaTecla>();
		j2_teclas = new ArrayList<CapturaTecla>();
		
		JPanel controles = new JPanel();
		
		JPanel j1_controles = new JPanel();
		
		j1_controles.setLayout(new GridLayout(6,1));
		
		j1_controles.setSize(300,300);
		
		j1_controles.add(new JLabel("Controles jugador 1"));
		JPanel j1_arriba = new JPanel();
		JLabel j = new JLabel("Arriba");
		j.setPreferredSize(new Dimension(100,20));
		j1_arriba.add(j);
		CapturaTecla ct = new CapturaTecla(KeyEvent.VK_W);
		ct.addActionListener(ct);
		j1_arriba.add(ct);
		j1_teclas.add(ct);
		j1_controles.add(j1_arriba);
		JPanel j1_derecha = new JPanel();
		j = new JLabel("Derecha");
		j.setPreferredSize(new Dimension(100,20));
		j1_derecha.add(j);
		ct = new CapturaTecla(KeyEvent.VK_D);
		ct.addActionListener(ct);
		j1_teclas.add(ct);
		j1_derecha.add(ct);
		j1_controles.add(j1_derecha);
		JPanel j1_izquierda = new JPanel();
		j = new JLabel("Izquierda");
		j.setPreferredSize(new Dimension(100,20));
		j1_izquierda.add(j);
		ct = new CapturaTecla(KeyEvent.VK_A);
		ct.addActionListener(ct);
		j1_teclas.add(ct);
		j1_izquierda.add(ct);
		j1_controles.add(j1_izquierda);
		JPanel j1_abajo = new JPanel();
		j = new JLabel("Abajo");
		j.setPreferredSize(new Dimension(100,20));
		j1_abajo.add(j);
		ct = new CapturaTecla(KeyEvent.VK_S);
		ct.addActionListener(ct);
		j1_teclas.add(ct);
		j1_abajo.add(ct);
		j1_controles.add(j1_abajo);
		JPanel j1_bomba = new JPanel();
		j = new JLabel("Colocar bomba");
		j.setPreferredSize(new Dimension(100,20));
		j1_bomba.add(j);
		ct = new CapturaTecla(KeyEvent.VK_SPACE);
		ct.addActionListener(ct);
		j1_teclas.add(ct);
		j1_bomba.add(ct);
		j1_controles.add(j1_bomba);
		
		JPanel j2_controles = new JPanel();
		
		j2_controles.setLayout(new GridLayout(6,1));
		
		j2_controles.setSize(300,300);
		
		j2_controles.add(new JLabel("Controles jugador 1"));
		JPanel j2_arriba = new JPanel();
		j = new JLabel("Arriba");
		j.setPreferredSize(new Dimension(100,20));
		j2_arriba.add(j);
		ct = new CapturaTecla(KeyEvent.VK_UP);
		ct.addActionListener(ct);
		j2_arriba.add(ct);
		j2_teclas.add(ct);
		j2_controles.add(j2_arriba);
		JPanel j2_derecha = new JPanel();
		j = new JLabel("Derecha");
		j.setPreferredSize(new Dimension(100,20));
		j2_derecha.add(j);
		ct = new CapturaTecla(KeyEvent.VK_RIGHT);
		ct.addActionListener(ct);
		j2_teclas.add(ct);
		j2_derecha.add(ct);
		j2_controles.add(j2_derecha);
		JPanel j2_izquierda = new JPanel();
		j = new JLabel("Izquierda");
		j.setPreferredSize(new Dimension(100,20));
		j2_izquierda.add(j);
		ct = new CapturaTecla(KeyEvent.VK_LEFT);
		ct.addActionListener(ct);
		j2_teclas.add(ct);
		j2_izquierda.add(ct);
		j2_controles.add(j2_izquierda);
		JPanel j2_abajo = new JPanel();
		j = new JLabel("Abajo");
		j.setPreferredSize(new Dimension(100,20));
		j2_abajo.add(j);
		ct = new CapturaTecla(KeyEvent.VK_DOWN);
		ct.addActionListener(ct);
		j2_teclas.add(ct);
		j2_abajo.add(ct);
		j2_controles.add(j2_abajo);
		JPanel j2_bomba = new JPanel();
		j = new JLabel("Colocar bomba");
		j.setPreferredSize(new Dimension(100,20));
		j2_bomba.add(j);
		ct = new CapturaTecla(KeyEvent.VK_BEGIN);
		ct.addActionListener(ct);
		j2_teclas.add(ct);
		j2_bomba.add(ct);
		j2_controles.add(j2_bomba);
		
		
		controles.add(j1_controles);
		controles.add(j2_controles);
		nombreJugador_textField.setPreferredSize(new Dimension(200,26));
		nombreJugador_textField.setFont(new Font("Arial", Font.PLAIN, 25));
		nombreJugador_textField.setMaximumSize(new Dimension(200,26));
		nombreJugador_textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreJugador_textField.setVisible(true);

		nombreJugador2_textField.setPreferredSize(new Dimension(200,26));
		nombreJugador2_textField.setFont(new Font("Arial", Font.PLAIN, 25));
		nombreJugador2_textField.setMaximumSize(new Dimension(200,26));
		nombreJugador2_textField.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreJugador2_textField.setVisible(true);
		
		botonJugar.setText("Jugar");
		botonJugar.setAlignmentX(Component.CENTER_ALIGNMENT);
		botonJugar.setFont(new Font("Arial", Font.PLAIN, 25));
		botonJugar.setVisible(true);
		
		nombreJugador1_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreJugador1_label.setText("Jugador 1: ");
		nombreJugador1_label.setFont(new Font("Arial", Font.PLAIN, 25));

		nombreJugador2_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		nombreJugador2_label.setText("Jugador 2: ");
		nombreJugador2_label.setFont(new Font("Arial", Font.PLAIN, 25));
		
		error_label.setAlignmentX(Component.CENTER_ALIGNMENT);
		error_label.setFont(new Font("Arial", Font.PLAIN, 10));
		error_label.setVisible(true);
		
		titulo.setText(titulo_text);
		titulo.setAlignmentX(Component.CENTER_ALIGNMENT);
		titulo.setFont(new Font("Arial", Font.PLAIN, 40));
		titulo.setVisible(true);
		
		this.add(titulo);
		this.add(Box.createVerticalStrut(20));
		this.add(nombreJugador1_label);
		this.add(nombreJugador_textField);
		this.add(nombreJugador2_label);
		this.add(nombreJugador2_textField);
		this.add(resolucion_label);
		this.add(Box.createVerticalStrut(10));
		this.add(botonJugar);
		this.add(error_label);
		this.add(controles);
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getJugadores().add(nombreJugador_textField.getText());
				getJugadores().add(nombreJugador2_textField.getText());
				try {
					comprobarDatos();
					Partida.comenzar();
				} catch ( NombresIgualesException e ) {
					error_label.setText(e.getMessage());
				} catch ( TeclaDesconocidaException e) {
					error_label.setText(e.getMessage());
				}
			}
		});
		this.setVisible(true);
	}
	
	public ArrayList<String> getJugadores() {
		return nombresJugadores;
	}
	
	public ArrayList<CapturaTecla> getControles(int jugador) {
		if ( jugador == 1 ) {
			return j1_teclas;
		} else if ( jugador == 2 ) {
			return j2_teclas;
		} else {
			return null;
		}
	}
	
	public void comprobarDatos() throws NombresIgualesException, TeclaDesconocidaException {
		for ( int i = 0 ; i < getJugadores().size() ; i++ ) {
			for ( int j = 0 ; j < getJugadores().size() ; j++ ) {
				if ( i == j ) {
					continue;
				}
				if ( getJugadores().get(i).equals(getJugadores().get(j)) ) {
					throw new NombresIgualesException();
				}
			}
		}
		String t;
		for ( CapturaTecla j1_tecla : j1_teclas ) {
			t = j1_tecla.getText().split(" ")[0];
			if ( t.equals("Unknown") ) {
				throw new TeclaDesconocidaException();
			}
		}
		for ( CapturaTecla j2_tecla : j2_teclas ) {
			t = j2_tecla.getText().split(" ")[0];
			if ( t.equals("Unknown") ) {
				throw new TeclaDesconocidaException();
			}
		}
	}
}
