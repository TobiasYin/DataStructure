package structure.java.queue;

import structure.Queue;

public class LoopQueue<E> implements Queue<E> {
    private E[] data;
    private int front, tail;
    private int size;

    public LoopQueue(int capacity) {
        data = (E[]) new Object[capacity + 1];
    }

    public LoopQueue() {
        this(10);
    }

    public int getCapacity() {
        return data.length - 1;
    }

    private void resize(int length) {
        E[] newData = (E[]) new Object[length + 1];
        for (int i = 0; i < size; i++) {
            newData[i] = data[(front + i) % data.length];
        }
        data = newData;
        front = 0;
        tail = size;
    }

    @Override
    public boolean isEmpty() {
        return front == tail;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    public void enQueue(E element) {
        if ((tail + 1) % data.length == front) {
            resize(getCapacity() * 2);
        }
        data[tail] = element;
        tail = (tail + 1) % data.length;
        size++;
    }

    @Override
    public E deQueue() {
        if (isEmpty())
            throw new IllegalArgumentException("structure.queue is empty");
        E temp = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        size--;
        if (size <= getCapacity() / 4 && getCapacity() / 2 != 0) resize(getCapacity() / 2);
        return temp;
    }

    @Override
    public E getFront() {
        if (isEmpty())
            throw new IllegalArgumentException("structure.queue is empty");
        E temp = data[front];
        data[front] = null;
        front = (front + 1) % data.length;
        return temp;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("structure.queue: size : %d,capacity : %d\n", size, getCapacity()));
        stringBuilder.append("front [");
        for (int i = front; i !=tail ; i=(i+1)%data.length) {
            stringBuilder.append(data[i]);
            if ((i+1) % data.length!=tail)
                stringBuilder.append(", ");
        }
        stringBuilder.append("] tail");
        return stringBuilder.toString();
    }
}
