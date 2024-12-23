package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.*;
import java.io.*;

import static org.junit.jupiter.api.Assertions.*;

class AppTest {
    private final ByteArrayOutputStream contenido=new ByteArrayOutputStream();
    private final PrintStream salida=System.out;
    @BeforeEach
    void setUpStreams() {
        System.setOut(new PrintStream(contenido));
    }
    @AfterEach
    void restoreStreams() {
        System.setOut(salida);
    }
    @Test
    void testNoArguments() {
        App.main(new String[]{});
        String expectedOutput="Usage: wc [-clw file]" + System.lineSeparator();
        assertEquals(expectedOutput,contenido.toString());
    }
    @Test
    void testWrongNumberOfArguments() {
        App.main(new String[]{"-c","file.txt","extraArg"});
        assertEquals("Wrong arguments!"+System.lineSeparator(),contenido.toString());
    }

    @Test
    void testFileNotFound(){
        App.main(new String[]{"-c","nonexistent.txt"});
        assertEquals("Cannot find file: nonexistent.txt"+System.lineSeparator(),contenido.toString());
    }

    @Test
    void testInvalidCommand() {
        File tempFile=null;
        try {
            tempFile = File.createTempFile("test",".txt");
            try (FileWriter writer = new FileWriter(tempFile)) {
                writer.write("Hello World\n");
            }

            App.main(new String[]{"-x",tempFile.getAbsolutePath()});
            assertEquals("Unrecognized command: x"+System.lineSeparator(),contenido.toString());
        } catch (IOException e){
            fail("Failed to create temporary file");
        } finally{
            if (tempFile!=null) {
                tempFile.delete();
            }
        }
    }
}
