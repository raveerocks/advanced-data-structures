package io.raveerocks.github.operations.binary;

public interface BinaryOperator<T, U> {

    U apply(T element);

    U apply(U element1, U element2);

    U getIdentityConstant();

    T getLazyConstant();

}