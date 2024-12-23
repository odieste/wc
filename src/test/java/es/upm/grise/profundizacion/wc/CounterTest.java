package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import es.upm.grise.profundizacion.wc.Counter;
import org.junit.jupiter.api.Test;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
public class CounterTest {
    private File createTempFile(String content) throws IOException {
        File tempFile=File.createTempFile("testFile", ".txt");
        FileWriter writer=new FileWriter(tempFile);
        writer.write(content);
        writer.close();
        return tempFile;
    }
    @ParameterizedTest
    @CsvSource({"'Prueba',1",
                "'Esto es una prueba',4",
                "'Prueba\t3\tprobando',3",
                "'Probando\ncon\nsaltos\nde\nlínea',5",
                "'Ahora mezclo\tespacios y tabulaciones',5",
                "'Probando con\nespacion y\nsaltos de\nlínea',7",
                "'Es\tel\nturno\tde\ntabulaciones\ty\nsaltos\tde\nlínea',9",
                "'Poniendo\ttodos\nlos tipos distintos',5"})
    public void testWordCount(String texto,int palabrasEsperadas) throws IOException {
        File file=createTempFile(texto);
        BufferedReader br=new BufferedReader(new FileReader(file));
        Counter counter=new Counter(br);
        assertEquals(palabrasEsperadas,counter.getNumberWords(),"Debe contar "+palabrasEsperadas+" palabras");
        file.delete();
    }
    @Test
    public void testCharacterCount() throws IOException {
        String content="Probando el conteo de caracteres";
        int caracteresEsperados=32;
        File file=createTempFile(content);
        BufferedReader br=new BufferedReader(new FileReader(file));
        Counter counter=new Counter(br);
        assertEquals(caracteresEsperados,counter.getNumberCharacters(),"Debe contar "+caracteresEsperados+" caracteres");
        file.delete();
    }
    @Test
    public void testLineCount() throws IOException {
        String content="Probando\nel\nnúmero\nde\nlíneas";
        int lineasEsperadas=5;
        File file=createTempFile(content);
        BufferedReader br=new BufferedReader(new FileReader(file));
        Counter counter=new Counter(br);
        assertEquals(lineasEsperadas,counter.getNumberLines(),"Debe contar "+lineasEsperadas+" línea");
        file.delete();
    }
}
