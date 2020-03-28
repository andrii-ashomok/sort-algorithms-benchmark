package com.example.structure;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    private Node root;

    public Tree(Node node) {
        root = node;
    }

    public void print() {
        Queue<Node> queue = new LinkedList<>();
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

    public void recursivePrint() {
        recursivePrint(root);
    }

    private static void recursivePrint(Node node) {
        if (node == null) {
            return;
        }

        System.out.println(node.data);
        recursivePrint(node.left);
        recursivePrint(node.right);
    }

    public int max() {
        return max(root);
    }

    private static int max(Node node) {
        int max = node.data;
        if (node.left != null) {
            int temp = max(node.left);
            if (max < temp) {
                max = temp;
            }
        }
        if (node.right != null) {
            int temp = max(node.right);
            if (max < temp) {
                max = temp;
            }
        }
        return max;
    }

    public int min() {
        return min(root);
    }

    private static int min(Node node) {
        int min = node.data;
        if (node.left != null) {
            int temp = min(node.left);
            if (min > temp) {
                min = temp;
            }
        }
        if (node.right != null) {
            int temp = min(node.right);
            if (min > temp) {
                min = temp;
            }
        }
        return min;
    }

    public int count() {
        return count(root) + 1;
    }

    private static int count(Node node) {
        if (node == null) {
            return 0;
        }

        int count = 0;
        if (node.left != null) {
            count += 1 + count(node.left);
        }
        if (node.right != null) {
            count += 1 + count(node.right);
        }

        return count;
    }

    public static class Node {
        private final int data;
        private Node left;
        private Node right;

        public Node(int data) {
            this.data = data;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public void setRight(Node right) {
            this.right = right;
        }

        public int getData() {
            return data;
        }

        public Node getLeft() {
            return left;
        }

        public Node getRight() {
            return right;
        }
    }

}
