package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;
import exceptions.DelimitadorException;
public class TesteDelimitador {

	
	Parser parser = new Parser();
	
	@Test
	public void testeDelimitadorCampo() throws DelimitadorException{
		parser.setDelimitador(";");
		assertEquals(';',parser.getDelimitador());
	}
	@Test
	public void teste02DelimitadorCampo() throws DelimitadorException{
		parser.setDelimitador("/");
		assertEquals('/',parser.getDelimitador());
	}
	
	@Test
	public void teste03DelimitadorCampo() throws DelimitadorException{
		parser.setDelimitador(",");
		assertEquals(',',parser.getDelimitador());
	}
	
	@Test(expected=DelimitadorException.class)
	public void testDelimitadorInvalidoException() throws DelimitadorException {
		parser.setDelimitador("string");
	}
}
