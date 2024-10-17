package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AppTest {

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    public void setUpStreams() {
        System.setOut(new PrintStream(outputStream));
    }

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    @Test
    public void noArgumentos() {
        App.main(new String[]{});
        assertEquals("Usage: wc [-clw file]", outputStream.toString().trim());
    }

    @Test
    public void numberoArgumentosMal() {
        App.main(new String[]{"-c"});
        assertEquals("Wrong arguments!", outputStream.toString().trim());
    }

    @Test
    public void archivoNoEncontrado() {
        App.main(new String[]{"-c", "non_existing_file.txt"});
        assertEquals("Cannot find file: non_existing_file.txt", outputStream.toString().trim());
    }

    @Test
    public void comandoNoReconocido() throws IOException {
        File tempFile = crearArchivoTemporalCon("Hello World\nThis is a test file\n");
        App.main(new String[]{"-x", tempFile.getAbsolutePath()});
        assertEquals("Unrecognized command: x", outputStream.toString().trim());
    }

    @Test
    public void contarCaracteres() throws IOException {
        File tempFile = crearArchivoTemporalCon("Hello World\nThis is a test file\n");
        App.main(new String[]{"-c", tempFile.getAbsolutePath()});
        assertEquals("32\t" + tempFile.getAbsolutePath(), outputStream.toString().trim());
    }

    @Test
    public void contarLineas() throws IOException {
        File tempFile = crearArchivoTemporalCon("Hello World\nThis is a test file\n");
        App.main(new String[]{"-l", tempFile.getAbsolutePath()});
        assertEquals("2\t" + tempFile.getAbsolutePath(), outputStream.toString().trim());
    }

    @Test
    public void contarPalabras() throws IOException {
        File tempFile = crearArchivoTemporalCon("Hello World\nThis is a test file\n");
        App.main(new String[]{"-w", tempFile.getAbsolutePath()});
        assertEquals("7\t" + tempFile.getAbsolutePath(), outputStream.toString().trim());
    }

    @Test
    public void contarTodo() throws IOException {
        File tempFile = crearArchivoTemporalCon("Hello World\nThis is a test file\n");
        App.main(new String[]{"-clw", tempFile.getAbsolutePath()});
        assertEquals("32\t2\t7\t" + tempFile.getAbsolutePath(), outputStream.toString().trim());
    }

    private File crearArchivoTemporalCon(String content) throws IOException {
        File tempFile = File.createTempFile("testFile", ".txt");
        tempFile.deleteOnExit();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile))) {
            writer.write(content);
        }
        return tempFile;
    }
}
