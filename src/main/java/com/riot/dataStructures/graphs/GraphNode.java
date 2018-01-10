package com.riot.dataStructures.graphs;

import java.util.ArrayList;
import java.util.List;

public class GraphNode {
    private Integer value;
    private List<GraphNode> adjacencies = new ArrayList<>();
    private Boolean marked = false;

    public GraphNode(Integer value) {
        this.value = value;
        this.adjacencies= new ArrayList<>();
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<GraphNode> getAdjacencies() {
        return adjacencies;
    }

    public void setAdjacencies(List<GraphNode> adjacencies) {
        this.adjacencies = adjacencies;
    }

    public Boolean getMarked() {
        return marked;
    }

    public void setMarked(Boolean marked) {
        this.marked = marked;
    }
}
