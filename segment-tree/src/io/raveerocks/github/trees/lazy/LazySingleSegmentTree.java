package io.raveerocks.github.trees.lazy;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.AbstractSingleSegmentTree;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.SingleSegmentTree;

import java.util.Arrays;


public class LazySingleSegmentTree<T,U> extends AbstractSingleSegmentTree<T,U> {

    private boolean[] isInserted;
    private Node<U>[] tree;
    private int nodeIndex=-1;
    private final T LAZY_CONSTANT;
    private final U LAZY_RESULT_CONSTANT;

    public LazySingleSegmentTree(int elementSize, BinaryOperator<T,U> operator){
         this(elementSize,operator,operator.getLazyConstant());
    }

    public LazySingleSegmentTree(int elementSize, BinaryOperator<T,U> operator, T defaultValue){
        T[] elements = (T[])new Object[elementSize];
        Arrays.fill(elements,defaultValue);
        initialise(elements,operator,Math.min(elements.length*DEFAULT_SCALING_FACTOR,MAX_ARRAY_SIZE));
        LAZY_CONSTANT=defaultValue;
        LAZY_RESULT_CONSTANT = operator.apply(LAZY_CONSTANT);
        isInserted = new boolean[elementSize];
        tree = new Node[treeSize];
        build();
    }

    @Override
    protected U get(int index) {
        return index==-1?LAZY_RESULT_CONSTANT:tree[index].getItem();
    }

    @Override
    protected void upsert(int index,  U updatedLeaf) {
        if(!isInserted[index]){
            insert(index,-1,0,0,elementSize-1);
        }
        update(index,0, 0,elementSize-1, operator.apply(elements[index]));
    }

    @Override
    protected int getLeftChild(int index) {
        if(index==-1){
            return -1;
        }
        return tree[index].getLeftChild();
    }

    @Override
    protected int getRightChild(int index) {
        if(index==-1){
            return -1;
        }
        return tree[index].getRightChild();
    }

    @Override
    protected U resolveIdentityOrLazy() {
        return operator.apply(operator.getLazyConstant());
    }

    public void build() {
        tree[++nodeIndex] = new Node<>(operator.apply(LAZY_CONSTANT),-1);
    }

    private int insert(int elementIndex, int parentIndex, int treeIndex, int leftBoundary, int rightBoundary){
        if (nodeIndex == treeSize-1) {
            throw new OutOfMemoryError("Segment tree size cannot exceed " + treeSize);
        }
        if(leftBoundary==rightBoundary){
            isInserted[elementIndex] = true;
            treeIndex = ++nodeIndex;
            tree[treeIndex] = new Node<>(LAZY_RESULT_CONSTANT,-1,-1,parentIndex);
            return treeIndex;
        }
        else{
            Node node;
            if(treeIndex==-1){
                treeIndex = ++nodeIndex;
                node = new Node<>(LAZY_RESULT_CONSTANT,-1,-1,parentIndex);
            }
            else {
                node = tree[treeIndex];
            }
            int middle = (leftBoundary + rightBoundary) >> 1;
            if (elementIndex<=middle) {
                node.setLeftChild(insert(elementIndex,treeIndex,node.getLeftChild(),leftBoundary,middle));
            }
            else {
                node.setRightChild(insert(elementIndex,treeIndex,node.getRightChild(),middle+1,rightBoundary));
            }
            tree[treeIndex]=node;
            return treeIndex;
        }

    }

    private void update(int elementIndex, int treeIndex, int leftBoundary,int rightBoundary, U updatedLeaf) {
        if (leftBoundary==rightBoundary) {
            tree[treeIndex].setItem(updatedLeaf);
            return;
        } else {
            int middleIndex = (leftBoundary + rightBoundary) >> 1;
            int leftChild = tree[treeIndex].getLeftChild();
            int rightChild = tree[treeIndex].getRightChild();

            if (elementIndex <= middleIndex) {
                update(elementIndex,leftChild,leftBoundary,middleIndex,updatedLeaf);
            } else if(elementIndex > middleIndex) {
                update(elementIndex,rightChild,Math.min(middleIndex+1,rightBoundary),rightBoundary,updatedLeaf);
            }
            U left = tree[treeIndex].getLeftChild()==-1?LAZY_RESULT_CONSTANT:tree[leftChild].getItem();
            U right = tree[treeIndex].getRightChild()==-1?LAZY_RESULT_CONSTANT:tree[rightChild].getItem();
            tree[treeIndex].setItem(operator.apply(left, right));
        }
    }




}
