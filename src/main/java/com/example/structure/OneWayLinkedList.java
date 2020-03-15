package com.example.structure;

public class OneWayLinkedList<T> {

    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    public OneWayLinkedList() {
        head = new Node<>(null, null);
        tail = new Node<>(null, null);
    }

    public void first(T t) {
        if (size == 0) {
            head.value = t;
        } else {
            Node<T> node = new Node<>(head.value, head.next);
            head.value = t;
            head.next = node;
        }
        size++;
    }

    public void add(T t) {
        if (size == 0) {
            head.value = t;
        } else {
            dig(t, head);
        }
        size++;
    }

    private void dig(T t, Node<T> node) {
        while (node.next != null) {
            node = node.next;
        }
        node.next = new Node<>(t, null);
        tail.value = t;
    }

    public boolean contains(T t) {
        return search(head, t) != null;
    }

    /**
     * Insert element t2 into list after element t1. If t1 is a tail element then t2 will set as a tail of list.
     * @param t1 - insert operation process after this element;
     * @param t2 - element inserting;
     * @return - true - if element successfully insert, false - if element was not insert;
     */
    public boolean put(T t1, T t2) {
        Node<T> current = search(head, t1);
        if (current != null) {
            Node<T> next = current.next;
            Node<T> node = new Node<>(t2, next);
            current.next = node;
            if (next == null) {
                tail = node;
            }
            size++;
            return true;
        }

        return false;
    }

    private Node<T> search(Node<T> temp, T t1) {
        if (temp == null) {
            return null;
        }
        T t2 = temp.value;
        if (t2 != null && t2.equals(t1)) {
            return temp;
        } if (t2 == null && t1 == null) {
            return temp;
        }

        return search(temp.next, t1); // while
    }

    public boolean remove(T t) {
        if (size == 0) {
            return false;
        }

        if (head.value.equals(t)) {
            Node<T> next = head.next;
            head.value = next.value;
            head.next = next.next;
            size--;
            return true;
        } else {
            return remove(head, t);
        }
    }

    private boolean remove(Node<T> current, T t) {
        Node<T> remove = current.next;
        if (remove == null) {
            return false;
        }

        if (remove.value.equals(t)) {
            current.next = remove.next;
            size--;

            if (remove.next == null) {
                tail.value = current.value;
            }

            return true;
        } else {
            return remove(remove, t);
        }
    }


    public T first() { return head.value; }

    public T last() { return tail.value; }

    public int size()  { return size; }

    public boolean isEmpty() { return size > 0;}

    private static class Node<T> {
        private T value;
        private Node<T> next;

        Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }

}
