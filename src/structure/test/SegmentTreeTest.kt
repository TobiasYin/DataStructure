package structure.test

import structure.kotlin.SegmentTree

fun main(args: Array<String>) {
    val ints = arrayOf(1, 57, 4, 34, 6, 2, 4, 7, 3, 7, 8, -3, 12, -6)
    val tree = SegmentTree<Int>(ints) { a, b -> a + b }
//    println(tree)
    tree[9] = 534
    ints[9] = 534
    if (tree.query(2,8) == ints.sliceArray(2..8).sum())
        println(true)
    else
        println(false)
}