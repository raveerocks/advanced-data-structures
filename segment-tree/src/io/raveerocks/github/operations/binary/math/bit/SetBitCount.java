package io.raveerocks.github.operations.binary.math.bit;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class SetBitCount extends AbstractBinaryOperator<Integer, Integer> {

    private static Integer IDENTITY_CONSTANT = 0;
    private static Integer LAZY_CONSTANT = 0;

    @Override
    public Integer apply(Integer element) {
         element =getIdentityOnNull(element);
        return countSetBits(element);
    }

    @Override
    public Integer apply(Integer number1, Integer number2) {
        return getLazyOnNullNonLeaf(number1) + getLazyOnNullNonLeaf(number2);
    }

    @Override
    public Integer getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Integer getLazyConstant() {
        return LAZY_CONSTANT;
    }

    static int countSetBits(int n) {
        int count = 0;
        while (n > 0) {
            n &= (n - 1);
            count++;
        }
        return count;
    }

    protected SetBitCount() {
    }

}