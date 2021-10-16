package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;

public class TesteFormatoDeSaida {

	Parser parser = new Parser();
	
	@Test
	public void testeFormatoDeSaidaDeColuna() {
		parser.setFormatoSaida(Parser.coluna);
		assertEquals(Parser.coluna, parser.getFormatoSaida());
	}
	
	@Test
	public void testeDefinirFormatoSaidaLinha() {
		parser.setFormatoSaida(Parser.linha);
		assertEquals(Parser.linha, parser.getFormatoSaida());
	}
	
	@Test
	public void testeDefinirFormatoSaidaGenerico() {
		parser.setFormatoSaida('.');
		assertEquals('.', parser.getFormatoSaida());
	}
	
}
