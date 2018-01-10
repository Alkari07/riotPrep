package com.riot.algorithms.sorts;

import java.util.ArrayList;
import java.util.List;

public class MergeSort {

    public static List<Integer> mergeSort(int[] inputs) {
        List<Integer> listInputs = new ArrayList<Integer>();
        for (int i=0; i<inputs.length; i++) {
            listInputs.add(inputs[i]);
        }
        return mergeSort(listInputs);
    }
    public static List<Integer> mergeSort (List<Integer> inputs) {
        //edge case
        if (inputs.isEmpty() || inputs.size()==1) {
            return inputs;
        }

        //determine the middle
        Integer middle = inputs.size()/2;
        //mergeSort the left
        List<Integer> mergedLeft = mergeSort(inputs.subList(0, middle));
        //mergeSort the right
        List<Integer> mergedRight = mergeSort(inputs.subList(middle, inputs.size()));
        //merge the two
        return merge(mergedLeft, mergedRight);
    }

    private static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> mergedResult = new ArrayList<Integer>();
        Integer rightIndex =0;
        Integer leftIndex=0;
        while (leftIndex<left.size() && rightIndex<right.size()){
            if (left.get(leftIndex) <= right.get(rightIndex)) {
                mergedResult.add(left.get(leftIndex));
                leftIndex++;
            } else {
                mergedResult.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        //add any remaining elements in either list
        for (int i=leftIndex; i<left.size(); i++) {
            mergedResult.add(left.get(i));
        }
        for (int i=rightIndex; i<right.size(); i++) {
            mergedResult.add(right.get(i));
        }
        return mergedResult;
    }
}
