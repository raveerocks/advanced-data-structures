package io.raveerocks.github.trees;

public interface SegmentTree<T,U> {

    SegmentTree setElement(int index, T element);

    SegmentTree setElement(int index, T element, U updatedLeaf);

    T getElement(int index);

    U query(int beginIndex, int endIndex);
}
