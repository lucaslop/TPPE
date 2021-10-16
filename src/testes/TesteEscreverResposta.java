package testes;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import org.junit.Test;

import aplication.Parser;
import exceptions.*;

public class TesteEscreverResposta {
	
	Parser parser = new Parser();

	
	@Test
	public void testeEscreverRespostaInteiro()throws ArquivoNaoEncontrado, DelimitadorException, FileNotFoundException, EscritaNaoPermitidaException {
		parser.lerArq("arquivos/analysisTime.out", "inteiro");
		parser.setDelimitador(",");
		parser.setFormatoSaida(Parser.linha);
		parser.escreverArquivoDeResposta("inteiro");
		
		String caminho = parser.getArquivoDeResposta();
		Scanner input = new Scanner(new FileReader(caminho));
		Vector <Vector <Integer>> array = new Vector <Vector <Integer>>();
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split(",");

			Vector<Integer> linha = new Vector<Integer>();
			 int col = colunas.length;
			for(int i=1; i<col; i++) {
				linha.add(Integer.parseInt(colunas[i]));
			}
			array.add(linha);

		}

		assertEquals(array, parser.getBufferInteger());
	}
	
	
	@Test
	public void testeEscreverRespostaDouble()throws ArquivoNaoEncontrado, DelimitadorException, FileNotFoundException, EscritaNaoPermitidaException {
		parser.lerArq("arquivos/analysisMemory.out", "double");
		parser.setFormatoSaida(Parser.linha);
		parser.setDelimitador(",");
		parser.escreverArquivoDeResposta("double");
		
		String caminho = parser.getArquivoDeResposta();
		Scanner input = new Scanner(new FileReader(caminho));
		Vector <Vector <Double>> array = new Vector <Vector <Double>>();
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split(",");

			Vector<Double> linha = new Vector<Double>();
			int col = colunas.length;
			for(int i=1; i<col; i++) {
				linha.add(Double.parseDouble(colunas[i]));
			}
			array.add(linha);
		}

		assertEquals(array, parser.getBufferDouble());
	}
	


}


