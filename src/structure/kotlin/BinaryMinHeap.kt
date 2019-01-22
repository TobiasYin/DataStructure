package structure.kotlin


// 二叉堆. 二叉堆的子节点都大于于他的父亲节点,对于任意节点的子树都是一个新的二叉堆(最小堆),也可以定义出同样的最大堆.
// Parent(i) = i / 2
// left child = i * 2
// right child = i * 2 + 1
class BinaryMinHeap<E : Comparable<E>> constructor(capacity: Int = 10) {
    private val data = ArrayList<E>(capacity)
    val size
        get() = data.size
    val empty
        get() = data.isEmpty

    constructor(list: List<E>) : this() {
        for (i in list){
            data.append(i)
        }
        for (i in parent(list.size - 1) downTo 0)
            siftDown(i)
    }


    private fun parent(index: Int): Int {
        if (index !in 1 until size) {
            throw IndexOutOfBoundsException()
        }
        return (index - 1) / 2
    }

    private fun leftChild(index: Int): Int {
        return index * 2 + 1
    }

    private fun rightChild(index: Int): Int {
        return index * 2 + 2
    }

    fun add(element: E) {
        data.append(element)
        siftUp(size - 1)
    }

    private fun siftUp(index: Int) {
        var nowIndex = index
        while (nowIndex != 0 && data[nowIndex] < data[parent(nowIndex)]) {
            swap(parent(nowIndex), nowIndex)
            nowIndex = parent(nowIndex)
        }
    }

    fun findMax(): E = data[0]

    fun extractMax(): E {
        val max = data[0]
        data[0] = data[size - 1]
        data.remove(size - 1)
        siftDown(0)
        return max
    }

    private fun swap(i: Int, j: Int) {
        val temp = data[i]
        data[i] = data[j]
        data[j] = temp
    }

    private fun siftDown(index: Int) {
        var nowIndex = index
        while (leftChild(nowIndex) < size) {
            var child = leftChild(nowIndex)
            if (rightChild(nowIndex) < size && data[rightChild(nowIndex)] < data[child])
                child++
            if (data[child] < data[nowIndex]) {
                swap(child, nowIndex)
                nowIndex = child
            } else break
        }
    }

    fun replace(element: E): E {
        val max = data[0]
        data[0] = element
        siftUp(0)
        return max
    }

}