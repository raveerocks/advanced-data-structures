package io.raveerocks.github.operations.binary;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.operations.binary.math.integer.Addition;

public abstract class AbstractBinaryOperator<T,U> implements BinaryOperator<T,U> {
    public U getDefaultOnNull(T element){
        return element==null?getDefaultValue():apply(element);
    }
}
