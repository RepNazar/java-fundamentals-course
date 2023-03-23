package com.bobocode.se;

import lombok.SneakyThrows;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

/**
 * A generic comparator that is comparing a random field of the given class. The field is either primitive or
 * {@link Comparable}. It is chosen during comparator instance creation and is used for all comparisons.
 * <p>
 * If no field is available to compare, the constructor throws {@link IllegalArgumentException}
 *
 * @param <T> the type of the objects that may be compared by this comparator
 *            <p><p>
 *            <strong>TODO: to get the most out of your learning, <a href="https://www.bobocode.com/learn">visit our website</a></strong>
 *            <p>
 * @author Stanislav Zabramnyi
 */
// SEEN
public class RandomFieldComparator<T> implements Comparator<T> {
    private final Field field;
    private final Class<T> type;

    public RandomFieldComparator(Class<T> targetType) {
        this.type = Objects.requireNonNull(targetType);
        this.field = Arrays.stream(type.getDeclaredFields())
                .filter(field1 -> Comparable.class.isAssignableFrom(field1.getType()) ||
                        field1.getType().isPrimitive())
                .findAny()
                .orElseThrow(IllegalArgumentException::new);
    }

    /**
     * Compares two objects of the class T by the value of the field that was randomly chosen. It allows null values
     * for the fields, and it treats null value greater than a non-null value.
     *
     * @param o1
     * @param o2
     * @return positive int in case of first parameter {@param o1} is greater than second one {@param o2},
     * zero if objects are equals,
     * negative int in case of first parameter {@param o1} is less than second one {@param o2}.
     */
    @Override
    public int compare(T o1, T o2) {
        Objects.requireNonNull(o1);
        Objects.requireNonNull(o2);
        return compareHelper(o1, o2);
    }

    @SneakyThrows
    private <R extends Comparable<R>> int compareHelper(T o1, T o2) {
        field.setAccessible(true);
        R val1 = (R) field.get(o1);
        R val2 = (R) field.get(o2);

        Comparator<R> comparator = Comparator.nullsLast(Comparator.naturalOrder());
        return comparator.compare(val1, val2);
    }

    /**
     * Returns the name of the randomly-chosen comparing field.
     */
    public String getComparingFieldName() {
        return field.getName();
    }

    /**
     * Returns a statement "Random field comparator of class '%s' is comparing '%s'" where the first param is the name
     * of the type T, and the second parameter is the comparing field name.
     *
     * @return a predefined statement
     */
    @Override
    public String toString() {
        return String.format("Random field comparator of class '%s' is comparing '%s'", type.getSimpleName(), getComparingFieldName());
    }

}
