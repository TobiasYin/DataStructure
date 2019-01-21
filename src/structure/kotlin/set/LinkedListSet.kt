package structure.kotlin.set
import structure.Set
import structure.kotlin.LinkedList

class LinkedListSet<T>:Set<T>{
    private val list = LinkedList<T>()

    override fun isEmpty(): Boolean = list.empty

    override fun add(element: T) {
        if (!list.contains(element))
          list.addFirst(element)
    }

    override fun remove(element: T) = list.remove(element)

    override fun contains(element: T): Boolean = list.contains(element)

    override fun getSize(): Int = list.size

}