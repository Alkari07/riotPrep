package com.riot.dataStructures.stack;

import java.util.Scanner;

public class HackerRankStack {
    //track the maximum of a stack as elements are pushed and popped to it
    //use a history stack
    public class TrackMax {

        public  void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Stack<Integer> stack = new Stack<>();
            Stack<Integer> maximumHistory = new Stack<>();
            int currToken;
            while (scanner.hasNextInt()) {
                currToken = scanner.nextInt();
                if (currToken==1) {
                    int insertionToken = scanner.nextInt();
                    stack.push(insertionToken);
                    if (maximumHistory.isEmpty() || insertionToken>=maximumHistory.peek()) {
                        maximumHistory.push(insertionToken);
                    }
                } else if (currToken==2) {
                    int top = stack.pop();
                    if (top==maximumHistory.peek()) {
                        maximumHistory.pop();
                    }
                } else if (!maximumHistory.isEmpty()) {
                    System.out.println(maximumHistory.peek());
                }
            }
        }
    }

    //check if a string brackets are balanced
    public class Solution {

        String isBalanced(String s) {
            if (s.equals("")) {
                return "YES";
            }
            Stack<Character> tokenStack = new Stack<>();
            char[] tokenArray = s.toCharArray();
            for (int i=0; i<tokenArray.length; i++) {
                char token = tokenArray[i];
                if (token=='(' || token=='[' || token=='{') {
                    //new opening of a bracket, push to stack
                    tokenStack.push(token);
                } else if (token==')' || token==']' || token=='}') {
                    //this character must close the top brack of the stack
                    //if it does not match, then the string is not "balanced"
                    if (tokenStack.isEmpty()) {
                        return "NO";
                    }
                    char poppedToken = tokenStack.pop();
                    if (poppedToken=='(' && token!=')') {
                        return "NO";
                    } else if (poppedToken=='[' && token!=']') {
                        return "NO";
                    } else if (poppedToken=='{' && token!='}') {
                        return "NO";
                    }
                }
            }
            //at this point, the stack should be empty - remaining tokens are unbalanced
            if (!tokenStack.isEmpty()) {
                return "NO";
            }
            return "YES";
        }

        public void main(String[] args) {
            Scanner in = new Scanner(System.in);
            int t = in.nextInt();
            for(int a0 = 0; a0 < t; a0++){
                String s = in.next();
                String result = isBalanced(s);
                System.out.println(result);
            }
            in.close();
        }
    }
}
