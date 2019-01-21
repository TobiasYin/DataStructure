package structure.kotlin.stack

import structure.Stack
import structure.java.stack.ArrayStack
import structure.kotlin.ArrayList

class ArrayStack<E> constructor(capacity: Int = 10) : Stack<E> {
    private val array: ArrayList<E> = ArrayList(capacity)
    val capacity: Int
        get() = array.capacity

    override fun getSize(): Int = array.size

    override fun isEmpty(): Boolean = array.isEmpty

    override fun push(element: E) = array.append(element)

    override fun pop(): E = array.pop()

    override fun peek(): E = array.getLast()

    override fun toString(): String {
        val stringBuilder = StringBuilder()
        stringBuilder.append("stack: ")
        stringBuilder.append('[')
        for (i in 0 until getSize()) {
            stringBuilder.append(array[i])
            if (i != getSize() - 1)
                stringBuilder.append(", ")
        }
        stringBuilder.append("] top")
        return stringBuilder.toString()
    }
}


// Test
fun main(args: kotlin.Array<String>) {
    val stack = structure.kotlin.stack.ArrayStack<Int>()
    for(i in 1..5){
        stack.push(i)
    }
    println(stack)
    println(stack.pop())
    println(stack)

    val stackJava = ArrayStack<Int>()
    for(i in 1..5){
        stackJava.push(i)
    }
    println(stackJava)
    println(stackJava.pop())
    println(stackJava)
}