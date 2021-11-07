package testes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import aplication.Parser;
import aplication.Writer;
import exceptions.EscritaNaoPermitidaException;


public class TesteArquivoDeSaida {

		Parser parser = new Parser();
		Writer writer = new Writer(parser);
		
		@Test
		public void testeArquivoDeSaida() throws EscritaNaoPermitidaException {
			parser.setArquivoSaida("arquivos/");
			assertEquals("arquivos/", parser.getNomeArquivoDeSaida());
		}
		
		@Test
		public void testeArquivoDeSaida02() throws EscritaNaoPermitidaException {
			parser.setArquivoSaida("arquivos");
			assertEquals("arquivos/", parser.getNomeArquivoDeSaida());
		}
		
		@Test
		public void testeArquivoDeSaida03() throws EscritaNaoPermitidaException {
			parser.setArquivoSaida("./arquivos/");
			assertEquals("./arquivos/", parser.getNomeArquivoDeSaida());
		}

		@Test(expected=EscritaNaoPermitidaException.class)
		public void testeArquivoDeSaida04Exception() throws EscritaNaoPermitidaException {
			parser.setArquivoSaida("/./arquivos");
		}
}	


