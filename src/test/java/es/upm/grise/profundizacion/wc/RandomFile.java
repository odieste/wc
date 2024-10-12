package es.upm.grise.profundizacion.wc;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

//
// Class RandomFile
//
// Creates a file with a random number of ASCII characters (8-bit chars).
//
public class RandomFile {
    private static final int MAX_CHAR = 1000;

    protected String fileName;
    protected int nChar;
    protected int nWord;
    protected int nLine;

    public RandomFile(String _filename) {
        this(_filename, MAX_CHAR);
    }

    public RandomFile(String _fileName, int maxChar) {
        // Create directories if needed
        try {
            Path path = Paths.get(_fileName);
            Files.createDirectories(path.getParent());
        } catch (IOException e) {
            System.out.println("An error occurred while creating directories.");
        }

        // Create random file with maxChar characters
        fileName = _fileName;
        Random random = new Random();
        try (FileWriter writer = new FileWriter(fileName, false)) {
            nChar = random.nextInt(maxChar);
            nWord = 0;
            nLine = 0;
            int i = 0;
            while (i < nChar) {
                char c = (char) random.nextInt(256); // Random ASCII character
                switch (c) {
                    case '\n':
                        nLine++;
                    case ' ':
                    case '\t':
                        nWord++;
                }
                writer.write(c);
                i++;
            }
        } catch (IOException e) {
            System.out.println("An error occurred while generating the random file.");
        }
    }

    public int getNChar() { return nChar; }
    public int getNWord() { return nWord; }
    public int getNLine() { return nLine; }

    public void delete() {
        // Delete random file
        File file = new File(fileName);
        if (file.delete()) {
            // Result ignored
        }
    }
}
