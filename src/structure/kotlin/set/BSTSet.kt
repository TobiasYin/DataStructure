package structure.kotlin.set

import structure.java.BinarySearchTree
import structure.Set

class BSTSet<T : Comparable<T>> :Set<T>{
   private val tree = BinarySearchTree<T>()

    override fun isEmpty(): Boolean = tree.isEmpty

    override fun add(element: T) = tree.add(element)

    override fun remove(element: T)  = tree.remove(element)

    override fun contains(element: T): Boolean = tree.contains(element)

    override fun getSize(): Int = tree.size
}