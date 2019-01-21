package structure.test.SetTest
import structure.kotlin.set.BSTSet
import structure.kotlin.set.LinkedListSet


fun main(args: Array<String>) {
    val bstSet = BSTSet<Int>()
    val linkedListSet = LinkedListSet<Int>()
    linkedListSet.add(3)
    linkedListSet.add(4)
    linkedListSet.add(5)
    linkedListSet.add(6)
    println(linkedListSet.getSize())
    linkedListSet.add(5)
    println(linkedListSet.getSize())
    linkedListSet.remove(4)
    println(linkedListSet.getSize())
}