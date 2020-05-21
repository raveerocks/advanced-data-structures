package io.raveerocks.github.operations.binary.math.integers.bytes;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Sum extends AbstractBinaryOperator<Byte,Byte> {
    private static Byte IDENTITY_CONSTANT = 0;
    private static Byte LAZY_CONSTANT = 0;


    @Override
    public Byte apply(Byte element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Byte apply(Byte number1, Byte number2) {
        return (byte)(getLazyOnNullNonLeaf(number1)+getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Byte getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Byte getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Sum(){}
}
