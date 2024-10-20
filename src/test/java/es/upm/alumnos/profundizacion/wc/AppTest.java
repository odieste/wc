package es.upm.alumnos.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import es.upm.grise.profundizacion.wc.App;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;

@ExtendWith(SystemStubsExtension.class)
public class AppTest {

    private static void callApp(String... args) {
        App.main(args);
    }

    @SystemStub
    private SystemOut sysOut;

    private List<String> getOutLines() {
        return sysOut.getLines().toList();
    }

    @Test
    public void testNoArgs() {
        callApp();
        assertLinesMatch(List.of("Usage: wc [-clw file]"), getOutLines(), "The program should print how to use it!");
    }

    @Test
    public void testOneArg() {
        callApp("-w");
        assertLinesMatch(List.of("Wrong arguments!"), getOutLines(), "The program only work with 2 arguments!");
    }

    @Test
    public void testMoreThanTwoArgs() {
        callApp("-w", "file.txt", "other.txt");
        assertLinesMatch(List.of("Wrong arguments!"), getOutLines(), "The program only work with 2 arguments!");
    }

    @Test
    public void testAnNonExistingFile() {
        final String filename = "no_exist_file.txt";
        callApp("-w", filename);
        assertLinesMatch(List.of("Cannot find file.*"), getOutLines(),
                "The program should report if the file cannot be found!");
        assertLinesMatch(List.of(".*" + filename), getOutLines(), "The program should report the name of the file!");
    }

    @Test
    public void testAnEmptyFile() throws IOException {
        File emptyFile = File.createTempFile("empty", null);
        emptyFile.deleteOnExit();  

        Path normalizedPath = emptyFile.toPath();
        String normalizedFilename = normalizedPath.toString();

        callApp("-cwl", normalizedFilename);

        assertAll(
            () -> assertLinesMatch(
                List.of("^.*0.*0.*0.*"), 
                getOutLines(),
                "The program should report zeros for all counts on an empty file!"
            ),
            () -> assertLinesMatch(
                List.of(".*" + Pattern.quote(normalizedFilename)), 
                getOutLines(),
                "The program should report the name of the file that had been processed!"
            )
        );
    }

}