package structure.kotlin.stack

import structure.Stack
import structure.kotlin.LinkedList

class LinkedStack<T> : Stack<T> {
    private val data = LinkedList<T>()
    val empty
        get() = data.size == 0

    override fun push(element: T) = data.addFirst(element)
    override fun pop(): T = data.removeFirst()
    override fun getSize(): Int {
        return data.size
    }

    override fun isEmpty(): Boolean {
        return empty
    }

    override fun peek() = data.getFirst()
}