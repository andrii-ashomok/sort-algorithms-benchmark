package com.example.structure.tree;

import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;

public class TreeTest {
    private static final int ROOT_DATA = 100;
    private static final int MIN_DATA = 1;
    private static final int MAX_DATA = 1000;
    private static final int LEFT_LEVEL = 10;
    private static final int RIGHT_LEVEL = 10;

    @Test
    public void max() {
        generate().max();
    }

    @Test
    public void min() {
        generate().min();
    }

    @Test
    public void count() {
        generate().count();
    }

    private Tree generate() {
        Node root = new Node(ROOT_DATA);
        System.out.println("Root data is: " + root.getData());
        fillLeftTree(LEFT_LEVEL, root);
        System.out.println("Left root data is: " + root.getLeft().getData());
        fillRightTree(RIGHT_LEVEL, root.getRight());
        System.out.println("Right root data is: " + root.getRight().getData());
        return new Tree(root);
    }

    private void fillLeftTree(int n, Node node) {
        if (n == 0) {
            return;
        }

        if (node.getLeft() == null) {
            if (node.getData() > 1) {
                int x = ThreadLocalRandom.current().nextInt(MIN_DATA, node.getData());
                int y = ThreadLocalRandom.current().nextInt(MIN_DATA, node.getData());
                if (x < y) {
                    node.setLeft(new Node(x));
                    node.setRight(new Node(y));
                } else if (x == y) {
                    return;
                } else {
                    node.setLeft(new Node(y));
                    node.setRight(new Node(x));
                }
                fillLeftTree(n - 1, node.getLeft());
            }
        }
    }

    private void fillRightTree(int n, Node node) {
        if (n == 0) {
            return;
        }

        if (node.getRight() == null) {
            if (node.getData() < 1000) {
                int x = ThreadLocalRandom.current().nextInt(node.getData(), MAX_DATA);
                int y = ThreadLocalRandom.current().nextInt(node.getData(), MAX_DATA);
                if (x < y) {
                    node.setLeft(new Node(x));
                    node.setRight(new Node(y));
                } else if (x == y) {
                    return;
                } else {
                    node.setLeft(new Node(y));
                    node.setRight(new Node(x));
                }
                fillRightTree(n - 1, node.getRight());
            }
        }
    }

}
