package Juego;

public class NombresIgualesException extends Exception {
	public NombresIgualesException() {
		super("Los nombres de los jugadores no pueden ser iguales");
	}
}
