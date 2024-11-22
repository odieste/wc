package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.StringReader;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import uk.org.webcompere.systemstubs.environment.EnvironmentVariables;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;

@org.junit.jupiter.api.extension.ExtendWith(SystemStubsExtension.class)
public class CounterTest {

    private static EnvironmentVariables ev;

    @BeforeAll
    public static void setUp() throws Exception {
        ev = new EnvironmentVariables();
    }

    @Test
    public void test_file1() throws Exception {
        ev
        .and("file1","We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.\n")
        .execute(() -> {
            String fileContent = System.getenv("file1");
            BufferedReader reader = new BufferedReader(new StringReader(fileContent));
            Counter counter = new Counter(reader);

            assertEquals(210, counter.getNumberCharacters(), "Number of characters mismatch.");
            assertEquals(1, counter.getNumberLines(), "Number of lines mismatch.");
            assertEquals(35, counter.getNumberWords(), "Number of words mismatch.");

            reader.close();
        });
    }

    @Test
    public void test_file2() throws Exception {
        ev
        .and("file2","I expect to pass through this world but once; any good thing therefore that I can do, or any kindness that I can show\nto any fellow-creature, let me do it now; let me not defer or neglect it, for I shall not pass this way again.\n")
        .execute(() -> {
            String fileContent = System.getenv("file2");
            BufferedReader reader = new BufferedReader(new StringReader(fileContent));
            Counter counter = new Counter(reader);

            assertEquals(229, counter.getNumberCharacters(), "Number of characters mismatch.");
            assertEquals(2, counter.getNumberLines(), "Number of lines mismatch.");
            assertEquals(47, counter.getNumberWords(), "Number of words mismatch.");

            reader.close();
        });
    }

    @Test
    public void test_file3() throws Exception {
        ev
        .and("file3","Tiger! Tiger! burning bright\nIn the forests of the night,\nWhat immortal hand or eye\nCould frame thy fearful symmetry?\nIn what distant deeps or skies\nBurnt the fire of thine eyes?\nOn what wings dare he aspire?\nWhat the hand dare seize the fire?\nAnd what shoulder, and what art,\nCould twist the sinews of thy heart?\nAnd when thy heart began to beat,\nWhat dread hand? and what dread feet?\nWhat the hammer? what the chain?\nIn what furnace was thy brain?\nWhat the anvil? what dread grasp\nDare its deadly terrors clasp?\nWhen the stars threw down their spears,\nAnd watered heaven with their tears,\nDid he smile his work to see?\nDid he who made the Lamb make thee?\nTiger! Tiger! burning bright\nIn the forests of the night,\nWhat immortal hand or eye\nDare frame thy fearful symmetry?\n")
        .execute(() -> {
            String fileContent = System.getenv("file3");
            BufferedReader reader = new BufferedReader(new StringReader(fileContent));
            Counter counter = new Counter(reader);

            assertEquals(774, counter.getNumberCharacters(), "Number of characters mismatch.");
            assertEquals(24, counter.getNumberLines(), "Number of lines mismatch.");
            assertEquals(143, counter.getNumberWords(), "Number of words mismatch.");

            reader.close();
        });
    }
}
