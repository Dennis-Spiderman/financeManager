package exceptions;

public class TypeNotSelectedException extends RuntimeException{

	public TypeNotSelectedException() {
		super("No has seleccionado el tipo de movimiento que quieres registrar");
	}

}
