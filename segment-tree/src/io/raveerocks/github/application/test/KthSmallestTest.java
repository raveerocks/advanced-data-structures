package io.raveerocks.github.application.test;

import io.raveerocks.github.application.KthSmallest;
import io.raveerocks.github.application.PowerOfThree;

public class KthSmallestTest {
    public static void main(String[] args) {
        int A[] = {1, 3, 5, 7, 6, 4, 2, 0, 1, 7, 8};
        int B[][] = new int[1][3];
        KthSmallest kthSmallest = new KthSmallest();
        for (int start = 1; start <= A.length; start++) {
            for (int end = start; end <= A.length; end++) {
                int range = end - start + 1;
                for (int k = 1; k <= range; k++) {
                    B[0][0] = start;
                    B[0][1] = end;
                    B[0][2] = k;
                    int[] results = kthSmallest.solve(A, B);
                    System.out.println("From "+start+" to "+end+" at "+k+":"+results[0]);
                }
            }
            System.out.println("***************");
        }
    }
}
