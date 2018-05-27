package Juego;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MenuPrincipal extends JPanel {
	private ArrayList<String> nombresJugadores;
	
	public MenuPrincipal(String titulo_text) {
		this.setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		this.setPreferredSize(new Dimension(300,600));
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
		botonJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				boolean error_nombres_iguales = false;
				getJugadores().add(nombreJugador_textField.getText());
				getJugadores().add(nombreJugador2_textField.getText());
				for ( int i = 0 ; i < getJugadores().size() ; i++ ) {
					for ( int j = 0 ; j < getJugadores().size() ; j++ ) {
						if ( i == j ) {
							continue;
						}
						if ( getJugadores().get(i).equals(getJugadores().get(j)) ) {
							error_nombres_iguales = true;
						}
					}
				}
				if ( error_nombres_iguales ) {
					error_label.setText("Los nombres de los jugadores deben ser diferentes");
				} else {
					Partida.comenzar();
				}
			}
		});
		this.setVisible(true);
	}
	
	public ArrayList<String> getJugadores() {
		return nombresJugadores;
	}
}
