package structure.kotlin.queue

import structure.Queue
import structure.java.BinarySearchTree

class BSTPriorityQueue<T :Comparable<T>> : Queue<T> {
    private  val tree = BinarySearchTree<T>()
    override fun enQueue(element: T) {
        tree.add(element)
    }

    override fun deQueue(): T {
        if(isEmpty()) throw IndexOutOfBoundsException()
        return tree.removeMax()
    }

    override fun getFront(): T {
        if(isEmpty()) throw IndexOutOfBoundsException()
        return tree.maximum()
    }

    override fun getSize(): Int = tree.size

    override fun isEmpty(): Boolean = tree.isEmpty
}