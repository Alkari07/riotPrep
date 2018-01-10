package com.riot.algorithms.sorts;

public class QuickSort {

    public static Integer[] quickSort(Integer [] input, int startIndex, int endIndex) {
        if (startIndex>=endIndex) {
            return input;
        }
        int i=startIndex-1;
        int j=startIndex;
        int pivot = input[endIndex-1];
        //partition
        while(j<endIndex-1) {
            if (input[j]<pivot) {
                i++;
                int temp = input[j];
                input[j]=input[i];
                input[i]=temp;
            }
            j++;
        }
        //swap pivot to the i position (if not already in the correct position
        //shift everything over - the position of the pivot is the i+1
        for (int backCount=endIndex-1; backCount>i+1; backCount--) {
            input[backCount]=input[backCount-1];
        }
        input[i+1]=pivot;
        System.out.println("\nCurrent State after moving pivot " + pivot);
        for (Integer element : input) {
            System.out.print(element + " ");
        }
        //the pivot is now in the correct position, partition left and right
        //don't do left if i never moved
        if (i!=startIndex-1) {
            quickSort(input, startIndex, i+1);
        }
        //don't do right if the pivot didn't move
        if (i!=endIndex-1) {
            quickSort(input, i+2, endIndex);
        }
        return input;
    }

}
