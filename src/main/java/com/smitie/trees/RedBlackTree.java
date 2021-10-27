package com.smitie.trees;

public class RedBlackTree {

    private static final int RED = 0;
    private static final int BLACK = 1;

    private final Node NIL = new Node(-999);
    private Node root = NIL;

    private class Node {

        int element; // dummy element
        int color = BLACK;

        Node parent = NIL;
        Node left = NIL;
        Node right = NIL;

        private Node(int element) {
            this.element = element;
        }
    }

    private void insert(Node node) {
        Node tmp = root;
        if (tmp == null) {
            root = node;
            node.color = BLACK;
            node.parent = NIL;
        } else {
            node.color = RED;
            while (true) {
                if (node.element < tmp.element) {
                    if (tmp.left == NIL) {
                        tmp.left = node;
                        node.parent = tmp;
                        break;
                    } else {
                        tmp = tmp.left;
                    }
                } else if (node.element >= tmp.element) {
                    if (tmp.right == NIL) {
                        tmp.right = node;
                        node.parent = tmp;
                        break;
                    } else {
                        tmp = tmp.right;
                    }
                }
            }

            fixAfterInsert(node);
        }
    }

    private void fixAfterInsert(Node node) {

        while (node.parent.color == RED) {
            Node ancestor;
            if (node.parent == node.parent.parent.left) {
                ancestor = node.parent.parent.right;
                if (ancestor != NIL && ancestor.color == RED) {
                    node.parent.color = BLACK;
                    ancestor.color = BLACK;
                    node.parent.parent.color = RED;
                    node = node.parent.parent;
                    continue;
                }
                if (node == node.parent.right) {
                    node = node.parent;

                }
            }
        }
    }


    private void rotateLeft(Node node) {
        if (node.parent != NIL) {
            if (node == node.parent.left) {
                node.parent.left = node.parent.right;

            } else {
                node.parent.right = node.right;
            }

            node.right.parent = node.parent;
            node.parent = node.right;
            if (node.right.left != NIL) {
                node.right.left.parent = node;
            }
            node.right = node.right.left;
            node.parent.left = node;
        } else {
            Node right = root.right;
            root.right = right.left;
            right.left.parent = root;
            root.parent = right;
            right.left = root;
            right.parent = NIL;
            root = right;
        }
    }

    private void rotateRight(Node node) {
        if (node.parent != NIL) {
            if (node == node.parent.left) {
                node.parent.left = node.left;
            } else {
                node.parent.right = node.left;
            }
            node.left.parent = node.parent;
            node.parent = node.left;

            if (node.left.right != NIL) {
                node.left.right.parent = node;
            }

            node.left = node.left.right;
            node.parent.right = node;
        } else {
            Node left = node.left;
            root.left = root.left.right;
            left.right.parent = root;
            root.parent = left;
            left.right = root;
            left.parent = NIL;
            root = left;
        }
    }

}
