package es.upm.grise.profundizacion.wc;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;



public class AppTest {
    
    /**
     * Captura la salida del programa
     */
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    /**
     * Restaura la salida del programa
     */
    private final PrintStream originalOut = System.out;

    /**
     * Redirige la salida del programa antes de cada test
     */
    @BeforeEach
    public void setUp() {
        System.setOut(new PrintStream(outputStream));
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
        assertEquals(expectedOutput, outputStream.toString().trim());
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si se le pasa un número
     * de parámetros distinto de 2
     */
    @Test
    public void llamadaConMasDeDosParametros() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si no se encuentra el archivo
     */
    @Test
    public void archivoNoEncontrado() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si no se puede leer el archivo
     */
    @Test
    public void archivoNoLeible() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si los comandos no empiezan por -
     */
    @Test
    public void comandosSinGuión() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * un mensaje de error si los comandos no son válidos
     */
    @Test
    public void comandosNoValidos() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de caracteres si se le pasa el comando -c
     */
    @Test
    public void contarCaracteres() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de líneas si se le pasa el comando -l
     */
    @Test
    public void contarLineas() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de palabras si se le pasa el comando -w
     */
    @Test
    public void contarPalabras() {
        fail();
    }

    /**
     * Test que comprueba que el programa imprime
     * el número de caracteres, líneas y palabras si se le pasa
     * el comando -clw
     */
    @Test
    public void contarTodo() {
        fail();
    }
}
