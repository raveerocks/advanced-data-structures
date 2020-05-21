package io.raveerocks.github.application;


import io.raveerocks.github.operations.binary.math.integers.ints.IntegerBinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.ArraySingleSegmentTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DistinctNumbers {
    public int[] solve(int[] A, int[][] B) {

        HashMap<Integer, ArrayList<String>> queryEndingWithMap = new HashMap<>();
        for (int i = 0; i < B.length; i++) {
            ArrayList list = queryEndingWithMap.getOrDefault(B[i][1], new ArrayList<>());
            list.add(B[i][0] + "," + i);
            queryEndingWithMap.put(B[i][1], list);
        }

        Integer[] elements = new Integer[A.length + 1];
        Arrays.fill(elements, Integer.valueOf(0));
        SegmentTree<Integer, Integer> segmentTree = new ArraySingleSegmentTree<>(elements, IntegerBinaryOperatorBuilder.getIntegerSum());
        int[] results = new int[B.length];
        HashMap<Integer, Integer> lastIndex = new HashMap<>();

        for (int end = 1; end <= A.length; end++) {

            if (lastIndex.containsKey(A[end - 1])) {
                int previous = lastIndex.get(A[end - 1]);
                segmentTree.setElement(previous, 0);
            }

            segmentTree.setElement(end, 1);

            if (queryEndingWithMap.containsKey(end)) {
                for (String query : queryEndingWithMap.get(end)) {
                    String[] queryParameters = query.split("[,]");
                    int start = Integer.valueOf(queryParameters[0]);
                    int index = Integer.valueOf(queryParameters[1]);
                    results[index] = segmentTree.query(start, end);
                }
            }
            lastIndex.put(A[end - 1], end);
        }

        return results;
    }
}
