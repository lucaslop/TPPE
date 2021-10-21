package testes;


import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import aplication.Parser;
import exceptions.DelimitadorException;

@RunWith(Parameterized.class)
public class TesteParametrizadoDelimitador {

	private String delimitadorErrado;
	private char delimitadorCorreto;

	public TesteParametrizadoDelimitador(String delimitador, char delimitadorCorreto) {
		this.delimitadorErrado = delimitador;
		this.delimitadorCorreto = delimitadorCorreto;
	}

	@Parameters
	public static Iterable<Object[]> getParameters() {
		Object[][] delimitadores = new Object[][] {
			{"*" , '*'},
			{"&" , '&'},
			{"_" , '_'},
			{"+" , '+'},
			{"-" , '-'},
			{"\n", '\n'},
			{"\r", '\r'},
			{"\t", '\t'},
			{";",';'}
			
		
		};
		return Arrays.asList(delimitadores);
	}

	@Test
	public void testDefinirDelimitadorDeCampo() throws DelimitadorException {
		Parser parser = new Parser();
		parser.setDelimitador(this.delimitadorErrado);
		assertEquals(this.delimitadorCorreto, parser.getDelimitador());
	}

} 