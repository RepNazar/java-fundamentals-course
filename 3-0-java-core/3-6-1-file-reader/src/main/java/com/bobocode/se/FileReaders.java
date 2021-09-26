package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;

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
            return Files.lines(Path.of(FileReaders.class.getClassLoader().getResource(fileName).toURI())).collect(Collectors.joining("\n"));
        } catch (IOException | URISyntaxException ignored) {
            return "";
        }
    }
}
