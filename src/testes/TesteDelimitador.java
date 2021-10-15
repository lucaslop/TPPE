package testes;

import aplication.Parser;
import exceptions.DelimitadorException;
public class TesteDelimitador {

	
	Parser parser = new Parser();
	
	public void testeDelimitadorCampo() throws DelimitadorException{
		parser.setDelimitador(';');
		assertEquals(';',parser.getDelimitador());
	}
}
