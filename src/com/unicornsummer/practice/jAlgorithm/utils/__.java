package com.unicornsummer.practice.jAlgorithm.utils;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
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
    
    public static Method findDeclaredMethodByName(Object obj, String methodName) {
        return findDeclaredMethodByName(obj.getClass(), methodName);
    }
    
    public static Method findDeclaredMethodByName(Class<?> clazz, String methodName) {
        int methodCounter = 0;
        Method target = null;
        Method[] methods = clazz.getDeclaredMethods();
        
        if (methods != null && methods.length > 0) {
            for (Method m : methods) {
                if (m.getName().equals(methodName)) {
                    if (methodCounter >= 1) {
                        throw new IllegalArgumentException("Too many method in the same name");
                    }
                    target = m;
                    ++methodCounter;
                }
            }
        }
        if (target == null) {
            throw new IllegalArgumentException("No such method");
        }
        
        return target;
    }
    
    public static void perfTest(int loop, Class<?> clazz, String methodName, Object... params) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  
    {
        Method m = findDeclaredMethodByName(clazz, methodName);
        for (int i = 0; i < loop; i++) {
            System.out.format("========================%s=========================", loop).println();
            long startTime = System.currentTimeMillis();
            m.invoke(null, params);
            long endTime = System.currentTimeMillis();
            System.out.format(">>> time:[%d ms]", endTime - startTime).println();
        }
    }
    
    public static void perfTest(int loop, Object obj, String methodName, Object... params) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  
    {
        Method m = findDeclaredMethodByName(obj, methodName);
        Object actualObj =  Modifier.isStatic(m.getModifiers()) ? null : obj;
        
        for (int i = 0; i < loop; i++) {
            System.out.format("========================%s=========================", loop).println();
            long startTime = System.currentTimeMillis();
            m.invoke(actualObj, params);
            long endTime = System.currentTimeMillis();
            System.out.format(">>> time:[%d ms]", endTime - startTime).println();
        }
    }
    
    public static void perfTest(int loop, DelegateInterface delegate, Object... params) {
        for (int i = 0; i < loop; i++) {
            System.out.format("========================%s=========================", loop).println();
            long startTime = System.currentTimeMillis();
            delegate.execute(params);
            long endTime = System.currentTimeMillis();
            System.out.format(">>> time:[%d ms]", endTime - startTime).println();
        }
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
            System.out.format("========================%s=========================", loop).println();
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
            System.out.format(">>> Len[%d], Total[%d ms]", length, diff).println();
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
