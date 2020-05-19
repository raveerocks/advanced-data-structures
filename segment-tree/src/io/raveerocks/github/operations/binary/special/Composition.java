package io.raveerocks.github.operations.binary.special;

import io.raveerocks.github.operations.binary.AbstractBinaryOperator;
import io.raveerocks.github.operations.binary.BinaryOperator;

public class Composition extends AbstractBinaryOperator<Object,Object> {


    BinaryOperator operator1,operator2;

    public Composition(BinaryOperator operator1, BinaryOperator operator2) {
        this.operator1=operator1;
        this.operator2 = operator2;
    }

    @Override
    @Deprecated
    public Object apply(Object element1, Object element2) {
        return null;
    }

    @Override
    public Object apply(Object element) {
        return operator2.apply(operator1.apply(element));
    }

    @Deprecated
    @Override
    public Object getIdentityConstant() {
        throw new RuntimeException("Identity Constant is Not Defined");
    }

    @Deprecated
    @Override
    public Object getLazyConstant() {
        throw new RuntimeException("Lazy Constant is Not Defined");
    }


}
