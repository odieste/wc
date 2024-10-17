package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class CounterTest {

	String texto_1 = "Hola mundo\nEsto es una prueba\n";
	String texto_2 = "";
	String texto_3 = "a";
	String texto_4 = "una_unica_palabra";
	String texto_5 = "hola que tal";
	String texto_6 = "\n\n";
	
	@Test 
	public void cuentaCaracterVariasPalabras() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_1));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(30, count);
	}
	
	@Test 
	public void cuentaCaracterVacio() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_2));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(0, count);
	}
	
	@Test 
	public void cuentaCaracterUnico() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_3));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(1, count);
	}
	
	@Test 
	public void cuentaCaracteres() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_4));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(17, count);
	}
	
	@Test 
	public void cuentaCaracteres2() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_5));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(12, count);
	}
	
	@Test 
	public void cuentaCaracteres3() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_6));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberCharacters();
		assertEquals(2, count);
	}
	  /*********/
	 /* Words */
	/*********/
	@Test 
	public void cuentaPalabras() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_1));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(6, count);
	}
	
	public void cuentaPalabras2() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_5));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(3, count);
	}
	
	@Test 
	public void cuentaPalabrasVacias() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_2));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(0, count);
	}
	
	@Test 
	public void cuentaPalabra1() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_3));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(1, count);
	}
	
	@Test 
	public void cuentaPalabra2() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_4));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(1, count);
	}
	
	@Test 
	public void cuentaNoPalabra() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_6));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberWords();
		assertEquals(0, count);
	}
	
	
	  /*********/
	 /* Lines */
	/*********/
	@Test 
	public void cuentaLinea() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_1));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberLines();
		assertEquals(2, count);
	}
	
	public void cuentaLinea2() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_5));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberLines();
		assertEquals(1, count);
	}
	
	@Test 
	public void cuentaLinea3() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_3));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberLines();
		assertEquals(1, count);
	}
	
	@Test 
	public void cuentaLineasVacias() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_2));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberLines();
		assertEquals(0, count);
	}
	

	@Test 
	public void cuentaLineas() throws IOException {
		BufferedReader reader = new BufferedReader(new StringReader(texto_6));
		Counter counter = new Counter(reader);
		
		int count = counter.getNumberLines();
		assertEquals(2, count);
	}
}
