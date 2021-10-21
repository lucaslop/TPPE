package testes;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;
import java.util.Vector;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import aplication.Parser;
import exceptions.ArquivoNaoEncontrado;

@RunWith(Parameterized.class)
public class TesteParametrizadoValoresArquivos {
	private String arquivo;
	private int numeroDeEvolucao;
	private int numeroDeAnalises;


	public TesteParametrizadoValoresArquivos(String arquivo, int numeroEvolucao, int numeroAnalises) {
		this.arquivo = arquivo;
		this.numeroDeEvolucao = numeroEvolucao;
		this.numeroDeAnalises = numeroAnalises;
	}

	@Parameters
	public static Iterable<Object[]> getParameters() {
		Object[][] data = new Object[][] {
			{"arquivos/analysisTime.out", 1, 11},
			{"arquivos/analysisTime.out", 2, 11},
			{"arquivos/analysisTime.out", 3, 11},
			{"arquivos/analysisTime.out", 4, 11},
			{"arquivos/analysisTime.out", 5, 11},
			{"arquivos/analysisTime.out", 6, 11},
			{"arquivos/analysisTime.out", 7, 11},
			{"arquivos/analysisTime.out", 8, 11},
			{"arquivos/analysisTime.out", 9, 11},
			{"arquivos/analysisTime.out", 10, 11},
	        {"arquivos/analysisTime.out", 11, 11},
			{"arquivos/analysisTime.out", 12, 11},
			{"arquivos/analysisTime.out", 13, 11},
			{"arquivos/analysisTime.out", 14, 11},
			{"arquivos/analysisTime.out", 15, 11},
			{"arquivos/analysisTime.out", 16, 11},
			{"arquivos/analysisTime.out", 17, 11},
			{"arquivos/analysisTime.out", 18, 11},
			{"arquivos/analysisTime.out", 19, 11},
			{"arquivos/analysisTime.out", 20, 11},
			{"arquivos/analysisTime.out", 21, 11},
	
		};
		return Arrays.asList(data);
	}
	


	@Test
	public void testNumeroAnalises() throws ArquivoNaoEncontrado {
		Parser parser = new Parser();
		parser.lerArq(arquivo,"inteiro");
		Vector<Vector<Integer>> array = parser.getBufferInteger();
		assertEquals(numeroDeAnalises, array.elementAt(numeroDeEvolucao-1).size());
	}


}