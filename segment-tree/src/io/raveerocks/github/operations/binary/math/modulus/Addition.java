package io.raveerocks.github.operations.binary.math.modulus;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public class Addition extends AbstractBinaryOperator<Long,Long> {
    private static Long IDENTITY_CONSTANT = 0l;
    private static Long LAZY_CONSTANT = 0l;
    private static int MODULUS = 1000000007;

    @Override
    public Long apply(Long element) {
        return getIdentityOnNull(element)%MODULUS;
    }

    @Override
    public Long apply(Long number1, Long number2) {
        return ((getLazyOnNullNonLeaf(number1)%MODULUS)+(getLazyOnNullNonLeaf(number2))%MODULUS)%MODULUS;
    }

    @Override
    public Long getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Long getLazyConstant() {
        return LAZY_CONSTANT;
    }

    private Addition(){ }

    static class DefaultInstance{
        private static Addition defaultSingletonInstance = new Addition();
    }

    public synchronized static Addition getLongAddition() {
        return DefaultInstance.defaultSingletonInstance;
    }
}