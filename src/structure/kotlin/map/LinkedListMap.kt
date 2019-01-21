package structure.kotlin.map

import structure.Map

class LinkedListMap<K, V> : Map<K, V> {
    private class Node<K, V>(val key: K?, var value: V?, var next: Node<K, V>? = null) {
        override fun toString(): String {
            return key.toString() + ": " + value.toString()
        }
    }

    private val dummyHead = Node<K, V>(null, null)
    private var size = 0
    val empty: Boolean
        get() = size == 0

    private fun getNode(key: K): Node<K, V> {
        var pre = dummyHead
        while (pre.next != null) {
            pre = pre.next!!
            if (pre.key == key) {
                return pre
            }
        }
        return dummyHead
    }

    override fun add(key: K, value: V) {
        val node = getNode(key)
        if (node == dummyHead) {
            val now = dummyHead.next
            dummyHead.next = Node(key, value)
            dummyHead.next!!.next = now
            size++
        } else {
            node.value = value
        }
    }

    override fun remove(key: K): V? {
        var pre: Node<K, V>? = dummyHead
        while (pre!!.next != null) {
            if (pre.next!!.key == key) {
                size--
                break
            }
            pre = pre.next!!
        }
        if (pre.next == null)
            return null
        val res = pre.next!!.value
        pre.next = pre.next!!.next
        return res
    }

    override fun contains(key: K): Boolean {
        return getNode(key) != dummyHead
    }

    override operator fun get(key: K): V? {
        return getNode(key).value
    }

    override operator fun set(key: K, value: V) {
        val node = getNode(key)
        if (node != dummyHead)
            node.value = value
        else add(key, value)
    }

    override fun getSize(): Int = size

    override fun isEmpty(): Boolean = empty

}