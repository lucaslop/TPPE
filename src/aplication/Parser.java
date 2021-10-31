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
	// Vetor de inteirros
	private Vector <Vector <Integer>> arq;
	// Vetor de Doubles
	private Vector <Vector <Double>> arqDouble;
	private char delimitador;
	private String caminhoDeSaida;
	public static int coluna=0;
	public static int linha=1;
	private int formato;
	private String ArquivoDeSaida;
	private String nomeArquivoDeEntrada;
	
	public Parser(){
		arq = new Vector <Vector <Integer>>();
		arqDouble = new Vector <Vector <Double>>();
		delimitador=';';
		caminhoDeSaida="arquivos/";
		this.formato = coluna;
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

	public void escreverArquivoDeResposta(String tipo) throws EscritaNaoPermitidaException {
		String caminho = this.nomeArquivoDeEntrada;
		int auxiliar = -1;
		int aux2 = caminho.length();
	    for(int i = aux2 - 1; i >= 0; i--) {
	        if(caminho.charAt(i) == '.') {
	            auxiliar = i;
	            break;
	        }
	    }

	    if(auxiliar == 0) {
	    	caminho = "Tab" + caminho;
	    } else if(auxiliar != -1) {
	    	caminho = caminho.substring(0,auxiliar) + "Tab" + caminho.substring(auxiliar);
	    } else {
	    	caminho = caminho + "Tab";
	    }

	    caminho = caminhoDeSaida + caminho;
	    this.ArquivoDeSaida = caminho;

	    //tenta criar o arquivo
	    try {
	    	File arquivo = new File(caminho);
	    	if(!arquivo.exists()) {
	    		arquivo.createNewFile();
	    	}

	    	FileWriter filew = new FileWriter(arquivo);
	    	BufferedWriter buffereWritter = new BufferedWriter(filew);
	    	// p formatos de linha
	    	if(tipo == "inteiro") {
			    if(this.formato == linha) {
			    	escreveArquivoInteiroFormatoLinha();
			    } else if(this.formato == coluna) {
			    	int tamanhoMax=0;
			    	for(int i=0; i<arq.size(); i++) {
			    		if(i!=0) buffereWritter.write(this.delimitador);
			    		buffereWritter.write(Integer.toString(i+1));
			    		if(arq.elementAt(i).size() > tamanhoMax) tamanhoMax = arq.elementAt(i).size();
			    	}
			    	buffereWritter.newLine();
			    	for(int k=0; k<tamanhoMax; k++) { 
			    		if(k!=0) buffereWritter.newLine();
			    		for(int i=0; i<arq.size(); i++) {
			    			if(i!=0) buffereWritter.write(this.delimitador);
			    			if(arq.elementAt(i).size() > k) {
			    				buffereWritter.write(Integer.toString(arq.elementAt(i).elementAt(k)));
			    			}
			    		}
			    	}
	
			    }
	    }
	    	
	    	if(tipo == "double") {
			    if(this.formato == linha) {
			    	for(int indexArquivo = 0; indexArquivo < this.arqDouble.size(); indexArquivo++) {
			    		if(indexArquivo != 0) buffereWritter.newLine();
			    		buffereWritter.write(Double.toString(indexArquivo+1));
			    		for (int i=0; i < this.arqDouble.elementAt(indexArquivo).size(); i++) {
			    			buffereWritter.write(this.delimitador);
			    			buffereWritter.write(Double.toString(arqDouble.elementAt(indexArquivo).elementAt(i)));
			    		}
			    	}
			    } else if(this.formato == coluna) {
			    	int tamMax=0;
			    	for(int i=0; i<arqDouble.size(); i++) {
			    		if(i!=0) buffereWritter.write(this.delimitador);
			    		buffereWritter.write(Double.toString(i+1));
			    		if(arqDouble.elementAt(i).size() > tamMax) 
			    			tamMax = arqDouble.elementAt(i).size();
			    	}
			    	buffereWritter.newLine();
			    	for(int j=0; j<tamMax; j++) { 
			    		if(j!=0) buffereWritter.newLine();
			    		for(int i=0; i<arqDouble.size(); i++) {
			    			if(i!=0) buffereWritter.write(this.delimitador);
			    			if(arqDouble.elementAt(i).size() > j) {
			    				buffereWritter.write(Double.toString(arqDouble.elementAt(i).elementAt(j)));
			    			}
			    		}
			    	}
	
			    }
	    }
	    	

	    	buffereWritter.close();
		    filew.close();
	    } catch (Exception e) {
	    	System.out.println(e);
	    	throw new EscritaNaoPermitidaException(caminho);
	    }
		
	}
	
	public FileWriter abrirArquivo() throws IOException {
		File arquivo = new File(this.ArquivoDeSaida);
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
	
	public void escreveArquivoInteiroFormatoLinha() throws EscritaNaoPermitidaException, IOException {
		FileWriter filew = abrirArquivo();
    	BufferedWriter bufferedWriter = new BufferedWriter(filew);
    	
    	for(int indexArquivo = 0; indexArquivo < this.arq.size(); indexArquivo++) {
    		if(indexArquivo != 0) bufferedWriter.newLine();
    		bufferedWriter.write(Integer.toString(indexArquivo+1));
    		for (int i=0; i < this.arq.elementAt(indexArquivo).size(); i++) {
    			bufferedWriter.write(this.delimitador);
    			bufferedWriter.write(Integer.toString(arq.elementAt(indexArquivo).elementAt(i)));
    		}
    	}
    	
    	fecharArquivo(bufferedWriter, filew);
	}
	}
	
	public String getArquivoDeResposta() {
		return ArquivoDeSaida;
	}

	
}


