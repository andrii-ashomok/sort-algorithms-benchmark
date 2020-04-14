package com.example.structure.tree;

import java.util.Objects;
import java.util.stream.Stream;

public class BTree {
    private final int min; // t - 1
    private final int t; // t
    private final int max; // 2t - 1
    private Node root;

    public BTree(int t) {
        if (t < 2) {
            throw new IllegalArgumentException("Number needs to be bigger than 2");
        }
        this.t = t;
        this.min = t - 1;
        max = 2*t - 1;
    }

    public void add(int element) {
        // first of all we try to add elem into root node if it possible
        if (root == null) {
            addRoot(element);
        } else {
            add(root, element);
        }

    }

    private void addRoot(int element) {
        root = new Node();
        root.elements[0] = element;
        root.incrementSize();
    }

    private void add(Node node, int element) {
        if (node.hasChild()) {
            addWithDiving(node, element);
        } else if (node.currentSize < max) {
            addElementToNode(node, element);
        }

        while (node != null && node.currentSize == max) {
            node = splitNode(node);
        }
    }

    private void addElementToNode(Node node, int element) {
        for (int i = 0; i < node.currentSize; i++) {
            if (node.elements[i] > element) {
                swap(node, i, element);
                node.incrementSize();
                return;
            }
        }

        node.elements[node.currentSize] = element;
        node.incrementSize();
    }

    private void addWithDiving(Node node, int element) {
        for (int i = 0; i < node.currentSize; i++) {
            if (node.elements[i] > element) {
                add(node.nodes[i], element);
                return;
            }
        }

        if (node.nodes[node.currentSize] != null) {
            add(node.nodes[node.currentSize], element);
        } else if (node.currentSize < max) {
            node.elements[node.currentSize] = element;
            node.incrementSize();
        } else if (node.nodes[max + 1] == null) {
            Node last = new Node();
            last.elements[0] = element;
            last.incrementSize();
            node.nodes[max + 1] = last;
        } else {
            add(node.nodes[max + 1], element);
        }
    }

    private void swap(Node node, int index, int element) {
        System.arraycopy(node.elements, index, node.elements, index + 1, node.currentSize - index);
        node.elements[index] = element;
    }

    // split if length equals max
    private Node splitNode(Node node) {
        Node left = new Node();
        Node right = new Node();

        System.arraycopy(node.elements, 0, left.elements, 0, min);
        System.arraycopy(node.nodes, 0, left.nodes, 0, min);
        System.arraycopy(node.elements, t, right.elements, 0, min);
        System.arraycopy(node.nodes, t, right.nodes, 0, min);

        left.currentSize = min;
        right.currentSize = min;

        if (node.parent == null) {
            Node middleNode = node.nodes[t];
            if (middleNode == null) {
                middleNode = new Node();
            }

            middleNode.currentSize = 1;
            left.parent = middleNode;
            right.parent = middleNode;
            middleNode.elements[0] = node.elements[min];
            middleNode.nodes[0] = left;
            middleNode.nodes[1] = right;

            root = middleNode;
        } else {
            node.parent.elements[node.parent.currentSize] = node.elements[min];
            node.parent.nodes[node.parent.currentSize] = left;
            left.parent = node.parent;
            node.parent.incrementSize();

            node.parent.nodes[node.parent.currentSize + 1] = right;
            right.parent = node.parent;
            node.parent.incrementSize();

            return node.parent;
        }

        return null;
    }

    public void print() {
        int level = 1;
        print(root, level);
    }

    private void print(Node node, int level) {
        for (int i = 0; i <= node.currentSize; i++) {
            if (node.elements[i] != null) {
                System.out.print(String.format("Level: %s,  number: %s", level, node.elements[i]));
                System.out.println();
            }

            if (node.nodes[i] != null) {
                print(node.nodes[i], level + 1);
            }
        }
        System.out.println();
    }

    /**
     * search contains of incoming element in B-tree
     * @param element - searching element
     * @return - true - element in tree, false - element was not find
     */
    public boolean search(int element) {
        return search(root, element);
    }

    private static boolean search(Node node, int search) {
        while (node != null) {
            for (int i = 0; i <= node.currentSize; i++) {
                Integer element = node.elements[i];
                if (element == null) {
                    return false;
                }

                if (element > search) {
                    node = node.nodes[i];
                    break;
                } else if (element == search) {
                    return true;
                }
            }
        }

        return false;
    }

    public void remove(int i) {

    }

    private class Node {
        private final Node[] nodes = new Node[max + 1];
        private final Integer[] elements = new Integer[max]; // if no elements (empty), set null
        private int currentSize = 0; // size of not null elements of  `Integer[] elements`
        private Node parent;

        void incrementSize() { currentSize++; }

        boolean hasChild() {
            return Stream.of(nodes).anyMatch(Objects::nonNull);
        }
    }

}
