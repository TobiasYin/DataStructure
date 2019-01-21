package structure.java.queue;

import structure.Queue;
import structure.java.ArrayList;

public class ArrayQueue<E> implements Queue<E> {
    private ArrayList<E> array;
    public ArrayQueue(){
        array = new ArrayList<>();
    }

    public ArrayQueue(int capacity){
        array = new ArrayList<>(capacity);
    }

    @Override
    public void enQueue(E element) {
        array.append(element);
    }

    @Override
    public E deQueue() {
        return array.unshift();
    }

    @Override
    public E getFront() {
        return array.getFirst();
    }

    @Override
    public int getSize() {
        return array.getSize();
    }

    @Override
    public boolean isEmpty() {
        return array.isEmpty();
    }

    @Override
    public String toString() {
        return array.toString();
    }
}
