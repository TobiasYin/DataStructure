package structure

interface Queue<E> {
    abstract fun enQueue(element:E)
    abstract fun deQueue() :E
    abstract fun getFront() :E
    abstract fun getSize() :Int
    abstract fun isEmpty() :Boolean
}