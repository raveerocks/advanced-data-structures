package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class Addition implements BinaryOperator<Integer,Integer> {
    private static Integer DEFAULT_VALUE = 0;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        number1 = number1==null?DEFAULT_VALUE:number1;
        number2 = number2==null?DEFAULT_VALUE:number2;
        return number1+number2;
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return Integer.class;
    }

    private Addition(){ }

    static class DefaultInstance{
        private static Addition defaultSingletonInstance = new Addition();
    }

    public synchronized static Addition getIntegerAddition() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
