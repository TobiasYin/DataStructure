package structure;

public interface Stack<E> {
    int getSize();
    boolean isEmpty();
    void push(E element);
    E pop();
    E peek();
}
