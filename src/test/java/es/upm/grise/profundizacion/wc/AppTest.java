package es.upm.grise.profundizacion.wc;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AppTest {

    private static Path testFile = Paths.get("ejemplo.txt");

    @BeforeAll
    public static void setup() throws IOException {
        Files.writeString(testFile, "kjdbvws wonvwofjw\n sdnfwijf ooj    kjndfohwouer 21374 vehf\n jgfosj\n\nskfjwoief ewjf\n\n\ndkfgwoihgpw vs wepfjwfin");
    }

    @AfterAll
    public static void teardown() {
        try {
            Files.deleteIfExists(testFile);
        } catch (IOException e) {
            System.err.println("Error deleting test file: " + e.getMessage());
            try {
                Thread.sleep(100);
                Files.deleteIfExists(testFile);
            } catch (IOException | InterruptedException ex) {
                System.err.println("Failed to delete test file on retry: " + ex.getMessage());
            }
        }
    }


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
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);
        
        try {
            App.main(new String[] {"c", testFile.toString()});
        }
        finally {
            printStream.close();
        }
        
        
        assertTrue(output.toString().contains("The commands do not start with -"));
    }

    @Test
    public void testCountLinesAndChars() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        try {
            App.main(new String[] {"-lc", testFile.toString()});
        }
        finally {
            printStream.close();
        }
        
        assertEquals(("\t7\t109\t" + testFile.getFileName().toString() + "\n").trim(), output.toString().trim());
    }

    @Test
    public void testCountWords() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(output);
        System.setOut(printStream);

        try {
            App.main(new String[] {"-w", testFile.toString()});
        }
        finally {
            printStream.close();
        }
        
        assertEquals(("\t20\t" + testFile.getFileName().toString() + "\n").trim(), output.toString().trim());
    }
}
