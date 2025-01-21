package es.upm.grise.profundizacion.wc;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CounterTest {

    @Test
    public void testCountCharacters() throws Exception {
        String content = "Hello World\nThis is a test.\n";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(28, counter.getNumberCharacters());
    }

    @Test
    public void testCountWords() throws Exception {
        String content = "Hello World\nThis is a test.\n";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(6, counter.getNumberWords());
    }

    @Test
    public void testCountLines() throws Exception {
        String content = "Hello World\nThis is a test.\n";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(2, counter.getNumberLines());
    }

    @Test
    public void testEmptyFile() throws Exception {
        String content = "";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);
        assertEquals(0, counter.getNumberCharacters());
        assertEquals(0, counter.getNumberWords());
        assertEquals(0, counter.getNumberLines());
    }

    @Test
    public void testFileWithMultipleEmptyLines() throws Exception {
        String content = "\n\n\n";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(3, counter.getNumberLines());
        assertEquals(3, counter.getNumberWords()); // Cada salto de línea cuenta como una palabra
        assertEquals(3, counter.getNumberCharacters());
    }

    @Test
    public void testFileWithOnlySpaces() throws Exception {
        String content = "     ";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(5, counter.getNumberCharacters());
        assertEquals(5, counter.getNumberWords()); // Cada espacio cuenta como una palabra
        assertEquals(0, counter.getNumberLines());
    }

    @Test
    public void testFileWithOneWord() throws Exception {
        String content = "Word";
        BufferedReader br = new BufferedReader(new StringReader(content));
        Counter counter = new Counter(br);

        assertEquals(4, counter.getNumberCharacters());
        assertEquals(0, counter.getNumberWords()); // Counter no detecta palabras sin separadores
        assertEquals(0, counter.getNumberLines());
    }

}
