package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
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
        App.main(new String[] {});
        String expedString = "Usage: wc [-clw file]";
        assertEquals(expedString, outContent.toString().trim(),
                "No arguments message is" + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testWrongArguments() {
        App.main(new String[] { "-c", "file1", "file2" });
        String expedString = "Wrong arguments!";
        assertEquals(expedString, outContent.toString().trim(),
                "Wrong arguments message is" + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testFileNotFound() {
        App.main(new String[] { "-c", "nonexistent.txt" });
        String expedString = "Cannot find file: nonexistent.txt";
        assertEquals(expedString, outContent.toString().trim(),
                "File not found message is" + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testCCommand() throws IOException {

        String filePathString = getTempFileString("Hello World\nThis is a test file.\n");

        App.main(new String[] { "-c", filePathString });
        String expedString = "33" + "\t" + filePathString;
        String resulString = outContent.toString().trim();
        assertEquals(expedString, resulString, "Character count is " + expedString + " but returned " + resulString);
    }

    @Test
    public void testLCommand() throws IOException {

        String filePathString = getTempFileString("Hello World\nThis is a test file.\n");

        App.main(new String[] { "-l", filePathString });
        String expedString = "2" + "\t" + filePathString;
        assertEquals(expedString, outContent.toString().trim(),
                "Line count is " + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testWCommand() throws IOException {

        String filePathString = getTempFileString("Hello World\nThis is a test file.\n");

        App.main(new String[] { "-w", filePathString });
        String expedString = "7" + "\t" + filePathString;
        assertEquals(expedString, outContent.toString().trim(),
                "Word count is " + expedString + " but returned " + outContent.toString().trim());
    }

    @Test
    public void testCommands() throws IOException {

        String filePathString = getTempFileString("Hello World\nThis is a test file.\n");

        App.main(new String[] { "-clw", filePathString });
        String expedString = "33\t2\t7" + "\t" + filePathString;
        assertEquals(expedString, outContent.toString().trim(),
                "Counts are " + expedString + " but returned " + outContent.toString().trim());
    }

    private String getTempFileString(String content) throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        Files.write(tempFile.toPath(), content.getBytes());
        return tempFile.toPath().toString();
    }
}
