package exceptions;


public class ArquivoNaoEncontrado extends Exception {

	public ArquivoNaoEncontrado(String arq){
        super("Erro ao abrir o arquivo: " + arq);
	}

}
