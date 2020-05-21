package io.raveerocks.github.operations.binary.math.reals.doubles;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Minimum extends AbstractBinaryOperator<Double,Double> {
    private static Double IDENTITY_CONSTANT = Double.MAX_VALUE;
    private static Double LAZY_CONSTANT = 0d;

    @Override
    public Double apply(Double element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Double apply(Double number1, Double number2) {
        return Math.min(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Double getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Double getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Minimum(){}
}
