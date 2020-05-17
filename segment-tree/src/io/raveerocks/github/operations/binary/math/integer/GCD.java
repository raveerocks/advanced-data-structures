package io.raveerocks.github.operations.binary.math.integer;


import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class GCD extends AbstractBinaryOperator<Integer,Integer> {

    private static Integer DEFAULT_VALUE = 0;

    @Override
    public  Integer apply(Integer number1, Integer number2) {
        return gcd(getDefaultOnNull(number1),getDefaultOnNull(number2));
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
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
