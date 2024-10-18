
package es.upm.grise.profundizacion.wc;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.IOException;


public class AppTest {

    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    public final String path_txt="src\\\\test\\\\java\\\\es\\\\upm\\\\grise\\\\profundizacion\\\\wc\\\\";
    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(System.out);
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
    public void testFileNotFound() {
        App.main(new String[]{"-c", "no_existe.txt"});
        assertEquals("Cannot find file: no_existe.txt\r\n", outContent.toString());
    }

    @Test
    public void testUnrecognizedCommand() throws IOException {
        String fileName = path_txt +"testA.txt";
        App.main(new String[]{"-t", fileName});
        assertEquals("Unrecognized command: t\r\n", outContent.toString());
    }
    @Test
    public void testEmptyFile() throws IOException {
        String fileName = path_txt + "test_empty.txt";
        App.main(new String[]{"-clw", fileName});
        assertTrue(outContent.toString().contains("\t0\t0\t0\t" + fileName));
    }
    @Test
    public void testCountCharactersA() throws IOException {
        String fileName = path_txt + "testA.txt";
        App.main(new String[]{"-c", fileName});
        assertTrue(outContent.toString().contains("\t182\t" + fileName));
    }
    @Test
    public void testCountCharactersB() throws IOException {
        String fileName =  path_txt + "testB.txt";
        App.main(new String[]{"-c", fileName});
        assertTrue(outContent.toString().contains("\t27\t" + fileName));
    }

    @Test
    public void testCountCharactersC() throws IOException {
        String fileName =  path_txt + "testC.txt";
        App.main(new String[]{"-c", fileName});
        assertTrue(outContent.toString().contains("\t30\t" + fileName));
    }
    @Test
    public void testCountLinesA() throws IOException {
        String fileName =  path_txt + "testA.txt";
        App.main(new String[]{"-l", fileName});
        assertTrue(outContent.toString().contains("\t4\t" + fileName));
    }
    @Test
    public void testCountLinesB() throws IOException {
        String fileName = path_txt + "testB.txt";
        App.main(new String[]{"-l", fileName});
        assertTrue(outContent.toString().contains("\t5\t" + fileName));
    }

    @Test
    public void testCountWordsA() throws IOException {
        String fileName = path_txt + "testA.txt";
        App.main(new String[]{"-w", fileName});
        assertTrue(outContent.toString().contains("\t33\t" + fileName));
    }
    @Test
    public void testCountWordsC() throws IOException {
        String fileName = path_txt + "testC.txt";
        App.main(new String[]{"-w", fileName});
        assertTrue(outContent.toString().contains("\t6\t" + fileName));
    }
    @Test
    public void testMultipleCommandsCL() throws IOException {
        String fileName = path_txt + "testB.txt";
        App.main(new String[]{"-cl", fileName});
        assertTrue(outContent.toString().contains("\t27\t5\t" + fileName));
    }
    @Test
    public void testMultipleCommandsLW() throws IOException {
        String fileName = path_txt +"testC.txt";
        App.main(new String[]{"-lw", fileName});
        assertTrue(outContent.toString().contains("\t4\t6\t" + fileName));
    }
    @Test
    public void testMultipleCommandsCW() throws IOException {
        String fileName = path_txt +"testA.txt";
        App.main(new String[]{"-cw", fileName});
        assertTrue(outContent.toString().contains("\t182\t33\t" + fileName));
    }
    @Test
    public void testMultipleCommandsCLW() throws IOException {
        String fileName = path_txt +"testC.txt";
        App.main(new String[]{"-clw", fileName});
        assertTrue(outContent.toString().contains("\t30\t4\t6\t" + fileName));
    }
}