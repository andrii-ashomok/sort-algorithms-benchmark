package com.example.structure.tree;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AVLTreeTest {
    private AVLTree tree;

    @Before
    public void init() {
        tree = new AVLTree(new AVLTree.AVLNode(10));
        tree.add(new Node(15));
        tree.add(new Node(5));
        tree.add(new Node(11));
        tree.add(new Node(1));
        tree.add(new Node(18));
        tree.add(new Node(16));
        tree.add(new Node(23));
    }

    @Test
    public void max() {
        Assert.assertEquals(23, tree.max());
    }

    @Test
    public void min() {
        Assert.assertEquals(1, tree.min());
    }

    @Test
    public void count() {
        Assert.assertEquals(8, tree.count());
    }

    @Test
    public void level() {
        Assert.assertEquals(4, tree.level());
    }

    @Test
    public void add() {
        tree.print();
    }

    @Test
    public void longLeftShift() {
        AVLTree tree = new AVLTree(new AVLTree.AVLNode(10));
        tree.add(new AVLTree.AVLNode(15));
        tree.add(new AVLTree.AVLNode(5));
        tree.add(new AVLTree.AVLNode(11));
        tree.add(new AVLTree.AVLNode(1));
        tree.add(new AVLTree.AVLNode(18));
        tree.add(new AVLTree.AVLNode(16));
        tree.add(new AVLTree.AVLNode(23));

        tree.print();
        Assert.assertEquals(-1, tree.getRoot().getLevel());
        AVLTree.AVLNode addedNode = new AVLTree.AVLNode(17);
        System.out.println("Add number: " + addedNode.getData());
        tree.add(addedNode);

        Assert.assertEquals(16, tree.getRoot().getRight().getData());
        Assert.assertEquals(0, tree.getRoot().getRight().getLevel());
        Assert.assertEquals(addedNode.getData(), tree.getRoot().getRight().getRight().getLeft().getData());
        Assert.assertEquals(0, tree.getRoot().getRight().getRight().getLeft().getLevel());
    }

}
