package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import uk.org.webcompere.systemstubs.SystemStubs;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class AppTest {

    // Helper method to create a temporary file with the given content
    private File createTempFile(String content) throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit(); // Automatically delete the file when the JVM exits
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

    // Test for "caracteres_especiales.txt"
    @Test
    public void testCaracteresEspeciales() throws Exception {
        File tempFile = createTempFile("@ # $ % ^ & * ( ) ! ?");
        String[] args = {"-c", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("\t21\t" + tempFile.getAbsolutePath()));
    }

    // Test for "ejemplo.txt"
    @Test
    public void testEjemplo() throws Exception {
        File tempFile = createTempFile("A\nB\nCDE\n");
        String[] args = {"-wlc", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("\t3\t3\t8\t" + tempFile.getAbsolutePath())); // 3 lines, 3 words, 11 characters
    }

    // Test for "multiples_espacios.txt"
    @Test
    public void testMultiplesEspacios() throws Exception {
        File tempFile = createTempFile("Hola     mundo    esto   es    una   prueba");
        String[] args = {"-w", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("\t19\t" + tempFile.getAbsolutePath())); // 6 words
    }

    // Test for "tabulaciones.txt"
    @Test
    public void testTabulaciones() throws Exception {
        File tempFile = createTempFile("Hola\tmundo\nEsto\tes\nuna\tprueba\n");
        String[] args = {"-wlc", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("\t6\t3\t30\t" + tempFile.getAbsolutePath())); // 3 lines, 6 words, 33 characters
    }

    // Test for an empty file
    @Test
    public void testEmptyFile() throws Exception {
        File tempFile = createTempFile("");
        String[] args = {"-wlc", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("\t0\t0\t0\t" + tempFile.getAbsolutePath())); // 0 lines, 0 words, 0 characters
    }

    // Test for missing arguments
    @Test
    public void testMissingArguments() throws Exception {
        String[] args = {};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("Usage: wc [-clw file]"));
    }

    // Test for invalid command
    @Test
    public void testInvalidCommand() throws Exception {
        File tempFile = createTempFile("A\nB\nCDE\n");
        String[] args = {"-x", tempFile.getAbsolutePath()};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("Unrecognized command: x"));
    }

    // Test for file not found
    @Test
    public void testFileNotFound() throws Exception {
        String[] args = {"-l", "nonexistent.txt"};
        String output = SystemStubs.tapSystemOut(() -> App.main(args));
        assertTrue(output.contains("Cannot find file: nonexistent.txt"));
    }
}
