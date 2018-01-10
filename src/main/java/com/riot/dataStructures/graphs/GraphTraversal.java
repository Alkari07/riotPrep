package com.riot.dataStructures.graphs;

import java.util.*;

public class GraphTraversal {

    public static GraphNode depthFirstSearch(GraphNode start, Integer target) {
        if (start==null || start.getMarked()) {
            return null;
        }
        System.out.println("Visited: " + start.getValue());
        if (start.getValue()==target) {
            return start;
        }
        start.setMarked(true);
        for (GraphNode adjacency : start.getAdjacencies()) {
            if (!adjacency.getMarked()) {
                GraphNode result = depthFirstSearch(adjacency, target);
                if (result!=null) {
                    return result;
                }
            }
        }
        return null;
    }

    public static List<GraphNode> findShortestPath(GraphNode start, Integer target) {
        List<GraphNode> results = new ArrayList<>();
        PathContainer startContainer = new PathContainer(start);
        startContainer.setShortestDistance(0);
        PathContainer result = unweightedBreadthFirstSearch(startContainer, target);
        if (result==null) {
            return null;
        }
        PathContainer currNode =result;
        while (currNode!=null) {
            results.add(currNode.getNode());
            currNode=currNode.getPrevNodeOnPath();
        }
        Collections.reverse(results);
        return results;
    }

    public static PathContainer unweightedBreadthFirstSearch (PathContainer start, Integer target) {
        //edge cases
        if (start==null) {
            return null;
        }

        Queue<PathContainer> queue = new LinkedList<>();
        queue.add(start);
        //search all adjacencies
        while (!queue.isEmpty()) {
            PathContainer currNode = queue.remove();
            if (!currNode.getNode().getMarked()) {
                //visit this node
                System.out.println("Visiting: " + currNode.getNode().getValue());
                currNode.getNode().setMarked(true);
                if (currNode.getNode().getValue()==target) {
                    System.out.println("Found target in distance: " + currNode.getShortestDistance());
                    return currNode;
                } else {
                    //keep searching
                    for (GraphNode nextNode : currNode.getNode().getAdjacencies()) {
                        PathContainer newContainer = new PathContainer(nextNode);
                        newContainer.setPrevNodeOnPath(currNode);
                        //for weighted edges, you would need to do some more work here
                        newContainer.setShortestDistance(currNode.getShortestDistance()+1);
                        queue.add(newContainer);
                    }
                }
            }
        }
        return null; //node not found on graph
    }
}
