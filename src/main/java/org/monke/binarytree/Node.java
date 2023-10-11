package org.monke.binarytree;

/**
 * Tree element. Defines a tree.
 * <br/><br/>
 * A tree consists of a root Node which contains its value and its children.
 * <br/>
 * Each child contains its own subtree.
 * <br/>
 * At the bottom of the tree, a Node without children is a leaf Node.
 * <br/><br/>
 * In case of a binary tree, each Node can only have 2 children, left and right.
 */
public class Node {

    public int value;
    public Node left;
    public Node right;

    public Node(int value) {
        this.value = value;
        left = null;
        right = null;
    }

    public boolean isLeaf() {
        return left == null && right == null;
    }
}
