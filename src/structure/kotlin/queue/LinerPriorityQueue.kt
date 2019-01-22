package structure.kotlin.queue

import structure.Queue
import structure.kotlin.LoopLinkedList

class LinerPriorityQueue<T :Comparable<T>> : Queue<T> {

    private val arr = LoopLinkedList<T>()

    override fun enQueue(element: T) {
        arr.add(element)
    }

    override fun deQueue(): T {
        if (isEmpty()) {
            throw IndexOutOfBoundsException()
        }
        var index = 0
        var max = arr[0]
        for (i in 0 until getSize()){
            val item = arr[i]
            if (item > max){
                max = item
                index = i
            }
        }
        arr.remove(index)
        return max
    }

    override fun getFront(): T {
        if (isEmpty()) {
            throw IndexOutOfBoundsException()
        }
        var max = arr[0]
        for (i in 0 until getSize()){
            val item = arr[i]
            if (item > max){
                max = item
            }
        }
        return max
    }

    override fun getSize(): Int = arr.size

    override fun isEmpty(): Boolean = arr.size == 0
}