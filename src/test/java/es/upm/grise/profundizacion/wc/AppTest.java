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

public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outContent)); // Redirect System.out to capture output
    }

    @AfterEach
    public void tearDown() {
        System.setOut(originalOut); // Restore original System.out
    }

    // Test case for line count
    @Test
    public void testMainWithLineCount() {
        String[] args = {"-l", "example.txt"};
        App.main(args);
        String expectedOutput = "5\texample.txt";  
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected line count.");
    }

    // Test case for word count
    @Test
    public void testMainWithWordCount() {
        String[] args = {"-w", "example.txt"};
        App.main(args);
        String expectedOutput = "6\texample.txt";  
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected word count.");
    }

    // Test case for character count
    @Test
    public void testMainWithCharacterCount() {
        String[] args = {"-c", "example.txt"};
        App.main(args);
        String expectedOutput = "37\texample.txt";  
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected character count.");
    }

    // Test case for wrong arguments
    @Test
    public void testMainWithWrongArguments() {
        String[] args = {"wrongArgument"};
        App.main(args);
        String expectedOutput = "Wrong arguments!";  
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected usage message.");
    }

    // Test case for non-existent file
    @Test
    public void testMainWithNonExistentFile() {
        String[] args = {"-l", "nonexistent.txt"};
        App.main(args);
        String expectedOutput = "Cannot find file: nonexistent.txt";
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected file not found message.");
    }

    // Test case for unrecognized command
    @Test
    public void testMainWithWrongCommand() {
        String[] args = {"-x", "example.txt"};
        App.main(args);
        String expectedOutput = "Unrecognized command: x";
        assertEquals(expectedOutput, outContent.toString().trim(), "Output does not match expected unrecognized command message.");
    }
}
