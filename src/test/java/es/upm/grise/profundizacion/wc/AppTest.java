package es.upm.grise.profundizacion.wc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;

import org.junit.jupiter.api.Test;
import java.io.PrintStream;

public class AppTest {

    @Test
    public void testUsageMessageWhenNoArgs() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        App.main(new String[] {});
        
        assertEquals("Usage: wc [-clw file]\n".trim(), output.toString().trim());
    }

    @Test
    public void testFileNotFound() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        App.main(new String[] {"-c", "non_existent_file.txt"});
        
        assertTrue(output.toString().contains("Cannot find file"));
    }

    @Test
    public void testStartWithHyphen() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
        
        App.main(new String[] {"c", "ejemplo.txt"});
        
        assertTrue(output.toString().contains("The commands do not start with -"));
    }

    @Test
    public void testCountLinesAndChars() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[] {"-lc", "ejemplo.txt"});
        
        assertEquals("\t7\t116\tejemplo.txt\n".trim(), output.toString().trim());
    }

    @Test
    public void testCountWords() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[] {"-w", "ejemplo.txt"});
        
        assertEquals("\t20\tejemplo.txt\n".trim(), output.toString().trim());
    }
}
