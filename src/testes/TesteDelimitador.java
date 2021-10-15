package testes;

import aplication.Parser;

public class TesteDelimitador {

	
	Parser parser = new Parser();
	
	public void testeDelimitadorCampo() throws DelimitadorException{
		parser.setDelimitador(';');
		assertEquals(';',parser.getDelimitador());
	}
}
