package io.raveerocks.github.application;

import io.raveerocks.github.operations.binary.BinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.ArraySingleSegmentTree;

import java.util.ArrayList;

public class SpecialSums {
    public int[] solve(int[] A, int[][] B) {

        final int MODULUS = 1000000007;

        Long[] elements = new Long[A.length+1];
        Long[] product = new Long[A.length+1];

        for (int i =0; i<A.length; i++){
            elements[i+1]=(long)A[i];
            product[i+1] = ((long)(i+1)*(A[i]))%MODULUS;
        }

        SegmentTree<Long,Long> elementTree = new ArraySingleSegmentTree<>(elements, BinaryOperatorBuilder.getLongAddition());
        SegmentTree<Long,Long> productTree = new ArraySingleSegmentTree<>(product, BinaryOperatorBuilder.getLongAddition());

        ArrayList<Integer> results = new ArrayList<>();

        for (int i=0; i<B.length; i++){
            int type = B[i][0];
            int x =B[i][1],l=B[i][1];
            long val =B[i][2];
            int r=B[i][2];

            switch (type){
                case 1:
                    elementTree.setElement(x,val);
                    productTree.setElement(x,((x)*(val)));
                    break;
                case 2: long elementSum = (elementTree.query(l,r));
                    long productSum = (productTree.query(l,r));
                    elementSum = (((l-1))*(elementSum));
                    long result = (productSum-elementSum+MODULUS)%MODULUS;
                    result = (result+MODULUS)%MODULUS;
                    results.add((int)result);
                    break;
            }
        }
        int []resultArray = new int[results.size()];
        for (int i=0; i<resultArray.length;i++){
            resultArray[i]=results.get(i);
        }

        return resultArray;

    }
}
