package com.smitie.trees;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> tree = new BinarySearchTree<>();

        tree.insert(10);
        tree.insert(20);
        tree.insert(14);
        tree.insert(25);
        tree.insert(6);
        tree.insert(4);
        tree.insert(8);


        System.out.println("==========IN============");
        tree.print(BinarySearchTree.TraversalOrder.IN);
        System.out.println();
        System.out.println("==========PRE============");
        tree.print(BinarySearchTree.TraversalOrder.PRE);
        System.out.println();
        System.out.println("==========POST============");
        tree.print(BinarySearchTree.TraversalOrder.POST);
        System.out.println();
        System.out.println("==========LEVEL============");
        tree.print(BinarySearchTree.TraversalOrder.LEVEL);
    }
}
