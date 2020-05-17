package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Addition extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer DEFAULT_VALUE = 0;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return getDefaultOnNull(number1)+getDefaultOnNull(number2);
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    private Addition(){ }

    static class DefaultInstance{
        private static Addition defaultSingletonInstance = new Addition();
    }

    public synchronized static Addition getIntegerAddition() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
