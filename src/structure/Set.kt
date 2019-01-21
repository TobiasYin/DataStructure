package structure

interface Set<E> {
    fun isEmpty(): Boolean
    fun add(element: E)
    fun remove(element: E)
    fun contains(element: E): Boolean
    fun getSize(): Int
}