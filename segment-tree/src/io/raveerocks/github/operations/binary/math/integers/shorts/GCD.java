package io.raveerocks.github.operations.binary.math.integers.shorts;


import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class GCD extends AbstractBinaryOperator<Short,Short> {

    private static Short IDENTITY_CONSTANT = 0;
    private static Short LAZY_CONSTANT = 0;

    @Override
    public Short apply(Short element) {
        return getIdentityOnNull(element);
    }

    @Override
    public  Short apply(Short number1, Short number2) {
        return gcd(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Short getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Short getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Short gcd(Short A, Short B) {
        if (A<0 || B<0){
            throw new NumberFormatException("GCD is only defined for positive numbers");
        }
        return B==0? A:gcd(B,(short)(A%B));
    }

    protected GCD(){}
}
