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

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Parser {
	// Vetor de inteiros
	private Vector <Vector <Integer>> arq;
	// Vetor de Doubles
	private Vector <Vector <Double>> arqDouble;
	private char delimitador;
	private String caminhoDeSaida;
	public static int coluna=0;
	public static int linha=1;
	private int formato;
	private String nomeArquivoDeSaida;
	private String nomeArquivoDeEntrada;
	
	public Parser(){
		arq = new Vector <Vector <Integer>>();
		arqDouble = new Vector <Vector <Double>>();
		delimitador=';';
		this.caminhoDeSaida="arquivos/";
		this.formato = coluna;
	}
	

	public Vector<Vector<Integer>> getArq() {
		return arq;
	}



	public void setArq(Vector<Vector<Integer>> arq) {
		this.arq = arq;
	}



	public Vector<Vector<Double>> getArqDouble() {
		return arqDouble;
	}



	public void setArqDouble(Vector<Vector<Double>> arqDouble) {
		this.arqDouble = arqDouble;
	}



	public char getDelimitador() {
		return delimitador;
	}



	public void setDelimitador(char delimitador) {
		this.delimitador = delimitador;
	}



	public String getCaminhoDeSaida() {
		return caminhoDeSaida;
	}



	public void setCaminhoDeSaida(String caminhoDeSaida) {
		this.caminhoDeSaida = caminhoDeSaida;
	}



	public static int getColuna() {
		return coluna;
	}



	public static void setColuna(int coluna) {
		Parser.coluna = coluna;
	}



	public static int getLinha() {
		return linha;
	}



	public static void setLinha(int linha) {
		Parser.linha = linha;
	}



	public int getFormato() {
		return formato;
	}



	public void setFormato(int formato) {
		this.formato = formato;
	}



	public String getNomeArquivoDeEntrada() {
		return nomeArquivoDeEntrada;
	}



	public void setNomeArquivoDeEntrada(String nomeArquivoDeEntrada) {
		this.nomeArquivoDeEntrada = nomeArquivoDeEntrada;
	}



	public void setNomeArquivoDeSaida(String nomeArquivoDeSaida) {
		this.nomeArquivoDeSaida = nomeArquivoDeSaida;
	}
	

	public String getNomeArquivoDeSaida() {
		return nomeArquivoDeSaida;
	}


	// fun��o para ler arquivo
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
				if(tipo == "inteiro") {
					Vector<Integer> row = new Vector<Integer>();
					arq.add(row);
				}else if(tipo == "double") {
					Vector<Double> row = new Vector<Double>();
					arqDouble.add(row);
				}
			}
			else {
				if(tipo == "inteiro") {
					arq.lastElement().add(Integer.parseInt(data));
				}else if(tipo == "double") {
					arqDouble.lastElement().add(Double.parseDouble(data));
				}
				
			}
		}
		
		input.close();
		
		int finalDoArquivo = arquivo.lastIndexOf('/');
		if(finalDoArquivo != -1) {
			this.nomeArquivoDeEntrada = arquivo.substring(finalDoArquivo+1);
		} else {
			this.nomeArquivoDeEntrada = arquivo;
		}
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
	
	public FileWriter abrirArquivo() throws IOException {
		File arquivo = new File(this.caminhoDeSaida+this.nomeArquivoDeSaida);
    	if(!arquivo.exists()) {
    		arquivo.createNewFile();
    	}
		
    	FileWriter filew = new FileWriter(arquivo);
    	return filew;
	}
	
	public void fecharArquivo(BufferedWriter bufferedWriter, FileWriter filew) {
		try {
			bufferedWriter.close();
			filew.close();
		} catch (IOException error) {
			error.printStackTrace();
		}
	}
}
