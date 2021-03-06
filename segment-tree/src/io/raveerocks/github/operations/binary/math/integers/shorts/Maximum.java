package io.raveerocks.github.operations.binary.math.integers.shorts;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Maximum extends AbstractBinaryOperator<Short,Short> {
    private static Short IDENTITY_CONSTANT = Short.MIN_VALUE;
    private static Short LAZY_CONSTANT = 0;

    @Override
    public Short apply(Short element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Short apply(Short number1, Short number2) {
        return (short)Math.max(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Short getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Short getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Maximum(){ }
}
