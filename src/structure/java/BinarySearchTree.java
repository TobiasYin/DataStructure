package structure.java;

import structure.Queue;
import structure.kotlin.queue.LinkedQueue;

public class BinarySearchTree<T extends Comparable<T>> {
    private class Node<E> {
        E value;
        Node<E> left;
        Node<E> right;

        Node(E element) {
            value = element;
            left = null;
            right = null;
        }
    }
    public BinarySearchTree(){
        root = null;
        size = 0;
    }

    private int size;
    private Node<T> root;

    public Boolean isEmpty() {
        return size == 0;
    }

    public void add(T element) {
        root = add(element, root);
    }

    private Node<T> add(T element, Node<T> node) {
        if (node == null) {
            Node<T> newNode = new Node<>(element);
            size++;
            return newNode;
        } else {
            if (node.value.compareTo(element) > 0) {
                node.left = add(element, node.left);
            } else if (node.value.compareTo(element) < 0) {
                node.right = add(element, node.right);
            }
            return node;
        }
    }

    public boolean contains(T element) {
        return contains(element, root);
    }

    private boolean contains(T element, Node<T> node) {
        if (node == null) {
            return false;
        } else {
            if (node.value.compareTo(element) == 0) {
                return true;
            } else if (node.value.compareTo(element) > 0) {
                return contains(element, node.left);
            } else {
                return contains(element, node.right);
            }
        }
    }

    public void preOrder() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        preOrder(root);
        System.out.println();

    }

    public void inOrder() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        inOrder(root);
        System.out.println();

    }

    public void postOrder() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        postOrder(root);
        System.out.println();

    }

    private void preOrder(Node<T> node) {
        if (node == null) return;

        System.out.print(""+node.value+" ");
        preOrder(node.left);
        preOrder(node.right);
    }

    private void inOrder(Node<T> node) {
        if (node == null) return;

        inOrder(node.left);
        System.out.print(""+node.value+" ");
        inOrder(node.right);
    }

    private void postOrder(Node<T> node) {
        if (node == null) return;

        postOrder(node.left);
        postOrder(node.right);
        System.out.print(""+node.value+" ");
    }

    public void levelOrder() {
        if (isEmpty()) {
            throw new IndexOutOfBoundsException();
        }
        Queue<Node<T>> queue = new LinkedQueue<>();
        queue.enQueue(root);
        while (!queue.isEmpty()) {
            Node node = queue.deQueue();
            System.out.println(node.value);
            if (node.left != null) {
                queue.enQueue(node.left);
            }
            if (node.right != null) {
                queue.enQueue(node.right);
            }
        }
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return super.toString();
    }


    public T minimum(){
        if (isEmpty()) throw new IndexOutOfBoundsException();
        return minimum(root).value;
    }

    private Node<T> minimum(Node<T> node) {
        if (node.left == null) return node;
        else return minimum(node.left);
    }

    public T maximum(){
        if (isEmpty()) throw new IndexOutOfBoundsException();
        return maximum(root).value;
    }

    private Node<T> maximum(Node<T> node) {
        if (node.right == null) return node;
        else return maximum(node.right);
    }

    public T removeMin(){
        if (isEmpty()) throw new IndexOutOfBoundsException();
        T res = minimum();
        root = removeMin(root);
        return res;
    }

    private Node<T> removeMin(Node<T> node) {
        if (node.left == null){
            Node<T> right = node.right;
            node.right = null;
            size--;
            return right;
        }
        node.left = removeMin(node.left);
        return node;
    }

    public T removeMax(){
        if (isEmpty()) throw new IndexOutOfBoundsException();
        T res = maximum();
        root = removeMax(root);
        return res;
    }

    private Node<T> removeMax(Node<T> node) {
        if (node.right == null){
            Node<T> left = node.left;
            node.left = null;
            size--;
            return left;
        }
        node.right = removeMax(node.right);
        return node;
    }

    public void remove(T element){
        root = remove(element, root);
    }

    private Node<T> remove(T element, Node<T> node) {
        if (node == null) return null;
        if (node.value.compareTo(element) > 0){
            node.left = remove(element,node.left);
            return node;
        }else if (node.value.compareTo(element) < 0){
            node.right = remove(element,node.right);
            return node;
        }else {
            if (node.right == null){
                Node<T> left = node.left;
                node.left = null;
                size--;
                return left;
            }

            T newValue = minimum(node.right).value;
            removeMin(node.right);
            Node<T> newNode = new Node<>(newValue);
            newNode.left = node.left;
            newNode.right = node.right;
            node.left = null;
            node.right = null;
            return newNode;

        }
    }

    public static void main(String[] args) {
        BinarySearchTree<Integer> binarySearchTree = new BinarySearchTree<>();
        int[] array = {9, 7, 3, 2, 4, 8, 18, 15, 14, 16, 40, 22, 19, 24, 45, 43, 49};
        for (int i: array){
            binarySearchTree.add(i);
        }
        binarySearchTree.inOrder();
        binarySearchTree.remove(18);
        binarySearchTree.inOrder();
        binarySearchTree.removeMax();
        binarySearchTree.removeMin();
        binarySearchTree.inOrder();
        System.out.println(binarySearchTree.contains(19));
    }
}
