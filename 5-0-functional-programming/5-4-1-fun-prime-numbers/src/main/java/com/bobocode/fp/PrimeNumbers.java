package com.bobocode.fp;

import com.bobocode.util.ExerciseNotCompletedException;

import java.util.List;
import java.util.function.IntConsumer;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * {@link PrimeNumbers} provides an API to work with prime numbers. It is using a stream of prime numbers.
 * <p>
 * See {@link OOSumOfPrimes} for a reference
 */
public class PrimeNumbers {
    private PrimeNumbers() {
    }

    // TODO: Refactor
    private static boolean isPrime(int number) {
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    private static IntStream generatePrimeStream(int n) {
        return IntStream.iterate(1, i -> i + 1)
                .filter(PrimeNumbers::isPrime)
                .limit(n);
    }

    /**
     * Calculates the sum on first n prime numbers.
     * E.g. if n = 5, the result should be 2 + 3 + 5 + 7 + 11 = 28
     *
     * @param n the number of first prime numbers
     * @return the sum of n prime numbers
     */
    // TODO: Refactor
    public static int sum(int n) {
        return generatePrimeStream(n + 1).skip(1).sum();
    }

    /**
     * Collects n first prime numbers.
     *
     * @return a list of collected prime numbers
     */
    public static List<Integer> collect(int n) {
        return generatePrimeStream(n).boxed().collect(Collectors.toList());
    }

    /**
     * Find a prime number by index and then applies a provided consumer passing found prime number
     *
     * @param idx      the position of a prime number (index)
     * @param consumer a logic that should be applied to the found prime number
     */
    public static void processByIndex(int idx, IntConsumer consumer) {
        generatePrimeStream(idx).skip(idx - 1).forEach(consumer);
    }
}
