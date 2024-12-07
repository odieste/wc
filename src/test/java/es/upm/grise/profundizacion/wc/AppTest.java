package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

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
    public void comandoNoReconocido(@TempDir Path tempDir) throws IOException {
    	Path tempFile = crearArchivoTemporalCon(tempDir, "comando_no_reconocido.txt", "Hello World\nThis is a test file\n");
        
        App.main(new String[]{"-x", tempFile.toAbsolutePath().toString()});
	    assertEquals("Unrecognized command: x", outputStream.toString().trim());
    }

    @Test
    public void contarCaracteres(@TempDir Path tempDir) throws IOException {
    	Path tempFile = crearArchivoTemporalCon(tempDir, "contar_caracteres.txt", "Hello World\nThis is a test file\n");
        
        App.main(new String[]{"-c", tempFile.toAbsolutePath().toString()});
        assertEquals("32\t" + tempFile.toAbsolutePath().toString(), outputStream.toString().trim());
    }

    @Test
    public void contarLineas(@TempDir Path tempDir) throws IOException {
    	Path tempFile = crearArchivoTemporalCon(tempDir, "contar_lineas.txt", "Hello World\nThis is a test file\n");

        App.main(new String[]{"-l", tempFile.toAbsolutePath().toString()});
        assertEquals("2\t" + tempFile.toAbsolutePath().toString(), outputStream.toString().trim());

    }

    @Test
    public void contarPalabras(@TempDir Path tempDir) throws IOException {
    	Path tempFile = crearArchivoTemporalCon(tempDir, "contar_palabras.txt", "Hello World\nThis is a test file\n");

        App.main(new String[]{"-w", tempFile.toAbsolutePath().toString() });
        assertEquals("7\t" + tempFile.toAbsolutePath().toString(), outputStream.toString().trim());
    }

    @Test
    public void contarTodo(@TempDir Path tempDir) throws IOException {
    	Path tempFile = crearArchivoTemporalCon(tempDir, "contar_todo.txt", "Hello World\nThis is a test file\n");

        App.main(new String[]{"-clw", tempFile.toAbsolutePath().toString() });
        assertEquals("32\t2\t7\t" + tempFile.toAbsolutePath().toString(), outputStream.toString().trim());
        
    }
    
    @Test
    public void noPasoArgumentos(@TempDir Path tempDir) throws IOException {
        Path tempFile = crearArchivoTemporalCon(tempDir, "no_argumentos.txt", "Hello World\nThis is a test file\n");

        App.main(new String[] {"test", tempFile.toAbsolutePath().toString() } );
        assertEquals("The commands do not start with -", outputStream.toString().trim());
    }

    private Path crearArchivoTemporalCon(Path tempDir, String fileName, String content) throws IOException {
        // Resuelve la ruta del archivo temporal
        Path tempFile = tempDir.resolve(fileName);
        
        // Crea el archivo si no existe y escribe el contenido
        Files.writeString(tempFile, content);
        
        
        return tempFile;
    }


}
