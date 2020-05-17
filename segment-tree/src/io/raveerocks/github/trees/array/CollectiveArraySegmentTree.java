package io.raveerocks.github.trees.array;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.CollectiveSegmentTree;

public class CollectiveArraySegmentTree<T> implements CollectiveSegmentTree<T> {

    private static final int DEFAULT_SCALING_FACTOR = 4;
    private static final int MAX_ARRAY_SIZE = 25000000;

    private T[] elements;
    BinaryOperator<T,Object> operations[];
    private int operationSize;
    private Object[][] tree;
    private int size;

    public CollectiveArraySegmentTree(T[] elements, BinaryOperator[] operations) {
        build(elements,operations);
    }

    @Override
    public CollectiveArraySegmentTree build(T[] elements, BinaryOperator[] operations) {
        if (elements.length>MAX_ARRAY_SIZE){
            throw new OutOfMemoryError("Segment tree size cannot exceed "+ MAX_ARRAY_SIZE);
        }
        this.elements = elements;
        this.operations = operations;
        this.operationSize = operations.length;
        this.size=DEFAULT_SCALING_FACTOR*elements.length;
        tree = new Object[this.size][operationSize];
        return build(0,0,elements.length-1);
    }

    @Override
    public CollectiveArraySegmentTree setElement(int index, T element) {
        if (this.elements[index]==element){
            return this;
        }
        this.elements[index] = element;

        Object[] updatedLeaf = new Object[operationSize];

        for (int i=0; i<operationSize;i++) {
            updatedLeaf[i] = operations[i].apply(element);
        }
        update(0,0,elements.length-1,index,updatedLeaf);
        return this;
    }

    @Override
    public CollectiveSegmentTree setElement(int index, T element, Object[] updatedLeaf) {
        if (this.elements[index]==element){
            return this;
        }
        this.elements[index] = element;
        update(0,0,elements.length-1,index,updatedLeaf);
        return this;
    }

    @Override
    public T getElement(int index) {
        return elements[index];
    }

    @Override
    public Object[]  query(int beginIndex, int endIndex) {
        checkBoundsBeginEnd(beginIndex,endIndex,tree.length);
        return query(0,0,elements.length-1,beginIndex,endIndex);
    }

    private CollectiveArraySegmentTree build(int index,int beginIndex, int endIndex){
        if (beginIndex==endIndex){
            for (int i=0; i<operationSize;i++){
                this.tree[index] [i]= operations[i].apply(this.elements[beginIndex]);
            }

        }
        else {
            int middleIndex = (beginIndex+endIndex)>>1;
            int leftChild = (index<<1)+1;
            int rightChild = (index<<1)+2;
            build(leftChild,beginIndex,middleIndex);
            build(rightChild,middleIndex+1,endIndex);
            for (int i=0; i<operationSize;i++) {
                this.tree[index][i] = this.operations[i].apply(this.tree[leftChild][i], this.tree[rightChild][i]);
            }
        }
        return this;
    }

    private void update(int index,int beginIndex, int endIndex,int position,Object[] updatedLeaf){
        if (beginIndex==endIndex){
            for (int i=0; i<operationSize;i++) {
                tree[index][i] = updatedLeaf;
            }
            return;
        }
        else {
            int middleIndex = (beginIndex+endIndex)>>1;
            int leftChild = (index<<1)+1;
            int rightChild = (index<<1)+2;

            if (position<=middleIndex){
                update(leftChild,beginIndex,middleIndex,position,updatedLeaf);
            }
            else {
                update(rightChild,middleIndex+1,endIndex,position,updatedLeaf);
            }
            for (int i=0; i<operationSize;i++) {
                tree[index][i] = operations[i].apply(tree[leftChild][i], tree[rightChild][i]);
            }
        }
    }


    private Object[]   query(int index, int leftBoundary, int rightBoundary, int beginIndex, int endIndex){
        Object[] result = new Object[operationSize];
        if (beginIndex>endIndex){
            return result;
        }

        if(leftBoundary==beginIndex && rightBoundary==endIndex){
            return tree[index];
        }
        int middle = (leftBoundary+rightBoundary)>>1;
        int leftChild = (index<<1)+1;
        int rightChild = (index<<1)+2;

        Object[] leftQuery = new Object[operationSize], rightQuery = new Object[operationSize];
        if (beginIndex <=middle) {
            leftQuery = query(leftChild, leftBoundary, middle, beginIndex, Math.min(middle, endIndex));
        }
        if (endIndex >= Math.max(beginIndex, middle + 1)) {
            rightQuery = query(rightChild, middle + 1, rightBoundary, Math.max(beginIndex, middle + 1), endIndex);
        }

        for (int i=0; i<operationSize; i++){
            result[i] = operations[i].apply(leftQuery[i],rightQuery[i]);
        }
        return result;
    }

    static void checkBoundsBeginEnd(int beginIndex, int endIndex, int length) {
        if (beginIndex < 0 || beginIndex > endIndex || endIndex > length) {
            throw new ArrayIndexOutOfBoundsException("begin " + beginIndex + ", end " + endIndex + ", length " + length);
        }
    }

}
