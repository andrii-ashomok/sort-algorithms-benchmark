package com.example.structure.tree;

import java.util.LinkedList;
import java.util.Queue;

public class Tree {

    protected Node root;

    public Tree(Node node) {
        root = node;
    }

    public void add(Node node) {
        if (root == null) {
            root = node;
        } else {
            int data = root.getData();
            if (data > node.getData()) {
                add(node, root.getLeft(), true);
            } else {
                add(node, root.getRight(), false);
            }
        }

    }

    private void add(Node node, Node tmp, boolean isLeft) {
        Node parent = root;
        while (tmp != null) {
            parent = tmp;
            if (tmp.getData() > node.getData()) {
                tmp = tmp.getLeft();
                isLeft = true;
            } else {
                tmp = tmp.getRight();
                isLeft = false;
            }
        }

        node.setParent(parent);
        if (isLeft) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
    }

    public void print() {
        print(root);
    }

    private static void print(Node root) {
        Queue<Node> queue = new LinkedList<>();
        queue.add(root);
        int level = 0;
        while (queue.size() != 0) {
            for (int i = 0; i < Math.pow(2, level); i++) {
                Node node = queue.poll();
                if (node != null) {
                    queue.add(node.getLeft());
                    queue.add(node.getRight());
                    System.out.print(String.format("Data: %s  --->  ", node.getData()));
                }
            }
            level++;
            System.out.println();
        }
    }

    public void recursivePrint(Node root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getData());
        recursivePrint(root.getLeft());
        recursivePrint(root.getRight());
    }

    public int max() {
        return max(root);
    }

    private static int max(Node node) {
        int max = node.getData();
        if (node.getLeft() != null) {
            int temp = max(node.getLeft());
            if (max < temp) {
                max = temp;
            }
        }
        if (node.getRight() != null) {
            int temp = max(node.getRight());
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
        int min = node.getData();
        if (node.getLeft() != null) {
            int temp = min(node.getLeft());
            if (min > temp) {
                min = temp;
            }
        }
        if (node.getRight() != null) {
            int temp = min(node.getRight());
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
        if (node.getLeft() != null) {
            count += 1 + count(node.getLeft());
        }
        if (node.getRight() != null) {
            count += 1 + count(node.getRight());
        }

        return count;
    }

    public void leftShift(Node node) {
        Node parent = node.getParent();
        Node newRoot = node.getLeft();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(newRoot);
            } else {
                parent.setRight(newRoot);
            }
            newRoot.setParent(parent);
        }

        node.setLeft(newRoot.getRight());
        node.setParent(newRoot);
        newRoot.setRight(node);
    }

    public void rightShift(Node node) {
        Node parent = node.getParent();
        Node newRoot = node.getRight();
        if (parent != null) {
            if (parent.getLeft() == node) {
                parent.setLeft(newRoot);
            } else {
                parent.setRight(newRoot);
            }
            newRoot.setParent(parent);
        }

        node.setRight(newRoot.getLeft());
        node.setParent(newRoot);
        newRoot.setLeft(node);
    }

    public int level() {
        return level(root);
    }

    private static int level(Node top) {
        if (top == null)
            return 0;

        return Math.max(level(top.getLeft()), level(top.getRight())) + 1; // TODO while
    }

    public Node getRoot() { return root; }
}
