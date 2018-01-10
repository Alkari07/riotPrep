package com.riot.dataStructures.graphs;

public class PathContainer {
    private GraphNode node =null;
    private PathContainer prevNodeOnPath = null;
    private Integer shortestDistance = null;

    public PathContainer(GraphNode node) {
        this.node = node;
    }

    public GraphNode getNode() {
        return node;
    }

    public void setNode(GraphNode node) {
        this.node = node;
    }

    public PathContainer getPrevNodeOnPath() {
        return prevNodeOnPath;
    }

    public void setPrevNodeOnPath(PathContainer prevNodeOnPath) {
        this.prevNodeOnPath = prevNodeOnPath;
    }

    public Integer getShortestDistance() {
        return shortestDistance;
    }

    public void setShortestDistance(Integer shortestDistance) {
        this.shortestDistance = shortestDistance;
    }
}
