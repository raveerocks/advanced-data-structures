package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Maximum extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer IDENTITY_CONSTANT = Integer.MIN_VALUE;
    private static Integer LAZY_CONSTANT = 0;


    @Override
    public Integer apply(Integer element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return Math.max(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Integer getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Integer getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Maximum(){ }

    static class DefaultInstance{
        private static Maximum defaultSingletonInstance = new Maximum();
    }

    public synchronized static Maximum getIntegerMaximum() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
