package io.raveerocks.github.trees.array;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.SingleSegmentTree;

public class SingleArraySegmentTree<T, U> implements SingleSegmentTree<T, U> {

    private static final int DEFAULT_SCALING_FACTOR = 4;
    private static final int MAX_ARRAY_SIZE = 25000000;

    private T[] elements;
    private BinaryOperator<T, U> operation;
    private U[] tree;
    private int size;


    public SingleArraySegmentTree(T[] elements, BinaryOperator<T,U> operation) {
        build(elements, operation);
    }


    @Override
    public SingleSegmentTree build(T[] elements, BinaryOperator<T, U> operation) {
        if (elements.length > MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError("Segment tree size cannot exceed " + MAX_ARRAY_SIZE);
        }
        this.elements = elements;
        this.operation = operation;
        this.size = DEFAULT_SCALING_FACTOR * elements.length;
        tree = (U[]) new Object[this.size];
        return build(0, 0, elements.length - 1);
    }

    @Override
    public SingleSegmentTree setElement(int index, T element) {
        if (this.elements[index] == element) {
            return this;
        }
        this.elements[index] = element;
        update(0, 0, elements.length - 1, index,operation.apply(elements[index]));
        return this;
    }

    @Override
    public SingleSegmentTree setElement(int index, T element, U updatedLeaf) {
        if (this.elements[index] == element) {
            return this;
        }
        this.elements[index] = element;
        update(0, 0, elements.length - 1, index,updatedLeaf);
        return this;
    }

    @Override
    public T getElement(int index) {
        return elements[index];
    }

    @Override
    public U query(int beginIndex, int endIndex) {
        checkBoundsBeginEnd(beginIndex, endIndex, elements.length);
        return query(0, 0, elements.length - 1, beginIndex, endIndex);
    }


    private SingleSegmentTree build(int index, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            this.tree[index] = operation.apply(this.elements[beginIndex]);
        } else {
            int middleIndex = (beginIndex + endIndex) >> 1;
            int leftChild = (index << 1) + 1;
            int rightChild = (index << 1) + 2;
            build(leftChild, beginIndex, middleIndex);
            build(rightChild, middleIndex + 1, endIndex);
            this.tree[index] = this.operation.apply(this.tree[leftChild], this.tree[rightChild]);
        }
        return this;
    }

    private void update(int index, int beginIndex, int endIndex, int position, U updatedLeaf) {
        if (beginIndex == endIndex) {
            tree[index] = updatedLeaf;
            return;
        } else {
            int middleIndex = (beginIndex + endIndex) >> 1;
            int leftChild = (index << 1) + 1;
            int rightChild = (index << 1) + 2;

            if (position <= middleIndex) {
                update(leftChild, beginIndex, middleIndex, position,updatedLeaf);
            } else {
                update(rightChild, middleIndex + 1, endIndex, position,updatedLeaf);
            }
            tree[index] = operation.apply(tree[leftChild], tree[rightChild]);
        }
    }

    private U query(int index, int leftBoundary, int rightBoundary, int beginIndex, int endIndex) {
        if (beginIndex > endIndex) {
            return null;
        }
        if (leftBoundary == beginIndex && rightBoundary == endIndex) {
            return tree[index];
        }
        int middle = (leftBoundary + rightBoundary) >> 1;
        int leftChild = (index << 1) + 1;
        int rightChild = (index << 1) + 2;
        U leftQuery = null, rightQuery = null;
        if (beginIndex <=middle) {
            leftQuery = query(leftChild, leftBoundary, middle, beginIndex, Math.min(middle, endIndex));
        }
        if (endIndex >= Math.max(beginIndex, middle + 1)) {
            rightQuery = query(rightChild, middle + 1, rightBoundary, Math.max(beginIndex, middle + 1), endIndex);
        }
        return operation.apply(leftQuery, rightQuery);
    }

    private void checkBoundsBeginEnd(int beginIndex, int endIndex, int length) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex > length) {
            throw new ArrayIndexOutOfBoundsException("begin " + beginIndex + ", end " + endIndex + ", length " + length);
        }
    }

}