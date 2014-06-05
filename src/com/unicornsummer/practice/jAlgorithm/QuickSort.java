package com.unicornsummer.practice.jAlgorithm;

import java.util.Arrays;

import com.unicornsummer.practice.jAlgorithm.utils.__;

public final class QuickSort {

    public static void main(String[] args) {
        try {
            __.perfTest(10, 5, 1, 10, QuickSort::sort, true);
//            __.perfTest(10000, 10000, 10, 1000, QuickSort::sort, false);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }
    
    private static int[] sort(int[] array) {
        sort(array, 0, array.length - 1);
        return array;
    }
    
    private static void sort(int[] array, int leftIndex, int rightIndex) {
        // break point
        if (leftIndex < rightIndex) {
            int privotIndex = (int) (leftIndex + rightIndex / 2);
            int cutPointIndex = partition(array, leftIndex, rightIndex, privotIndex);
            System.out.println("LI:" + leftIndex + " RI:" + rightIndex + " PI: " + privotIndex  + " CI: " + cutPointIndex + " SUB:" + Arrays.toString(array));
            sort(array, leftIndex, cutPointIndex - 1);
            sort(array, cutPointIndex + 1, rightIndex);
        }
    }
    
    private static int partition(int[] array, int leftIndex, int rightIndex, int privotIndex) {
        int privotVal = array[privotIndex];
        int stroeIndex = leftIndex;
        __.swapArrayElement(array, rightIndex, privotIndex);
        for (int i = leftIndex; i < rightIndex; i++) {
            if (array[i] <= privotVal) {
                __.swapArrayElement(array, i, stroeIndex);
                stroeIndex += 1;
            }
        }
        __.swapArrayElement(array, stroeIndex, array.length - 1);
        return stroeIndex;
    }
    
}
