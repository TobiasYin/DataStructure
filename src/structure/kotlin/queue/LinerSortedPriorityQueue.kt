package structure.kotlin.queue

import structure.Queue
import structure.kotlin.LinkedList
import structure.kotlin.LoopLinkedList

class LinerSortedPriorityQueue<T : Comparable<T>> : Queue<T> {

    private val array = LoopLinkedList<T>()

    override fun enQueue(element: T) {
        if (isEmpty()) array.addFirst(element)
        else {
            for (i in 0 until array.size) {
                if (element > array[i]) {
                    array.add(element, i)
                    return
                }
            }
            array.addLast(element)
        }
    }

    override fun deQueue(): T {
        if (isEmpty()) throw IndexOutOfBoundsException()
        return array.remove(0)
    }

    override fun getFront(): T {
        if (isEmpty()) throw IndexOutOfBoundsException()
        return array[0]
    }

    override fun getSize(): Int = array.size

    override fun isEmpty(): Boolean = array.size == 0
}