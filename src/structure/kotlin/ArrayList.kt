package structure.kotlin

import kotlin.Array
class ArrayList<E> @JvmOverloads constructor(capacity: Int = 10) {
    private var data: Array<E>
    var size: Int = 0
        private set

    val capacity: Int
        get() = data.size

    val isEmpty: Boolean
        get() = size == 0

    init {
        data = arrayOfNulls<Any>(capacity) as Array<E>
        size = 0
    }

    private fun resize(length:Int){
        val newData = arrayOfNulls<Any>(length) as Array<E>
        for(i in 0 until size){
            newData[i] = data[i]
        }
        data = newData
    }

    fun append(element: E) {
        //        if (size == data.length)
        //            throw new IllegalArgumentException("Append failed .ArrayList is full.");
        //        data[size] = element;
        //        size++;
        insert(size, element)
    }


    fun insert(index: Int, element: E) {
        if (size == data.size)
            resize(size*2)
        if (index < 0 || index > size)
            throw IllegalArgumentException("Append failed .Require index>=0 and index <= size")
        for (i in size - 1 downTo index)
            data[i + 1] = data[i]
        data[index] = element
        size++
    }

    fun shift(element: E) {
        insert(0, element)
    }

    operator fun get(index: Int): E {
        if (index < 0 || index > size - 1)
            throw IllegalArgumentException("Get Failed. Index Out Bounded.")
        return data[index]
    }

    fun updata(index: Int, element: E) {
        if (index < 0 || index > size)
            throw IllegalArgumentException("Get Failed. Index Out Bounded.")
        data[index] = element
    }

    fun remove(index: Int): E {
        if (index < 0 || index >= size)
            throw IllegalArgumentException("Get Failed. Index Out Bounded.")
        val result = data[index]
        for (i in index until size - 1) {
            data[i] = data[i + 1]
        }
        size--
        if (size<data.size/4 && data.size / 2 != 0){
            resize(data.size/2)
        }
        return result
    }

    fun unshift(): E {
        return remove(0)
    }

    fun pop(): E {
        return data[--size]
    }

    fun find(element: E): Int {
        for (i in 0 until size) {
            if (data[i] == element) {
                return i
            }
        }
        return -1
    }

    fun isContain(element: E): Boolean {
        for (i in 0 until size) {
            if (data[i] == element) {
                return true
            }
        }
        return false
    }

    fun removeByValue(element: E) {
        val index = find(element)
        if (index == -1)
            throw IllegalArgumentException("Item do not find")
        remove(index)
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(String.format("ArrayList: size = %d,capacity : %d\n", size, data.size))
        stringBuilder.append('[')
        for (i in 0 until size) {
            stringBuilder.append(data[i])
            if (i != size - 1)
                stringBuilder.append(", ")
        }
        stringBuilder.append(']')
        return stringBuilder.toString()
    }

    fun getFirst():E = get(0)
    fun getLast():E = get(size-1)

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


fun main(args: Array<String>) {
    val array = ArrayList<Int>()
    for (i in 1..20){
        array.append(i)
    }
    for (i in 1..20){
        array.remove(0)
    }
}