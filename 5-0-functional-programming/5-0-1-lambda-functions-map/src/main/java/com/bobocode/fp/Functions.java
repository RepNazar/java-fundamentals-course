package com.bobocode.fp;

public class Functions {
    /**
     * A static factory method that creates an integer function map with basic functions:
     * - abs (absolute value)
     * - sgn (signum function)
     * - increment
     * - decrement
     * - square
     *
     * @return an instance of {@link FunctionMap} that contains all listed functions
     */
    public static FunctionMap<Integer, Integer> intFunctionMap() {
        FunctionMap<Integer, Integer> intFunctionMap = new FunctionMap<>();

        intFunctionMap.addFunction("abs", Math::abs);
        intFunctionMap.addFunction("sgn", i -> i == 0 ? 0 : i >> 31 == 0 ? 1 : -1);
        intFunctionMap.addFunction("increment", i -> ++i);
        intFunctionMap.addFunction("decrement", i -> --i);
        intFunctionMap.addFunction("square", i -> i * i);

        System.out.println(intFunctionMap.getFunction("sgn").apply(1));
        return intFunctionMap;
    }
}
