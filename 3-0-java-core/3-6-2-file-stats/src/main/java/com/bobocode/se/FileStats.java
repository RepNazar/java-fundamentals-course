package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//TODO: Optimise

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    private final String content;

    public FileStats(String content) {
        this.content = content;
    }

    private static Path getPathFromFileName(String fileName) {
        try {
            URL url = FileStats.class.getClassLoader().getResource(fileName);
            if (url == null) {
                throw new FileStatsException("No such File");
            }
            return Path.of(url.toURI());
        } catch (URISyntaxException e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    public static FileStats from(String fileName) {
        try {
            return new FileStats(Files.lines(getPathFromFileName(fileName)).collect(Collectors.joining()));
        } catch (IOException e) {
            throw new FileStatsException(e.getMessage());
        }
    }

    private Stream<Character> contentToCharStream() {
        return content
                .lines()
                .flatMapToInt(String::chars)
                .mapToObj(value -> (char) value)
                .filter(character -> character != ' ');
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return (int) contentToCharStream()
                .filter(c -> c.equals(character))
                .count();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return contentToCharStream()
                .max(Comparator.comparingInt(this::getCharCount))
                .orElseThrow(() -> new FileStatsException("No most popular char"));
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return contentToCharStream()
                .anyMatch(c -> c == character);
    }
}
