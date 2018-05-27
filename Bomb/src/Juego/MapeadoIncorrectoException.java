package Juego;

public class MapeadoIncorrectoException extends Exception{
	public MapeadoIncorrectoException() {
		super("El mapeado introducido es incorrecto");
	}
	public MapeadoIncorrectoException(String mensaje) {
		super(mensaje);
	}
}
