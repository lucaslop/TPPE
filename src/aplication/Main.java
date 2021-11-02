/**
 * 
 */
package aplication;
import java.util.Scanner;

import exceptions.*;

/**
 * @author lucas
 *
 */



	public class Main {

		public static void main(String[] args) {
			Parser parser = new Parser();
			Scanner scanner = new Scanner(System.in);
			Writer writer = null;
			Reader reader = new Reader(parser);
			
			System.out.print("Digite a pasta do arquivo de entrada: ");
			String caminhoArquivoEntrada = scanner.next();
			
			System.out.print("Digite o tipo de dados do arquivo de entrada (1 - Inteiro, 2 - Double)");
			int tipoArquivo = scanner.nextInt();
			scanner.nextLine();

			try {
				if(tipoArquivo == 1)
					reader.lerArquivo(caminhoArquivoEntrada,"inteiro");
				if (tipoArquivo==2)
					reader.lerArquivo(caminhoArquivoEntrada, "double");
			} catch (ArquivoNaoEncontrado e) {
				System.out.println(e);
				System.exit(-1);
			}

			System.out.print("Digite o delimitador: ");
			String delimitador = scanner.nextLine();

			try {
				parser.setDelimitador(delimitador);
			} catch (DelimitadorException e) {
				System.out.println(e);
				System.exit(-1);
			}

			System.out.print("Digite o formato do arquivo de saida (0 - Linha, 1 - Coluna): ");
			int formatoArquivoSaida = scanner.nextInt();
			scanner.nextLine();

			if (formatoArquivoSaida == 0 || formatoArquivoSaida == 1) {
				parser.setFormato(formatoArquivoSaida);	
			} else {
				System.out.println("Formato de saída inválido.");
				System.exit(-1);
			}

			System.out.print("Digite o caminho da pasta para salvar o arquivo de saída: ");
			String caminhoArquivoSaida = scanner.nextLine();

			try {
				parser.setArquivoSaida(caminhoArquivoSaida);
			} catch (EscritaNaoPermitidaException e) {
				System.out.println(e);
				System.exit(-1);
			}
			
			writer = new Writer(parser);
								
			try {
				if(tipoArquivo == 1)
					writer.escreverArquivoDeResposta("inteiro");
				if(tipoArquivo == 2)
					writer.escreverArquivoDeResposta("double");
				
				System.out.println("Arquivo salvo na pasta " + caminhoArquivoSaida );

			} catch (Exception e) {
				 e.printStackTrace();
			}

			

			scanner.close();
		}

	}


