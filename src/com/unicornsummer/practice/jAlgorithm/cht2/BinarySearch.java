package com.unicornsummer.practice.jAlgorithm.cht2;

public final class BinarySearch {
    
    public static void main(String[] args) {
        try {
            int[] array = {-1, 2, 3, 10, 22, 34, 36};
            System.out.println(search(array, -1) + "; expected=" + 0);
            System.out.println(search(array, 2) + "; expected=" + 1);
            System.out.println(search(array, 3) + "; expected=" + 2);
            System.out.println(search(array, 34) + "; expected=" + 5);
            System.out.println(search(array, 22) + "; expected=" + 4);
            System.out.println(search(array, 10) + "; expected=" + 3);
            System.out.println(search(array, 1) + "; expected=" + -1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private static int search(int[] array, int x) {
        int low = 0;
        int high = array.length - 1;
        int mid = (high + low) / 2;  //犯过的错误：int mid = (high - low) / 2
        
        while (low <= high) { //犯过的错误：low < high
            if (x == array[mid]) {
              return mid;  
            } else if (x > array[mid]) {
                low = mid + 1;
            } else if (x < array[mid]) {
                high = mid -1;Lis
            }
            mid = (high + low) / 2; //犯过的错误：int mid = (high - low) / 2
        }
        return -1;
    }

}
