package es.upm.alumnos.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import es.upm.grise.profundizacion.wc.Counter;

public class CounterTest {

    private static final String TEST_TEXT = " hello, there\n are \t\n\t this is a sample text\n";

    static StringReader stringReader = new StringReader(TEST_TEXT);
    static BufferedReader bReader = new BufferedReader(stringReader);
    static Counter counter;

    @Test
    public void getNumberCharactersTest() throws IOException {
        assert counter.getNumberCharacters() == TEST_TEXT.length();
    }

    @Test
    public void getNumberWordsTest() throws IOException {
        assert counter.getNumberWords() == TEST_TEXT.trim().split("\\s+").length;
    }

    @Test
    public void getNumberLines() throws IOException {
        assert counter.getNumberLines() == TEST_TEXT.split("\r\n|\r|\n").length;
    }

    @BeforeAll
    public static void markBuffer() throws IOException {
        bReader.mark(TEST_TEXT.length() + 1);
        counter = new Counter(bReader);
    }

    @BeforeEach
    public void resetBuffer() throws IOException { bReader.reset(); }

    @AfterAll
    public static void closeBufferedReader() throws IOException {
        bReader.close();
        stringReader.close();
    }
}