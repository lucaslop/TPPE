package exceptions;

public class EscritaNaoPermitidaException extends Exception {
	private static final long serialVersionUID = 1L;

	public EscritaNaoPermitidaException(String message){
        super("O Caminho não tem permissão de escrita: " + message);
	}
}