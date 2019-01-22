package structure.test.QueueTest

import structure.Queue
import structure.kotlin.queue.BSTPriorityQueue
import structure.kotlin.queue.LinerPriorityQueue
import structure.kotlin.queue.LinerSortedPriorityQueue
import structure.kotlin.queue.MaxHeapPriorityQueue
import structure.timeTest
import kotlin.random.Random

fun main(args: Array<String>) {
    val queue3 = BSTPriorityQueue<Int>()
    val queue4 = MaxHeapPriorityQueue<Int>()
    val queue1 = LinerPriorityQueue<Int>()
    val queue2 = LinerSortedPriorityQueue<Int>()
    val n = 10000000
    queueTest(queue3,n,"BSTPriorityQueue")
    queueTest(queue4,n,"MaxHeapPriorityQueue")
    queueTest(queue1,n,"LinerPriorityQueue")
    queueTest(queue2,n,"LinerSortedPriorityQueue")
}

fun queueTest(queue: Queue<Int>, n: Int, name: String) {
    val random = Random(System.nanoTime())
    println( "$name : " + timeTest(1) {
        for (i in 0..n) {
            queue.enQueue(random.nextInt())
        }
        while (!queue.isEmpty()) {
            queue.deQueue()
        }
    })
}