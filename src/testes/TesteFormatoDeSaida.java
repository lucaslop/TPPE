package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;

public class TesteFormatoDeSaida {

	Parser parser = new Parser();
	
	@Test
	public void testeFormatoDeSaidaDeColuna() {
		parser.setFormato(Parser.coluna);
		assertEquals(Parser.coluna, parser.getFormato());
	}
	
	@Test
	public void testeDefinirFormatoSaidaLinha() {
		parser.setFormato(Parser.linha);
		assertEquals(Parser.linha, parser.getFormato());
	}
	
	@Test
	public void testeDefinirFormatoSaidaQualquer() {
		parser.setFormato('.');
		assertEquals('.', parser.getFormato());
	}
	
}
