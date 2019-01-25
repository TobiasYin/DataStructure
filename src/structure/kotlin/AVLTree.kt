package structure.kotlin

import kotlin.math.abs
import kotlin.math.max

class AVLTree<K : Comparable<K>, V> {
    private class Node<K, V>(
        val key: K,
        var value: V? = null,
        var leftChild: Node<K, V>? = null,
        var rightChild: Node<K, V>? = null
    ) {
        var height = 1
    }

    private var root: Node<K, V>? = null
    var size = 0
        private set
    val empty
        get() = size == 0

    private fun getHeight(node: Node<K, V>?): Int = node?.height ?: 0

    private fun getBalanceFactor(node: Node<K, V>?): Int = getHeight(node?.leftChild) - getHeight(node?.rightChild)

    fun isBST(): Boolean = isBST(root)

    private fun isBST(node: Node<K, V>?): Boolean = when {
        node == null -> true
        node.leftChild != null && node.rightChild != null && node.leftChild!!.key > node.rightChild!!.key -> false
        else -> isBST(node.leftChild) && isBST(node.rightChild)
    }

    fun isBalanced(): Boolean = isBalanced(root)

    private fun isBalanced(node: Node<K, V>?): Boolean = when {
        node == null -> true
        abs(getBalanceFactor(node)) > 1 -> false
        else -> isBalanced(node.leftChild) && isBalanced(node.rightChild)
    }

    private fun rightRotate(node: Node<K, V>): Node<K, V> {
        val left = node.leftChild!!
        val right = left.rightChild
        node.leftChild = right
        left.rightChild = node
        node.height = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild))
        left.height = 1 + max(getHeight(left.leftChild), getHeight(left.rightChild))
        return left
    }

    private fun leftRotate(node: Node<K, V>): Node<K, V> {
        val right = node.rightChild!!
        val left = right.leftChild
        node.rightChild = left
        right.leftChild = node
        node.height = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild))
        right.height = 1 + max(getHeight(right.leftChild), getHeight(right.rightChild))
        return right
    }

    fun add(key: K, value: V? = null) {
        root = add(root, key, value)
    }

    private fun add(node: Node<K, V>?, key: K, value: V?): Node<K, V> {
        if (node == null) {
            size++
            return Node(key, value)
        }
        when {
            node.key > key -> node.leftChild = add(node.leftChild, key, value)
            node.key < key -> node.rightChild = add(node.rightChild, key, value)
            else -> node.value = value
        }
        val originHeight = node.height
        node.height = 1 + max(getHeight(node.leftChild), getHeight(node.rightChild))
        if (node.height == originHeight) return node
        val balanceFactor = getBalanceFactor(node)
        return when {
            balanceFactor > 1 && getBalanceFactor(node.leftChild) >= 0 -> rightRotate(node)
            balanceFactor < -1 && getBalanceFactor(node.rightChild) <= 0 -> leftRotate(node)
            balanceFactor > 1 && getBalanceFactor(node.leftChild) < 0 -> {
                node.leftChild = leftRotate(node.leftChild!!)
                rightRotate(node)
            }
            balanceFactor < -1 && getBalanceFactor(node.rightChild) > 0 -> {
                node.rightChild = rightRotate(node.rightChild!!)
                leftRotate(node)
            }
            else -> node
        }
    }

    fun isContains(key: K): Boolean = isContains(root, key)

    private fun isContains(node: Node<K, V>?, key: K): Boolean {
        if (node == null)
            return false
        return when {
            node.key == key -> true
            node.key > key -> isContains(node.leftChild, key)
            else -> isContains(node.rightChild, key)
        }
    }

    operator fun get(key: K): V? = get(root, key)

    private fun get(node: Node<K, V>?, key: K): V? {
        if (node == null)
            return null
        return when {
            node.key == key -> return node.value
            node.key > key -> get(node.leftChild, key)
            else -> get(node.rightChild, key)
        }
    }

    operator fun set(key: K, value: V) = set(root, key, value)

    private fun set(node: Node<K, V>?, key: K, value: V) {
        if (node == null) {
            add(key, value)
            return
        }
        return when {
            node.key == key -> node.value = value
            node.key > key -> set(node.leftChild, key, value)
            else -> set(node.rightChild, key, value)
        }
    }

    fun inOrder() {
        inOrder(root)
        println()
    }

    private fun inOrder(node: Node<K, V>?) {
        if (node == null)
            return
        inOrder(node.leftChild)
//        println("key: ${node.key}, value: ${node.value}")
        print("" + node.key + " ")
        inOrder(node.rightChild)
    }

    fun preOrder() {
        preOrder(root)
        println()
    }

    private fun preOrder(node: Node<K, V>?) {
        if (node == null)
            return
        print("" + node.key + " ")
        preOrder(node.leftChild)
        preOrder(node.rightChild)
    }

    fun minimum(): K? = if (empty) null else minimum(root!!).key

    private fun minimum(node: Node<K, V>): Node<K, V> = if (node.leftChild == null) node else minimum(node.leftChild!!)

    fun maximum(): K? = if (empty) null else maximum(root!!).key

    private fun maximum(node: Node<K, V>): Node<K, V> =
        if (node.rightChild == null) node else maximum(node.rightChild!!)

    fun removeMax(): K {
        if (size == 0) throw IndexOutOfBoundsException()
        val max = maximum()!!
        root = remove(root,max)
        return max
    }

    fun removeMin(): K {
        if (size == 0) throw IndexOutOfBoundsException()
        val min = minimum()!!
        root = remove(root, min)
        return min
    }

    fun remove(key: K) {
        root = remove(root, key)
    }

    private fun remove(node: Node<K, V>?, key: K): Node<K, V>? {
        if (node == null)
            return null
        val retNode = when {
            node.key == key && node.rightChild == null -> {
                val left = node.leftChild
                node.leftChild = null
                size--
                if (left == null) return null
                left
            }
            node.key > key -> {
                node.leftChild = remove(node.leftChild, key)
                node
            }
            node.key < key -> {
                node.rightChild = remove(node.rightChild, key)
                node
            }
            else -> {
                val min = minimum(node.rightChild!!)
                min.rightChild = remove(node.rightChild, min.key)
                min.leftChild = node.leftChild
                node.leftChild = null
                node.rightChild = null
                min
            }
        }
        retNode.height = 1 + max(getHeight(retNode.leftChild), getHeight(retNode.rightChild))
        val balanceFactor = getBalanceFactor(retNode)
        return when {
            balanceFactor > 1 && getBalanceFactor(
                retNode.leftChild
            ) >= 0 -> rightRotate(retNode)
            balanceFactor < -1 && getBalanceFactor(retNode.rightChild) <= 0 -> leftRotate(retNode)
            balanceFactor > 1 && getBalanceFactor(retNode.leftChild) < 0 -> {
                retNode.leftChild = leftRotate(retNode.leftChild!!)
                rightRotate(retNode)
            }
            balanceFactor < -1 && getBalanceFactor(retNode.rightChild) > 0 -> {
                retNode.rightChild = rightRotate(retNode.rightChild!!)
                leftRotate(retNode)
            }
            else -> retNode
        }
    }
}

fun main(args: Array<String>) {
    val binarySearchTree = AVLTree<Int, Int>()
    val array = intArrayOf(1, 20, 9, 7, 3, 2, 4, 8, 18, 15, 14, 16, 40, 22, 19, 24, 45, 43, 49)
    for (i in array) {
        binarySearchTree.add(i)
    }
    println(binarySearchTree.isBalanced())
    binarySearchTree.preOrder()
    binarySearchTree.inOrder()
    binarySearchTree.remove(18)
    binarySearchTree.remove(1)
    binarySearchTree.remove(15)
    binarySearchTree.remove(22)
    binarySearchTree.remove(19)
    binarySearchTree.remove(20)
    binarySearchTree.inOrder()
    binarySearchTree.removeMax()
    binarySearchTree.removeMin()
    binarySearchTree.inOrder()
    println(binarySearchTree.isContains(19))
    println(binarySearchTree.isBST())
    binarySearchTree.preOrder()
    println(binarySearchTree.isBalanced())
}