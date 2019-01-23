package structure.test

import structure.kotlin.Trie

fun main(args: Array<String>) {
    val trie = Trie()
    trie.add("apple")
    trie.add("bull")
    trie.add("cow")
    trie.add("app")
    println(trie.size)
    trie.remove("app")
    println(trie.size)
    println(trie.contains("apple"))
    println(trie.contains("app"))
    trie.remove("bull")
    println(trie.contains("bull"))
    println(trie.size)
}