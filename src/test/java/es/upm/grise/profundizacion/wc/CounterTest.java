package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;
import java.nio.file.Files;

public class CounterTest {

    private static Counter emptyFileCounter;
    private static Counter randomFileCounter;

    // Variables for random file generation
    private static final int MAX_CHAR = 1000;
    private static int testChar;
    private static int testWord;
    private static int testLine;

    @BeforeAll
    static public void beforeAll() {
        String basedir = "src/test/rest/";

        // Create directories for generated files
        try {
            Path path = Paths.get(basedir);
            Files.createDirectories(path);
        } catch (IOException e) {
            System.out.println("An error occurred while creating directories.");
        }

        // Create counter for an empty file
        String emptyFilename = basedir + "EmptyFile.txt";
        try (FileWriter ignored = new FileWriter(emptyFilename, false)) {
            emptyFileCounter = new Counter(new BufferedReader(new FileReader(emptyFilename)));
        } catch (IOException e) {
            System.out.println("An error occurred while generating the empty file.");
        }

        // Create counter for an random file with MAX_CHAR characters
        Random random = new Random();
        String randomFilename = basedir + "RandomFile.txt";
        try (FileWriter writer = new FileWriter(randomFilename, false)) {
            testChar = random.nextInt(MAX_CHAR);
            testWord = 0;
            testLine = 0;
            int nChar = 0;
            while (nChar < testChar) {
                char c = (char) random.nextInt(256); // Random ASCII character
                switch (c) {
                    case '\n':
                        testLine++;
                    case ' ':
                    case '\t':
                        testWord++;
                }
                writer.write(c);
                nChar++;
            }
            writer.close();
            randomFileCounter = new Counter(new BufferedReader(new FileReader(randomFilename)));
        } catch (IOException e) {
            System.out.println("An error occurred while generating the random file.");
        }
    }

    @AfterAll
    static public void afterAll() {
    }

    @BeforeEach
    public void beforeEach() {
    }

    @AfterEach
    public void afterEach() {
    }

    @Test()
    public void emptyFileCharCount() {
        assertEquals(0, emptyFileCounter.getNumberCharacters());
    }

    @Test()
    public void emptyFileWordCount() {
        assertEquals(0, emptyFileCounter.getNumberWords());
    }

    @Test()
    public void emptyFileLineCount() {
        assertEquals(0, emptyFileCounter.getNumberLines());
    }

    @Test()
    public void ramdomFileCharCount() {
        assertEquals(testChar, randomFileCounter.getNumberCharacters());
    }

    @Test()
    public void randomFileWordCount() {
        assertEquals(testWord, randomFileCounter.getNumberWords());
    }

    @Test()
    public void randomFileLineCount() {
        assertEquals(testLine, randomFileCounter.getNumberLines());
    }
}