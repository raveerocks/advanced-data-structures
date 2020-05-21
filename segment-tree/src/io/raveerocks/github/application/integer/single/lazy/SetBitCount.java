package io.raveerocks.github.application.integer.single.lazy;

import io.raveerocks.github.operations.binary.math.bit.BitBinaryOperatorBuilder;
import io.raveerocks.github.operations.binary.math.integers.ints.IntegerBinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.ArraySingleSegmentTree;
import io.raveerocks.github.trees.lazy.LazySingleSegmentTree;

public class SetBitCount {

    public static void main(String[] args) {
        Integer[] numbers = {2,4,8,10,15,20,35,7,28,8,22,87,1};
        SegmentTree<Integer,Integer> segmentTree = new LazySingleSegmentTree(numbers.length, BitBinaryOperatorBuilder.getSetBitCount());
        for (int i=0; i<numbers.length; i++) {
            System.out.println("*********** Inserted "+numbers[i]+" at "+i+" ***********");
            segmentTree.setElement(i,numbers[i]);
            printTree(segmentTree,numbers.length);
        }
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
