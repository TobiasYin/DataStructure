package structure.kotlin

import kotlin.IndexOutOfBoundsException

class LinkedList<T>(vararg data: T) {
    private var dummyHead: Node<T> = Node(null, null)
    private var head: Node<T>?
        get() = dummyHead.next
        set(value) {
            dummyHead.next = value
        }
    var size: Int = data.size
        private set
    val empty
        get() = size == 0

    init {
        if (size != 0) {
            dummyHead.next = Node(data[0])
            var now = head
            for (i in 1 until data.size) {
                now!!.next = Node(data[i])
                now = now.next
            }
        }
    }

    private class Node<T>(var element: T?, var next: Node<T>? = null) {
        override fun toString(): String {
            return element.toString()
        }
    }

    fun addFirst(e: T) {
        head = Node(e, head)
        size++
    }

    fun addLast(e: T) {
        add(e)
    }

    fun add(element: T, index: Int = size) {
        if (index < 0 || index > size)
            throw IndexOutOfBoundsException()
        var now: Node<T> = dummyHead
        for (i in 0 until index)
            now = now.next!!
        now.next = Node(element, now.next)
        size++
    }

    operator fun get(index: Int): T = getNode(index).element!!

    private fun getNode(index: Int): Node<T> {
        return getPre(index).next!!
    }

    fun getFirst() = get(0)
    fun getLast() = get(size - 1)

    private fun getPre(index: Int): Node<T> {
        if (index < 0 || index >= size)
            throw IndexOutOfBoundsException()
        var now: Node<T> = dummyHead
        for (i in 0 until index)
            now = now.next!!
        return now
    }

    operator fun set(index: Int, element: T) {
        val now = getNode(index)
        now.element = element
    }

    fun contains(element: T): Boolean {
        if (empty) return false
        var now = head
        while (now != null) {
            if (now.element!! == element) {
                return true
            }
            now = now.next
        }
        return false
    }

    fun remove(index: Int): T {
        val pre = getPre(index)
        val delNode = pre.next!!
        pre.next = pre.next!!.next
        delNode.next = null
        size--
        return delNode.element!!
    }

    fun removeLast() = remove(size - 1)
    fun removeFirst() = remove(0)

    override fun toString(): String {
        val result = arrayListOf<T>()
        if (empty)
            return "LinkedNode[]"
        var now = head
        while (now != null) {
            result.add(now.element!!)
            now = now.next
        }
        return result.joinToString(" -> ", "LinkedNode[ ", " ]")
    }

}

fun main(args: kotlin.Array<String>) {
    val linkedListKt = LinkedList<Int>(1, 2, 3, 4, 5, 6)
    linkedListKt.add(0,3)
    linkedListKt[3] = 5
    println(linkedListKt.remove(4))
    println(linkedListKt.contains(21))
    println(linkedListKt[3])
    println(linkedListKt)
    linkedListKt.removeFirst()
    println(linkedListKt)
}