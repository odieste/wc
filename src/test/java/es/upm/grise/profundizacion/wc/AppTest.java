package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import java.io.*;

public class AppTest {

    // Random file
    private final static String randomFileName = "src/test/res/RandomFile.txt";
    private static RandomFile randomFile;

    @BeforeAll
    static public void beforeAll() {
        // Create directories for generated files if needed
        randomFile = new RandomFile(randomFileName);
    }

    @AfterAll
    static public void afterAll() {
        // Delete random file
        randomFile.delete();
    }

    @BeforeEach
    public void beforeEach() {
    }

    @AfterEach
    public void afterEach() {
    }

    @Test()
    // Check command response with 0 args
    public void zeroArguments() {
        String expected = "Usage: wc [-clw file]";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with wrong number of args
    public void wrongNumberArguments() {
        String expected = "Wrong arguments!";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"wc"};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check find not found response
    public void fileNotFound() {
        String filename = "abcdefghijklmnopqrstuvwxyz";
        String expected = "Cannot find file: " + filename;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-clw", filename};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response without file read permissions
    public void readPermissions() {
        String filename = "src/test/res/RandomFile.txt";
        String expected = "Error reading file: " + filename;
        File file = new File(filename);
        boolean permissionsSet = file.setReadable(false);

        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-clw", filename};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assumingThat(permissionsSet, () -> assertEquals(expected, actual));
    }

    @Test()
    // Check command response when options do no start with -
    public void wrongOptions() {
        String expected = "The commands do not start with -";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"clv", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with invalida option (w)
    public void unrecognizedOption() {
        String expected = "Unrecognized command: v";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-clv", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with c option
    public void cOptions() {
        String expected = "\t" + randomFile.getNChar() +
                "\t" + randomFileName;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-c", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with cw options
    public void cwOptions() {
        String expected = "\t" + randomFile.getNChar() +
                "\t" + randomFile.getNWord() +
                "\t" + randomFileName;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-cw", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with cwl options
    public void cwlOptions() {
        String expected = "\t" + randomFile.getNChar() +
                "\t" + randomFile.getNWord() +
                "\t" + randomFile.getNLine() + "\t" + randomFileName;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-cwl", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }

    @Test()
    // Check command response with wlc options
    public void wlcOptions() {
        String expected = "\t" + randomFile.getNWord() +
                "\t" + randomFile.getNLine() +
                "\t" + randomFile.getNChar() + "\t" + randomFileName;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        System.setOut(printStream);

        String[] args = {"-wlc", randomFileName};
        App.main(args); // call the main method

        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[lines.length - 1];

        // checkout output
        assertEquals(expected, actual);
    }
}
