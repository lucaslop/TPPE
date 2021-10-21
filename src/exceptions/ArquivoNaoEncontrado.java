package exceptions;



public class ArquivoNaoEncontrado extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public ArquivoNaoEncontrado(String msg){
        super("Nao foi possivel abrir o arquivo: " + msg);
	}

}