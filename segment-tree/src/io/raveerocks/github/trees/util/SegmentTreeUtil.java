package io.raveerocks.github.trees.util;

import io.raveerocks.github.trees.SegmentTree;

public class SegmentTreeUtil {

    public static int findKthIndex(SegmentTree<Integer[],Integer[]> tree, int k, int size){
        Integer[] root = tree.query(0,size-1);
        return 0;
    }
}
