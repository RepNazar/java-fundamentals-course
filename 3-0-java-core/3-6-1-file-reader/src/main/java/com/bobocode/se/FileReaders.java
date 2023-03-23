package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;

/**
 * {@link FileReaders} provides an API that allow to read whole file into a {@link String} by file name.
 */
public class FileReaders {

    /**
     * Returns a {@link String} that contains whole text from the file specified by name.
     *
     * @param fileName a name of a text file
     * @return string that holds whole file content
     */
    public static String readWholeFile(String fileName) {
        try {
            return Files.readString(Path.of(Objects.requireNonNull(FileReaders.class.getClassLoader().getResource(fileName)).toURI()));
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

}
