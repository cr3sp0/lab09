package it.unibo.mvc;

import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.charset.StandardCharsets;

/**
 * Application controller. Performs the I/O.
 */
public final class Controller {

    private File currentFile = new File(System.getProperty("user.home") + File.separator
            + "output.txt");

    /**
     * Set a new file as the current one.
     * 
     * @param newFile as new current File
     */
    public void setFile(final File newFile) {
        this.currentFile = newFile;
    }

    /**
     * @return the current file
     */
    public File getFile() {
        return this.currentFile;
    }

    /**
     * @return the current file path
     */
    public String getFilePath() {
        return this.currentFile.getAbsolutePath();
    }

    /**
     * prints the String content on the current File.
     * 
     * @param newLine as input for printing
     */
    public void writeOnFile(final String newLine) throws IOException {
        try (PrintStream ps = new PrintStream(this.getFilePath(), StandardCharsets.UTF_8)) {
            ps.print(newLine);
            System.out.println("Finished writing"); // NOPMD
        } catch (final IOException e) {
            e.printStackTrace(); // NOPMD
        }
    }
}
