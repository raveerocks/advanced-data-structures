package io.raveerocks.github.trees.array;

import io.raveerocks.github.operations.binary.special.SpecialBinaryOperatorBuilder;


import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;

public class IndexSegmentTreeUtil<T extends Comparable> {

    private T[] elements;
    private Integer[][] indices;
    private Integer[][] tree;
    private ArraySingleSegmentTree<Integer[],Integer[]> segmentTree;
    private int elementSize;
    int [] indexMap;

    public IndexSegmentTreeUtil(T[] elements) {
        this.elements = elements;
        this.elementSize = elements.length;
        Integer[] sortedIndices = getSortedIndices(elements);
        indexMap = new int[sortedIndices.length];

        for (int i=0; i<elements.length;i++){
            indexMap[sortedIndices[i]] = i;
        }
        this.indices = new Integer[elementSize][1];
        for (int i=0; i<elementSize; i++){
            this.indices[i] = new Integer[1];
            this.indices[i][0] = Integer.valueOf(sortedIndices[i]);
        }
        this.segmentTree = new ArraySingleSegmentTree(this.indices, SpecialBinaryOperatorBuilder.getSortAndMerge());
        tree = getRawTree();
    }

    private Integer[][] getRawTree(){
        Object rawTree[]= segmentTree.getRawTree();
        Integer[][] result = new Integer[rawTree.length][];

        for (int i=0; i<rawTree.length;i++){
            Object[] node = (Object[]) rawTree[i];
            if(node!=null){
                result[i] = new Integer[node.length];
                for (int j=0; j<node.length; j++) {
                    result[i][j] = Integer.valueOf((Integer) node[j]);
                }
            }
        }
        return result;
    }

    public T atOrdinalIndex(int beginIndex, int endIndex,int ordinalIndex){
        checkBoundsOrdinalIndex(beginIndex,endIndex,ordinalIndex);
        int actualIndex = actualIndexOf(beginIndex,endIndex,ordinalIndex,0,0,elementSize-1);
        return elements[actualIndex];
    }

    private int actualIndexOf(int beginIndex, int endIndex,int ordinalIndex,int treeIndex, int leftBoundary, int rightBoundary){
        if(leftBoundary==rightBoundary){
            return  tree[treeIndex][0];
        }

       int countLeft = countInRange(beginIndex,endIndex,tree[segmentTree.getLeftChild(treeIndex)]);
        int middle = (leftBoundary+rightBoundary)>>1;
       if(countLeft>=ordinalIndex){
           return actualIndexOf(beginIndex,endIndex,ordinalIndex,segmentTree.getLeftChild(treeIndex),leftBoundary,middle);
       }
       else {
           return actualIndexOf(beginIndex,endIndex,ordinalIndex-countLeft,segmentTree.getRightChild(treeIndex), Math.min(middle+1,rightBoundary),rightBoundary);
       }
    }

    private int countInRange(int beginIndex, int endIndex,Integer[] node){

        int left=0, right=node.length-1,p1=-1,p2=-1;
        while (left<=right){
            int mid = (left+right)>>1;
            if(node[mid]<beginIndex){
                left = mid+1;
            }
            else if(node[mid]>beginIndex){
                p1=mid;
                right = mid-1;
            }
            else {
                p1=mid;
                break;
            }
        }

        left=0;
        right=node.length-1;
        while (left<=right){
            int mid = (left+right)>>1;
            if(node[mid]<endIndex){
                p2=mid;
                left = mid+1;
            }
            else if(node[mid]>endIndex){
                right = mid-1;
            }
            else {
                p2=mid;
                break;
            }
        }

        int count= p1==-1||p2==-1?0:p2-p1+1;
        return count>0?count:0;
    }

    private Integer[] getSortedIndices(T[] elements){
        HashMap<Integer,T> indexMap = new HashMap<>();
        Integer [] result = new Integer[elements.length];
        for (int i=0;i<elements.length;i++){
            result[i]=i;
            indexMap.put(i,elements[i]);
        }
        Arrays.sort(result, Comparator.comparing(indexMap::get));
        return result;
    }

    private void checkBoundsOrdinalIndex(int beginIndex, int endIndex,int ordinalIndex) {

        if (beginIndex < 0 || beginIndex > endIndex || endIndex >= elementSize) {
            throw new ArrayIndexOutOfBoundsException("begin " + beginIndex + ", end " + endIndex + ", length " + elementSize);
        }

        int maxOrdinalIndex = endIndex-beginIndex+1;
        if (ordinalIndex < 0 || ordinalIndex>maxOrdinalIndex) {
            throw new ArrayIndexOutOfBoundsException("OrdinalIndex must be within "+ 0 +" and "+maxOrdinalIndex+". "+ordinalIndex+" is invalid");
        }
    }
}
