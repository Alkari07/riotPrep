package com.riot.dataStructures.graphs;

import java.util.ArrayList;
import java.util.LinkedList;

public class CodingInterviewGraph {
    /***
    //Linked list of all nodes that are on a certain level of a binary tree
     **/
    public class TreeNode {
        TreeNode left;
        TreeNode right;
    }

    //recursive method
    void createLevelLinkedList(TreeNode root, ArrayList<LinkedList<TreeNode>> lists, int level) {
        if (root==null) return; //base case

        LinkedList<TreeNode> list = null;
        if(lists.size()==level) {
            //the list has gone through every level up to this one, need to create
            list = new LinkedList<>();
            lists.add(list);
        } else {
            list = lists.get(level);
        }
        list.add(root);
        createLevelLinkedList(root.left, lists, level+1);
        createLevelLinkedList(root.right, lists, level+1);
    }



    //kickoff method
    ArrayList<LinkedList<TreeNode>> createLevelLinkedList(TreeNode root) {
        ArrayList<LinkedList<TreeNode>> lists = new ArrayList<>();
        createLevelLinkedList(root, lists, 0);
        return lists;
    }

    /**
     * check if a binary tree is balanced
     */

    //kickoff method
    boolean isBalanced(TreeNode root) {
        return checkHeight(root) !=Integer.MIN_VALUE;
    }

    //go down the tree seeing if an error occurs because a heigh differential is detected
    int checkHeight(TreeNode root) {
        if (root==null) return -1; //base case, this level should be deducted from the height

        int leftHeight = checkHeight(root.left);
        if (leftHeight==Integer.MIN_VALUE) return leftHeight; //error detected, pass it up

        int rightHeight = checkHeight(root.right);
        if (rightHeight==Integer.MIN_VALUE) return rightHeight; //error detected, pass it up

        int heightDiff = leftHeight-rightHeight;
        if (Math.abs(heightDiff)>1) {
            //found the error
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight)+1;
        }

    }

    /**
     *
     */

}
