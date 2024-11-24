package es.upm.grise.profundizacion.wc;

import org.junit.jupiter.api.io.TempDir;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TestUtils {

    public static File createTempFileWithContent(String content, @TempDir File tempDir) throws IOException {
        File tempFile = new File(tempDir, "temp_test_file.txt");
        try (FileWriter writer = new FileWriter(tempFile)) {
            writer.write(content);
        }
        return tempFile;
    }
}
