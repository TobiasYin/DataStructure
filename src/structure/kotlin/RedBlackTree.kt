package structure.kotlin

private const val RED = true
private const val BLACK = false

class RedBlackTree<K : Comparable<K>, V> {

    private class Node<K, V>(
        val key: K,
        var value: V? = null,
        var leftChild: Node<K, V>? = null,
        var rightChild: Node<K, V>? = null
    ) {
        var color: Boolean = RED
    }

    private var root: Node<K, V>? = null
    var size = 0
        private set
    val empty
        get() = size == 0

    private fun isRed(node: Node<K, V>?): Boolean = node?.color ?: BLACK

    private fun leftRotate(node: Node<K, V>): Node<K, V> {
        val right = node.rightChild!!
        node.rightChild = right.leftChild
        right.leftChild = node
        right.color = node.color
        node.color = RED
        return right
    }

    private fun rightRotate(node: Node<K, V>): Node<K, V> {
        val left = node.leftChild!!
        node.leftChild = left.rightChild
        left.rightChild = node
        left.color = node.color
        node.color = RED
        return left
    }

    private fun filpColors(node: Node<K, V>) {
        node.leftChild!!.color = BLACK
        node.rightChild!!.color = BLACK
        node.color = RED
    }

    fun add(key: K, value: V? = null) {
        root = add(root, key, value)
        root!!.color = BLACK
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
        var retNode: Node<K, V> = node
        if (isRed(node.rightChild) && !isRed(node.leftChild)) retNode = leftRotate(node)
        if (isRed(node.leftChild) && isRed(node.leftChild!!.leftChild)) retNode = rightRotate(node)
        if (isRed(node.rightChild) && isRed(node.leftChild)) filpColors(retNode)
        return retNode
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

    fun minimum(): K? = if (empty) null else minimum(root!!).key

    private fun minimum(node: Node<K, V>): Node<K, V> = if (node.leftChild == null) node else minimum(node.leftChild!!)

    fun maximum(): K? = if (empty) null else maximum(root!!).key

    private fun maximum(node: Node<K, V>): Node<K, V> =
        if (node.rightChild == null) node else maximum(node.rightChild!!)

    fun removeMax(): K? {
        val max = maximum()
        root = removeMax(root)
        return max
    }

    private fun removeMax(node: Node<K, V>?): Node<K, V>? {
        if (node == null) return null

        if (node.rightChild == null) {
            val left = node.leftChild
            node.leftChild = null
            size--
            return left
        }
        node.rightChild = removeMax(node.rightChild)
        return node
    }

    fun removeMin(): K? {
        val min = minimum()
        root = removeMin(root)
        return min
    }

    private fun removeMin(node: Node<K, V>?): Node<K, V>? {
        if (node == null)
            return null
        if (node.leftChild == null) {
            val right = node.rightChild
            node.rightChild = null
            size--
            return right
        }
        node.leftChild = removeMin(node.leftChild)
        return node
    }

    fun remove(key: K) {
        root = remove(root, key)
    }

    private fun remove(node: Node<K, V>?, key: K): Node<K, V>? {
        if (node == null)
            return null
        when {
            node.key == key && node.rightChild == null -> {
                val left = node.leftChild
                node.leftChild = null
                size--
                return left
            }
            node.key > key -> node.leftChild = remove(node.leftChild, key)
            node.key < key -> node.rightChild = remove(node.rightChild, key)
            else -> {
                val min = minimum(node.rightChild!!)
                min.rightChild = remove(node.rightChild, min.key)
                min.leftChild = node.leftChild
                node.leftChild = null
                node.rightChild = null
                return min
            }
        }
        return node
    }
}