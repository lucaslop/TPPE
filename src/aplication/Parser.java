package aplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;
import exceptions.ArquivoNaoEncontrado;
import exceptions.DelimitadorException;
import exceptions.EscritaNaoPermitidaException;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Parser {
	// Vetor de inteirros
	private Vector <Vector <Integer>> arq;
	// Vetor de Doubles
	private Vector <Vector <Double>> arqDouble;
	private char delimitador;
	private String caminhoDeSaida;
	public static int coluna=0;
	public static int linha=1;
	private int formato;
	
	public Parser(){
		arq = new Vector <Vector <Integer>>();
		arqDouble = new Vector <Vector <Double>>();
		delimitador=';';
		caminhoDeSaida="arquivos/";
		this.formato = coluna;
	}

	// função para ler arquivo
	public void lerArq(String arquivo,String tipo) throws ArquivoNaoEncontrado {
		Scanner input;
		try {
			input  = new Scanner(new FileReader(arquivo));
		} catch(FileNotFoundException exception) {
			throw new ArquivoNaoEncontrado(arquivo);
		}
		
		while(input.hasNextLine()) {

			String data = input.nextLine();
			
			if (data.startsWith("-")) {
				if(tipo=="inteiro") {
					Vector<Integer> row = new Vector<Integer>();
					arq.add(row);
				}else if(tipo == "double") {
					Vector<Double> row = new Vector<Double>();
					arqDouble.add(row);
				}
			}
			else {
				if(tipo=="inteiro") {
					arq.lastElement().add(Integer.parseInt(data));
				}else if(tipo =="double") {
					arqDouble.lastElement().add(Double.parseDouble(data));
				}
				
			}
		}
		
		input.close();
	}

	
	public Vector <Vector <Integer>> getBufferInteger() {
		return arq;
	}
	
	public Vector<Vector<Double>> getBufferDouble() {
		return arqDouble;
	}
	
	public void setDelimitador(String delimitador) throws DelimitadorException {
		if (delimitador.length() == 1) {
			this.delimitador = delimitador.charAt(0);
		}
		else {
			throw new DelimitadorException(delimitador);
		}
	}

	public char getDelimitador() {
		return delimitador;
	}
	
	
	public void setArquivoSaida(String caminho) throws EscritaNaoPermitidaException {
		
		boolean barra = caminho.endsWith("/");
		//System.out.println(barra);
		if (!barra) {
			caminho = caminho + "/";
		}

		Path caminhosaida = Paths.get(caminho);
		
		if(!Files.isWritable(caminhosaida)) {
			throw new EscritaNaoPermitidaException(caminho);
		}

		this.caminhoDeSaida = caminho;
	}
	

	public String getArquivoSaida() {
		return caminhoDeSaida;
	}
	
	public void setFormatoSaida(int formato) {
		this.formato = formato;

	}

	public int getFormatoSaida() {
		return formato;
	}

	
}


