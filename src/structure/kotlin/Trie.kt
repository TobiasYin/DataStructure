package structure.kotlin

import structure.kotlin.stack.Stack


class Trie {
    private class Node(var isWord: Boolean = false) {
        val next = hashMapOf<Char, Node>()
    }

    var size = 0
        private set
    private val root = Node()

    fun add(word: String) {
        var now = root
        for (i in word) {
            if (!now.next.containsKey(i))
                now.next[i] = Node()
            now = now.next[i]!!
        }
        if (!now.isWord) {
            now.isWord = true
            size++
        }
    }

    fun contains(word: String): Boolean {
        var now = root
        for (i in word) {
            if (!now.next.containsKey(i))
                return false
            now = now.next[i]!!
        }
        return now.isWord
    }


    fun isPrefix(pre: String): Boolean {
        var now = root
        for (i in pre) {
            if (!now.next.containsKey(i))
                return false
            now = now.next[i]!!
        }
        return true
    }

    fun remove(word: String) {
        var now = root
        val stack = Stack<Pair<Node, Char>>()
        for (i in word) {
            if (!now.next.containsKey(i))
                return
            now = now.next[i]!!
            stack.push(now to i)
        }
        if (now.next.isNotEmpty()) {
            now.isWord = false
            size--
        } else {
            stack.pop()
            while (!stack.empty) {
                val temp = stack.pop()
                temp.first.next.remove(temp.second)
            }
            size--
        }
    }
}