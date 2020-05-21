package io.raveerocks.github.operations.binary.math.reals.floats;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Minimum extends AbstractBinaryOperator<Float,Float> {
    private static Float IDENTITY_CONSTANT = Float.MAX_VALUE;
    private static Float LAZY_CONSTANT = 0f;

    @Override
    public Float apply(Float element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Float apply(Float number1, Float number2) {
        return Math.min(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Float getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Float getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Minimum(){}
}
