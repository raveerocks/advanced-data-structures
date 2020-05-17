package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Minimum extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer DEFAULT_VALUE = Integer.MAX_VALUE;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return Math.min(getDefaultOnNull(number1),getDefaultOnNull(number2));
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }


    private Minimum(){ }

    static class DefaultInstance{
        private static Minimum defaultSingletonInstance = new Minimum();
    }


    public synchronized static Minimum getIntegerMinimum() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
