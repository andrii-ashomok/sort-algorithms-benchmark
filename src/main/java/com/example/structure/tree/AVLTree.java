package com.example.structure.tree;

public class AVLTree extends Tree {

    public AVLTree(AVLNode node) {
        super(node);
    }

    /**
     * 1. init ROOT;
     * 2. correct defect if it was found:
     *  2.1 calcLevel;
     *  2.2 defect;
     * @param node - avl tree node with level property
     */
    public void add(AVLNode node) {
        super.add(node);
        if (node != root) {
            correctDefects(node);
        }
    }

    // correct running while no defect found
    private void correctDefects(AVLNode addedNode) {
        AVLNode defected = findDefectedNode(addedNode);
        if (defected != null) {
            System.out.println("Defected avl tree:");
            print();
            if (defected.getLevel() == 1) {
                // move to the right
                rightShift(defected);
            } else {
                // move to the left
                leftShift(defected);
            }
        }
    }

    // recalculate parent nodes balance factor
    private AVLNode findDefectedNode(AVLNode nodeAdded) {
        AVLNode parent = (AVLNode) nodeAdded.getParent();
        while (parent != null) {
            if (parent.getLeft() != null && parent.getLeft().equals(nodeAdded)) {
                if (parent.getLevel() == 1) {
                    return parent;
                } else {
                    parent.setLevel(parent.getLevel() + 1);
                }
            } else {
                if (parent.getLevel() == -1) {
                    return parent;
                } else {
                    parent.setLevel(parent.getLevel() - 1);
                }
            }

            if (parent.getLevel() == 0) {
                return null;
            }

            if (parent.getParent() == null) {
                return null;
            }

            nodeAdded = parent;
            parent = (AVLNode) parent.getParent();
        }

        return null;
    }

    public int level() {
        return level(root);
    }

    private static int level(Node top) {
        if (top == null)
            return 0;

        return Math.max(level(top.getLeft()), level(top.getRight())) + 1; // TODO while
    }

    public void leftShift(AVLNode node) {
        int variant = 0;
        if ((variant = isFullLeftShift(node, variant)) > 0) {
            fullLeftShift(node, variant);
        } else if (isShortLeftShift(node, variant) > 0) {
            shortLeftShift(node, variant);
        } else {
            throw new RuntimeException("Unknown left node positions");
        }
    }

    private void shortLeftShift(AVLNode a, int variant) {
        AVLNode b = a.getRight();
        AVLNode q = b.getLeft();

        if (a.getParent() != null) {
            Node parent = a.getParent();
            if (parent.getLeft().equals(a)) {
                parent.setLeft(b);
            } else {
                parent.setRight(b);
            }
        }

        b.setLeft(a);
        a.setParent(b);

        if (q != null) {
            a.setRight(q);
            q.setParent(a);
        }

        if (variant == 1) {
            a.setLevel(0);
            b.setLevel(0);
        } else if (variant == 2) {
            a.setLevel(-1);
            b.setLevel(1);
        } else {
            throw new RuntimeException("Unknown left short shift prediction");
        }

        System.out.println("After short left shift: ");
        print();
    }

    private void fullLeftShift(AVLNode a, int variant) {
        AVLNode b = a.getRight();
        AVLNode c = b.getLeft();
        AVLNode q = c.getLeft();
        AVLNode r = c.getRight();

        if (a.getParent() != null) {
            Node parent = a.getParent();
            if (parent.getLeft().equals(a)) {
                parent.setLeft(c);
            } else {
                parent.setRight(c);
            }
            c.setParent(parent);
        }

        c.setLeft(a);
        c.setRight(b);
        a.setParent(c);
        b.setParent(c);

        if (q != null) {
            q.setParent(a);
        }
        a.setRight(q);

        if (r != null) {
            r.setParent(b);
        }
        b.setLeft(r);

        if (variant == 1) {
            a.setLevel(0);
            b.setLevel(-1);
            c.setLevel(0);
        } else if (variant == 2) {
            a.setLevel(1);
            b.setLevel(0);
            c.setLevel(0);
        } else if (variant == 3) {
            a.setLevel(0);
            b.setLevel(0);
            c.setLevel(0);
        } else {
            throw new RuntimeException("Unknown left long shift prediction");
        }

        System.out.println("After long left shift: ");
        print();
    }

    private int isShortLeftShift(AVLNode node, int variant) {
        if (node.getLevel() == -1 && node.getRight().getLevel() == -1) {
            variant = 1;
        } else if (node.getLevel() == -1 && node.getRight().getLevel() == 0) {
            variant = 2;
        }
        return variant;
    }

    // https://neerc.ifmo.ru/wiki/index.php?title=%D0%90%D0%92%D0%9B-%D0%B4%D0%B5%D1%80%D0%B5%D0%B2%D0%BE#.D0.94.D0.BE.D0.B1.D0.B0.D0.B2.D0.BB.D0.B5.D0.BD.D0.B8.D0.B5_.D0.B2.D0.B5.D1.80.D1.88.D0.B8.D0.BD.D1.8B
    private int isFullLeftShift(AVLNode node, int variant) {
        if (node.getRight().getLeft() != null) {
            if (node.getLevel() == -1 && node.getRight().getLevel() == 1 && node.getRight().getLeft().getLevel() == 1) {
                return 1;
            }

            if (node.getLevel() == -1 && node.getRight().getLevel() == 1 && node.getRight().getLeft().getLevel() == -1) {
                return 2;
            }

            if (node.getLevel() == -1 && node.getRight().getLevel() == 1 && node.getRight().getLeft().getLevel() == 0) {
                return 3;
            }
        }

        return variant;
    }

    public void rightShift(AVLNode node) {
        int variant = 0;
        if ((variant = isFullRightShift(node, variant)) > 0) {
            fullRightShift(node, variant);
        } else if ((variant = isShortRightShift(node, variant)) > 0) {
            shortRightShift(node, variant);
        } else {
            throw new RuntimeException("Unknown right node positions");
        }
    }

    private int isShortRightShift(AVLNode node, int variant) {
        // TODO add realization after understood how balance factor resets
        return 1;
    }

    private int isFullRightShift(AVLNode node, int variant) {
        // TODO add realization after understood how balance factor resets
        return 2;
    }

    public void shortRightShift(Node a, int variant) {
        Node b = a.getLeft();
        Node r = b.getRight();
        Node parent = a.getParent();

        if (parent != null) {
            if (parent.getLeft().equals(a)) {
                parent.setLeft(b);
            } else {
                parent.setRight(b);
            }
            b.setParent(parent);
        }

        b.setRight(a);
        a.setParent(b);

        if (r != null) {
            r.setParent(a);
        }
        a.setLeft(r);

        // TODO reset factor balance
    }

    public void fullRightShift(Node a, int variant) {
        Node b = a.getLeft();
        Node c = b.getRight();
        Node m = c.getLeft();
        Node n = c.getRight();
        Node parent = a.getParent();

        if (parent != null) {
            if (parent.getLeft().equals(a)) {
                parent.setLeft(c);
            } else {
                parent.setRight(c);
            }
            c.setParent(parent);
        }

        c.setLeft(b);
        b.setParent(c);
        c.setRight(a);
        a.setParent(c);

        if (m != null) {
            m.setParent(b);
        }
        b.setRight(m);

        if (n != null) {
            n.setParent(a);
        }
        a.setLeft(n);

        // TODO reset factor balance
    }

    public AVLNode getRoot() {
        return (AVLNode) root;
    }

    public static class AVLNode extends Node {

        private int level = 0;

        public AVLNode(int data) {
            super(data);
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public AVLNode getRight() {
            return (AVLNode) super.getRight();
        }

        public AVLNode getLeft() {
            return (AVLNode) super.getLeft();
        }
    }
}
