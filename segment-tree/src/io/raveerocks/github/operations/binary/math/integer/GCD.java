package io.raveerocks.github.operations.binary.math.integer;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class GCD implements BinaryOperator<Integer,Integer> {

    private static Integer DEFAULT_VALUE = 0;

    @Override
    public  Integer apply(Integer number1, Integer number2) {
        number1 = number1==null?DEFAULT_VALUE:number1;
        number2 = number2==null?DEFAULT_VALUE:number2;
        return gcd(number1,number2);
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return Integer.class;
    }

    /**
     *
     * @param A : First Number
     * @param B : Second Number
     * @return  : GCD
     */
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
