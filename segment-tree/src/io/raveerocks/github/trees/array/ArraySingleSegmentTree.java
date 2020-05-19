package io.raveerocks.github.trees.array;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.AbstractSingleSegmentTree;

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
            build(rightChild, middleIndex + 1, endIndex);
            this.tree[index] = this.operator.apply(this.tree[leftChild], this.tree[rightChild]);
        }
    }

    private void update(int index, int treeIndex,int beginIndex, int endIndex, U updatedLeaf) {
        if (beginIndex == endIndex) {
            tree[treeIndex] = updatedLeaf;
        }
        else {
            int middleIndex = (beginIndex + endIndex) >> 1;
            int leftChild = getLeftChild(index);
            int rightChild = getRightChild(index);

            if (index <= middleIndex) {
                update(index,leftChild, beginIndex, middleIndex,updatedLeaf);
            } else {
                update(index,rightChild, middleIndex + 1, endIndex,updatedLeaf);
            }
            tree[index] = operator.apply(tree[leftChild], tree[rightChild]);
        }
    }
}