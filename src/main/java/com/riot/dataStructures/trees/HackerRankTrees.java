package com.riot.dataStructures.trees;

import java.util.*;

public class HackerRankTrees {
    //Determine height of a tree
    //note: could not assume it would be balanced!
    class Node {
    	int data;
    	Node left;
    	Node right;
	}
	//find the height of a tree
    static int height(Node root) {
        // Write your code here.
        Integer currLayer = 0;
        return visit (root, currLayer);
    }

    public static Integer visit (Node node, Integer currLayer) {
        if (node==null) {
            return currLayer -1;
        }
        int leftDepth = visit(node.left, currLayer+1);
        int rightDepth = visit(node.right, currLayer+1);
        return leftDepth>rightDepth ? leftDepth : rightDepth;
    }

    //Print only the top (visible from a plane) part of a tree (all interior not shown, read left to right)
    void topView(Node root) {
        //any value on the "interior" of the tree is visible - therefore, "in order traversal"
        if (root==null) {
            return;
        }
        List<Integer> results = new ArrayList<>();
        Node currNode = root;
        while (currNode.left!=null) {
            currNode=currNode.left;
            results.add(currNode.data);
        }

        //results are refersed on left
        Collections.reverse(results);
        //add root
        results.add(root.data);
        currNode=root;
        while (currNode.right!=null) {
            currNode=currNode.right;
            results.add(currNode.data);
        }

        for (Integer data : results) {
            System.out.print(data + " ");
        }

    }

    //Breadth first Node traversal (by layer print)
    void levelOrder(Node root) {
        //breadth first search
        if (root==null) {
            return;
        }
        Queue<Node> sequence = new LinkedList<>();
        sequence.add(root);
        Node currNode;
        while (!sequence.isEmpty()) {
            currNode = sequence.remove();
            System.out.print(currNode.data + " ");
            if (currNode.left!=null) {
                sequence.add(currNode.left);
            }
            if (currNode.right!=null) {
                sequence.add(currNode.right);
            }
        }

    }
}
