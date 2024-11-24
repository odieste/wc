package es.upm.grise.profundizacion.wc;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.PrintStream;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CounterTest {

    private File tempFile;

    private File createTempFile(String content) throws IOException {
        tempFile = File.createTempFile("testFile", ".txt");
        FileWriter writer = new FileWriter(tempFile);
        writer.write(content);
        writer.close();
        return tempFile;
    }

    @AfterEach
    public void tearDown() {
        if (tempFile != null && tempFile.exists()) {
            tempFile.delete();
        }
    }

    @Test
    public void testNumberLines() throws IOException {
        String content = "With the first pick in the 2003 NBA Draft\nThe Cleveland Cavaliers select\nLeBron James\n"; // Add a trailing newline
        int expLines = 3;
        File file = createTempFile(content);
        BufferedReader br = new BufferedReader(new FileReader(file));
        Counter counter = new Counter(br);
        assertEquals(expLines, counter.getNumberLines(), "Expected " + expLines + " lines");
    }

    @Test
    public void testWordCount() throws IOException {
        String content = "Thunder trade Josh Giddey to Bulls in exchange for Alex Caruso";
        int expWords = 10; // Count words manually
        File file = createTempFile(content);
        BufferedReader br = new BufferedReader(new FileReader(file));
        Counter counter = new Counter(br);
        assertEquals(expWords, counter.getNumberWords(), "Expected " + expWords + " words");
    }

    @Test
    public void testNumberCharacters() throws IOException {
        String content = "JDub";
        int expChars = 4;
        File file = createTempFile(content);
        BufferedReader br = new BufferedReader(new FileReader(file));
        Counter counter = new Counter(br);
        assertEquals(expChars, counter.getNumberCharacters(), "Expected " + expChars + " characters");
    }
}

