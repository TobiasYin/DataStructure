package structure.kotlin

import structure.java.stack.Stack
import structure.kotlin.queue.LinkedQueue

class BinarySearchTree<E : Comparable<E>> {
    private class Node<E>(var value: E, var leftChild: Node<E>? = null, var rightChild: Node<E>? = null)

    private var root: Node<E>? = null
    var size = 0
        private set
    val empty = size == 0

    fun add(element: E) {
        if (root == null) {
            root = Node(element)
        } else {
            var now = root!!
            while (true) {
                if (element > now.value) {
                    if (now.rightChild == null) {
                        now.rightChild = Node(element)
                        size++
                        break
                    }
                    now = now.rightChild!!
                } else if (element < now.value) {
                    if (now.leftChild == null) {
                        now.leftChild = Node(element)
                        size++
                        break
                    }
                    now = now.leftChild!!
                } else {
                    break
                }
            }
        }
    }

    fun contains(element: E): Boolean {
        var now = root
        while (now != null) {
            when {
                element > now.value -> now = now.rightChild
                element < now.value -> now = now.leftChild
                else -> return true
            }
        }
        return false
    }

    fun containsRecursion(element: E): Boolean {
        return contains(element, root)
    }

    private tailrec fun contains(element: E, node: Node<E>?): Boolean {
        if (node == null)
            return false
        if (element < node.value)
            return contains(element, node.rightChild)
        else if (element > node.value)
            return contains(element, node.leftChild)
        return true
    }

    fun addRecursion(element: E) {
        root = add(element, root)
    }

    private fun add(element: E, node: Node<E>?): Node<E>? {
        if (node == null) {
            size++
            return Node(element)
        }

        if (element > node.value) {
            node.rightChild = add(element, node.rightChild)
        } else if (element < node.value) {
            node.leftChild = add(element, node.leftChild)
        }
        return node
    }

    private fun add(node: Node<E>, element: E) {
        if (element > node.value) {
            if (node.rightChild == null) {
                node.rightChild = Node(element)
                size++
            } else {
                add(node.rightChild!!, element)
            }
        } else if (element < node.value) {
            if (node.leftChild == null) {
                node.leftChild = Node(element)
                size++
            } else {
                add(node.leftChild!!, element)
            }
        }
    }

    fun preOrder(): List<E> {
        if (root == null) return listOf()
        val stack = Stack<Node<E>>()
        stack.push(root)
        val result = arrayListOf<E>()
        while (!stack.isEmpty) {
            val now = stack.pop()
            if (now.rightChild != null) stack.push(now.rightChild)
            if (now.leftChild != null) stack.push(now.leftChild)
            result.add(now.value)
        }
        return result
    }

    fun inOrder(): List<E> {
        val stack = Stack<Node<E>>()
        val result = arrayListOf<E>()
        var now = root
        while (now != null || !stack.isEmpty) {
            now = if (now != null) {
                stack.push(now)
                now.leftChild
            } else {
                val node = stack.pop()
                result.add(node.value)
                node.rightChild
            }
        }
        return result
    }

    fun inOrderRecursion(): List<E> = inOrder(root).toList()

    private fun inOrder(node: Node<E>?): LoopLinkedList<E> {
        val result = LoopLinkedList<E>()
        if (node == null)
            return result
        result.link(inOrder(node.leftChild))
        result.addLast(node.value)
        result.link(inOrder(node.rightChild))
        return result
    }

    fun preOrderRecursion(): List<E> = preOrder(root).toList()

    private fun preOrder(node: Node<E>?): LoopLinkedList<E> {
        if (node == null) {
            return LoopLinkedList()
        }
        val result = LoopLinkedList(node.value)
        result.link(preOrder(node.leftChild))
        result.link(preOrder(node.rightChild))
//        println(result)
        return result
    }

    fun postOrderRecursion(): List<E> = postOrder(root).toList()

    private fun postOrder(node: Node<E>?): LoopLinkedList<E> {
        if (node == null) return LoopLinkedList()
        val result = LoopLinkedList<E>()
        result.link(postOrder(node.leftChild))
        result.link(postOrder(node.rightChild))
        result.addLast(node.value)
        return result
    }

    fun Order(): List<E> {
        if (root == null)
            return emptyList()
        val result = arrayListOf<E>()
        val queue = LinkedQueue<Node<E>>()
        queue.push(root!!)
        while (!queue.empty) {
            val temp = queue.pop()
            result.add(temp.value)
            if (temp.leftChild != null)
                queue.push(temp.leftChild!!)
            if (temp.rightChild != null)
                queue.push(temp.leftChild!!)
        }
        return result
    }

    fun delSmall() {
        if (root == null)
            return
        size--
        val stack = Stack<Node<E>>()
        var now = root
        while (now != null) {
            stack.push(now)
            now = now.leftChild
        }
        now = stack.pop()
        if (stack.isEmpty) {
            root = null
            return
        }
        val pre = stack.pop()
        if (now.rightChild == null) {
            pre.leftChild = null
        } else {
            pre.leftChild = now.rightChild
        }
    }

    fun remove(element: E) {
        var target: Node<E>? = null
        val stack = Stack<Node<E>>()
        var now = root
        outer@while (now != null) {
            when {
                element > now.value -> {
                    stack.push(now)
                    now = now.rightChild
                }
                element < now.value -> {
                    stack.push(now)
                    now = now.leftChild
                }
                else -> {
                    target = now
                    break@outer
                }
            }
        }
        if (target != null) {
            size--
            val pre = stack.pop()
            if (target.leftChild == null) {
                if (pre.value > target.value)
                    pre.leftChild = target.rightChild
                else
                    pre.rightChild = target.rightChild
            } else if (target.rightChild == null) {
                if (pre.value > target.value)
                    pre.leftChild = target.leftChild
                else
                    pre.rightChild = target.leftChild
            } else {
                if (pre.value < target.value) {
                    pre.rightChild = target.rightChild
                    val temp = Stack<Node<E>>()
                    var node = target.rightChild
                    while (node!=null){
                        temp.push(node)
                        node = node.leftChild
                    }
                    node = temp.pop()
                    node.leftChild = target.leftChild
                }else{
                    pre.leftChild = target.leftChild
                    val temp = Stack<Node<E>>()
                    var node = target.rightChild
                    while (node!=null){
                        temp.push(node)
                        node = node.rightChild
                    }
                    node = temp.pop()
                    node.rightChild = target.rightChild
                }
            }
        }

    }

    fun delLarge() {
        if (root == null)
            return
        size--
        val stack = Stack<Node<E>>()
        var now = root
        while (now != null) {
            stack.push(now)
            now = now.rightChild
        }
        now = stack.pop()
        if (stack.isEmpty) {
            root = null
            return
        }
        val pre = stack.pop()
        if (now.leftChild != null) {
            pre.rightChild = now.leftChild
        } else {
            pre.rightChild = null
        }

    }

}

fun main(args: Array<String>) {
    val tree = BinarySearchTree<Int>()
    for (i in listOf(9, 7, 3, 2, 4, 8, 18, 15, 14, 16, 40, 22, 19, 24, 45, 43, 49)) {
        if (i % 2 == 0) {
            tree.add(i)
        } else {
            tree.addRecursion(i)
        }
    }
//    for (i in listOf(51, 432, 654, 234, 23, 45, 324, 67, 2341, 4, 67, 34, 123, 55)) {
//        if (i % 2 == 0) {
//            tree.add(i)
//        } else {
//            tree.addRecursion(i)
//        }
//    }
//    for (i in 100 downTo 1) {
//        if (i % 2 == 0) {
//            tree.add(i)
//        } else {
//            tree.addRecursion(i)
//        }
//    }
//    println()
    println(tree.inOrder().joinToString(","))
    tree.remove(18)
    println(tree.preOrder().joinToString(","))
    tree.delSmall()
    tree.delLarge()
    println(tree.preOrder().joinToString(","))
//    println(tree.inOrderRecursion().joinToString(","))
//    tree.remove(40)
//    tree.remove(7)
//    println(tree.preOrder().joinToString(" "))
//    println(tree.preOrderRecursion().joinToString(" "))
//    println(tree.inOrderRecursion().joinToString(","))
//    println(tree.inOrder().joinToString(" "))
//    println(tree.postOrderRecursion().joinToString(" "))
//    println(tree.contains(999))
//    println(tree.containsRecursion(100))
}