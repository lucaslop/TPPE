package aplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;

import exceptions.ArquivoNaoEncontrado;

public class Reader {
	private Parser parser;
	
	public Reader(Parser parser) {
		this.parser = parser;
	}
	
	public Parser getParser() {
		return this.parser;
	}

	public void setParser(Parser parser) {
		this.parser = parser;
	}
	
	public void lerArquivo(String arquivo, String tipo) throws ArquivoNaoEncontrado {
		Scanner input;
		Vector <Vector<Integer>> arqInt = parser.getBufferInteger();
		Vector <Vector<Double>> arqDouble = parser.getBufferDouble();
		
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
					arqInt.add(row);
				}else if(tipo == "double") {
					Vector<Double> row = new Vector<Double>();
					arqDouble.add(row);
				}
			}
			else {
				if(tipo == "inteiro") {
					arqInt.lastElement().add(Integer.parseInt(data));
				}else if(tipo == "double") {
					arqDouble.lastElement().add(Double.parseDouble(data));
				}
				
			}
		}
		
		input.close();
		
		int finalDoArquivo = arquivo.lastIndexOf('/');
		if(finalDoArquivo != -1) {
			parser.setNomeArquivoDeEntrada(arquivo.substring(finalDoArquivo+1));
		} else {
			parser.setNomeArquivoDeEntrada(arquivo);
		}
	}

	
}
