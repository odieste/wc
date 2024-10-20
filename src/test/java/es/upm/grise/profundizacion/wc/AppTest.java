package es.upm.grise.profundizacion.wc;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import java.io.*;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @Before
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    // Test case for counting lines (-l)
    @Test
    public void testLineCount() throws IOException {
        String[] args = {"-l", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t3\tsrc/test/resources/ejemplo.txt"));
    }

    // Test case for counting characters (-c)
    @Test
    public void testCharCount() throws IOException {
        String[] args = {"-c", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t11\tsrc/test/resources/ejemplo.txt"));
    }

    // Test case for counting words (-w)
    @Test
    public void testWordCount() throws IOException {
        String[] args = {"-w", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t3\tsrc/test/resources/ejemplo.txt"));
    }

    // Test case for counting lines and characters (-lc)
    @Test
    public void testLineAndCharCount() throws IOException {
        String[] args = {"-lc", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t3\t11\tsrc/test/resources/ejemplo.txt")); // Suponiendo que el archivo tiene 24 líneas y 144 caracteres
    }

    // Test case for counting words and characters (-wc)
    @Test
    public void testWordAndCharCount() throws IOException {
        String[] args = {"-wc", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t3\t11\tsrc/test/resources/ejemplo.txt")); // Suponiendo 30 palabras y 144 caracteres
    }

    // Test case for counting lines, words, and characters (-wlc)
    @Test
    public void testWordLineAndCharCount() throws IOException {
        String[] args = {"-wlc", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t3\t3\t11\tsrc/test/resources/ejemplo.txt"));
    }

    // Test for empty file
    @Test
    public void testEmptyFile() throws IOException {
        String[] args = {"-wlc", "src/test/resources/vacio.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t0\t0\t0\tsrc/test/resources/vacio.txt"));
    }

    // Test for file with multiple spaces
    @Test
    public void testMultipleSpaces() throws IOException {
        String[] args = {"-w", "src/test/resources/multiples_espacios.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t6\tsrc/test/resources/multiples_espacios.txt"));
        //falla el conteo de las palabras cuando se añaden varios espacios seguidos
    }

    // Test for file with tabs
    @Test
    public void testTabsInFile() throws IOException {
        String[] args = {"-wlc", "src/test/resources/tabulaciones.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("\t6\t3\t33\tsrc/test/resources/tabulaciones.txt")); //
    }

    // Test for file with only newlines
    @Test
    public void testOnlyNewlines() throws IOException {
        String[] args = {"-wlc", "src/test/resources/solo_saltos_de_linea.txt"};//Fichero con 5 saltos de linea
        App.main(args);
        assertTrue(outContent.toString().contains("\t0\t5\t5\tsrc/test/resources/solo_saltos_de_linea.txt"));
    }
    // Test for missing arguments
    @Test
    public void testMissingArguments() {
        String[] args = {};
        App.main(args);
        assertTrue(outContent.toString().contains("Usage: wc [-clw file]"));
    }

    // Test for invalid command
    @Test
    public void testInvalidCommand() {
        String[] args = {"-x", "src/test/resources/ejemplo.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("Unrecognized command: x"));
    }

    // Test for file not found
    @Test
    public void testFileNotFound() {
        String[] args = {"-l", "src/test/resources/no_existe.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("Cannot find file: src/test/resources/no_existe.txt"));
    }
}
