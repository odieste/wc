package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.io.StringReader;

public class Tests {

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
        String[] args = {};
        App.main(args);
        assertTrue(outContent.toString().contains("Usage: wc [-clw file]"));    
    }

    @Test
    public void testTooManyArguments() {
        String[] args = {"-c", "file.txt", "extraArgument"};
        App.main(args);
        assertTrue(outContent.toString().contains("Wrong arguments!"));    
    }

    @Test
    public void testFileNotFound() {
        String[] args = {"-c", "non_existent_file.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("Cannot find file: "));    
    }
    
    @Test
    public void testNoDashCommand() {
        String[] args = {"x", "sample.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("The commands do not start with -"));    
    }
    
    @Test
    public void testWrongCommand() {
        String[] args = {"-x", "sample.txt"};
        App.main(args);
        assertTrue(outContent.toString().contains("Unrecognized command: "));    
    }

    @Test
    public void testC() throws Exception {
        String fileName = "sample.txt";
        try (PrintStream ps = new PrintStream(fileName)) {
            ps.println("Hello World");
        }

        String[] args = {"-c", fileName};
        App.main(args);
        assertTrue(outContent.toString().contains("\t13")); 
    }
    
    @Test
    public void testL() throws Exception {
        String fileName = "sample.txt";
        try (PrintStream ps = new PrintStream(fileName)) {
            ps.println("This is the first line");
            ps.println("This is the second line");
        }

        String[] args = {"-l", fileName};
        App.main(args);
        assertTrue(outContent.toString().contains("\t2")); 
    }
    
    @Test
    public void testW() throws Exception {
        String fileName = "sample.txt";
        try (PrintStream ps = new PrintStream(fileName)) {
            ps.println("The program should read six words");
        }

        String[] args = {"-w", fileName};
        App.main(args);
        assertTrue(outContent.toString().contains("\t6")); 
    }
    
    @Test
    public void testCLW() throws Exception {
        String fileName = "sample.txt";
        try (PrintStream ps = new PrintStream(fileName)) {
            ps.println("Hello World");
        }

        String[] args = {"-clw", fileName};
        App.main(args);
        assertTrue(outContent.toString().contains("\t13\t1\t2")); 
    }
    
    @Test
    public void testCounterWithEmptyInput() throws IOException {
        String input = ""; 
        BufferedReader br = new BufferedReader(new StringReader(input));

        Counter counter = new Counter(br);

        assertEquals(0, counter.getNumberCharacters(), "Debería haber 0 caracteres");
        assertEquals(0, counter.getNumberWords(), "Debería haber 0 palabras");
        assertEquals(0, counter.getNumberLines(), "Debería haber 0 líneas");
    }
    
    @Test
    public void testCounterIOException() {
        BufferedReader br = new BufferedReader(new StringReader("test")) {
            @Override
            public int read() throws IOException {
                throw new IOException("Test IOException");
            }
        };

        assertThrows(IOException.class, () -> {
            new Counter(br);
        });
    }
    
}
