package io.raveerocks.github.operations.binary.math.integer;


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

    private int gcd(int A, int B) {
        if (A<0 || B<0){
            throw new NumberFormatException("GCD is only defined for positive numbers");
        }
        return B==0? A:gcd(B,A%B);
    }

    private GCD(){ }

    static class DefaultInstance{
        private static GCD defaultSingletonInstance = new GCD();
    }

    public synchronized static GCD getGCD() {
        return DefaultInstance.defaultSingletonInstance;
    }
}
