package structure.test

import structure.kotlin.queue.ArrayQueue
import structure.kotlin.queue.LinkedQueue
import structure.kotlin.stack.LinkedStack
import structure.Stack
import structure.timeTest


fun main(args: kotlin.Array<String>) {
    val linkedStack = LinkedStack<Int>()
    val stack = structure.kotlin.stack.Stack<Int>()
    fun test(stack: Stack<Int>) {
        for (i in 0..100000) {
            stack.push(i)
        }
        for (i in 0..100000) {
            stack.pop()
        }
    }
    println(timeTest(10) { test(linkedStack) })
    println(timeTest(10) { test(stack) })
    val queue = structure.java.queue.LoopQueue<Int>()
    println(timeTest(10000) {
        for (i in 0..100000) {
            queue.enQueue(i)
        }
        for (i in 0..100000) {
            queue.deQueue()
        }
    })
    val lqueue = structure.kotlin.queue.LoopQueue<Int>()
    println(timeTest(10000) {
        for (i in 0..100000) {
            lqueue.enQueue(i)
        }
        for (i in 0..100000) {
            lqueue.deQueue()
        }
    })
    val linkedQueue = LinkedQueue<Int>()
    println(timeTest(10000) {
        for (i in 0..100000) {
            linkedQueue.push(i)
        }
        for (i in 0..100000) {
            linkedQueue.pop()
        }
    })
    val arrayQueue = ArrayQueue<Int>()
    println(timeTest(10) {
        for (i in 0..100000) {
            arrayQueue.enQueue(i)
        }
        for (i in 0..100000) {
            arrayQueue.deQueue()
        }
    })
}