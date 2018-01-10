package com.riot.dataStructures.graphs;

import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

public class GraphTraversalTests {
    Integer SIZE = 10; //the size of the graph
    Map<Integer, GraphNode> graphMap = new HashMap<>();


    @Before
    public void setUp() throws Exception {
        GraphNode node0 = new GraphNode(0);
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        GraphNode node5 = new GraphNode(5);

        node0.getAdjacencies().add(node1);
        node0.getAdjacencies().add(node2);
        node1.getAdjacencies().add(node2);
        node2.getAdjacencies().add(node3);
        node2.getAdjacencies().add(node0);
        node2.getAdjacencies().add(node1);
        node4.getAdjacencies().add(node3);
        node5.getAdjacencies().add(node3);
        node3.getAdjacencies().add(node5);
        node2.getAdjacencies().add(node5);

        graphMap.put(0, node0);
        graphMap.put(1, node1);
        graphMap.put(2, node2);
        graphMap.put(3, node3);
        graphMap.put(4, node4);
        graphMap.put(5, node5);

    }

    @Test
    public void depthFirstSucceed() {
        resetNodes();
        debugOut(3, 0);
        GraphNode result = GraphTraversal.depthFirstSearch(graphMap.get(0), 3);
        assertNotNull(result);
        assertTrue(result.getValue()==3);

        resetNodes();
        debugOut(0, 2);
        result = GraphTraversal.depthFirstSearch(graphMap.get(2), 0);
        assertNotNull(result);
        assertTrue(result.getValue()==0);

        resetNodes();
        debugOut(5, 0);
        result = GraphTraversal.depthFirstSearch(graphMap.get(0), 5);
        assertNotNull(result);
        assertTrue(result.getValue()==5);
    }

    @Test
    public void depthFirstFails() {
        resetNodes();
        debugOut(4, 0);
        GraphNode result = GraphTraversal.depthFirstSearch(graphMap.get(0), 4);
        assertTrue(result==null);
    }

    @Test
    public void breadthFirstSucceeds() {
        resetNodes();
        debugOut(5, 0);
        List<GraphNode> results = GraphTraversal.findShortestPath(graphMap.get(0), 5);
        assertTrue(results.get(0).getValue()==0);
        assertTrue(results.get(1).getValue()==2);
        assertTrue(results.get(2).getValue()==5);

        resetNodes();
        debugOut(5, 1);
        results = GraphTraversal.findShortestPath(graphMap.get(1), 5);
        assertTrue(results.get(0).getValue()==1);
        assertTrue(results.get(1).getValue()==2);
        assertTrue(results.get(2).getValue()==5);
    }

    @Test
    public void breadthFirstFails() {
        resetNodes();
        debugOut(4, 2);
        List<GraphNode> results = GraphTraversal.findShortestPath(graphMap.get(2), 4);
        assertNull(results);

    }

    private void resetNodes() {
        for (GraphNode node : graphMap.values()) {
            node.setMarked(false);
        }
    }
    private void debugOut(int target, int start) {
        System.out.println("\nFinding node: " + target + " from node: " + start);
    }
}
