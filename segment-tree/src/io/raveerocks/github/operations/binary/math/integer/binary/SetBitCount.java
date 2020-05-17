package io.raveerocks.github.operations.binary.math.integer.binary;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class SetBitCount extends AbstractBinaryOperator<Integer, Integer> {

    private static Integer DEFAULT_VALUE = 0;

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return getDefaultOnNull(number1) + getDefaultOnNull(number2);
    }

    public Integer apply(Integer number) {
        number = number == null ? DEFAULT_VALUE : number;
        return countSetBits(number);
    }

    @Override
    public Integer getDefaultValue() {
        return DEFAULT_VALUE;
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