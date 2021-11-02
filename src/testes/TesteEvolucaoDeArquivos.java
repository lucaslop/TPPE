package testes;

import static org.junit.Assert.assertEquals;

import java.util.Vector;

import org.junit.Test;

import aplication.Parser;
import aplication.Reader;
import exceptions.ArquivoNaoEncontrado;

public class TesteEvolucaoDeArquivos {
	
	Parser parser = new Parser();
	Reader reader = new Reader(parser);
	
	@Test
	public void testeEvolucoesInteiros() throws ArquivoNaoEncontrado {
		reader.lerArquivo("arquivos/analysisTime.out","inteiro");
		Vector<Vector<Integer>> array = parser.getBufferInteger();
		assertEquals(21, array.size());
	}

	@Test
	public void testeEvolucoesDouble() throws ArquivoNaoEncontrado {
		reader.lerArquivo("arquivos/analysisMemory.out","double");
		Vector<Vector<Double>> array = parser.getBufferDouble();
		assertEquals(21, array.size());
	}
	
	@Test(expected=ArquivoNaoEncontrado.class)
	public void testeEvolucoesInexistente() throws ArquivoNaoEncontrado {
		reader.lerArquivo("arquivos/arquivoInexistente.out","inteiro");
		Vector<Vector<Integer>> array = parser.getBufferInteger();
		assertEquals(0, array.size());
	}

}
