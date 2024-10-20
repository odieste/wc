package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void testNoArguments() {
        App.main(new String[]{});
        assertEquals("Usage: wc [-clw file]".trim(), outContent.toString().trim());
    }

    @Test
    public void testWrongArguments() {
        App.main(new String[]{"-c", "file1", "file2"});
        assertEquals("Wrong arguments!".trim(), outContent.toString().trim());
    }

    @Test
    public void testFileNotFound() {
        App.main(new String[]{"-c", "nonexistent.txt"});
        assertEquals("Cannot find file: nonexistent.txt".trim(), outContent.toString().trim());
    }
}
