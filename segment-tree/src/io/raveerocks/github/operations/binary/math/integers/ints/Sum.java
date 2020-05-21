package io.raveerocks.github.operations.binary.math.integers.ints;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Sum extends AbstractBinaryOperator<Integer,Integer> {
    private static Integer IDENTITY_CONSTANT = 0;
    private static Integer LAZY_CONSTANT = 0;

    @Override
    public Integer apply(Integer element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return getLazyOnNullNonLeaf(number1)+getLazyOnNullNonLeaf(number2);
    }

    @Override
    public Integer getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Integer getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Sum(){}

}
