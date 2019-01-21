package structure.kotlin.queue

import structure.Queue
import structure.java.queue.ArrayQueue
import structure.kotlin.ArrayKt

class ArrayQueue<E> constructor(capacity: Int = 10) : Queue<E> {
    private val array = ArrayKt<E>(capacity)
    override fun enQueue(element: E) {
        array.append(element)
    }

    override fun deQueue(): E = array.unshift()

    override fun getFront(): E = array[0]

    override fun getSize(): Int = array.size

    override fun isEmpty(): Boolean = array.isEmpty

    override fun toString(): String {
        return array.toString()
    }
}

fun main(args: kotlin.Array<String>) {
    val queue = ArrayQueue<Int>()
    for(i in 1..1000000){
        queue.enQueue(i)
    }
    println(queue)
    for (i in 1..999999) queue.deQueue()
    println(queue)

    val queueJv = ArrayQueue<Int>()
    for( i in 1..1000000){
        queueJv.enQueue(i)
    }
    println(queueJv)
    for (i in 1..999999) queueJv.deQueue()
    println(queueJv)
}