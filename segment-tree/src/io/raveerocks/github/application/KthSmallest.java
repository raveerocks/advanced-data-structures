package io.raveerocks.github.application;

import io.raveerocks.github.trees.array.IndexSegmentTreeUtil;


public class KthSmallest {

    public int[] solve(int[] A, int[][] B) {
        Integer elements[]  = new Integer[A.length];
        for (int i=0; i<A.length; i++){
            elements[i]= Integer.valueOf(A[i]);
        }
        IndexSegmentTreeUtil<Integer> integerIndexSegmentTree = new IndexSegmentTreeUtil<>(elements);
        int[] results = new int[B.length];

        for (int i=0; i<B.length; i++){
            results[i] = integerIndexSegmentTree.atOrdinalIndex(B[i][0]-1,B[i][1]-1,B[i][2]);
        }
        return results;
    }


}
