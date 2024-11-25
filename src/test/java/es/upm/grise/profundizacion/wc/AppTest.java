package es.upm.grise.profundizacion.wc;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AppTest {

    /**
     * Captura la salida del programa
     */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Restaura la salida del programa
     */
    private final PrintStream originalOut = System.out;

    File tempFile;

    /**
     * Redirige la salida del programa antes de cada test
     * @throws IOException 
     */
    @BeforeEach
    public void setUp() throws IOException {
        System.setOut(new PrintStream(outputStream));

        String text = "12\n34 56";
        this.tempFile = File.createTempFile("tempFile",".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer=new FileWriter(tempFile)) {
            writer.write(text);
        }
    }

    /**
     * Restaura la salida del programa después de cada test
     */
    @AfterEach
    public void tearDown() {
        System.setOut(originalOut);
    }

    /**
     * Test que comprueba que el programa imprime
     * el mensaje de uso si no se le pasan parámetros
     */
    @Test
    public void llamadaSinParametros() {
        String [] args = {};
        App.main(args);

        String expectedOutput = "Usage: wc [-clw file]";
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints an error message message if no parameters are passed");
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si se le pasa un número
     * de parámetros distinto de 2
     */
    @Test
    public void llamadaConMasDeDosParametros() {
        String [] args = {"-c", "file", "extra"};
        App.main(args);

        String expectedOutput = "Wrong arguments!";
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints an error message if the number of parameters is different from 2");
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si no se encuentra el archivo
     */
    @Test
    public void archivoNoEncontrado() {
        String [] args = {"-c", "file"};
        App.main(args);

        String expectedOutput = "Cannot find file: " + args[1];
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints an error message if the file is not found");
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si los comandos no empiezan por -
     */
    @Test
    public void comandosSinGuión() {
        String [] args = {"c", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput = "The commands do not start with -";
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints an error message if the commands do not start with -");
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si los comandos no son válidos
     */
    @Test
    public void comandosNoValidos() {
        String [] args = {"-a", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput = "Unrecognized command: " + args[0].charAt(1);
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints an error message if the command passed is not a valid command, if it is not c, l, w");
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de caracteres si se le pasa el comando -c
     */
    @Test
    public void contarCaracteres() {
        String [] args = {"-c", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput = "8" + "\t" + args[1];
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints the number of characters if the -c command is passed");
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de líneas si se le pasa el comando -l
     */
    @Test
    public void contarLineas() {
        String [] args = {"-l", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput = "1" + "\t" + args[1];
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints the number of lines if the -l command is passed");
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de palabras si se le pasa el comando -w
     */
    @Test
    public void contarPalabras() {
        String [] args = {"-w", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput = "2" + "\t" + args[1];
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints the number of words if the -w command is passed");
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de caracteres, líneas y palabras si se le pasa
     * el comando -clw
     */
    @Test
    public void contarTodo() {
        String [] args = {"-clw", tempFile.getAbsolutePath()};
        App.main(args);

        String expectedOutput ="8" +"\t" + "1" +"\t" + "2" + "\t" + args[1];
        assertEquals(expectedOutput, outputStream.toString().trim(), "This method tests that the program prints the number of characters, lines and words if the -clw command is passed");
    }
}
