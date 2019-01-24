package structure.test.UnionFindTest

import structure.UnionFind
import structure.kotlin.union.find.*
import structure.timeTest
import kotlin.random.Random

fun main(args: Array<String>) {
    val size = 10000000
    val m = 100000000
    val un1 = UnionFind1(size)
    val un2 = UnionFind2(size)
    val un3 = UnionFind3(size)
    val un4 = UnionFind4(size)
    val un5 = UnionFind5(size)
    val un6 = UnionFind6(size)
//    println("UnionFind1 takes : ${unionFindTest(un1,m)}s")
//    println("UnionFind2 takes : ${unionFindTest(un2,m)}s")
//    println("UnionFind3 takes : ${unionFindTest(un3,m)}s")
//    println("UnionFind4 takes : ${unionFindTest(un4,m)}s")
    println("UnionFind5 takes : ${unionFindTest(un5,m)}s")
    println("UnionFind6 takes : ${unionFindTest(un6,m)}s")

}

fun unionFindTest(un: UnionFind, m: Int):Double {
    val random = Random
    val size = un.getSize()
    return timeTest(1) {
        for (i in 0..m){
            un.unionElements(random.nextInt(size),random.nextInt(size))
        }
        for (i in 0..m){
            un.isConnected(random.nextInt(size),random.nextInt(size))
        }
    }
}