package structure.java;

import java.util.List;

public class LinkedList<T> {
    private class Node<T> {
        public T element;
        public Node next;

        public Node(T e) {
            element = e;
        }

        public Node(T e, Node n) {
            element = e;
            next = n;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return element.toString();
        }
    }

    private Node dummyHead = new Node();
    int size;

    public LinkedList() {
        size = 0;
    }

    public LinkedList(T e) {
        size = 1;
        dummyHead.next = new Node<>(e);
    }

    public LinkedList(List<T> list) {
        size = list.size();
        if (size == 0) {
            return;
        }
        dummyHead.next = new Node<>(list.get(0));
        Node now = dummyHead.next;
        for (int i = 1; i < list.size(); i++) {
            now.next = new Node<>(list.get(i));
            now = now.next;
        }
    }

    public int getSize() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public void addFist(T e) {
        add(0, e);
    }

    public void add(int index, T e) {
        if (index < 0 || index > size)
            throw new IndexOutOfBoundsException();
        Node now = dummyHead;
        for (int i = 0; i < index; i++)
            now = now.next;
        now = new Node(e, now.next);
        size++;
    }

    public void addLast(T e) {
        add(size, e);
    }

    public T get(int index) {
        return (T) getNode(index).element;
    }

    public void set(int index) {
        Node now = getNode(index);
    }

    private Node getNode(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node now = dummyHead.next;
        for (int i = 0; i < index; i++) {
            now = now.next;
        }
        return now;
    }

    private boolean contains(T e) {
        Node now = dummyHead;
        while (now != null) {
            if (now.element == e) {
                return true;
            }
            now = now.next;
        }
        return false;
    }

    private T remove(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException();
        Node pre = dummyHead;
        for (int i = 0; i < index; i++) {
            pre = pre.next;
        }
        Node delNode = pre.next;
        pre.next = pre.next.next;
        delNode.next = null;
        size--;
        return (T) delNode.element;
    }

    public T removeFirst() {
        return remove(0);
    }

    public T removeLst() {
        return remove(size - 1);
    }
}
