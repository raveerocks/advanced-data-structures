package io.raveerocks.github.trees.lazy;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.SingleSegmentTree;

import java.util.Arrays;


public class LazySingleSegmentTree<T,U> implements SingleSegmentTree<T,U> {



    private static final int DEFAULT_SCALING_FACTOR = 4;
    private static final int MAX_ARRAY_SIZE = 100000000;

    private T[] elements;
    private boolean[] isInserted;
    private BinaryOperator<T, U> operation;
    private Node<U>[] tree;
    private int nodeIndex=-1;
    private U defaultValue;
    private int size;

    public LazySingleSegmentTree(int length, BinaryOperator<T,U> operation){
         this(length,operation,operation.getDefaultValue());
    }

    public LazySingleSegmentTree(int length, BinaryOperator<T,U> operation, U defaultValue){
        this.operation = operation;
        this.defaultValue=defaultValue;
        this.elements = (T[])new Object[length];
        Arrays.fill(elements,defaultValue);
        this.isInserted = new boolean[length];
        this.size = Math.min(elements.length*DEFAULT_SCALING_FACTOR,MAX_ARRAY_SIZE);
        tree = new Node[this.size];
        build(elements,operation);
    }

    @Override
    public SingleSegmentTree build(T[] elements, BinaryOperator<T, U> operation) {
        tree[++nodeIndex] = new Node<>(defaultValue,-1);
        return this;
    }

    @Override
    public SegmentTree setElement(int index, T element) {
        return setElement(index,element,operation.apply(element));
    }

    @Override
    public SegmentTree setElement(int index, T element, U updatedLeaf) {
        if (this.elements[index] == element) {
            return this;
        }
        this.elements[index] = element;
        if(!isInserted[index]){
            insert(index,-1,0,0,elements.length-1);
        }
        update(index,0, 0,elements.length - 1,operation.apply(elements[index]));
        return this;
    }

    @Override
    public T getElement(int index) {
        return elements[index];
    }

    @Override
    public U query(int beginIndex, int endIndex) {
        checkBoundsBeginEnd(beginIndex, endIndex, elements.length);
        return query(beginIndex,endIndex,0, 0, elements.length - 1);
    }

    private int insert(int elementIndex, int parentIndex, int treeIndex, int leftBoundary, int rightBoundary){
        if (nodeIndex == size-1) {
            throw new OutOfMemoryError("Segment tree size cannot exceed " + size);
        }
        if(leftBoundary==rightBoundary){
            isInserted[elementIndex] = true;
            treeIndex = ++nodeIndex;
            tree[treeIndex] = new Node<>(defaultValue,-1,-1,parentIndex);
            return treeIndex;
        }
        else{
            Node node;
            if(treeIndex==-1){
                treeIndex = ++nodeIndex;
                node = new Node<>(defaultValue,-1,-1,parentIndex);
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
            U left = tree[treeIndex].getLeftChild()==-1?defaultValue:tree[leftChild].getItem();
            U right = tree[treeIndex].getRightChild()==-1?defaultValue:tree[rightChild].getItem();
            tree[treeIndex].setItem(operation.apply(left, right));
        }
    }

    private U query(int beginIndex, int endIndex,int treeIndex, int leftBoundary, int rightBoundary) {
        if (beginIndex > endIndex) {
            return null;
        }
        if (treeIndex==-1){
            return defaultValue;
        }
        if (leftBoundary == beginIndex && rightBoundary == endIndex) {
            return tree[treeIndex].getItem();
        }
        int middle = (leftBoundary + rightBoundary) >> 1;
        U leftQuery = operation.getDefaultValue(), rightQuery = operation.getDefaultValue();
        if (beginIndex <=middle) {
            leftQuery = query(beginIndex, Math.min(middle, endIndex),tree[treeIndex].getLeftChild(), leftBoundary, middle);
        }
        if (endIndex >= Math.max(beginIndex, middle + 1)) {
            rightQuery = query(Math.max(beginIndex, middle + 1), endIndex,tree[treeIndex].getRightChild(), middle + 1, rightBoundary);
        }
        return operation.apply(leftQuery, rightQuery);
    }

    private void checkBoundsBeginEnd(int beginIndex, int endIndex, int length) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex > length) {
            throw new ArrayIndexOutOfBoundsException("begin " + beginIndex + ", end " + endIndex + ", length " + length);
        }
    }



}
