package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private File tempFile;

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut); // Reset System.out
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }


    @Test
    public void testMissingArguments() {
        System.setOut(new PrintStream(outContent));

        String[] args = {};
        App.main(args);

        String expectedOutput = "Usage: wc [-clw file]";
        assertEquals(expectedOutput.trim(), outContent.toString().trim(), "Expected usage output does not match.");
    }

    @Test
    public void testWrongArguments() {
        System.setOut(new PrintStream(outContent));

        String[] args = {"wrongArgument"};
        App.main(args);

        String expectedOutput = "Wrong arguments!";
        assertEquals(expectedOutput.trim(), outContent.toString().trim(), "Expected wrong arguments output does not match.");
    }
}
