package com.smitie.trees;

import java.util.*;

public class BinarySearchTree<T extends Comparable<T>> {

    private int nodeCount;
    private Node root = null;

    private class Node {

        private T element;
        private Node left;
        private Node right;

        public Node(Node left, Node right, T element) {
            this.element = element;
            this.left = left;
            this.right = right;
        }
    }

    public enum TraversalOrder {
        PRE,
        IN,
        POST,
        LEVEL
    }

    public boolean insert(T element) {
        if (element == null) {
            return false;
        }
        if (contains(element))
            return false;

        root = insert(root, element);
        nodeCount++;
        return true;
    }

    public boolean delete(T element) {
        if (contains(element)) {
            root = delete(root, element);
            nodeCount--;
            return true;
        }
        return false;
    }

    private Node delete(Node node, T element) {
        if (node == null)
            return null;

        if (node.element.compareTo(element) == 0) {
            if (node.right == null) {
                Node left = node.left;
                node = null;
                return left;

            } else if (node.left == null) {
                Node right = node.right;
                node = null;
                return right;

            } else {
                Node leftMost = findLeftMostNode(node.right);
                node.element = leftMost.element;
                node.right = delete(node.right, node.element);
            }
        }
        return node;
    }

    private Node findLeftMostNode(Node node) {
        while (node.left != null)
            node = node.left;
        return node;

    }

    private Node findRightMostNode(Node node) {
        while (node.right != null)
            node = node.right;
        return node;
    }

    public T root() {
        if (root == null)
            return null;
        return root.element;
    }

    public T max() {
        if (root == null)
            return null;
        return findRightMostNode(root).element;
    }

    public T min() {
        if (root == null)
            return null;
        return findRightMostNode(root).element;
    }

    public int size() {
        return nodeCount;
    }

    private int height(Node node) {
        if (root == null)
            return 0;
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    public void print(TraversalOrder to) {

        if (size() == 0) {
            System.out.println("empty");
            return;
        }

        switch (to) {
            case IN -> printInOrder(root);
            case PRE -> printPreOrder(root);
            case POST -> printPostOrder(root);
            case LEVEL -> printLevelOrder(root);
        }
    }

    private void printInOrder(Node node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print(" " + node.element);
            printInOrder(node.right);
        }
    }

    private void printPreOrder(Node node) {
        if (node != null) {
            System.out.print(" " + node.element);
            printPreOrder(node.left);
            printPreOrder(node.right);
        }
    }

    private void printPostOrder(Node node) {
        if (node != null) {
            printPostOrder(node.left);
            printPostOrder(node.right);
            System.out.print(" " + node.element);
        }
    }

    private void printLevelOrder(Node node) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            System.out.print(" " + current.element);
            if (current.left != null)
                queue.add(current.left);

            if (current.right != null)
                queue.add(current.right);
        }
    }

    public List<T> asList(TraversalOrder to) {

        if (size() == 0)
            return Collections.emptyList();

        List<T> res = new ArrayList<>();

        switch (to) {
            case PRE -> traversePreOrderAsList(root, res);
            case IN -> traverseInOrderAsList(root, res);
            case POST -> traversePostOrderAsList(root, res);
            case LEVEL -> traverseLevelOrderAsList(root, res);
        }

        return res;
    }

    private void traverseInOrderAsList(Node node, List<T> treeList) {
        if (node != null) {
            traverseInOrderAsList(node.left, treeList);
            treeList.add(node.element);
            traverseInOrderAsList(node.right, treeList);
        }
    }

    private void traversePreOrderAsList(Node node, List<T> treeList) {
        if (node != null) {
            System.out.print(" " + node.element);
            traversePreOrderAsList(node.left, treeList);
            traversePreOrderAsList(node.right, treeList);
        }
    }

    private void traversePostOrderAsList(Node node, List<T> treeList) {
        if (node != null) {
            traversePostOrderAsList(node.left, treeList);
            traversePostOrderAsList(node.right, treeList);
            System.out.print(" " + node.element);
        }
    }

    private void traverseLevelOrderAsList(Node node, List<T> treeList) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(node);
        while (!queue.isEmpty()) {
            Node current = queue.poll();
            treeList.add(current.element);

            if (current.left != null)
                queue.add(current.left);

            if (current.right != null)
                queue.add(current.right);
        }
    }

    public Iterator<T> iterator(TraversalOrder to) {
        if (size() == 0)
            return Collections.emptyIterator();

        return switch (to) {
            case IN -> traverseInOrderAsIterator(root);
            case PRE -> traversePreOrderAsIterator(root);
            case POST -> traversePostOrderAsIterator(root);
            case LEVEL -> traverseLevelOrderAsIterator(root);
        };
    }

    private Iterator<T> traverseInOrderAsIterator(Node node) {
        final int expectedNodeCount = size();
        final Deque<Node> stack = new ArrayDeque<>();

        stack.push(node);

        return new Iterator<T>() {
            Node cursor = node;

            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                return node != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();
                while (cursor != null && cursor.left != null) {
                    stack.push(cursor.left);
                    cursor = cursor.left;
                }
                Node current = stack.pop();

                if (current.right != null) {
                    stack.push(current.right);
                    cursor = current.right;
                }
                return current.element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };

    }


    private Iterator<T> traversePreOrderAsIterator(Node node) {
        final int expectedNodeCount = size();

        final Deque<Node> stack = new ArrayDeque<>();

        stack.push(node);


        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                return node != null && !stack.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                Node current = stack.pop();

                if (current.right != null)
                    stack.push(current.right);

                if (current.left != null)
                    stack.push(current.left);
                return current.element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }
        };
    }

    private Iterator<T> traversePostOrderAsIterator(Node node) {

        final int expectedNodeCount = nodeCount;

        Deque<Node> stacka = new ArrayDeque<>();
        Deque<Node> stackb = new ArrayDeque<>();

        stacka.push(node);

        while (stacka.isEmpty()) {
            Node nodea = stacka.pop();
            if (nodea != null) {
                stackb.push(nodea);
                if (nodea.left != null)
                    stacka.push(nodea.left);
                if (nodea.right != null)
                    stacka.push(nodea.right);
            }
        }

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {

                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                return node != null && !stackb.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                return stackb.pop().element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException();
            }

        };
    }

    private Iterator<T> traverseLevelOrderAsIterator(Node node) {
        final int expectedNodeCount = nodeCount;
        Queue<Node> queue = new ArrayDeque<>();
        queue.offer(node);

        return new Iterator<T>() {
            @Override
            public boolean hasNext() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                return node != null && !queue.isEmpty();
            }

            @Override
            public T next() {
                if (expectedNodeCount != nodeCount)
                    throw new ConcurrentModificationException();

                Node current = queue.poll();

                if (current.left != null)
                    queue.offer(current.left);

                if (current.right != null)
                    queue.offer(current.right);

                return current.element;
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("remove");
            }
        };
    }

    private Node insert(Node current, T element) {
        if (current == null)
            return new Node(null, null, element);

        if (element.compareTo(current.element) < 0)
            current.left = insert(current.left, element);
        else
            current.right = insert(current.right, element);

        return current;
    }

    public boolean contains(T element) {
        return contains(root, element);
    }

    private boolean contains(Node current, T element) {
        if (current == null || element == null)
            return false;

        if (element.compareTo(current.element) == 0)
            return true;

        return element.compareTo(current.element) < 0
                ? contains(current.left, element)
                : contains(current.right, element);
    }
}
