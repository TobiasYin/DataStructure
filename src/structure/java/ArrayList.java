package structure.java;

public class ArrayList<E> {
    private E[] data;
    private int size;

    public ArrayList(int capacity) {
        data = (E[])new Object[capacity];
        size = 0;
    }

    public ArrayList() {
        this(10);
    }

    public int getSize() {
        return size;
    }

    public int getCapacity() {
        return data.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    private void resize(int length){
        E[] newData = (E[]) new Object[length];
        for(int i = 0; i<size ;i++){
            newData[i] = data[i];
        }
        data = newData;
    }

    public void append(E element) {
//        if (size == data.length)
//            throw new IllegalArgumentException("Append failed .ArrayList is full.");
//        data[size] = element;
//        size++;
        insert(size, element);
    }

    public void insert(int index, E element) {
        if (size == data.length)
            resize(size*2);
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Append failed .Require index>=0 and index <= size");
        for (int i = size - 1; i >= index; i--)
            data[i + 1] = data[i];
        data[index] = element;
        size++;
    }

    public void shift(E element) {
        insert(0, element);
    }

    public E get(int index) {
        if (index < 0 || index > size - 1)
            throw new IllegalArgumentException("Get Failed. Index Out Bounded.");
        return data[index];
    }

    public void updata(int index, E element) {
        if (index < 0 || index > size)
            throw new IllegalArgumentException("Get Failed. Index Out Bounded.");
        data[index] = element;
    }

    public E remove(int index) {
        if (index < 0 || index >= size)
            throw new IllegalArgumentException("Get Failed. Index Out Bounded.");
        E result = data[index];
        for (int i = index; i < size - 1; i++) {
            data[i] = data[i + 1];
        }
        size--;
        if(size<data.length/4 && data.length/2!=0) resize(data.length/2);
        data[size]=null;
        return result;
    }

    public E unshift(){
        return remove(0);
    }

    public E pop() {
        return data[--size];
    }

    public int find(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    public boolean isContain(E element) {
        for (int i = 0; i < size; i++) {
            if (data[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    public void removeByValue(E element) {
        int index = find(element);
        if (index == -1)
            throw new IllegalArgumentException("Item do not find");
        remove(index);
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format("ArrayList: size = %d,capacity : %d\n", size, data.length));
        stringBuilder.append('[');
        for (int i = 0; i < size; i++) {
            stringBuilder.append(data[i]);
            if (i != size - 1)
                stringBuilder.append(", ");
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }

    public E getLast(){
        return get(size-1);
    }

    public E getFirst(){
        return get(0);
    }

    //    public static void structure.kotlin.main(String[] args) {
//        int[] arr = new int[10];
//        for (int i = 0; i < arr.length; i++) {
//            arr[i] = i;
//        }
//        int[] scores = new int[]{100, 99, 96};
//        for (int i = 0; i < scores.length; i++) {
//            System.out.println(scores[i]);
//        }
//        for (int score : scores) {
//            System.out.println(score);
//        }
//    }
}
