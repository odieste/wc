package es.upm.alumnos.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EmptySource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import es.upm.grise.profundizacion.wc.Counter;

public class CounterTest {

    private static final String TEST_TEXT = " \thello\n\n\n this is a test text\n\t", BLANK = " ";

    static BufferedReader latestBufferedReader;

    private static Counter createCounter(String testedString) throws IOException {
        latestBufferedReader = new BufferedReader(new StringReader(testedString));
        return new Counter(latestBufferedReader);
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {TEST_TEXT, BLANK})
    public void getNumberCharactersTest(String testedString) throws IOException {
        Counter counter = createCounter(testedString);
        assertEquals(counter.getNumberCharacters(), testedString.length());
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = {TEST_TEXT, BLANK})
    public void getNumberWordsTest(String testedString) throws IOException {
        Counter counter = createCounter(testedString);
        if ( ! testedString.isBlank())
            assertEquals(counter.getNumberWords(), testedString.trim().split("\\s+").length);
        else
            assertEquals(counter.getNumberWords(), 0);
    }

    @ParameterizedTest 
    @EmptySource
    @ValueSource(strings = {TEST_TEXT, BLANK})
    public void getNumberLines(String testedString) throws IOException {
        Counter counter = createCounter(testedString);
        Matcher m = Pattern.compile("\r\n|\r|\n").matcher(testedString);
        int lines = 0;
        while (m.find()){lines ++;}
        assertEquals(counter.getNumberLines(), lines);
    }

    @AfterEach
    public void closeBufferedReader() throws IOException {
        latestBufferedReader.close();
    }
}