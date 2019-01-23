package structure.kotlin

class SegmentTree<T>(arr: Array<T>, val operator: (T, T) -> (T)) {
    val data = arrayOfNulls<Any>(arr.size) as Array<T>
    val tree = arrayOfNulls<Any>(arr.size * 4) as Array<T>
    val size
        get() = data.size

    init {
        for ((index, item) in arr.withIndex()) data[index] = item
        buildSegmentTree(0, 0, size - 1)
    }

    // 在treeIndex之间创建left到right的tree.
    private fun buildSegmentTree(treeIndex: Int, left: Int, right: Int) {
        if (left == right) {
            tree[treeIndex] = data[left]
            return
        }

        val leftTreeIndex = leftChild(treeIndex)
        val rightTreeIndex = rightChild(treeIndex)

        val mid = left + (right - left) / 2
        buildSegmentTree(leftTreeIndex, left, mid)
        buildSegmentTree(rightTreeIndex, mid + 1, right)

        tree[treeIndex] = operator(tree[leftTreeIndex], tree[rightTreeIndex])
    }

    private fun leftChild(index: Int) = 2 * index + 1
    private fun rightChild(index: Int) = 2 * index + 2

    //返回left..right的值
    fun query(left: Int, right: Int): T {
        if (left < 0 || left >= size || right < 0 || right >= size || left > right) throw IndexOutOfBoundsException()
        return query(0, 0, data.size - 1, left, right)
    }

    private fun query(treeIndex: Int, left: Int, right: Int, queryLeft: Int, queryRight: Int): T {
//        println("left = $left , right = $right , ql = $queryLeft , qr = $queryRight")
        if (queryLeft == left && queryRight == right) return tree[treeIndex]
        val mid = left + (right - left) / 2
        return when {
            queryLeft >= mid + 1 -> query(rightChild(treeIndex), mid + 1, right, queryLeft, queryRight)
            queryRight <= mid -> query(leftChild(treeIndex), left, mid, queryLeft, queryRight)
            else -> operator(
                query(leftChild(treeIndex), left, mid, queryLeft, mid),
                query(rightChild(treeIndex), mid + 1, right, mid + 1, queryRight)
            )
        }
    }

    operator fun get(index: Int): T {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException()
        return data[index]
    }

    operator fun set(index: Int, value: T) {
        if (index < 0 || index >= size) throw IndexOutOfBoundsException()
        data[index] = value
        set(0, 0, size - 1, index, value)
    }

    private fun set(treeIndex: Int, left: Int, right: Int, index: Int, value: T) {
        if (left == right) {
            tree[treeIndex] = value
            return
        }
        val mid = left + (right - left) / 2
        if (index > mid) set(rightChild(treeIndex), mid + 1, right, index, value)
        else if (index <= mid) set(leftChild(treeIndex), left, mid, index, value)
        tree[treeIndex] = operator(tree[leftChild(treeIndex)], tree[rightChild(treeIndex)])
    }

    override fun toString(): String {
        val s = StringBuilder()
        s.append("[ ")
        for (i in tree) {
            if (i == null) {
                s.append("null ,")
            } else {
                s.append("$i ,")
            }
        }
        s.replace(s.length - 1, s.length, "]")
        return s.toString()
    }
}