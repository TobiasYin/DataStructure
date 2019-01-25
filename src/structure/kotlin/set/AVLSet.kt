package structure.kotlin.set

import structure.Set
import structure.kotlin.AVLTree

class AVLSet<T : Comparable<T>> : Set<T> {
    private val set = AVLTree<T, Int>()
    override fun isEmpty(): Boolean = set.empty

    override fun add(element: T) = set.add(element)

    override fun remove(element: T) = set.remove(element)

    override fun contains(element: T): Boolean = set.isContains(element)

    override fun getSize(): Int = set.size
}