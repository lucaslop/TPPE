package testes;

import static org.junit.Assert.assertEquals;

import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import org.junit.Test;

import aplication.Parser;
import exceptions.*;

public class TesteEscreverResposta {
	
	Parser parser = new Parser();

	
	@Test
	public void testeEscreverRespostaInteiro()throws ArquivoNaoEncontrado, DelimitadorException {
		parser.lerArq("arquivos/analysisTime.out", "inteiro");
		parser.setDelimitador("/");
		parser.setFormatoSaida(Parser.linha);
		parser.escreverResposta();
		
		String caminho = parser.getArquivoSaida();
		Scanner input = new Scanner(new FileReader(caminho));
		Vector <Vector <Integer>> array = new Vector <Vector <Integer>>();
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split("/");

			Vector<Integer> linha = new Vector<Integer>();
			for(int i=1; i<colunas.length; i++) {
				linha.add(Integer.parseInt(colunas[i]));
			}

		}

		assertEquals(array, parser.getBufferInteger());
	}
	
	
	@Test
	public void testeEscreverRespostaDouble()throws ArquivoNaoEncontrado, DelimitadorException {
		parser.lerArq("arquivos/analysisMemory.out", "double");
		parser.setDelimitador("/");
		parser.setFormatoSaida(Parser.linha);
		parser.escreverResposta();
		
		String caminho = parser.getArquivoSaida();
		Scanner input = new Scanner(new FileReader(caminho));
		Vector <Vector <Double>> array = new Vector <Vector <Double>>();
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split("/");

			Vector<Double> linha = new Vector<Double>();
			for(int i=1; i<colunas.length; i++) {
				linha.add(Double.parseDouble(colunas[i]));
			}

		}

		assertEquals(array, parser.getBufferInteger());
	}
}


