package es.upm.alumnos.profundizacion.wc;

import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.regex.Pattern;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.io.TempDir;

import es.upm.grise.profundizacion.wc.App;
import uk.org.webcompere.systemstubs.jupiter.SystemStub;
import uk.org.webcompere.systemstubs.jupiter.SystemStubsExtension;
import uk.org.webcompere.systemstubs.stream.SystemOut;

@ExtendWith(SystemStubsExtension.class)
public class AppTest {
    
	@TempDir
    private static File dir;
    private static File emptyFile;
    private static File notAllowed;
    private static File quotes;
    
    @BeforeAll
    public static void initFiles() throws IOException {
        emptyFile = new File(dir, "emptyFile.txt");
        emptyFile.createNewFile();
        notAllowed = new File(dir, "you_shall_not_pass.txt");
        notAllowed.createNewFile();
        notAllowed.setReadable(false);
        quotes = new File(dir, "quotes.txt");
        Files.write(quotes.toPath(),
                List.of("Without music, life would be a mistake. - Nietzsche",
                        "There's no point in being grown up if you can't act childish sometimes. - 4th Doctor",
                        "Forget the past. No one becomes successful in the past. - Anonymous")
                );
    }
    
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
        assertLinesMatch(List.of(".*" + Pattern.quote(filename)), getOutLines(), "The program should report the name of the file!");
    }

    @Test
    public void testPermissionsOnAFile() {
        final String filename = notAllowed.getAbsolutePath();
        callApp("-cwl", filename);
        assertLinesMatch(List.of("Cannot find file.*"), getOutLines(),
                "The program should report if the file cannot be accesible!");
        assertLinesMatch(List.of(".*" + Pattern.quote(filename)), getOutLines(), "The program should report the name of the file!");
    }

    @Test
    public void testAnEmptyFile() {
        final String filename = emptyFile.getAbsolutePath();
        callApp("-cwl", filename);
        assertLinesMatch(List.of("\\s\\d+\\s\\d+\\s\\d+.*"), getOutLines(),
                "The program should report as many counts requested!");
        assertLinesMatch(List.of(".*" + Pattern.quote(filename)), getOutLines(),
                "The program should report the name of the file that had been processed!");
    }

    @Test
    public void testAWrittenFileTwoOptions() {
        final String filename = quotes.getAbsolutePath();
        callApp("-cl", filename);
        assertLinesMatch(List.of("\\s\\d+\\s\\d+.*"), getOutLines(),
                "The program should report as many counts requested!");
        assertLinesMatch(List.of(".*" + Pattern.quote(filename)), getOutLines(),
                "The program should report the name of the file that had been processed!");
    }
}