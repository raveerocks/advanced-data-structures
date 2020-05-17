package io.raveerocks.github.operations.binary.math.integer.binary;

import io.raveerocks.github.operations.binary.BinaryOperator;

public class SetBitCount implements BinaryOperator<Integer, Integer> {

    private static Integer DEFAULT_VALUE = 0;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        number1 = number1 == null ? DEFAULT_VALUE : number1;
        number2 = number2 == null ? DEFAULT_VALUE : number2;
        return number1 + number2;
    }

    public Integer apply(Integer number) {
        number = number == null ? DEFAULT_VALUE : number;
        return countSetBits(number);
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
    }

    @Override
    public Class getResultType() {
        return Integer.class;
    }


    static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    private SetBitCount() {
    }

    static class DefaultInstance {
        private static SetBitCount defaultSingletonInstance = new SetBitCount();
    }

    public synchronized static SetBitCount getBinarySetBitCount() {
        return DefaultInstance.defaultSingletonInstance;
    }
}