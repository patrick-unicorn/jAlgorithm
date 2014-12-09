package com.unicornsummer.practice.jAlgorithm.cht2;

import java.util.ArrayList;
import java.util.List;

import com.unicornsummer.practice.jAlgorithm.utils.__;

@SuppressWarnings("all")
public final class SubSequence {
    
    public static void main(String[] args) {
        try {
            Integer[] testArr0 = __.wrapReferencArray(__.randomGenerateArray(20, 100, true));
            __.perfTest(SubSequence.class, "computeMaxSubSequence0", testArr0);
            
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Integer[] computeMaxSubSequence0(Integer[] sequence) {
        List<Integer> results = new ArrayList<Integer>();
        return results.toArray(new Integer[] {});
    }

}
