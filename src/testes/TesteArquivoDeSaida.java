package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;


public class TesteArquivoDeSaida {

		Parser parse = new Parser();
		
		@Test
		public void testeArquivoDeSaida() throws EscritaNaoPermitidaException {
			parser.setArquivoSaida("arquivos/");
			assertEquals("arquivos/", parser.getArquivoSaida());
		}
}


