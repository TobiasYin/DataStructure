package structure.kotlin.queue

import kotlin.IndexOutOfBoundsException
import structure.Queue

class LinkedQueue<T>:Queue<T> {
    override fun enQueue(element: T) {
        push(element)
    }

    override fun deQueue(): T = pop()

    override fun getFront(): T{
        if (empty)
            throw IndexOutOfBoundsException()
        return head.element!!
    }

    override fun getSize(): Int = size

    override fun isEmpty(): Boolean = empty

    private class Node<T>(var element: T? = null, var next: Node<T>? = null, var pre: Node<T>? = null) {
        override fun toString(): String {
            return element.toString()
        }
    }

    private var head = Node<T>()
    private var last = head
    private var size = 0
    val empty
        get() = size == 0

    fun push(element: T) {
        if (size == 0) {
            head = Node(element)
            last = head
        } else {
            last.next = Node(element, pre = last)
            last = last.next as Node<T>
        }
        size++
    }

    fun pop(): T {
        if (empty)
            throw IndexOutOfBoundsException()
        val del = head
        head = if (size == 1) Node<T>() else del.next!!
        del.next = null
        size--
        return del.element!!
    }

    override fun toString(): String {
        val result = arrayListOf<T>()
        if (empty)
            return "queue[]"
        var now: Node<T>? = head
        while (now != null) {
            result.add(now.element!!)
            now = now.next
        }
        return result.joinToString(" <- ", "queue[ ", " ]")
    }
}

fun main(args: kotlin.Array<String>) {
    val queue = LinkedQueue<Int>()
    for (i in 1..5) {
        println(queue)
        queue.push(i)
    }
    for (i in 1..5) {
        println(queue)
        println(queue.pop())
    }
}