package structure.kotlin.map

import structure.Map

class BSTMap<K : Comparable<K>, V> : Map<K, V> {

    private class ComparablePair<K : Comparable<K>, V>(val key: K, var value: V) : Comparable<ComparablePair<K, V>> {
        override fun compareTo(other: ComparablePair<K, V>): Int {
            return key.compareTo(other.key)
        }
    }

    private class Tree<K : Comparable<K>, V, E : ComparablePair<K, V>> {
        private class Node<E>(
            val value: E,
            var left: Node<E>? = null,
            var right: Node<E>? = null
        )

        var size: Int = 0
            private set
        private var root: Node<E>? = null
        private var res: V? = null
        val empty
            get() = size == 0

        fun add(element: E) {
            root = add(element, root)
        }

        private fun add(element: E, node: Node<E>?): Node<E> {
            if (node == null) {
                val newNode = Node(element)
                size++
                return newNode
            } else {
                if (node.value > element) {
                    node.left = add(element, node.left)
                } else if (node.value < element) {
                    node.right = add(element, node.right)
                }
                return node
            }
        }

        operator fun contains(key: K): Boolean {
            return contains(key, root)
        }

        private fun contains(key: K, node: Node<E>?): Boolean {
            return if (node == null) {
                false
            } else {
                if (node.value.key == key) {
                    true
                } else if (node.value.key > key) {
                    contains(key, node.left)
                } else {
                    contains(key, node.right)
                }
            }
        }

        operator fun get(key: K): V? {
            get(key, root)
            val r = res
            res = null
            return r
        }

        private fun get(key: K, node: Node<E>?): Boolean {
            return if (node == null) {
                false
            } else {
                if (node.value.key == key) {
                    res = node.value.value
                    true
                } else if (node.value.key > key) {
                    get(key, node.left)
                } else {
                    get(key, node.right)
                }
            }
        }


        operator fun set(key: K, value: V) {
            set(key, value, root)
        }

        private fun set(key: K, value: V, node: Node<E>?): Boolean {
            return if (node == null) {
                false
            } else {
                if (node.value.key == key) {
                    node.value.value = value
                    true
                } else if (node.value.key > key) {
                    set(key, value, node.left)
                } else {
                    set(key, value, node.right)
                }
            }
        }

        fun minimum(): E {
            if (empty) throw IndexOutOfBoundsException()
            return minimum(root!!).value
        }

        private fun minimum(node: Node<E>): Node<E> {
            return if (node.left == null)
                node
            else
                minimum(node.left!!)
        }

        fun maximum(): E {
            if (empty) throw IndexOutOfBoundsException()
            return maximum(root!!).value
        }

        private fun maximum(node: Node<E>): Node<E> {
            return if (node.right == null)
                node
            else
                maximum(node.right!!)
        }

        fun removeMin(): E {
            if (empty) throw IndexOutOfBoundsException()
            val res = minimum()
            root = removeMin(root!!)
            return res
        }

        private fun removeMin(node: Node<E>): Node<E>? {
            if (node.left == null) {
                val right = node.right
                node.right = null
                size--
                return right
            }
            node.left = removeMin(node.left!!)
            return node
        }

        fun removeMax(): E {
            if (empty) throw IndexOutOfBoundsException()
            val res = maximum()
            root = removeMax(root!!)
            return res
        }

        private fun removeMax(node: Node<E>): Node<E>? {
            if (node.right == null) {
                val left = node.left
                node.left = null
                return left
            }
            node.right = removeMax(node.right!!)
            return node
        }


        fun remove(element: K): V? {
            remove(element, root)
            val r = res
            res = null
            return r
        }

        private fun remove(element: K, node: Node<E>?): Node<E>? {
            if (node == null) return null
            if (node.value.key > element) {
                node.left = remove(element, node.left)
                return node
            } else if (node.value.key < element) {
                node.right = remove(element, node.right)
                return node
            } else {
                res = node.value.value
                if (node.right == null) {
                    val left = node.left
                    node.left = null
                    size--
                    return left
                }


                val newValue = minimum(node.right!!).value
                removeMin(node.right!!)
                val newNode = Node(newValue)
                newNode.left = node.left
                newNode.right = node.right
                node.left = null
                node.right = null
                return newNode
            }
        }


    }


    private val tree = Tree<K, V, ComparablePair<K, V>>()
    override fun add(key: K, value: V) {
        if (!contains(key))
            tree.add(ComparablePair(key, value))
        else
            set(key, value)
    }

    override fun remove(key: K): V? = tree.remove(key)

    override fun contains(key: K): Boolean = tree.contains(key)

    override operator fun get(key: K): V? = tree[key]

    override fun set(key: K, value: V) {
        if (contains(key)) {
            tree[key] = value
        } else {
            add(key, value)
        }
    }

    override fun getSize(): Int = tree.size

    override fun isEmpty(): Boolean = tree.empty

}