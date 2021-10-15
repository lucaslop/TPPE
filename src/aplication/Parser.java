package aplication;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.Vector;
import exceptions.ArquivoNaoEncontrado;

public class Parser {
	// Vetor de inteirros
	private Vector <Vector <Integer>> arq;
	// Vetor de Doubles
	private Vector <Vector <Double>> arqDouble;

	
	
	public Parser(){
		arq = new Vector <Vector <Integer>>();
		arqDouble = new Vector <Vector <Double>>();
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
	
}


