package com.example.structure.tree;

// https://www.cs.usfca.edu/~galles/visualization/RedBlack.html
// https://www.programiz.com/dsa/red-black-tree

/**
 * A red-black tree satisfies the following properties:
 *
 * Red/Black Property: Every node is colored, either red or black.
 * Root Property: The root is black.
 * Leaf Property: Every leaf (NIL) is black.
 * Red Property: If a red node has children then, the children are always black.
 * Depth Property: For each node, any simple path from this node to any of its descendant leaf has the same black-depth (the number of black nodes).
 */
public class RedBlackTree extends Tree {

    public RedBlackTree(ColorNode node) {
        super(node);
    }

    // add only RED color new nodes
    public void add(ColorNode node) {

    }

    private void correctCase1(ColorNode addedNode) {
        if (root == null) {
            addedNode.setColor(false);
            root = addedNode;
        } else {
            addedNode.setColor(true);
            super.add(addedNode);
        }
    }

    private void correctCase2(ColorNode addedNode) {
        if (!addedNode.getParent().isColor()) {
            return;
        } else {
            correctCase3(addedNode);
        }
    }

    private void correctCase3(ColorNode addedNode) {
        if (addedNode.getParent().isColor()) {
            ColorNode grandPa = addedNode.getParent().getParent();
            if (grandPa == null) {
                addedNode.getParent().setColor(false);
                return;
            }

            ColorNode uncle = getUncle(addedNode);
            if (uncle == null) {
                addedNode.getParent().setColor(false);
                grandPa.setColor(true);
                return;
            }

            if (uncle.isColor()) {
                addedNode.getParent().setColor(false);
                uncle.setColor(false);
                grandPa.setColor(true);
                correctCase1(grandPa);
            } else {
                correctCase4(addedNode);
            }
        }

    }

    private void correctCase4(ColorNode addedNode) {
        ColorNode parent = addedNode.getParent();
        ColorNode grandPa = parent.getParent();
//       if (parent.getRight().equals(addedNode) && grandPa.getLeft().equals(parent))
    }

    // checking if parent is RED
    private ColorNode findColorDefect(ColorNode node) {
        while (node != root)  {
            ColorNode parent = node.getParent();
            if (parent == null) {
                return null;
            }

            // if parent is red and child is red
            if (parent.isColor() && node.isColor()) {
                return node;
            }

            node = parent;
        }

        return null;
    }

    static Boolean getParentColor(ColorNode node) {
        if (node.getParent() == null) return null;
        return node.getParent().isColor();
    }

    static ColorNode getUncle(ColorNode node) {
        ColorNode parent = node.getParent();

        if (parent.getParent() == null) { return null; }
        ColorNode grandPa = parent.getParent();
        if (grandPa.getLeft() == parent) {
            if (grandPa.getRight() == null) {
                return null;
            }
            return grandPa.getRight();
        } else {
            if (grandPa.getLeft() == null)
                return null;

            return grandPa.getLeft();
        }
    }

    static void leftShift(ColorNode node) {

    }

    static void rightShift(ColorNode node) {

    }

    public ColorNode getRoot() {
        return (ColorNode) root;
    }

    static class ColorNode extends Node {
        /**
         * false - black
         * true - red
         */
        private boolean color = false;

        public ColorNode(int data) {
            super(data);
        }

        public boolean isColor() {
            return color;
        }

        public void setColor(boolean color) {
            this.color = color;
        }

        public ColorNode getRight() {
            return (ColorNode) super.getRight();
        }

        public ColorNode getLeft() {
            return (ColorNode) super.getLeft();
        }

        public ColorNode getParent() {
            return (ColorNode) super.getParent();
        }
    }
}
