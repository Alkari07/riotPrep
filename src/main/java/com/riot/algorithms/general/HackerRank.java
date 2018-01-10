package com.riot.algorithms.general;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class HackerRank {
    //Delta between sum of diagnols in nxn matrix
    public class SumDiagnols {

        public void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Integer n = scanner.nextInt();
            if (n==null || n==0) {
                //error, empty input
                return;
            }
            int[][] matrix = new int[n][n];

            //read in the matrix
            for (int i=0; i<n; i++) {
                for (int j=0; j<n; j++) {
                    matrix[i][j] = scanner.nextInt();
                }
            }

            //start from center and work your way out to the edges
        /*Integer leftSum = matrix[n/2][n/2];
        Integer rightSum = matrix[n/2][n/2];

        Integer widthOffset =1;
        Integer heightOffset=1;

        while ((n/2+widthOffset)<n && (n/2+heightOffset)<n) {
            rightSum+=matrix[n/2+widthOffset][n/2+heightOffset];
            rightSum+=matrix[(n/2-widthOffset)<0 ? 0 : n/2-widthOffset][(n/2-heightOffset)<0 ? 0 : n/2-heightOffset];
            leftSum+=matrix[(n/2-widthOffset)<0 ? 0 : n/2-widthOffset][n/2+heightOffset];
            leftSum+=matrix[n/2+widthOffset][(n/2-heightOffset)<0 ? 0 : n/2-heightOffset];
            widthOffset+=1;
            heightOffset+=1;
        }*/
            Integer leftSum =0;
            Integer rightSum=0;
            int i=0;
            int j=n;
            while (i<n) {
                leftSum+=matrix[i][n-j];
                rightSum+=matrix[i][j-1];
                i+=1;
                j-=1;
            }
            System.out.println(Math.abs(leftSum-rightSum));
        }
    }

    //Formatted strings /decimal precision

    public class FormattedDecimals {

        public  void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Integer inputCount = scanner.nextInt();
            if (inputCount==null || inputCount==0) {
                return;
            }
            Double negativeCount =0.0;
            Double zeroesCount =0.0;
            Double positiveCount =0.0;
            Double totalCount=0.0;
            while (scanner.hasNextInt()) {
                Integer token = scanner.nextInt();
                if (token <0.0) {
                    negativeCount+=1;
                } else if (token==0.0) {
                    zeroesCount+=1;
                } else if (token>0.0) {
                    positiveCount+=1;
                }
                totalCount+=1;
            }
            DecimalFormat formatter = new DecimalFormat("0.000000");
            Double negativeRatio =(Math.round(negativeCount/totalCount *1000000.0)/1000000.0);
            Double positiveRatio = (Math.round(positiveCount/totalCount *1000000.0)/1000000.0);
            Double zeroRatio = (Math.round(zeroesCount/totalCount *1000000.0)/1000000.0);
            System.out.print(formatter.format(positiveRatio) + "\n" + formatter.format(negativeRatio) +
                    "\n" + formatter.format(zeroRatio));
        }
    }

    //Staircase of size n
    public class Staircase {

        public void main(String[] args) {
            //get the staircase size
            Scanner scanner = new Scanner(System.in);
            Integer n = scanner.nextInt();
            if (n==null || n==0) {
                //error
                return;
            }
            //how many spaces?
            //spaces of first element is n-1

            //for every integer from n-1 to 0, print that many spaces followed by # up to n
            for (int i=n-1; i>=0; i--) {
                for (int j=0; j<i; j++) {
                    //print spaces
                    System.out.print(" ");
                }
                for (int j=i; j<n; j++) {
                    //print stair
                    System.out.print("#");
                }
                //newline
                System.out.print("\n");
            }
        }
    }

    //huge Integer addition
    //remember, big decimal is immutable!
    public class HugeIntegerAddition {

        public void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int[] arr = new int[5];
            for(int arr_i=0; arr_i < 5; arr_i++){
                arr[arr_i] = in.nextInt();
            }
            //sort the array
            Arrays.sort(arr);
            //once sorted, the min is always the first four elements and the max the last four

            //to be more efficient, grab the middle sum since those 3 numbers are always used
            BigDecimal middleSum = new BigDecimal(0);
            middleSum=middleSum.add(new BigDecimal(arr[1]));
            middleSum=middleSum.add(new BigDecimal(arr[2]));
            middleSum=middleSum.add(new BigDecimal(arr[3]));
            System.out.print(middleSum.add(new BigDecimal(arr[0])) + " " + middleSum.add(new BigDecimal(arr[4])));
        }
    }

    //candles on a cake
    //can only blow out the tallest candle(s)
    public class BlowOutTallestCandles {

        int birthdayCakeCandles(int n, int[] ar) {
            //check inputs
            if (n==0 || ar==null) {
                return 0;
            }

            //sort the array of candle heights

            //she can always blow out candle ar[n-1];
            //loop backwards through the remaining ones and break when a candle is found that is smaller
            //OR when all candles have been checked (all have been blown out)

            //alternative solution in O(n) - track the biggest found candle thus far and if one exceeds it,
            //reset the count to 1
            Integer blownOutCount =0;
            Integer maxHeight =0;
            for (int i=0; i<n; i++) {
                int currCandle = ar[i];
                if (currCandle>maxHeight) {
                    blownOutCount=1;
                    maxHeight=currCandle;
                } else if (currCandle==maxHeight) {
                    blownOutCount+=1;
                }
            }
            return blownOutCount;
        }

        public void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int n = in.nextInt();
            int[] ar = new int[n];
            for(int ar_i = 0; ar_i < n; ar_i++){
                ar[ar_i] = in.nextInt();
            }
            int result = birthdayCakeCandles(n, ar);
            System.out.println(result);
        }
    }

    //standard to mil clock conversion
    public class TimeConversion {

        String timeConversion(String s) {
            //check inputs
            if (s==null || s.equals("")) {
                return "";
            }
            //check if PM
            //parse tokens from substring before AM/PM
            String[] originalTime;
            Integer hour=0;
            if (s.contains("PM")) {
                originalTime = s.split("PM")[0].split(":");;
                hour = Integer.parseInt(originalTime[0]);
                if (hour!=12) {
                    hour+=12;
                }
            } else {
                originalTime = s.split("AM")[0].split(":");
                hour = Integer.parseInt(originalTime[0]);
                if (hour==12) {
                    hour=0;
                }
            }
            return (hour<10 ? "0" + hour : hour) + ":" +originalTime[1] + ":" + originalTime[2];

        }

        public void main(String[] args) {
            Scanner in = new Scanner(System.in);
            String s = in.next();
            String result = timeConversion(s);
            System.out.println(result);
        }
    }

    //Finding the two numbers in a random set that are closest together
    //watch out for N^2 loops, (nested loops on the same data!)
    //use one "n" loop to put everything into another data structure, or sort it!
    static int minimumAbsoluteDifference(int n, int[] arr) {
        //check bad inputs
        //n=0 elements in array
        if (n==0 || n==1) {
            //error
            return 0;
        }
        int min =-1;
        //for every i=0<n-1
        /*for (int i=0; i<n-1; i++) {
            //for every j=i+1<n
            for (int j=i+1; j<n; j++) {
               //calculate the abs(delta)
                int delta = Math.abs(arr[i]-arr[j]);
                //if it is a new minimum, set new Min
                if (min==-1 || delta<min) {
                    min = delta;
                }
            }

        }*/
        Arrays.sort(arr);
        //compare each pair
        for (int i=0; i<n-1; i++) {
            int delta = Math.abs(arr[i]-arr[i+1]);
            if (min==-1 || delta<min) {
                min=delta;
            }
        }
        return min;

        //edge cases?
        //only one element in array
        //two elements in the array of the same value

    }

    //Check if grid can be lexographically sorted by only rearranging the rows
    public void lexoSortGrid(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        //bad inputs? not expected //does case matter?  No
        Scanner scanner = new Scanner(System.in);
        int numTestCases = scanner.nextInt();

        //for each test case, T
        for (int t=0; t<numTestCases; t++) {
            int numLines = scanner.nextInt();
            List<char[]> lines = new ArrayList<>();
            for (int n=0; n<numLines; n++) {
                char[] line = scanner.next().toCharArray();
                Arrays.sort(line);
                lines.add(line);
            }
            Boolean sorted =true;
            //now, check every column pair.  Break as soon as one check fails
            for (int i=0; i<numLines; i++) {
                for (int j=0; j<numLines-1; j++) {
                    if (lines.get(j+1)[i]<lines.get(j)[i]) {
                        sorted=false;
                        break;
                    }
                }
                if (!sorted) {
                    break;
                }
            }
            if (sorted) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }

        }
        //lexographically sort every row

        //compare each pair in the column; break as soon as you find one not lexographically sorted
        //edge conditions
        //entire grid is the same character
    }
}
