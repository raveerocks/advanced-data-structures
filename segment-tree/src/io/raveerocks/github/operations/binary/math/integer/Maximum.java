package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class Maximum implements BinaryOperator<Integer,Integer> {
    private static Integer DEFAULT_VALUE = Integer.MIN_VALUE;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        number1 = number1==null?DEFAULT_VALUE:number1;
        number2 = number2==null?DEFAULT_VALUE:number2;
        return Math.max(number1,number2);
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return Integer.class;
    }

    private Maximum(){ }

    static class DefaultInstance{
        private static Maximum defaultSingletonInstance = new Maximum();
    }

    public synchronized static Maximum getIntegerMaximum() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
