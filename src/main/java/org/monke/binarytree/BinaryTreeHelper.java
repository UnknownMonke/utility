package org.monke.binarytree;

public class BinaryTreeHelper {

    public static BinaryTree createBinaryTree() {
        BinaryTree bt = new BinaryTree();

        bt.add(6);
        bt.add(4);
        bt.add(8);
        bt.add(3);
        bt.add(5);
        bt.add(7);
        bt.add(9);

        return bt;
    }

    /**
     * Adds a Node recursively.
     * <br/><br/>
     * Rules :
     * <ul>
     *     <li>
     *         If the new Node value is lower than the current Node’s, we go to the left child.
     *     </li>
     *     <li>
     *         If the new Node value is greater than the current Node’s, we go to the right child.
     *     </li>
     *     <li>
     *         When the current node is null, we’ve reached a leaf node and insert the new Node in that position.
     *     </li>
     * </ul>
     */
    public static Node addRecursive(Node current, int value) {
        // Exit condition.
        if (current == null) {
            return new Node(value);
        }

        if (value < current.value) {
            current.left = addRecursive(current.left, value);
        } else if (value > current.value) {
            current.right = addRecursive(current.right, value);
        } else {
            // Value already exists.
            return current;
        }
        return current;
    }

    public static boolean containsNodeRecursive(Node current, int value) {
        // Exit condition. No Node found.
        if (current == null) {
            return false;
        }

        if (value == current.value) {
            return true;
        }
        return value < current.value
                ? containsNodeRecursive(current.left, value)
                : containsNodeRecursive(current.right, value);
    }

    public static Node deleteRecursive(Node current, int value) {
        // Exit condition. No Node found.
        if (current == null) {
            return null;
        }

        if (value == current.value) {
            // Node to delete found
            // ... code to delete the node will go here
        }
        if (value < current.value) {
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }
}
