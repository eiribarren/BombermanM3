package Juego;

public class TeclaDesconocidaException extends Exception {
	public TeclaDesconocidaException() {
		super("No se pudo reconocer alguna de las teclas, por favor cambia la tecla Unknown");
	}
}
