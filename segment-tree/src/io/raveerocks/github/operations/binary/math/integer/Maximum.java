package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Maximum extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer DEFAULT_VALUE = Integer.MIN_VALUE;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return Math.max(getDefaultOnNull(number1),getDefaultOnNull(number2));
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    private Maximum(){ }

    static class DefaultInstance{
        private static Maximum defaultSingletonInstance = new Maximum();
    }

    public synchronized static Maximum getIntegerMaximum() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
