
package es.upm.grise.profundizacion.wc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.io.IOException;


public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private String contentEmpty = "";
    private String contentA = "En un lugar de la Mancha,\n" + //
                "de cuyo nombre no quiero acordarme,\n" + //
                "no ha mucho tiempo que vivía un hidalgo de los de lanza en astillero,\n" + //
                "adarga antigua, rocín flaco y galgo corredor.\n";

    private String contentB = "Esto\n" + //
                "es\n" + //
                "una\n" + //
                "pr4-ba\n" + //
                ":)";
    private String contentC = "que hora es?\n" + //
                "8t\n" + //
                "hola!\n" + //
                "24+";
    
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
    }

    private Path createTestFile(String fileName, String content) throws IOException {
        Path filePath = Files.createTempFile(fileName, ".txt");
        Files.writeString(filePath, content);
        return filePath; 
    }

    private void deleteTestFile(Path filePath) throws IOException {
        try{
            Files.deleteIfExists(filePath);
        } catch (IOException e) {}
    }

    @Test
    public void testNoArguments() {
        App.main(new String[]{});
        assertEquals("Usage: wc [-clw file]\r\n", outContent.toString());
    }

    @Test
    public void testWrongArguments() {
        App.main(new String[]{"-c", "file1.txt", "extraArg"});
        assertEquals("Wrong arguments!\r\n", outContent.toString());
    }
    @Test
    public void testUnrecognizedCommand() throws IOException {
        Path filePath = createTestFile("test_empty", contentEmpty);
        App.main(new String[]{"-t", filePath.toString()});
        assertEquals("Unrecognized command: t\r\n", outContent.toString());
        deleteTestFile(filePath);
    }
    @Test
    public void testEmptyFile() throws IOException {
        Path filePath = createTestFile("test_empty", contentEmpty);
        App.main(new String[]{"-clw", filePath.toString()});
        assertTrue(outContent.toString().contains("\t0\t0\t0\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testCountCharactersA() throws IOException {
        Path filePath = createTestFile("testA", contentA);
        App.main(new String[]{"-c", filePath.toString()});
        assertTrue(outContent.toString().contains("\t178\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testCountCharactersB() throws IOException {
        Path filePath = createTestFile("testB", contentB);
        App.main(new String[]{"-c", filePath.toString()});
        assertTrue(outContent.toString().contains("\t21\t" + filePath.toString()));
        deleteTestFile(filePath);
    }

    @Test
    public void testCountCharactersC() throws IOException {
        Path filePath = createTestFile("testC", contentC);
        App.main(new String[]{"-c", filePath.toString()});
        assertTrue(outContent.toString().contains("\t25\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testCountLinesA() throws IOException {
        Path filePath = createTestFile("testA", contentA);
        App.main(new String[]{"-l", filePath.toString()});
        assertTrue(outContent.toString().contains("\t4\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testCountLinesB() throws IOException {
        Path filePath = createTestFile("testB", contentB);
        App.main(new String[]{"-l", filePath.toString()});
        assertTrue(outContent.toString().contains("\t4\t" + filePath.toString()));
        deleteTestFile(filePath);
    }

    @Test
    public void testCountWordsA() throws IOException {
        Path filePath = createTestFile("testA", contentA);
        App.main(new String[]{"-w", filePath.toString()});
        assertTrue(outContent.toString().contains("\t33\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testCountWordsC() throws IOException {
        Path filePath = createTestFile("testC", contentC);
        App.main(new String[]{"-w", filePath.toString()});
        assertTrue(outContent.toString().contains("\t5\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testMultipleCommandsCL() throws IOException {
        Path filePath = createTestFile("testB", contentB);
        App.main(new String[]{"-cl", filePath.toString()});
        assertTrue(outContent.toString().contains("\t21\t4\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testMultipleCommandsLW() throws IOException {
        Path filePath = createTestFile("testC", contentC);
        App.main(new String[]{"-lw", filePath.toString()});
        assertTrue(outContent.toString().contains("\t3\t5\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testMultipleCommandsCW() throws IOException {
        Path filePath = createTestFile("testA", contentA);
        App.main(new String[]{"-cw", filePath.toString()});
        assertTrue(outContent.toString().contains("\t178\t33\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
    @Test
    public void testMultipleCommandsCLW() throws IOException {
        
        Path filePath = createTestFile("testC", contentC);
        App.main(new String[]{"-clw", filePath.toString()});
        assertTrue(outContent.toString().contains("\t25\t3\t5\t" + filePath.toString()));
        deleteTestFile(filePath);
    }
}