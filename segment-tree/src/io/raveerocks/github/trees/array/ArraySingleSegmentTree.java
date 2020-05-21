package io.raveerocks.github.trees.array;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.AbstractSingleSegmentTree;

import java.lang.reflect.Type;
import java.util.Arrays;

public class ArraySingleSegmentTree<T, U> extends AbstractSingleSegmentTree<T,U> {
    private U[] tree;

    public ArraySingleSegmentTree(T[] elements, BinaryOperator<T,U> operator) {
        if (elements.length > MAX_ARRAY_SIZE) {
            throw new OutOfMemoryError("Segment tree size cannot exceed " + MAX_ARRAY_SIZE);
        }
        initialise(elements,operator,DEFAULT_SCALING_FACTOR * elements.length);
        tree = (U[]) new Object[treeSize];
        build(0, 0, elements.length - 1);
    }

    @Override
    protected U get(int index) {
        return tree[index];
    }

    @Override
    protected void upsert(int index, U updatedLeaf) {
        update(index,0,0,elementSize-1,updatedLeaf);
    }

    @Override
    protected int getLeftChild(int index) {
        return (index << 1) + 1;
    }

    @Override
    protected int getRightChild(int index) {
        return (index << 1) + 2;
    }

    @Override
    protected U resolveIdentityOrLazy() {
        return operator.getIdentityConstant();
    }

    private void build(int index, int beginIndex, int endIndex) {
        if (beginIndex == endIndex) {
            this.tree[index] = operator.apply(elements[beginIndex]);
        } else {
            int middleIndex = (beginIndex + endIndex) >> 1;
            int leftChild = getLeftChild(index);
            int rightChild = getRightChild(index);
            build(leftChild, beginIndex, middleIndex);
            build(rightChild, Math.min(middleIndex + 1,endIndex), endIndex);
            this.tree[index] = operator.apply(tree[leftChild], tree[rightChild]);
        }
    }

    private void update(int index, int treeIndex,int leftBoundary, int rightBoundary, U updatedLeaf) {
        if (leftBoundary == rightBoundary) {
            tree[treeIndex] = updatedLeaf;
            return;
        }
        else {
            int middleIndex = (leftBoundary + rightBoundary) >> 1;
            int leftChild = getLeftChild(treeIndex);
            int rightChild = getRightChild(treeIndex);

            if (index <= middleIndex) {
                update(index,leftChild, leftBoundary, middleIndex,updatedLeaf);
            } else {
                update(index,rightChild, Math.min(middleIndex+1,rightBoundary), rightBoundary,updatedLeaf);
            }
            tree[treeIndex] = operator.apply(tree[leftChild], tree[rightChild]);
        }
    }

    protected U[] getRawTree(){
        return tree;
    }

}