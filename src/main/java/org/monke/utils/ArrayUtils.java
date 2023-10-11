package org.monke.utils;

import java.util.Arrays;

public class ArrayUtils {

    /**
     * Generates a list of all permutations in an array of Integers, using the Heap algorithm for efficiency.
     */
    public static void heapPermutationsRecursive(int size, Integer[] input) {
        // Exit condition
        if(size == 1) {
            System.out.println(Arrays.toString(input));
        } else {
            // Generate permutations with k-th unaltered
            // Initially k = input.length()
            heapPermutationsRecursive(size - 1, input);

            for(int i = 0; i < size-1; i++) {
                if(size % 2 != 0) {
                    int temp1 = input[i];
                    input[i] = input[size - 1];
                    input[size - 1] = temp1;
                } else {
                    int temp2 = input[0];
                    input[0] = input[size - 1];
                    input[size - 1] = temp2;
                }
            }
            heapPermutationsRecursive(size - 1, input);
        }
    }

    /** Swap 2 integers without a temp variable. */
    public static Integer[] swapInt(int i, int j) {
        i = i + j;
        j = i - j; // j = i +j - j = i
        i = i - j; //i = i + j - (j=i) = j
        return new Integer[]{i,j};
    }
}
