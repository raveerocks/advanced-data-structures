package io.raveerocks.github.application;

import io.raveerocks.github.operations.binary.BinaryOperatorBuilder;
import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.CollectiveArraySegmentTree;

public class Demo {
    public static void main(String[] args) {
        Integer[] numbers = {2,4,8,10,15,20,35,7,28,8,22,87,1};

        SegmentTree<Integer,Object[]> segmentTree = new CollectiveArraySegmentTree<>(numbers,
                BinaryOperatorBuilder.getIntegerAddition(),
                BinaryOperatorBuilder.getGCD(),
                BinaryOperatorBuilder.getIntegerMaximum(),
                BinaryOperatorBuilder.getIntegerMinimum());

        for (int i=0; i<numbers.length; i++){
            for (int j=i; j<numbers.length; j++){
                Object[] result = segmentTree.query(i,j);
                System.out.println("From "+i+" To "+j+"\tSum = "+result[0]+"\tGCD ="+result[1]+"\tMax = "+result[2]+"\tMin = "+result[3]);
            }
        }
    }
}
