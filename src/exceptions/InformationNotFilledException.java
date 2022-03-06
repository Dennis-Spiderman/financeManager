package exceptions;

public class InformationNotFilledException extends RuntimeException{

	public InformationNotFilledException() {
		super("No has llenado la información necesaria para registrar el gasto o ingreso.");
	}

}
