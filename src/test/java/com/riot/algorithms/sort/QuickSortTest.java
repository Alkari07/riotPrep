package com.riot.algorithms.sort;

import com.riot.algorithms.sorts.QuickSort;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Random;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class QuickSortTest {
    private Integer[] numbers;
    private final static int SIZE = 15;
    private final static int MAX = 20;

    @Before
    public void setUp() throws Exception {
        numbers = new Integer[SIZE];
        Random generator = new Random();
        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = generator.nextInt(MAX);
        }
    }

    @Test
    public void testQuickSort() {
        long startTime = System.currentTimeMillis();
        Integer[] manualTest = {7,2,1,8,6,3,5,4};
        System.out.println("Attempting Quicksort ");
        for (Integer element : numbers) {
            System.out.print(element + " ");
        }

        Integer[] results = QuickSort.quickSort(numbers, 0, numbers.length);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Quicksort " + elapsedTime);

        for (int i = 0; i < numbers.length - 1; i++) {
            System.out.println("INDEX " + i + ": " + results[i]);
            if (results[i] > results[i+1]) {
                fail("Should not happen");
            }
        }
        assertTrue(true);

    }

    @Test
    public void itWorksRepeatably() {
        for (int i = 0; i < 200; i++) {
            numbers = new Integer[SIZE];
            Random generator = new Random();
            for (int a = 0; a < numbers.length; a++) {
                numbers[a] = generator.nextInt(MAX);
            }
            Integer[] results = QuickSort.quickSort(numbers, 0, numbers.length);
            for (int j = 0; j < numbers.length - 1; j++) {
                System.out.println("INDEX " + i + ": " + results[j]);
                if (results[j] > results[j+1]) {
                    fail("Should not happen");
                }
            }
            assertTrue(true);
        }
    }

    @Test
    public void testStandardSort() {
        long startTime = System.currentTimeMillis();
        Arrays.sort(numbers);
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println("Standard Java sort " + elapsedTime);

        for (int i = 0; i < numbers.length - 1; i++) {
            if (numbers[i] > numbers[i + 1]) {
                fail("Should not happen");
            }
        }
        assertTrue(true);
    }
}
