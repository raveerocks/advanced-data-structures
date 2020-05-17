package io.raveerocks.github.operations.binary;

public interface BinaryOperator<T, U> {
    U getDefaultValue();

    U apply(U element1, U element2);

    Class getResultType();

    default U apply(T element) {
        return element == null ? getDefaultValue() : (U) element;
    }

}