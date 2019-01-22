package structure.test.BinaryMaxHeapTest

import structure.kotlin.BinaryMaxHeap
import structure.kotlin.BinaryMinHeap
import structure.range
import java.lang.IllegalArgumentException
import kotlin.random.Random

fun main(args: Array<String>) {
    val heap = BinaryMinHeap<Int>()
    val random = Random(System.nanoTime())
    val n = 1000000
    for (i in range(0,n)){
        heap.add(random.nextInt())
    }
    val arr = arrayListOf<Int>()
    for (i in range(0,n)){
        arr.add(heap.extractMax())
    }
    for (i in 1 until n){
        if(arr[i-1] > arr[i]){
            throw  IllegalArgumentException()
        }
    }
}