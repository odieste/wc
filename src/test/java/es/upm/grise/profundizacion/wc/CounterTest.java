package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.StringReader;
import java.io.IOException;

import org.junit.jupiter.api.Test;

public class CounterTest {

    private static final String HELLO_WORLD = "Hello World\n";
    private static final int HELLO_WORLD_CHAR_COUNT = 12;
    private static final int HELLO_WORLD_WORD_COUNT = 2;
    private static final int HELLO_WORLD_LINE_COUNT = 1;

    final BufferedReader helloWorldBufferedReader = new BufferedReader(new StringReader(HELLO_WORLD));

    @Test
    public void testHelloWorldNumberCharacters() throws IOException {
        Counter counter = new Counter(helloWorldBufferedReader);
        int actualCharacterCount = counter.getNumberCharacters();
        assertEquals(HELLO_WORLD_CHAR_COUNT, actualCharacterCount, "Number of characters should be " + HELLO_WORLD_CHAR_COUNT + " but returned " + actualCharacterCount);
    }

    @Test
    public void testHelloWorldNumberWords() throws IOException {
        Counter counter = new Counter(helloWorldBufferedReader);
        int actualWordCount = counter.getNumberWords();
        assertEquals(HELLO_WORLD_WORD_COUNT, actualWordCount, "Number of words should be " + HELLO_WORLD_WORD_COUNT + " but returned " + actualWordCount);
    }

    @Test
    public void testHelloWorldNumberLines() throws IOException {
        Counter counter = new Counter(helloWorldBufferedReader);
        int actualLineCount = counter.getNumberLines();
        assertEquals(HELLO_WORLD_LINE_COUNT, actualLineCount, "Number of lines should be " + HELLO_WORLD_LINE_COUNT + " but returned " + actualLineCount);
    }

}
