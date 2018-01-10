package com.riot.dataStructures.queue;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Scanner;

public class Heap {

    //Impliments a priority queue for basic minimum heap operations
    public void minQueue(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numCommands = scanner.nextInt();
        Queue<Integer> heap = new PriorityQueue<Integer>();

        for (int i=0; i<numCommands; i++){
            Integer command = scanner.nextInt();
            Integer val =0;
            if (command!=3) {
                val = scanner.nextInt();
            }

            if (command==1) {
                heap.add(val);
            } else if (command==2) {
                //System.out.println("Removing : " + val);
                heap.remove(val);
            } else{
                System.out.println(heap.peek());
            }
        }
    }

    //Impliments a priority queue for basic maximum heap operations
    public void maxQueue(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Integer numCommands = scanner.nextInt();
        Queue<Integer> heap = new PriorityQueue<Integer>(Collections.reverseOrder());

        for (int i=0; i<numCommands; i++){
            Integer command = scanner.nextInt();
            Integer val =0;
            if (command!=3) {
                val = scanner.nextInt();
            }

            if (command==1) {
                heap.add(val);
            } else if (command==2) {
                //System.out.println("Removing : " + val);
                heap.remove(val);
            } else{
                System.out.println(heap.peek());
            }
        }
    }

    //Cookie construction queue
    public void cookies(String[] args) {
        //illegal inputs expected: none
        Scanner scanner = new Scanner(System.in);
        //get the number of cookies
        Integer numCookies = scanner.nextInt();
        if (numCookies==0) {
            return;
        }
        //get the sweetness
        Integer sweetTarget = scanner.nextInt();
        Queue<Integer> cookies = new PriorityQueue<>();
        //loop through all cookies and put them in an priority queue (heap)
        for (int i=0; i<numCookies; i++) {
            cookies.add(scanner.nextInt());
        }

        //set count to zero
        Integer count=0;
        //get first cookie
        Integer currCookie = cookies.poll();
        //while this cookie to inspect is less than the required sweetness
        while (currCookie<sweetTarget) {
            //while this cookie is not yet the required sweetness
            //System.out.println("Cookie " + currCookie + " not sweet enough");
            if (cookies.isEmpty()) {
                //do we have another cookie?  if not, set count = -1, output, return
                System.out.println("-1");
                return;
            } else {
                //if we do, perform operation and increment operation counter
                cookies.add(currCookie+2*cookies.poll());
                count++;
                currCookie=cookies.poll();
            }
        }
        //output number of operations
        System.out.println(count);
    }

    //using a priority queue (heap) to sort cupcake calories

    public void cupcakes(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int[] calories = new int[n];
        Queue<Integer> cupcakes = new PriorityQueue<>(Collections.reverseOrder());
        for(int calories_i=0; calories_i < n; calories_i++){
            cupcakes.add(in.nextInt());
        }
        // your code goes here
        Long miles=0L;
        for (int j=0; j<n; j++) {
            miles+=((Double)(Math.pow(2, j)*cupcakes.poll())).longValue();
        }
        System.out.print(miles);
        //bad inputs?
        //edge cases?
        //1 calorie, 1000 calories, 1 eaten, 40 eaten.
    }
}
