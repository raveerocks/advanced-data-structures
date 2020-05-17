package io.raveerocks.github.trees;

import io.raveerocks.github.operations.binary.BinaryOperator;

public interface SingleSegmentTree<T, U>extends SegmentTree<T,U> {
    SingleSegmentTree build(T[] elements, BinaryOperator<T,U> operation);
}