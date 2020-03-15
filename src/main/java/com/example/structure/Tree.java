package com.example.structure;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    private Node root;

    public void print() {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        int level = 0;
        while (queue.size() != 0) {
            for (int i = 0; i < Math.pow(2, level); i++) {
                Node node = queue.poll();
                if (node != null) {
                    queue.add(node.left);
                    queue.add(node.right);
                    System.out.print(node.data + " ");

                }
            }
            level++;
            System.out.println();
        }
    }

    public void recurcPrint() {
        recurcPrint(root);
    }

    private static void recurcPrint(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        recurcPrint(node.left);
        recurcPrint(node.right);
    }

    public void create() {
        root = new Node(0);
        root.left = new Node(1);
        root.right = new Node(2);

    }

    public int max() {
        return max(root);
    }

    private static int max(Node node) {
        int max = node.data;
        if (node.left != null) {
            int leftMax = max(node.left);
            if (max < leftMax) {
                max = leftMax;
            }
        }
        if (node.right != null) {
            int leftMax = max(node.right);
            if (max < leftMax) {
                max = leftMax;
            }
        }
        return max;
    }

    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
        }
    }

    public static void main(String[] args) {
        Tree tree = new Tree();
        tree.create();
        tree.recurcPrint();
    }

}
