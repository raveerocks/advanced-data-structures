package io.raveerocks.github.operations.binary.math.reals.floats;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Maximum extends AbstractBinaryOperator<Float,Float> {
    private static Float IDENTITY_CONSTANT = Float.MIN_VALUE;
    private static Float LAZY_CONSTANT = 0f;

    @Override
    public Float apply(Float element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Float apply(Float number1, Float number2) {
        return Math.max(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Float getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Float getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Maximum(){ }
}
