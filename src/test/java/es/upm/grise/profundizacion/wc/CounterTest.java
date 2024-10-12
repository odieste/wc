package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.*;

public class CounterTest {

    // Random file
    private final static String randomFileName = "src/test/res/RandomFile.txt";
    private static RandomFile randomFile;

    private static BufferedReader randomFileBR;
    private static Counter randomFileCounter;

    // Variables for random file generation
    @BeforeAll
    static public void beforeAll() {
        // Create directories for generated files if needed
        randomFile = new RandomFile(randomFileName);

        try {
            randomFileBR = new BufferedReader(new FileReader(randomFileName));
            randomFileCounter = new Counter(randomFileBR);
        } catch (IOException e) {
            // Ignored
        }
    }

    @AfterAll
    static public void afterAll() {
        try {
            randomFileBR.close();
        } catch (IOException e) {
            // Ignored
        }

        // Delete random file
        randomFile.delete();
    }

    @BeforeEach
    public void beforeEach() {
    }

    @AfterEach
    public void afterEach() {
    }

    @Test()
    // Detect IOException during initialization
    public void counterIOException() {
        try {
            BufferedReader br = new BufferedReader(new FileReader(randomFileName));
            br.close(); // Force I/O exception in next line
            assertThrows(IOException.class, () -> new Counter(br));
        } catch (IOException e) {
            // Ignored
        }
    }

    @Test()
    // Check getNChar() method
    public void randomFileCharCount() {
        assertEquals(randomFile.getNChar(), randomFileCounter.getNumberCharacters());
    }

    @Test()
    // Check getWord() method
    public void randomFileWordCount() {
        assertEquals(randomFile.getNWord(), randomFileCounter.getNumberWords());
    }

    @Test()
    // Check getLine() method
    public void randomFileLineCount() {
        assertEquals(randomFile.getNLine(), randomFileCounter.getNumberLines());
    }
}