package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;

public class TesteFormatoDeSaida {

	Parser parser = new Parser();
	
	@Test
	public void testeFormatoDeSaida() {
		parser.setFormatoSaida(parser.coluna);
		assertEquals(parser.coluna, parser.getFormatoSaida());
	}
	
}
