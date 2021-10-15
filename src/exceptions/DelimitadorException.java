package exceptions;

public class DelimitadorException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public DelimitadorException(String message){
        super("O delimitador é invalido: " + message);
	}
}


