package com.bobocode.se;

import com.bobocode.util.ExerciseNotCompletedException;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;
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

    private Map<Character, Long> charCountMap;
    private String data;

    public static FileStats from(String fileName) {
        FileStats fileStats = new FileStats();
        try {
            fileStats.data = Files.readString(
                    Path.of(
                            FileStats.class.getClassLoader().getResource(fileName).toURI()));
            fileStats.charCountMap = computeMap(fileStats.data);
        } catch (IOException | URISyntaxException | NullPointerException e){
            throw new FileStatsException("", e);
        }
        return fileStats;
    }

    private static Map<Character, Long> computeMap(String data) {
        return data.chars()
                .mapToObj(value -> (char) value)
                .filter(c -> c != ' ')
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    /**
     * Returns a number of occurrences of the particular character.
     *
     * @param character a specific character
     * @return a number that shows how many times this character appeared in a text file
     */
    public int getCharCount(char character) {
        return charCountMap.get(character).intValue();
    }

    /**
     * Returns a character that appeared most often in the text.
     *
     * @return the most frequently appeared character
     */
    public char getMostPopularCharacter() {
        return data.chars()
                .mapToObj(value -> (char) value)
                .distinct()
                .filter(c -> c != ' ')
                .max(Comparator.comparing(this::getCharCount))
                .orElseThrow(() -> new FileStatsException("Empty File"));
    }

    /**
     * Returns {@code true} if this character has appeared in the text, and {@code false} otherwise
     *
     * @param character a specific character to check
     * @return {@code true} if this character has appeared in the text, and {@code false} otherwise
     */
    public boolean containsCharacter(char character) {
        return charCountMap.containsKey(character);
    }
}
