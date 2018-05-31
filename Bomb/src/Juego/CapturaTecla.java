package Juego;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JButton;


public class CapturaTecla extends JButton implements ActionListener{
	
	private int tecla;
	
	public CapturaTecla() {
		super();
		this.setPreferredSize(new Dimension(100,20));
	}
	
	public CapturaTecla(int tecla) {
		this();
		this.tecla = tecla;
		this.setText(KeyEvent.getKeyText(tecla));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		this.addKeyListener(new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				tecla = e.getKeyCode();
				setText(KeyEvent.getKeyText(tecla));
				removeKeyListener(this);
			}

			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				
			}
		});
	}
	
	public int getTecla() {
		return this.tecla;
	}
}
