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
    
    /**
     * 当可变参数为单个非<code>Object[]</code>数组时，将其包装为一个<code>Object[]</code>数组
     * 
     * @param params 可变参数(必定是个数组)
     * @return
     */
    public static Object[] reduceVariadicArguments(Object... params) {
        Class<?> clazz = params.getClass().getComponentType();
        if (clazz.equals(Object.class)) {
            return params;
        }
        return new Object[] { params };
    }
    
    public static void print(String tagName, Object obj) {
        print(tagName, obj, new Object[]{});
    }
    
    /**
     * 打印对象
     * 
     * @param tagName
     * @param obj 如果该参数为数组类型，那么要求数组元素必须为引用类型才能正确转换
     * @param a 泛型参数T必须是引用类型，不能为<code>int</code>等基本值类型
     */
    @SuppressWarnings("unchecked")
    public static <T> void print(String tagName, Object obj, T[] a) {
        if (obj.getClass().isArray()) {
            System.out.println(tagName+ ": " + Arrays.toString((T[]) obj));
        } else {
            System.out.println(tagName+ ": " + obj);
        }
    }

    public static int[] randomGenerateArray() 
            throws NoSuchAlgorithmException 
    {
        return randomGenerateArray(Integer.MAX_VALUE);
    }
    
    public static int[] randomGenerateArray(int expectedLength) 
            throws NoSuchAlgorithmException 
    {
        return randomGenerateArray(expectedLength, Integer.MAX_VALUE);
    }
    
    public static int[] randomGenerateArray(int expectedLength, int max) 
            throws NoSuchAlgorithmException 
    {
        return randomGenerateArray(expectedLength, max, false);
    }
    
    public static int[] randomGenerateArray(int expectedLength, int max, boolean containNegative) 
            throws NoSuchAlgorithmException 
    {
        boolean isPositive = true;
        SecureRandom sr1 = SecureRandom.getInstanceStrong();
        
        int[] arrays = new int[expectedLength];
        SecureRandom sr0 = SecureRandom.getInstanceStrong();
        for (int i = 0; i < expectedLength; i++) {
            if (containNegative) {
                isPositive = sr1.nextBoolean();
            }
            arrays[i] = sr0.nextInt(max) * (isPositive ? 1 : -1);
        }
        return arrays;
    }
    
    public static Integer[] wrapReferencArray(int[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        Integer[] result = new Integer[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Long[] wrapReferencArray(long[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        Long[] result = new Long[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
    }
    
    public static Short[] wrapReferencArray(short[] array) {
        if (array == null) {
            throw new NullPointerException("array");
        }
        Short[] result = new Short[array.length];
        for (int i = 0; i < array.length; i++) {
            result[i] = array[i];
        }
        return result;
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
                        throw new IllegalArgumentException("Too many methods in the same name");
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
    
    public static void perfTest(Class<?> clazz, String methodName, Object... params) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  
    {
        perfTest(1, clazz, methodName, params);
    }
    
    public static void perfTest(int loop, Class<?> clazz, String methodName, Object... params) 
            throws IllegalAccessException, IllegalArgumentException, InvocationTargetException  
    {
        Method m = findDeclaredMethodByName(clazz, methodName);
        for (int i = 0; i < loop; i++) {
            System.out.format("========================%s=========================", loop).println();
            print("PARAM", params);

            long startTime = System.currentTimeMillis();
            Object result = m.invoke(null, reduceVariadicArguments(params));
            long endTime = System.currentTimeMillis();
            
            print("RESULT", result);
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
            print("PARAM", params);
            
            long startTime = System.currentTimeMillis();
            Object result = m.invoke(actualObj, params);
            long endTime = System.currentTimeMillis();
            
            print("RESULT", result);
            System.out.format(">>> time:[%d ms]", endTime - startTime).println();
        }
    }
    
    public static void perfTest(int loop, DelegateInterface delegate, Object... params) {
        for (int i = 0; i < loop; i++) {
            System.out.format("========================%s=========================", loop).println();
            print("PARAM", params);
            
            long startTime = System.currentTimeMillis();
            Object result = delegate.execute(params);
            long endTime = System.currentTimeMillis();
            
            print("RESULT", result);
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
            int[] arrays = randomGenerateArray(length, maxVal);
            
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
