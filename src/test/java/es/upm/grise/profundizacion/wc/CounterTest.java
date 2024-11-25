package es.upm.grise.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;

import org.junit.jupiter.api.Test;

import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.properties.SystemProperties;

@org.junit.jupiter.api.extension.ExtendWith(SystemStubsExtension.class)
public class CounterTest {

    private File createTempFile(String content) throws Exception {
        File tempFile=File.createTempFile("tempFile",".txt");
        tempFile.deleteOnExit();
        try (FileWriter writer=new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }

    @Test
    public void test_file1(SystemProperties properties) throws Exception {
        String content="We hold these truths to be self-evident, that all men are created equal, that they are endowed by their Creator with certain unalienable Rights, that among these are Life, Liberty and the pursuit of Happiness.\n";
        File tempFile=createTempFile(content);

        properties.set("file1",tempFile.getAbsolutePath());
        String filePath=System.getProperty("file1");

        try (BufferedReader reader=new BufferedReader(new FileReader(filePath))) {
            Counter counter=new Counter(reader);

            assertEquals(210,counter.getNumberCharacters(),"Number of characters mismatch.");
            assertEquals(1,counter.getNumberLines(),"Number of lines mismatch.");
            assertEquals(35,counter.getNumberWords(),"Number of words mismatch.");
        }
    }

    @Test
    public void test_file2(SystemProperties properties) throws Exception {
        String content="I expect to pass through this world but once; any good thing therefore that I can do, or any kindness that I can show\n"+
            "to any fellow-creature, let me do it now; let me not defer or neglect it, for I shall not pass this way again.\n";
        File tempFile=createTempFile(content);

        properties.set("file2",tempFile.getAbsolutePath());
        String filePath=System.getProperty("file2");

        try (BufferedReader reader=new BufferedReader(new FileReader(filePath))) {
            Counter counter=new Counter(reader);

            assertEquals(229,counter.getNumberCharacters(),"Number of characters mismatch.");
            assertEquals(2,counter.getNumberLines(),"Number of lines mismatch.");
            assertEquals(47,counter.getNumberWords(),"Number of words mismatch.");
        }
    }

    @Test
    public void test_file3(SystemProperties properties) throws Exception {
        String content="Tiger! Tiger! burning bright\nIn the forests of the night,\nWhat immortal hand or eye\nCould frame thy fearful symmetry?\n"+
            "In what distant deeps or skies\nBurnt the fire of thine eyes?\nOn what wings dare he aspire?\nWhat the hand dare seize the fire?\n"+
            "And what shoulder, and what art,\nCould twist the sinews of thy heart?\nAnd when thy heart began to beat,\nWhat dread hand? and what dread feet?\n"+
            "What the hammer? what the chain?\nIn what furnace was thy brain?\nWhat the anvil? what dread grasp\nDare its deadly terrors clasp?\n"+
            "When the stars threw down their spears,\nAnd watered heaven with their tears,\nDid he smile his work to see?\nDid he who made the Lamb make thee?\n"+
            "Tiger! Tiger! burning bright\nIn the forests of the night,\nWhat immortal hand or eye\nDare frame thy fearful symmetry?\n";
        File tempFile=createTempFile(content);

        properties.set("file3",tempFile.getAbsolutePath());
        String filePath=System.getProperty("file3");

        try (BufferedReader reader=new BufferedReader(new FileReader(filePath))) {
            Counter counter=new Counter(reader);

            assertEquals(774,counter.getNumberCharacters(),"Number of characters mismatch.");
            assertEquals(24,counter.getNumberLines(),"Number of lines mismatch.");
            assertEquals(143,counter.getNumberWords(),"Number of words mismatch.");
        }
    }
}
