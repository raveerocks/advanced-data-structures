package io.raveerocks.github.operations.binary.math.integers.ints;


import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class GCD extends AbstractBinaryOperator<Integer,Integer> {

    private static Integer IDENTITY_CONSTANT = 0;
    private static Integer LAZY_CONSTANT = 0;

    @Override
    public Integer apply(Integer element) {
        return getIdentityOnNull(element);
    }

    @Override
    public  Integer apply(Integer number1, Integer number2) {
        return gcd(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Integer getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Integer getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Integer gcd(Integer A, Integer B) {
        if (A<0 || B<0){
            throw new NumberFormatException("GCD is only defined for positive numbers");
        }
        return B==0? A:gcd(B,A%B);
    }

    protected GCD(){}

}
