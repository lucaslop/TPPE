package testes;

import org.junit.Test;

import aplication.Parser;

public class TesteFormatoDeSaida {

	Parser parser = new Parser();
	
	@Test
	public void testeFormatoDeSaida() {
		parser.setFormatoSaida(parser.formato);
		assertEquals(parser.formato, parser.getFormatoSaida());
	}
	
}
