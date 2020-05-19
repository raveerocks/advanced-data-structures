package io.raveerocks.github.operations.binary;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.operations.binary.math.integer.Addition;

public abstract class AbstractBinaryOperator<T,U> implements BinaryOperator<T,U> {
    public T getLazyOnNullLeaf(T element){
        return element==null?getLazyConstant():element;
    }

    public U getLazyOnNullNonLeaf(U element){
        return element==null?apply(getLazyConstant()):element;
    }

    public U getIdentityOnNull(U element){ return element==null?getIdentityConstant():element; }

}
