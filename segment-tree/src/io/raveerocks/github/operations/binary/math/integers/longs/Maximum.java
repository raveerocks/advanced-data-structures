package io.raveerocks.github.operations.binary.math.integers.longs;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;

public final class Maximum extends AbstractBinaryOperator<Long,Long> {
    private static Long IDENTITY_CONSTANT = Long.MIN_VALUE;
    private static Long LAZY_CONSTANT = 0l;

    @Override
    public Long apply(Long element) {
        return getIdentityOnNull(element);
    }

    @Override
    public Long apply(Long number1, Long number2) {
        return Math.max(getLazyOnNullNonLeaf(number1),getLazyOnNullNonLeaf(number2));
    }

    @Override
    public Long getIdentityConstant() {
        return IDENTITY_CONSTANT;
    }

    @Override
    public Long getLazyConstant() {
        return LAZY_CONSTANT;
    }

    protected Maximum(){ }
}
