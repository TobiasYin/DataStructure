package structure.kotlin.queue

import structure.Queue
import kotlin.Array

class LoopQueue<E> constructor(capacity: Int = 10) : Queue<E> {
    var data: kotlin.Array<E> = arrayOfNulls<Any>(capacity + 1) as Array<E>
    private var front = 0
    var tail = 0
    private var size = 0
    val capacity: Int
        get() = data.size - 1

    override fun getSize(): Int = size

    override fun isEmpty(): Boolean = front == tail

    private fun resize(length: Int) {
        val newData = arrayOfNulls<Any>(length + 1) as Array<E>
        for (i in 0 until size) {
            newData[i] = data[(front + i) % data.size]
        }
        data = newData
        front = 0
        tail = size
    }


    override fun enQueue(element: E) {
        if ((tail + 1) % data.size == front) resize(capacity * 2)
        data[tail] = element
        tail = (tail + 1) % data.size
        size++
    }


    override fun deQueue(): E {
        if (isEmpty())
            throw IllegalArgumentException("structure.queue is empty")
        val temp = data[front]
        front = (front + 1) % data.size
        size--
        if (size <= capacity / 4 && capacity / 2 != 0) resize(capacity / 2)
        return temp
    }


    override fun getFront(): E {
        if (isEmpty())
            throw IllegalArgumentException("structure.queue is empty")
        return data[front]
    }

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append(String.format("structure.queue: size : %d,capacity : %d\n", size, capacity))
        stringBuilder.append("front [")
        var i = front
        while (i != tail) {
            stringBuilder.append(data[i])
            if ((i + 1) % data.size != tail)
                stringBuilder.append(", ")
            i = (i + 1) % data.size
        }
        stringBuilder.append("] tail")
        return stringBuilder.toString()
    }
}

fun main(args: Array<String>) {
    val queue = LoopQueue<Int>()
    for (i in 0..10000) {
        queue.enQueue(i)
    }

    for (i in 0..10000)
        queue.deQueue()
    println(queue.tail)
    println(queue.data.size)
    queue.enQueue(1)
}
