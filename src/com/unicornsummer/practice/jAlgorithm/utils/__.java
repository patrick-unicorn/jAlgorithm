package com.unicornsummer.practice.jAlgorithm.utils;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Arrays;

/**
 * 
 * @author unicorn(haiyin-ma) 2014-04-11
 */
public final class __ {

    public static int[] RanndomGenerateArray() 
            throws NoSuchAlgorithmException 
    {
        return RanndomGenerateArray(Integer.MAX_VALUE);
    }
    
    public static int[] RanndomGenerateArray(int expectedLength) 
            throws NoSuchAlgorithmException 
    {
        return RanndomGenerateArray(expectedLength, Integer.MAX_VALUE);
    }
    
    public static int[] RanndomGenerateArray(int expectedLength, int max) 
            throws NoSuchAlgorithmException 
    {
        int[] arrays = new int[expectedLength];
        SecureRandom sr = SecureRandom.getInstanceStrong();
        for (int i = 0; i < expectedLength; i++) {
            arrays[i] = sr.nextInt(max);
        }
        return arrays;
    }
    
    public static void perfTest(int baseLength, int step, int loop, Sorter sorter) 
            throws NoSuchAlgorithmException 
    {
        perfTest(baseLength, step, loop, Integer.MAX_VALUE, sorter, true);
    }
    
    public static void perfTest(int baseLength, int step, int loop, Sorter sorter, boolean isPrintArray) 
            throws NoSuchAlgorithmException 
    {
        perfTest(baseLength, step, loop, Integer.MAX_VALUE, sorter, isPrintArray);
    }
    
    public static void perfTest(int baseLength, int step, int loop, int maxVal, Sorter sorter, boolean isPrintArray) 
            throws NoSuchAlgorithmException 
    {
        for (int i = 0; i < loop; i++) {
            System.out.println("==========================================================");
            int length = baseLength + i * step;
            int[] arrays = RanndomGenerateArray(length, maxVal);
            
            if (isPrintArray) {
                System.out.println("TARGET: " + Arrays.toString(arrays));
            }
            long before = System.currentTimeMillis();
            int[] result = sorter.sort(arrays);
            long after = System.currentTimeMillis();
            long diff = after - before;
            
            if (isPrintArray) {
                System.out.println("RESULT: " + Arrays.toString(result));
            }
            System.out.println(String.format(">>> Len[%s], Total[%d ms]", length, diff));
        }
    }
    
    public static void swapArrayElement(int[] array, int index0, int index1) {
        if (array == null || index0 > (array.length - 1) || index1 > (array.length - 1)) {
            throw new IllegalArgumentException();
        }
        if (index0 == index1) {
            return;
        }
        int temp = array[index0];
        array[index0] = array[index1];
        array[index1] = temp;
    }
    
}
