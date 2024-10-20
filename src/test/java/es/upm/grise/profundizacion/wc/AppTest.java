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
        String expedString = "Usage: wc [-clw file]";
        assertEquals(expedString, outContent.toString().trim(), "No arguments message is" + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testWrongArguments() {
        App.main(new String[]{"-c", "file1", "file2"});
        String expedString = "Wrong arguments!";
        assertEquals(expedString, outContent.toString().trim(), "Wrong arguments message is" + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testFileNotFound() {
        App.main(new String[]{"-c", "nonexistent.txt"});
        String expedString = "Cannot find file: nonexistent.txt";
        assertEquals(expedString, outContent.toString().trim(), "File not found message is" + expedString + " but returned " + outContent.toString().trim());
    }
}
