package structure.java.stack;

public class Stack<E> implements structure.Stack<E> {
    private E[] data;
    private int size;
    public Stack(){
        data = (E[]) new Object[10];
    }

    public boolean isEmpty(){
        return size==0;
    }


    private void resize(int length){
        E[] newData = (E[]) new Object[length];
        for(int i = 0;i<size;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public int getSize() {
        return size;
    }

    @Override
    public void push(E element) {
        if(size==data.length) resize(size*2);
        data[size] = element;
        size++;
    }

    public E pop(){
        E temp = data[size-1];
        size--;
        data[size]=null;
        if(size<data.length/4 && data.length/2!=0) resize(data.length/2);
        return temp;
    }

    public E peek(){
        return data[size-1];
    }
}
