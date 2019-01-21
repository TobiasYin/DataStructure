package structure.kotlin.stack

import structure.Stack
import kotlin.Array

class Stack<E> : Stack<E> {
    override fun isEmpty(): Boolean {
        return empty
    }

    private var data = arrayOfNulls<Any>(10) as Array<E>
    private var size = 0
    val empty: Boolean
        get() = size == 0

    override fun getSize(): Int = size

    private fun resize(length: Int) {
        val newData = arrayOfNulls<Any>(length) as Array<E>
        for (i in 0 until size) {
            newData[i] = data[i]
        }
        data = newData
    }

    override fun push(element: E) {
        if (size == data.size) resize(size * 2)
        data[size++] = element
    }

    override fun pop(): E {
        val temp = data[--size]
        if (size < data.size / 4 && data.size / 2 != 0) resize(data.size / 2)
        return temp
    }

    override fun peek(): E = data[size - 1]
}