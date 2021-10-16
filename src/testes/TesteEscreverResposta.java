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
	
	@Test
	public void teste02EscreverArquivoRespostaInteiroColuna() throws ArquivoNaoEncontrado, DelimitadorException, FileNotFoundException, EscritaNaoPermitidaException {
		parser.lerArq("arquivos/analysisTime.out","inteiro");
		parser.setDelimitador("/");
		parser.setFormatoSaida(Parser.coluna);
		parser.setArquivoSaida("arquivos");
		parser.escreverArquivoDeResposta("inteiro");

		String path = parser.getArquivoDeResposta();
		Scanner input = new Scanner(new FileReader(path));
		Vector <Vector <Integer>> array = new Vector <Vector <Integer>>();
		int numero_linhas=0;
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split("/");
			if(numero_linhas == 0) {
				for(String each:colunas) {
					array.add(new Vector<Integer>());
				}
			}else {
				for(int i=0; i<colunas.length; i++) {
					array.elementAt(i).add(Integer.valueOf(colunas[i]));
				}
			}

			numero_linhas++;
		}

		assertEquals(array, parser.getBufferInteger());
	}
	
	@Test
	public void teste03EscreverArquivoRespostaDoubleColuna() throws ArquivoNaoEncontrado, DelimitadorException, FileNotFoundException, EscritaNaoPermitidaException {
		parser.lerArq("arquivos/analysisMemory.out","double");
		parser.setDelimitador("/");
		parser.setFormatoSaida(Parser.coluna);
		parser.setArquivoSaida("arquivos");
		parser.escreverArquivoDeResposta("double");

		String path = parser.getArquivoDeResposta();
		Scanner input = new Scanner(new FileReader(path));
		Vector <Vector <Double>> array = new Vector <Vector <Double>>();
		int numero_linhas=0;
		while(input.hasNextLine()) {
			String dados = input.nextLine();
			String colunas[] = dados.split("/");
			if(numero_linhas == 0) {
				for(String each:colunas) {
					array.add(new Vector<Double>());
				}
			}else {
				for(int i=0; i<colunas.length; i++) {
					array.elementAt(i).add(Double.valueOf(colunas[i]));
				}
			}

			numero_linhas++;
		}

		assertEquals(array, parser.getBufferDouble());
	}
}


