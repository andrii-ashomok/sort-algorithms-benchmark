package com.example.structure.tree;

import org.junit.Assert;
import org.junit.Test;

public class BTreeTest {

    @Test(expected = IllegalArgumentException.class)
    public void badTreeParameter() {
        new BTree(1);
    }

    @Test
    public void add() {
        BTree tree = new BTree(3);
        tree.add(10);
        tree.print();

        tree.add(1);
        tree.print();

        tree.add(5);
        tree.print();

        tree.add(25);
        tree.print();

        tree.add(35);
        tree.print();

        tree.add(2);
        tree.print();

        tree.add(100);
        tree.print();

        tree.add(80);
        tree.print();

        tree.add(70);
        tree.print();

        Assert.assertTrue(tree.search(70));
    }

}
