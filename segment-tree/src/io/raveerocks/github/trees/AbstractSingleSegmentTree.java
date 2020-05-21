package io.raveerocks.github.trees;

import io.raveerocks.github.operations.binary.BinaryOperator;

import java.security.InvalidParameterException;

public abstract class AbstractSingleSegmentTree<T, U> implements SingleSegmentTree<T, U> {

    protected static final int DEFAULT_SCALING_FACTOR = 4;
    protected static final int MAX_ARRAY_SIZE = 100000000;

    protected T[] elements;
    protected int elementSize;
    protected BinaryOperator<T, U> operator;
    protected int treeSize;


    protected abstract U get(int index);

    protected abstract int getLeftChild(int index);

    protected abstract int getRightChild(int index);

    protected abstract void upsert(int index, U updatedLeaf);

    protected abstract U resolveIdentityOrLazy();


    protected void initialise(T[] elements, BinaryOperator<T, U> operator, int treeSize) {
        this.elements = elements;
        this.operator = operator;
        this.treeSize = treeSize;
        this.elementSize = elements.length;
    }

    @Override
    public void setElement(int index, T element) {
        if (this.elements[index] == element) {
            return;
        }
        this.elements[index] = element;
        upsert(index, operator.apply(element));
    }

    @Override
    public void setElement(int index, T element, U updatedLeaf) {
        if (this.elements[index] == element) {
            return;
        }
        this.elements[index] = element;
        upsert(index, updatedLeaf);
    }

    @Override
    public T getElement(int index) {
        return elements[index];
    }

    @Override
    public U query(int beginIndex, int endIndex) {
        checkBoundsBeginEnd(beginIndex, endIndex, elementSize);
        return query(beginIndex, endIndex, 0, 0, elementSize - 1);
    }

    protected U query(int beginIndex, int endIndex, int treeIndex, int leftBoundary, int rightBoundary) {
        if (beginIndex > endIndex) {
            throw new InvalidParameterException();
        }
        if (leftBoundary == beginIndex && rightBoundary == endIndex) {
            return get(treeIndex);
        }

        int middle = (leftBoundary + rightBoundary) >> 1;

        U leftQuery = resolveIdentityOrLazy(), rightQuery = resolveIdentityOrLazy();

        if (beginIndex <= middle) {
            leftQuery = query(beginIndex, Math.min(endIndex,middle), getLeftChild(treeIndex), leftBoundary, middle);
        }
        if (endIndex > middle) {
            rightQuery = query(Math.max(beginIndex,middle + 1), endIndex, getRightChild(treeIndex), middle + 1, rightBoundary);
        }
        return operator.apply(leftQuery, rightQuery);
    }

    private void checkBoundsBeginEnd(int beginIndex, int endIndex, int length) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex >= length) {
            throw new ArrayIndexOutOfBoundsException("begin " + beginIndex + ", end " + endIndex + ", length " + length);
        }
    }




}
