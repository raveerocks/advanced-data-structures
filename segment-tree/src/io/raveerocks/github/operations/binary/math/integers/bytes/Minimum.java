package io.raveerocks.github.operations.binary.math.integers.bytes;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Minimum extends AbstractBinaryOperator<Byte,Byte> {
    private static Byte IDENTITY_CONSTANT = Byte.MAX_VALUE;
    private static Byte LAZY_CONSTANT = 0;

    @Override
    public Byte apply(Byte element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Byte apply(Byte number1, Byte number2) {
        return (byte)Math.min(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Byte getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Byte getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Minimum(){}
}
