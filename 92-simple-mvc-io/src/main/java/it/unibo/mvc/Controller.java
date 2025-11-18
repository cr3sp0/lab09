package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public class Controller {

    private File currentFile = new File(System.getProperty("user.home") + File.separator
            + "output.txt");

    public Controller() {
    }

    public void setFile(final File newFile) {
        this.currentFile = newFile;
    }

    public File getFile(final File newFile) {
        return this.currentFile;
    }

    public String getFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    public void writeOnFile(final String newLine) throws IOException {
        try (PrintStream ps = new PrintStream(this.getFilePath(), StandardCharsets.UTF_8)) {
            ps.print(newLine);
            System.out.println("Finished writing"); // NOPMD
        } catch (final IOException e) {
            e.printStackTrace(); // NOPMD
        }
    }
}
