package aplication;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

import exceptions.EscritaNaoPermitidaException;

public class Writer {
	private Parser parser;
	private static String ARQUIVO_INTEIRO = "analysisTimeTab.out";
	private static String ARQUIVO_DOUBLE = "analysisMemoryTab.out";

	public Writer(Parser parser) {
		this.parser = parser;
	}
	
	public Parser getParser() {
		return this.parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}
	
	public void escreveArquivoInteiroFormatoLinha() throws EscritaNaoPermitidaException, IOException {
		FileWriter filew = parser.abrirArquivo();
		BufferedWriter bufferedWriter = new BufferedWriter(filew);
		Vector <Vector<Integer>> arqInt = parser.getBufferInteger();

		for(int indexArquivo = 0; indexArquivo < arqInt.size(); indexArquivo++) {
			if(indexArquivo != 0) bufferedWriter.newLine();
			bufferedWriter.write(Integer.toString(indexArquivo + 1));

			for (int i = 0; i < arqInt.elementAt(indexArquivo).size(); i++) {
				bufferedWriter.write(parser.getDelimitador());
				bufferedWriter.write(Integer.toString(arqInt.elementAt(indexArquivo).elementAt(i)));
			}
		}

		parser.fecharArquivo(bufferedWriter, filew);
	}

	public void escreveArquivoInteiroFormatoColuna() throws EscritaNaoPermitidaException, IOException {
		FileWriter filew = parser.abrirArquivo();
		BufferedWriter bufferedWriter = new BufferedWriter(filew);
		Vector <Vector<Integer>> arqInt = parser.getBufferInteger();

		int tamanhoMax=0;
		for(int i = 0; i < arqInt.size(); i++) {
			if(i!=0) bufferedWriter.write(parser.getDelimitador());
			bufferedWriter.write(Integer.toString(i+1));
			if(arqInt.elementAt(i).size() > tamanhoMax) tamanhoMax = arqInt.elementAt(i).size();
		}
		bufferedWriter.newLine();
		for(int k = 0; k<tamanhoMax; k++) { 
			if(k != 0) bufferedWriter.newLine();
			for(int i = 0; i < arqInt.size(); i++) {
				if(i != 0) bufferedWriter.write(parser.getDelimitador());
				if(arqInt.elementAt(i).size() > k) {
					bufferedWriter.write(Integer.toString(arqInt.elementAt(i).elementAt(k)));
				}
			}
		}

		parser.fecharArquivo(bufferedWriter, filew);
	}

	public void escreveArquivoDoubleFormatoLinha() throws IOException {
		FileWriter filew = parser.abrirArquivo();
		BufferedWriter bufferedWriter = new BufferedWriter(filew);
		Vector <Vector<Double>> arqDouble = parser.getBufferDouble();

		for(int indexArquivo = 0; indexArquivo < arqDouble.size(); indexArquivo++) {
			if(indexArquivo != 0) bufferedWriter.newLine();
			bufferedWriter.write(Double.toString(indexArquivo+1));
			for (int i = 0; i < arqDouble.elementAt(indexArquivo).size(); i++) {
				bufferedWriter.write(parser.getDelimitador());
				bufferedWriter.write(Double.toString(arqDouble.elementAt(indexArquivo).elementAt(i)));
			}
		}

		parser.fecharArquivo(bufferedWriter, filew);
	}

	public void escreveArquivoDoubleFormatoColuna() throws IOException {
		FileWriter filew = parser.abrirArquivo();
		BufferedWriter bufferedWriter = new BufferedWriter(filew);
		Vector <Vector<Double>> arqDouble = parser.getBufferDouble();

		int tamMax=0;
		for(int i = 0; i < arqDouble.size(); i++) {
			if(i!=0) bufferedWriter.write(parser.getDelimitador());
			bufferedWriter.write(Double.toString(i+1));
			if(arqDouble.elementAt(i).size() > tamMax) 
				tamMax = arqDouble.elementAt(i).size();
		}
		bufferedWriter.newLine();
		for(int j = 0; j<tamMax; j++) { 
			if(j != 0) bufferedWriter.newLine();
			for(int i = 0; i<arqDouble.size(); i++) {
				if(i != 0) bufferedWriter.write(parser.getDelimitador());
				if(arqDouble.elementAt(i).size() > j) {
					bufferedWriter.write(Double.toString(arqDouble.elementAt(i).elementAt(j)));
				}
			}
		}

		parser.fecharArquivo(bufferedWriter, filew);
	}
	
	public void escreverArquivoDeResposta(String tipo) throws EscritaNaoPermitidaException {
		String caminho = parser.getCaminhoDeSaida();

		int auxiliar = -1;
		int aux2 = caminho.length();
		for(int i = aux2 - 1; i >= 0; i--) {
			if(caminho.charAt(i) == '.') {
				auxiliar = i;
				break;
			}
		}
		
		if(tipo.equals("inteiro")) { 
			parser.setNomeArquivoDeSaida(ARQUIVO_INTEIRO);
		}else{
			parser.setNomeArquivoDeSaida(ARQUIVO_DOUBLE);
		}
		
		//tenta criar o arquivo
		try {
			File arquivo = new File(parser.getCaminhoDeSaida()+parser.getNomeArquivoDeSaida());
			
			if(!arquivo.exists()) {
				arquivo.createNewFile();
			}

			FileWriter filew = new FileWriter(arquivo);
			BufferedWriter buffereWritter = new BufferedWriter(filew);
			
			if(tipo == "inteiro") {
				if(parser.getFormato() == Parser.linha) {
					this.escreveArquivoInteiroFormatoLinha();
				} else if(parser.getFormato() == Parser.coluna) {
					try{
						escreveArquivoInteiroFormatoColuna();
					}catch(Exception e) {
						e.printStackTrace();
					}
				}
			}

			if(tipo == "double") {
				if(parser.getFormato() == Parser.linha) {
					escreveArquivoDoubleFormatoLinha();
				} else if(parser.getFormato() == Parser.coluna) {
					escreveArquivoDoubleFormatoColuna();
				}
			}

			parser.fecharArquivo(buffereWritter, filew);
		} catch (Exception e) {
			System.out.println(e);
			throw new EscritaNaoPermitidaException(caminho);
		}
	}

	
}
