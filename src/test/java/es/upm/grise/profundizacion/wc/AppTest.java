package es.upm.grise.profundizacion.wc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintStream;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class AppTest {

    private final File testFile = new File("testFile.txt");
    private final File emptyFile = new File("emptyFile.txt");

    @AfterEach
    public void cleanUpFiles() {
        if (testFile.exists()) {
            testFile.delete();
        }
        if (emptyFile.exists()) {
            emptyFile.delete();
        }
    }

    @Test
    public void testUsageWithoutParameters() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{});

        String actualOutput = output.toString().trim();
        assertEquals("Usage: wc [-clw file]", actualOutput);
    }

    @Test
    public void testFileNotFound() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-c", "nonexistent.txt"});

        assertEquals("Cannot find file: nonexistent.txt", output.toString().trim());
    }

    @Test
    public void testInvalidArguments() {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-c", "file.txt", "extraParam"});

        assertEquals("Wrong arguments!", output.toString().trim());
    }

    @Test
    public void testCorrectOutput() throws Exception {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Hello World\nThis is a test.\n");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-lcw", testFile.getName()});

        assertEquals("2\t28\t6\ttestFile.txt", output.toString().trim());
    }

    @Test
    public void testCorrectOutputWithOnlyCharactersFlag() throws Exception {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Hello World\nThis is a test.\n");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-c", testFile.getName()});
        assertEquals("28\ttestFile.txt", output.toString().trim());
    }

    @Test
    public void testCorrectOutputWithOnlyWordsFlag() throws Exception {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Hello World\nThis is a test.\n");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-w", testFile.getName()});
        assertEquals("6\ttestFile.txt", output.toString().trim());
    }

    @Test
    public void testCorrectOutputWithOnlyLinesFlag() throws Exception {
        try (FileWriter writer = new FileWriter(testFile)) {
            writer.write("Hello World\nThis is a test.\n");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-l", testFile.getName()});
        assertEquals("2\ttestFile.txt", output.toString().trim());
    }

    @Test
    public void testCorrectOutputForEmptyFile() throws Exception {
        try (FileWriter writer = new FileWriter(emptyFile)) {
            writer.write("");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-lcw", emptyFile.getName()});
        assertEquals("0\t0\t0\temptyFile.txt", output.toString().trim());
    }

    @Test
    public void testInvalidFlag() throws Exception {
        String filePath = "testFile.txt";
        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write("This is a test.");
        }

        ByteArrayOutputStream output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));

        App.main(new String[]{"-z", filePath});

        assertEquals("Unrecognized command: z", output.toString().trim());

        new File(filePath).delete();
    }

}
