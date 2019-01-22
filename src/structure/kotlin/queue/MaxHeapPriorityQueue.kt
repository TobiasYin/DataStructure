package structure.kotlin.queue

import structure.Queue
import structure.kotlin.BinaryMaxHeap

class MaxHeapPriorityQueue<T :Comparable<T>>:Queue<T> {
    val data = BinaryMaxHeap<T>()
    override fun enQueue(element: T) = data.add(element)

    override fun deQueue(): T = data.extractMax()

    override fun getFront(): T = data.findMax()

    override fun getSize(): Int = data.size

    override fun isEmpty(): Boolean = data.empty
}