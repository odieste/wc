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
    
    private static final String EMPTY = "";
    private static final int EMPTY_CHAR_COUNT = 0;
    private static final int EMPTY_WORD_COUNT = 0;
    private static final int EMPTY_LINE_COUNT = 0;
    
    private static final String ONE_WORD = "ThisIsAUniqueWordForTest\n";
    private static final int ONE_WORD_CHAR_COUNT = 25;
    private static final int ONE_WORD_WORD_COUNT = 1;
    private static final int ONE_WORD_LINE_COUNT = 1;
    
    private static final String MULTIPLE_LINES = "Hello\nWorld\n";
    private static final int MULTIPLE_LINES_CHAR_COUNT = 12;
    private static final int MULTIPLE_LINES_WORD_COUNT = 2;
    private static final int MULTIPLE_LINES_LINE_COUNT = 2;
    
    private static final String SEPARATORS = "Hello\t World\nThis is a\ttest\n";
    private static final int SEPARATORS_CHAR_COUNT = 28;
    private static final int SEPARATORS_WORD_COUNT = 7;
    private static final int SEPARATORS_LINE_COUNT = 2;

    final BufferedReader helloWorldBufferedReader = new BufferedReader(new StringReader(HELLO_WORLD));
    final BufferedReader emptyBufferedReader = new BufferedReader(new StringReader(EMPTY));
    final BufferedReader oneWordBufferedReader = new BufferedReader(new StringReader(ONE_WORD));
    final BufferedReader multipleLinesBufferedReader = new BufferedReader(new StringReader(MULTIPLE_LINES));    
    final BufferedReader separatorsBufferedReader = new BufferedReader(new StringReader(SEPARATORS));

    @Test
    public void testNumberCharacters() throws IOException {
        int actualCharacterCount = new Counter(helloWorldBufferedReader).getNumberCharacters();
        assertEquals(HELLO_WORLD_CHAR_COUNT, actualCharacterCount, "Number of characters should be " + HELLO_WORLD_CHAR_COUNT + " but returned " + actualCharacterCount);
    }

    @Test
    public void testNumberWords() throws IOException {
        int actualWordCount = new Counter(helloWorldBufferedReader).getNumberWords();
        assertEquals(HELLO_WORLD_WORD_COUNT, actualWordCount, "Number of words should be " + HELLO_WORLD_WORD_COUNT + " but returned " + actualWordCount);
    }

    @Test
    public void testNumberLines() throws IOException {
        int actualLineCount = new Counter(helloWorldBufferedReader).getNumberLines();
        assertEquals(HELLO_WORLD_LINE_COUNT, actualLineCount, "Number of lines should be " + HELLO_WORLD_LINE_COUNT + " but returned " + actualLineCount);
    }
    
    @Test
    public void testNumCharacterEmtpyString() throws IOException {
        int actualCharCount = new Counter(emptyBufferedReader).getNumberCharacters();
        assertEquals(EMPTY_CHAR_COUNT, actualCharCount, "Number of characters should be " + EMPTY_CHAR_COUNT + " but returned " + actualCharCount);
    }
    
    @Test
    public void testNumWordEmtpyString() throws IOException {
        int actualWordCount = new Counter(emptyBufferedReader).getNumberWords();
        assertEquals(EMPTY_WORD_COUNT, actualWordCount, "Number of words should be " + EMPTY_WORD_COUNT + " but returned " + actualWordCount);
    }
    
    @Test
    public void testNumLineEmtpyString() throws IOException {
        int actualLineCount = new Counter(emptyBufferedReader).getNumberLines();
        assertEquals(EMPTY_LINE_COUNT, actualLineCount, "Number of lines should be " + EMPTY_LINE_COUNT + " but returned " + actualLineCount);
    }
    
    
    @Test
    public void testNumCharacterOneWord() throws IOException {
        int actualCharCount = new Counter(oneWordBufferedReader).getNumberCharacters();
        assertEquals(ONE_WORD_CHAR_COUNT, actualCharCount, "Number of characters should be " + ONE_WORD_CHAR_COUNT + " but returned " + actualCharCount);
    }
    
    @Test
    public void testNumWordOneWord() throws IOException {
        int actualWordCount = new Counter(oneWordBufferedReader).getNumberWords();
        assertEquals(ONE_WORD_WORD_COUNT, actualWordCount, "Number of words should be " + ONE_WORD_WORD_COUNT + " but returned " + actualWordCount);
    }
    
    @Test
    public void testNumLineOneWord() throws IOException {
        int actualLineCount = new Counter(oneWordBufferedReader).getNumberLines();
        assertEquals(ONE_WORD_LINE_COUNT, actualLineCount, "Number of lines should be " + ONE_WORD_LINE_COUNT + " but returned " + actualLineCount);
    }
    

    @Test
    public void testNumCharacterMultipleLines() throws IOException {
        int actualCharCount = new Counter(multipleLinesBufferedReader).getNumberCharacters();
        assertEquals(MULTIPLE_LINES_CHAR_COUNT, actualCharCount, "Number of characters should be " + MULTIPLE_LINES_CHAR_COUNT + " but returned " + actualCharCount);
    }
    
    @Test
    public void testNumWordMultipleLines() throws IOException {
        int actualWordCount = new Counter(multipleLinesBufferedReader).getNumberWords();
        assertEquals(MULTIPLE_LINES_WORD_COUNT, actualWordCount, "Number of words should be " + MULTIPLE_LINES_WORD_COUNT + " but returned " + actualWordCount);
    }
    
    @Test
    public void testNumLineMultipleLines() throws IOException {
        int actualLineCount = new Counter(multipleLinesBufferedReader).getNumberLines();
        assertEquals(MULTIPLE_LINES_LINE_COUNT, actualLineCount, "Number of lines should be " + MULTIPLE_LINES_LINE_COUNT + " but returned " + actualLineCount);
    }
    
    @Test
    public void testNumCharacterSeparators() throws IOException {
        int actualCharCount = new Counter(separatorsBufferedReader).getNumberCharacters();
        assertEquals(SEPARATORS_CHAR_COUNT, actualCharCount, "Number of characters should be " + SEPARATORS_CHAR_COUNT + " but returned " + actualCharCount);
    }
    
    @Test
    public void testNumWordSeparators() throws IOException {
        int actualWordCount = new Counter(separatorsBufferedReader).getNumberWords();
        assertEquals(SEPARATORS_WORD_COUNT, actualWordCount, "Number of words should be " + SEPARATORS_WORD_COUNT + " but returned " + actualWordCount);
    }
    
    @Test
    public void testNumLineSeparators() throws IOException {
        int actualLineCount = new Counter(separatorsBufferedReader).getNumberLines();
        assertEquals(SEPARATORS_LINE_COUNT, actualLineCount, "Number of lines should be " + SEPARATORS_LINE_COUNT + " but returned " + actualLineCount);
    }
}
