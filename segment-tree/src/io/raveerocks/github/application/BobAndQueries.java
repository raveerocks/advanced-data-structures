package io.raveerocks.github.application;

import io.raveerocks.github.operations.binary.BinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.ArraySingleSegmentTree;
import io.raveerocks.github.trees.lazy.LazySingleSegmentTree;

import java.util.ArrayList;
import java.util.Arrays;

public class BobAndQueries {
    public ArrayList<Integer> solve(int A, int[][] B) {
        Integer[] elements = new Integer[A];
        Arrays.fill(elements,Integer.valueOf(0));
        ArrayList<Integer> results = new ArrayList<>();

  SegmentTree<Integer, Integer> segmentTree = new ArraySingleSegmentTree<>(elements, BinaryOperatorBuilder.getBinarySetBitCount());
    // SegmentTree<Integer, Integer> segmentTree = new LazySingleSegmentTree<>(elements.length, BinaryOperatorBuilder.getBinarySetBitCount());
        for (int i = 0; i < B.length; i++) {
            int x = B[i][0], y = B[i][1]-1, z = B[i][2]-1;
            if (y >= 0 && y < elements.length) {
                switch (x) {
                    case 1:
                        Integer value = segmentTree.getElement(y);
                        int result = segmentTree.query(y, y);
                        value <<= 1;
                        value |=1;
                        result+=1;
                        segmentTree.setElement(y, value,result);
                        break;
                    case 2:
                        value = segmentTree.getElement(y);
                        result = segmentTree.query(y, y);
                        value >>= 1;
                        result -=1;
                        if (result<0){
                            value=result=0;
                        }
                        segmentTree.setElement(y, value,result);
                        break;
                    case 3:
                        result = segmentTree.query(y, z);
                        results.add(result);
                        break;
                }
            }
        }

        return results;
    }
}
