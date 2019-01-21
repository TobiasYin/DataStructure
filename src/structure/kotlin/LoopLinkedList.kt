package structure.kotlin

class LoopLinkedList<T>(vararg data: T) {
    private class Node<T>(var value: T? = null, var next: Node<T>? = null, var pre: Node<T>? = null) {
        override fun toString(): String {
            return "value: $value"
        }
    }

    private val dummyHead = Node<T>()
    var size = 0
        private set
    val empty = size == 0
    val first
        get() = dummyHead.next!!.value
    val last
        get() = dummyHead.pre!!.value

    init {
        dummyHead.next = dummyHead
        dummyHead.pre = dummyHead
        for (i in data) {
            addLast(i)
        }
    }

    private fun parseIndex(index: Int, flag: Boolean = true): Int {
        val result = if (index < 0) {
            size + index
        } else index
        if (result > size || (flag && result == size)) {
            throw IndexOutOfBoundsException()
        }
        return result
    }

    private inline fun getPreOrNext(
        absIndex: Int,
        func1: (pre: Node<T>) -> Node<T>?,
        func2: (next: Node<T>) -> Node<T>?
    ): Node<T>? {
        val node: Node<T>?
        if (absIndex <= (size + 1) / 2) {
            var pre = dummyHead
            for (i in 1..absIndex) {
                pre = pre.next!!
            }
            node = func1(pre)
        } else {
            var next = dummyHead
            for (i in 1..(size - absIndex - 1)) {
                next = next.pre!!
            }
            node = func2(next)
        }
        return node
    }

    private fun getNow(absIndex: Int): Node<T> =
        getPreOrNext(absIndex, { pre -> pre.next }, { next -> next.pre })!!

    fun add(element: T, index: Int = 0) {
        val absIndex = parseIndex(index, false)
        val pre = getPreOrNext(absIndex, { it }, {
            if (size == absIndex) {
                dummyHead.pre
            } else it.pre!!.pre
        })!!
        val now = pre.next!!
        val new = Node(element, now, pre)
        pre.next = new
        now.pre = new
        size++
    }

    fun addFirst(element: T) = add(element, 0)
    fun addLast(element: T) = add(element, size)

    operator fun get(index: Int): T {
        val absIndex = parseIndex(index)
        return getNow(absIndex).value!!
    }

    operator fun set(index: Int, element: T) {
        val absIndex = parseIndex(index)
        val node = getNow(absIndex)
        node.value = element

    }

    fun remove(index: Int = size - 1): T {
        val absIndex = parseIndex(index)
        val pre = getPreOrNext(absIndex, { it }, { it.pre!!.pre })!!
        val now = pre.next
        pre.next = pre.next!!.next
        pre.next!!.pre = pre
        size--
        return now!!.value!!
    }

    fun removeFirst() = remove(0)

    fun removeLast() = remove()

    fun removeAll() {
        for (i in 1..size) {
            remove()
        }
    }

    fun toList(): List<T> {
        val result = arrayListOf<T>()
        var pre = dummyHead.next!!
        for (i in 0 until size) {
            result.add(pre.value!!)
            pre = pre.next!!
        }
        return result
    }

    fun toReversedList(): List<T> {
        val result = arrayListOf<T>()
        var next = dummyHead.pre!!
        for (i in 0 until size) {
            result.add(next.value!!)
            next = next.pre!!
        }
        return result
    }

    fun link(list: LoopLinkedList<T>) {
        if (list.size == 0)
            return
        else if (list.size == 1) {
            addLast(list[0])
            return
        }
        val last = dummyHead.pre!!
        last.next = list.dummyHead.next
        list.dummyHead.next!!.pre = last
        dummyHead.pre = list.dummyHead.pre
        list.dummyHead.pre!!.next = dummyHead
        list.dummyHead.next = null
        list.dummyHead.pre = null
        size += list.size
        list.size = 0
    }

    fun append(element: T) = addLast(element)

    override fun toString(): String {
        val result = StringBuilder()
        result.append("Loop -> ")
        var pre = dummyHead.next!!
        for (i in 0 until size) {
            result.append(pre.value)
            result.append(" -> ")
            pre = pre.next!!
        }
        result.append(" Loop")
        return result.toString()
    }
}

fun main(args: kotlin.Array<String>) {
    val list0 = LoopLinkedList(1)
    list0.addLast(0)
    println(list0)
    val list = LoopLinkedList(1, 2, 3, 4, 5, 6, 7, 8)
    val list2 = LoopLinkedList(9, 8, 7, 6)
    list.link(list2)
    list.link(LoopLinkedList(11, 22, 33))
    list.link(LoopLinkedList(99, 88, 77))
//    println(list)
//    list.add(1)
//    list.add(4, 1)
//    list.add(2)
//    list.add(3, 2)
//    list.add(5, list.size)
//    list.add(6, -2)
    list.addLast(3)
    println(list)
//    println(list.remove(1))
//    println(list)
//    println(list.remove(3))
//    println(list)
//    println(list.remove(-1))
//    println(list[0])
//    println(list[1])
//    println(list[2])
//    println(list[3])
//    println(list[-1])
//    println(list[-2])
//    println(list)
    println(list.toList().joinToString(" "))
    println(list.toReversedList().joinToString(" "))
}