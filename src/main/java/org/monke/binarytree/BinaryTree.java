package org.monke.binarytree;

public class BinaryTree {

    public Node root;

    public BinaryTree() {}


    public void add(int value) {
        root = BinaryTreeHelper.addRecursive(root, value);
    }

    public boolean containsNode(int value) {
        return BinaryTreeHelper.containsNodeRecursive(root, value);
    }

    public void delete(int value) {
        root = BinaryTreeHelper.deleteRecursive(root, value);
    }
}
