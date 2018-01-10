package com.riot.dataStructures.graphs;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

public class UniDirectionalGraph {
    private GraphNode root;

    GraphNode search (Integer target) {
        Queue<GraphNode> queue = new LinkedList<>();
        if (root==null) {
            //exception, no graph
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            GraphNode currNode = queue.remove();
            currNode.setMarked(true);
            if (currNode.getValue()==target) {
                return currNode;
            } else {
                for (GraphNode adjacency : currNode.getAdjacencies()) {
                    if (!adjacency.getMarked()) {
                        queue.add(adjacency);
                    }
                }
            }
        }
        //error - target not found all nodes visited from root
        return null;
    }

    Boolean pathBetweenNodes (GraphNode leftStart, GraphNode rightStart) {
        Map<GraphNode, Integer> visitedFromLeft = new HashMap<>();
        Map<GraphNode, Integer> visitedFromRight = new HashMap<>();
        Queue<GraphNode> leftQueue = new LinkedList<>();
        Queue<GraphNode> rightQueue = new LinkedList<>();

        leftQueue.add(leftStart);
        rightQueue.add(rightStart);
        while (leftQueue!=null && rightQueue!=null) {
            GraphNode currLeft = leftQueue.remove();
            GraphNode currRight = rightQueue.remove();

            visitedFromLeft.put(currLeft, currLeft.getValue());
            visitedFromRight.put(currRight, currLeft.getValue());

            if (visitedFromRight.containsKey(currLeft)) {
                return true;
            }
            for (GraphNode adjacency : currLeft.getAdjacencies()) {
                leftQueue.add(adjacency);
            }
            for (GraphNode adjacency : currRight.getAdjacencies()) {
                rightQueue.add(adjacency);
            }

        }
        //graphs not connected or no path
        return null;
    }

}
