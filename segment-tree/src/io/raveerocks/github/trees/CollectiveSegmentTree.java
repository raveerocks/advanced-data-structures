package io.raveerocks.github.trees;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.array.CollectiveArraySegmentTree;

public interface CollectiveSegmentTree<T> extends SegmentTree<T,Object[]> {

     CollectiveArraySegmentTree build(T[] elements, BinaryOperator... operations);

}
