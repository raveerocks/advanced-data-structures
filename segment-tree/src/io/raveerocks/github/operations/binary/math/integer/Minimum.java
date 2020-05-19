package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Minimum extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer IDENTITY_CONSTANT = Integer.MAX_VALUE;
    private static Integer LAZY_CONSTANT = 0;

    @Override
    public Integer apply(Integer element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return Math.min(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Integer getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Integer getLazyConstant() {
        return LAZY_CONSTANT;
    }


    private Minimum(){ }

    static class DefaultInstance{
        private static Minimum defaultSingletonInstance = new Minimum();
    }


    public synchronized static Minimum getIntegerMinimum() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
