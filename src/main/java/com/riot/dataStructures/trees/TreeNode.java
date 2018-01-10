package com.riot.dataStructures.trees;

import java.util.List;

public class TreeNode {
    private Integer value;
    private List<TreeNode> children;

    public TreeNode(Integer data) {
        this.value=data;
    }
    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }
}
