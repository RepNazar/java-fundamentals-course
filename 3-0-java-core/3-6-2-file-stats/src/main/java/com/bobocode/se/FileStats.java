package com.bobocode.se;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.stream.Stream;

/**
 * {@link FileStats} provides an API that allow to get character statistic based on text file. All whitespace characters
 * are ignored.
 */
public class FileStats {
    /**
     * Creates a new immutable {@link FileStats} objects using data from text file received as a parameter.
     *
     * @param fileName input text file name
     * @return new FileStats object created from text file
     */
    private final Path path;

    public static FileStats from(String fileName) {
        return new FileStats(fileName);
    }

    public FileStats(String fileName) {
        try {
            URL url = FileStats.class.getClassLoader().getResource(fileName);
            if (url == null) {
                throw new FileStatsException("");
            }
            path = Path.of(url.toURI());
        } catch (URISyntaxException e) {
            throw new FileStatsException("");
        }

    }


    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        try (Stream<String> lines = Files.lines(path)) {
            return (int) lines
                    .map(String::chars)
                    .flatMap(intStream -> intStream.mapToObj(value -> (char) value))
                    .filter(c -> c != ' ')
                    .filter(c -> c.equals(character))
                    .count();
        } catch (IOException e) {
            throw new FileStatsException("");
        }
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(String::chars)
                    .flatMap(intStream -> intStream.mapToObj(value -> (char) value))
                    .filter(c -> c != ' ')
                    .distinct()
                    .max(Comparator.comparing(this::getCharCount))
                    .orElseThrow(() -> new FileStatsException(""));
        } catch (IOException e) {
            throw new FileStatsException("");
        }
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        try (Stream<String> lines = Files.lines(path)) {
            return lines
                    .map(String::chars)
                    .flatMap(intStream -> intStream.mapToObj(value -> (char) value))
                    .filter(c -> c != ' ')
                    .anyMatch(character1 -> character1 == character);
        } catch (IOException e) {
            throw new FileStatsException("");
        }
    }
}
