package io.raveerocks.github.operations.binary.math.integers.longs;


import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class GCD extends AbstractBinaryOperator<Long,Long> {

    private static Long IDENTITY_CONSTANT = 0l;
    private static Long LAZY_CONSTANT = 0l;

    @Override
    public Long apply(Long element) {
        return getIdentityOnNull(element);
    }

    @Override
    public  Long apply(Long number1, Long number2) {
        return gcd(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Long getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Long getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Long gcd(Long A, Long B) {
        if (A<0 || B<0){
            throw new NumberFormatException("GCD is only defined for positive numbers");
        }
        return B==0? A:gcd(B,A%B);
    }

    protected GCD(){}
}
