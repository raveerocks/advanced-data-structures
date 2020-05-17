package io.raveerocks.github.application;

import io.raveerocks.github.operations.binary.BinaryOperations;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.SingleArraySegmentTree;

import java.util.ArrayList;

public class BobAndQueries {
    public ArrayList<Integer> solve(int A, int[][] B) {
        Integer[] elements = new Integer[A];
        for (int i=0; i<elements.length;i++){
            elements[i]=0;
        }
        ArrayList<Integer> results = new ArrayList<>();

        SegmentTree<Integer, Integer> segmentTree = new SingleArraySegmentTree<>(elements, BinaryOperations.getBinarySetBitCount());
        for (int i = 0; i < B.length; i++) {
            int x = B[i][0], y = B[i][1]-1, z = B[i][2]-1;
            if (y >= 0 && y < elements.length) {
                switch (x) {
                    case 1:
                        Integer value = segmentTree.getElement(y);
                        value = value == null ? 0 : value;
                        segmentTree.setElement(y, (value << 1) | 1);
                        break;
                    case 2:
                        value = segmentTree.getElement(y);
                        value = value == null ? 0 : value;
                        segmentTree.setElement(y, value >> 1);
                        break;
                    case 3:
                        int result = segmentTree.query(y, z);
                        results.add(result);
                        break;
                }
            }
        }

        return results;
    }

    public static void main(String[] args) {
        BobAndQueries bobAndQueries = new BobAndQueries();
        int [][] B = {{1, 1, -1}, {1, 2, -1}, {1, 3, -1}, {3, 1, 3}, {3, 2, 4}};
        ArrayList<Integer> results = bobAndQueries.solve(5,B);
        System.out.println(results);
    }
}
