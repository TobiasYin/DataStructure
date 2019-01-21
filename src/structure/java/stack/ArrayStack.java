package structure.java.stack;

import structure.Stack;
import structure.java.ArrayList;

public class ArrayStack<E> implements Stack<E> {
    private ArrayList<E> array;

    public ArrayStack(int capacity){
        array = new ArrayList<>(capacity);
    }

    public ArrayStack() {
        array = new ArrayList<>();
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
    public void push(E element) {
        array.append(element);
    }

    @Override
    public E pop() {
        return array.pop();
    }

    @Override
    public E peek() {
        return array.getLast();
    }

    public int getCapacity(){
        return array.getCapacity();
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("stack: ");
        stringBuilder.append('[');
        for (int i = 0; i < getSize(); i++) {
            stringBuilder.append(array.get(i));
            if (i != getSize() - 1)
                stringBuilder.append(", ");
        }
        stringBuilder.append("] top");
        return stringBuilder.toString();
    }
}
