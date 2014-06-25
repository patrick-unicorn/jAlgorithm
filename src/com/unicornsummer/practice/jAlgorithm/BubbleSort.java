package com.unicornsummer.practice.jAlgorithm;

import com.unicornsummer.practice.jAlgorithm.utils.Sorter;
import com.unicornsummer.practice.jAlgorithm.utils.__;

public final class BubbleSort {

    public static void main(String[] args) {
        try {
            __.perfTest(10, 5, 5, 100, new BubbleSortImpl_0()::sort, true);
            __.perfTest(10000, 10000, 10, 1000, new BubbleSortImpl_0()::sort, false);
            
//            System.out.println("*************");
//            
//            __.perfTest(10, 5, 5, 100, new BubbleSortImpl_1()::sort, true);
//            __.perfTest(10000, 10000, 10, 1000, new BubbleSortImpl_1()::sort, false);
        } catch(Throwable t) {
            t.printStackTrace();
        }
    }
    
    static class BubbleSortImpl_0 implements Sorter {
        public int[] sort(int[] arrays) {
            int tempVal = 0;
            for (int i = 0; i < arrays.length; i++) {
                for (int j = i + 1; j < arrays.length; j++) {
                    if (arrays[j] < arrays[i]) {
                        tempVal = arrays[i];
                        arrays[i] = arrays[j];
                        arrays[j] = tempVal;
                    }
                }
            }
            return arrays;
        }
    }

    static class BubbleSortImpl_1 implements Sorter {
        public int[] sort(int[] arrays) {
            int tempVal = 0;
            
            for (int i = 0; i < arrays.length; i++) {
                boolean isExchange = false;
                for (int j = i + 1; j < arrays.length; j++) {
                    if (arrays[j] < arrays[i]) {
                        tempVal = arrays[i];
                        arrays[i] = arrays[j];
                        arrays[j] = tempVal;
                        isExchange = true;
                    }
                }
                if (!isExchange) {
                    break;
                } 
            }
            return arrays;
        }
    }
}
