package com.riot.dataStructures.trees;

import java.util.*;

public class BinarySearchTree {
    BinaryTreeNode root;

    public void add(Integer data) {
        BinaryTreeNode newNode = new BinaryTreeNode(data);
        if (root ==null) {
            root=newNode;
            return;
        }

        BinaryTreeNode currNode = root;
        //could also have been done recursively by adding a put method inside the node
        while (true) {
            if (data < currNode.getValue()) {
                if (currNode.getLeft()==null) {
                    currNode.setLeft(newNode);
                    break;
                } else {
                    currNode=currNode.getLeft();
                }
                currNode=currNode.getLeft();
            } else {
                if (currNode.getRight()==null) {
                    currNode.setRight(newNode);
                    break;
                }
                else {
                    currNode=currNode.getRight();
                }
            }
        }
    }

    public void inOrderTraversal(BinaryTreeNode node) {
        //In a binary search tree, these are printed in ascending order
        if (node!=null) {
            inOrderTraversal(node.getLeft());
            System.out.println(node.getValue());
            inOrderTraversal(node.getRight());
        }


    }

    public BinarySearchTree createFromArray(int[] input) {
        if (input==null || input.length==0) {
            //error null input
            return null;
        }
        BinarySearchTree result = new BinarySearchTree();
        int center= input.length/2;
        int leftOffset = 0;
        int rightOffset =0;
        result.add(input[center]);
        for (int i =0; i<center; i++) {
            leftOffset++;
            rightOffset++;
            if (leftOffset>-1) {
                result.add(input[center-leftOffset]);
            }
            if (rightOffset<input.length) {
                result.add(input[center+rightOffset]);
            }
        }
        return result;
    }

    public List<LinkedList<BinaryTreeNode>> getLayers(BinaryTreeNode node) {
        if (node==null) {
            return null;
        }
        List<LinkedList<BinaryTreeNode>> result = new ArrayList<>();
        visitNode(result, 0, node);
        return result;
    }

    public void visitNode(List<LinkedList<BinaryTreeNode>> result, Integer currLayer, BinaryTreeNode node) {
        LinkedList<BinaryTreeNode> currentLayerList = result.get(currLayer);
        if (currentLayerList==null) {
            currentLayerList = new LinkedList<BinaryTreeNode>();
            result.add(currLayer,currentLayerList);
        }
        currentLayerList.add(node);
        if (node.getLeft()!=null) {
            visitNode(result, currLayer+1, node.getLeft());
        }
        if (node.getRight()!=null) {
            visitNode(result, currLayer+1, node.getRight());
        }
    }

    public Boolean checkBalanced(BinaryTreeNode root) {

        //check root is not null
        if (root==null) {
            return false;
        }
        List<BinaryTreeNode> firstLayer = new ArrayList<>();
        firstLayer.add(root.left);
        firstLayer.add(root.right);
        return checkLayerBalance(1, firstLayer);

    }

    public Boolean checkLayerBalance(Integer layerNumber, List<BinaryTreeNode> layer) {
        if (Math.pow(2, layerNumber)==layer.size()) {
            //layer is full, tree still balanced
            List<BinaryTreeNode> nextLayer = new ArrayList<>();
            for (BinaryTreeNode currNode : layer) {
                nextLayer.add(currNode.left);
                nextLayer.add((currNode.right));
            }
            return checkLayerBalance(layerNumber+1, nextLayer);
        } else {
            //there is a node missing!  if any of this layer have children, the tree is unbalanced
            for (BinaryTreeNode currNode : layer) {
                if (currNode.getLeft()!=null || currNode.getRight()!=null) {
                    return false;
                }
            }
            return true; //this layer had missing elements, but none of the existing ones had children
            //tree is balanced
        }
    }

    public Boolean checkIsBinary(BinaryTreeNode root) {
        if (root==null) {
            return null; //error
        }
        Integer localMinima = root.getValue();
        Integer localMaxima = root.getValue();

        return isBinary(root.getLeft(), null, localMaxima) &&
                isBinary(root.getLeft(), localMinima, null);

    }

    public Boolean isBinary(BinaryTreeNode node, Integer localMinima, Integer localMaxima) {
        if (node==null) {
            return true;
        }
        if (localMinima!=null && node.getValue()<localMinima) {
            return false;
        }
        if (node.getValue()>localMaxima) {
            return false;
        }
        return isBinary(node.getLeft(), localMinima, node.getValue()) && isBinary(node.getRight(), node.getValue(), localMaxima);
    }

}
