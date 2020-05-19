package io.raveerocks.github.application.integer.collective;

import io.raveerocks.github.operations.binary.BinaryOperator;
import io.raveerocks.github.operations.binary.BinaryOperatorBuilder;
import io.raveerocks.github.trees.SegmentTree;
import io.raveerocks.github.trees.array.CollectiveArraySegmentTree;

public class Demo {
    public static void main(String[] args) {
        Integer[] numbers = {2,4,8,10,15,20,35,7,28,8,22,87,1};

        SegmentTree<Integer,Object[]> segmentTree = new CollectiveArraySegmentTree<>(numbers,
                BinaryOperatorBuilder.getIntegerAddition(),
                BinaryOperatorBuilder.getGCD(),
                BinaryOperatorBuilder.getIntegerMaximum(),
                BinaryOperatorBuilder.getIntegerMinimum(),
                BinaryOperatorBuilder.getBinarySetBitCount());


        System.out.println( "_____________________________________");
        System.out.printf("|From|To|Sum|GCD|Max|Min|SetBitCount|\n");

        for (int i=0; i<numbers.length; i++){
            for (int j=i; j<numbers.length; j++){

                Object[] result = segmentTree.query(i,j);


                System.out.println("|----|--|---|---|---|---|-----------|");
                System.out.printf("|%4d|%2d|%3d|%3d|%3d|%3d|%11d|\n",i,j,result[0],result[1],result[2],result[3],result[4]);
            }
        }


        System.out.println("`````````````````````````````````````");
    }
}

/*  SAMPLE OUTPUT
_____________________________________
|From|To|Sum|GCD|Max|Min|SetBitCount|
|----|--|---|---|---|---|-----------|
|   0| 0|  2|  2|  2|  0|          1|
|----|--|---|---|---|---|-----------|
|   0| 1|  6|  2|  4|  0|          2|
|----|--|---|---|---|---|-----------|
|   0| 2| 14|  2|  8|  0|          3|
|----|--|---|---|---|---|-----------|
|   0| 3| 24|  2| 10|  0|          5|
|----|--|---|---|---|---|-----------|
|   0| 4| 39|  1| 15|  0|          9|
|----|--|---|---|---|---|-----------|
|   0| 5| 59|  1| 20|  0|         11|
|----|--|---|---|---|---|-----------|
|   0| 6| 94|  1| 35|  0|         14|
|----|--|---|---|---|---|-----------|
|   0| 7|101|  1| 35|  0|         17|
|----|--|---|---|---|---|-----------|
|   0| 8|129|  1| 35|  0|         20|
|----|--|---|---|---|---|-----------|
|   0| 9|137|  1| 35|  0|         21|
|----|--|---|---|---|---|-----------|
|   0|10|159|  1| 35|  0|         24|
|----|--|---|---|---|---|-----------|
|   0|11|246|  1| 87|  0|         29|
|----|--|---|---|---|---|-----------|
|   0|12|247|  1| 87|  1|         30|
|----|--|---|---|---|---|-----------|
|   1| 1|  4|  4|  4|  0|          1|
|----|--|---|---|---|---|-----------|
|   1| 2| 12|  4|  8|  0|          2|
|----|--|---|---|---|---|-----------|
|   1| 3| 22|  2| 10|  0|          4|
|----|--|---|---|---|---|-----------|
|   1| 4| 37|  1| 15|  0|          8|
|----|--|---|---|---|---|-----------|
|   1| 5| 57|  1| 20|  0|         10|
|----|--|---|---|---|---|-----------|
|   1| 6| 92|  1| 35|  0|         13|
|----|--|---|---|---|---|-----------|
|   1| 7| 99|  1| 35|  0|         16|
|----|--|---|---|---|---|-----------|
|   1| 8|127|  1| 35|  0|         19|
|----|--|---|---|---|---|-----------|
|   1| 9|135|  1| 35|  0|         20|
|----|--|---|---|---|---|-----------|
|   1|10|157|  1| 35|  0|         23|
|----|--|---|---|---|---|-----------|
|   1|11|244|  1| 87|  0|         28|
|----|--|---|---|---|---|-----------|
|   1|12|245|  1| 87|  0|         29|
|----|--|---|---|---|---|-----------|
|   2| 2|  8|  8|  8|  0|          1|
|----|--|---|---|---|---|-----------|
|   2| 3| 18|  2| 10|  0|          3|
|----|--|---|---|---|---|-----------|
|   2| 4| 33|  1| 15|  0|          7|
|----|--|---|---|---|---|-----------|
|   2| 5| 53|  1| 20|  0|          9|
|----|--|---|---|---|---|-----------|
|   2| 6| 88|  1| 35|  0|         12|
|----|--|---|---|---|---|-----------|
|   2| 7| 95|  1| 35|  0|         15|
|----|--|---|---|---|---|-----------|
|   2| 8|123|  1| 35|  0|         18|
|----|--|---|---|---|---|-----------|
|   2| 9|131|  1| 35|  0|         19|
|----|--|---|---|---|---|-----------|
|   2|10|153|  1| 35|  0|         22|
|----|--|---|---|---|---|-----------|
|   2|11|240|  1| 87|  0|         27|
|----|--|---|---|---|---|-----------|
|   2|12|241|  1| 87|  0|         28|
|----|--|---|---|---|---|-----------|
|   3| 3| 10| 10| 10|  0|          2|
|----|--|---|---|---|---|-----------|
|   3| 4| 25|  5| 15|  0|          6|
|----|--|---|---|---|---|-----------|
|   3| 5| 45|  5| 20|  0|          8|
|----|--|---|---|---|---|-----------|
|   3| 6| 80|  5| 35|  0|         11|
|----|--|---|---|---|---|-----------|
|   3| 7| 87|  1| 35|  0|         14|
|----|--|---|---|---|---|-----------|
|   3| 8|115|  1| 35|  0|         17|
|----|--|---|---|---|---|-----------|
|   3| 9|123|  1| 35|  0|         18|
|----|--|---|---|---|---|-----------|
|   3|10|145|  1| 35|  0|         21|
|----|--|---|---|---|---|-----------|
|   3|11|232|  1| 87|  0|         26|
|----|--|---|---|---|---|-----------|
|   3|12|233|  1| 87|  0|         27|
|----|--|---|---|---|---|-----------|
|   4| 4| 15| 15| 15|  0|          4|
|----|--|---|---|---|---|-----------|
|   4| 5| 35|  5| 20|  0|          6|
|----|--|---|---|---|---|-----------|
|   4| 6| 70|  5| 35|  0|          9|
|----|--|---|---|---|---|-----------|
|   4| 7| 77|  1| 35|  0|         12|
|----|--|---|---|---|---|-----------|
|   4| 8|105|  1| 35|  0|         15|
|----|--|---|---|---|---|-----------|
|   4| 9|113|  1| 35|  0|         16|
|----|--|---|---|---|---|-----------|
|   4|10|135|  1| 35|  0|         19|
|----|--|---|---|---|---|-----------|
|   4|11|222|  1| 87|  0|         24|
|----|--|---|---|---|---|-----------|
|   4|12|223|  1| 87|  0|         25|
|----|--|---|---|---|---|-----------|
|   5| 5| 20| 20| 20|  0|          2|
|----|--|---|---|---|---|-----------|
|   5| 6| 55|  5| 35|  0|          5|
|----|--|---|---|---|---|-----------|
|   5| 7| 62|  1| 35|  0|          8|
|----|--|---|---|---|---|-----------|
|   5| 8| 90|  1| 35|  0|         11|
|----|--|---|---|---|---|-----------|
|   5| 9| 98|  1| 35|  0|         12|
|----|--|---|---|---|---|-----------|
|   5|10|120|  1| 35|  0|         15|
|----|--|---|---|---|---|-----------|
|   5|11|207|  1| 87|  0|         20|
|----|--|---|---|---|---|-----------|
|   5|12|208|  1| 87|  0|         21|
|----|--|---|---|---|---|-----------|
|   6| 6| 35| 35| 35|  0|          3|
|----|--|---|---|---|---|-----------|
|   6| 7| 42|  7| 35|  0|          6|
|----|--|---|---|---|---|-----------|
|   6| 8| 70|  7| 35|  0|          9|
|----|--|---|---|---|---|-----------|
|   6| 9| 78|  1| 35|  0|         10|
|----|--|---|---|---|---|-----------|
|   6|10|100|  1| 35|  0|         13|
|----|--|---|---|---|---|-----------|
|   6|11|187|  1| 87|  0|         18|
|----|--|---|---|---|---|-----------|
|   6|12|188|  1| 87|  0|         19|
|----|--|---|---|---|---|-----------|
|   7| 7|  7|  7|  7|  0|          3|
|----|--|---|---|---|---|-----------|
|   7| 8| 35|  7| 28|  0|          6|
|----|--|---|---|---|---|-----------|
|   7| 9| 43|  1| 28|  0|          7|
|----|--|---|---|---|---|-----------|
|   7|10| 65|  1| 28|  0|         10|
|----|--|---|---|---|---|-----------|
|   7|11|152|  1| 87|  0|         15|
|----|--|---|---|---|---|-----------|
|   7|12|153|  1| 87|  0|         16|
|----|--|---|---|---|---|-----------|
|   8| 8| 28| 28| 28|  0|          3|
|----|--|---|---|---|---|-----------|
|   8| 9| 36|  4| 28|  0|          4|
|----|--|---|---|---|---|-----------|
|   8|10| 58|  2| 28|  0|          7|
|----|--|---|---|---|---|-----------|
|   8|11|145|  1| 87|  0|         12|
|----|--|---|---|---|---|-----------|
|   8|12|146|  1| 87|  0|         13|
|----|--|---|---|---|---|-----------|
|   9| 9|  8|  8|  8|  0|          1|
|----|--|---|---|---|---|-----------|
|   9|10| 30|  2| 22|  0|          4|
|----|--|---|---|---|---|-----------|
|   9|11|117|  1| 87|  0|          9|
|----|--|---|---|---|---|-----------|
|   9|12|118|  1| 87|  0|         10|
|----|--|---|---|---|---|-----------|
|  10|10| 22| 22| 22|  0|          3|
|----|--|---|---|---|---|-----------|
|  10|11|109|  1| 87|  0|          8|
|----|--|---|---|---|---|-----------|
|  10|12|110|  1| 87|  0|          9|
|----|--|---|---|---|---|-----------|
|  11|11| 87| 87| 87|  0|          5|
|----|--|---|---|---|---|-----------|
|  11|12| 88|  1| 87|  0|          6|
|----|--|---|---|---|---|-----------|
|  12|12|  1|  1|  1|  0|          1|
`````````````````````````````````````
 */