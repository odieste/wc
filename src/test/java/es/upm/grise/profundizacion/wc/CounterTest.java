package es.upm.grise.profundizacion.wc;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

public class CounterTest {
	private static final String EXAMPLE_FILE_PATH = Paths.get("example.txt").toString();
    private BufferedReader br;
    private Counter counter;

    @BeforeEach
    public void setUp() throws IOException {
        br = new BufferedReader(new FileReader(EXAMPLE_FILE_PATH));
        counter = new Counter(br);
    }

    @Test
    public void testNumberCharacters() throws IOException { // En windows al parecer son 2 caracteres para el salto de linea ¿?
        assertEquals(37, counter.getNumberCharacters(), "Expected character count does not match.");
    }

    @Test
    public void testNumberLines() throws IOException {
        assertEquals(5, counter.getNumberLines(), "Expected line count does not match.");
    }

    @Test
    public void testNumberWords() throws IOException {
        assertEquals(6, counter.getNumberWords(), "Expected word count does not match.");
    }

    @AfterEach
    public void tearDown() throws IOException {
        if (br != null) {
            br.close(); // Close after each test
        }
    }
    
}
