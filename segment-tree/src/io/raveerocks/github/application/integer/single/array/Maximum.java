package io.raveerocks.github.application.integer.single.array;

import io.raveerocks.github.operations.binary.BinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.ArraySingleSegmentTree;

public class Maximum {

    public static void main(String[] args) {
        Integer[] numbers = {2,4,8,10,15,20,35,7,28,8,22,87,1};
        SegmentTree<Integer,Integer> segmentTree = new ArraySingleSegmentTree<>(numbers, BinaryOperatorBuilder.getIntegerMaximum());
        printTree(segmentTree,numbers.length);
    }

    private static void printTree(SegmentTree<Integer,Integer> segmentTree, int length){

        for (int i=0; i<length; i++) {
            for (int j = i; j < length; j++) {
                int result = segmentTree.query(i,j);
                System.out.println("From "+i+" To "+j+" : "+result);
            }
        }
    }
}
