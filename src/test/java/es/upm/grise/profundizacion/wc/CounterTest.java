package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

public class CounterTest {

    private static final String[] fn = {"test_file/a.txt", "test_file/b.txt", "test_file/c.txt"};
    private static Counter[] counter = new Counter[fn.length];
    private static BufferedReader[] br = new BufferedReader[fn.length];

    @BeforeAll
    public static void set() throws IOException {
        br[0] = new BufferedReader(new FileReader(fn[0]));
        counter[0] = new Counter(br[0]);

        br[1] = new BufferedReader(new FileReader(fn[1]));
        counter[1] = new Counter(br[1]);

        br[2] = new BufferedReader(new FileReader(fn[2]));
        counter[2] = new Counter(br[2]);
    }

    @AfterAll
    public static void out() throws IOException {
        br[0].close();
        br[1].close();
        br[2].close();
    }

    @Test
    public void test_getNumberCharacters() {
        int[] e = {210, 229, 774};
        int[] a = {counter[0].getNumberCharacters(), counter[1].getNumberCharacters(), counter[2].getNumberCharacters()};

        assertEquals(e[0], a[0], "test_getNumberCharacters : " + fn[0]);
        assertEquals(e[1], a[1], "test_getNumberCharacters : " + fn[1]);
        assertEquals(e[2], a[2], "test_getNumberCharacters : " + fn[2]);
    }

    @Test
    public void test_getNumberLines() {
        int[] e = {1, 2, 24};
        int[] a = {counter[0].getNumberLines(), counter[1].getNumberLines(), counter[2].getNumberLines()};

        assertEquals(e[0], a[0], "test_getNumberLines : " + fn[0]);
        assertEquals(e[1], a[1], "test_getNumberLines : " + fn[1]);
        assertEquals(e[2], a[2], "test_getNumberLines : " + fn[2]);
    }

    @Test
    public void test_getNumberWords() {
        int[] e = {35, 47, 143};
        int[] a = {counter[0].getNumberWords(), counter[1].getNumberWords(), counter[2].getNumberWords()};

        assertEquals(e[0], a[0], "test_getNumberWords : " + fn[0]);
        assertEquals(e[1], a[1], "test_getNumberWords : " + fn[1]);
        assertEquals(e[2], a[2], "test_getNumberWords : " + fn[2]);
    }
}
