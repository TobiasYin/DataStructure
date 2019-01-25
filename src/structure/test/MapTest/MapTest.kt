package structure.test.MapTest

import structure.kotlin.AVLTree
import structure.kotlin.BST
import structure.kotlin.map.BSTMap
import structure.kotlin.map.LinkedListMap



fun main(args: Array<String>) {
//    val map = LinkedListMap<Int,Int>()
//    map.add(1,2)
//    map.add(3,4)
//    map.add(2,3)
//    map[2] = 5
//    println(map[1])
//    println(map[2])

    println("Pride and Prejudice")

    val words = arrayListOf<String>()
    if (FileOperation.readFile("src/structure/test/MapTest/pride-and-prejudice.txt", words)) {
        System.out.println("Total words: " + words.size)

        val map = AVLTree<String, Int>()
        for (word in words) {
            if (map.isContains(word))
                map.set(word, map.get(word)!! + 1)
            else
                map.add(word, 1)
        }

        println("Total different words: " + map.size)
        println("Frequency of PRIDE: " + map.get("pride")!!)
        println("Frequency of PREJUDICE: " + map.get("prejudice")!!)
        println(map.isBalanced())
        println(map.isBST())
        for (word in words){
            map.remove(word)
//            if (!map.isBST() || !map.isBalanced())
//                throw IllegalArgumentException("Not right")
        }
    }

    println()
}