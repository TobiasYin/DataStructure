package structure.kotlin.union.find

import structure.UnionFind

class UnionFind6(private val size: Int) : UnionFind {
    private val parent = IntArray(size)
    private val rank = IntArray(size)

    init {
        for (i in 0 until size) {
            parent[i] = i
            rank[i] = 1
        }
    }

    private fun find(p: Int): Int {
        if (p < 0 || p >= size) throw  IndexOutOfBoundsException()
        if (p!=parent[p]) parent[p] = find(parent[p])
        return parent[p]
    }

    override fun isConnected(p: Int, q: Int): Boolean = find(p) == find(q)

    override fun unionElements(p: Int, q: Int) {
        val pID = find(p)
        val qID = find(q)
        if (pID == qID)
            return
        if (rank[pID] < rank[qID]) {
            parent[pID] = qID
        } else if (rank[pID] > rank[qID]) {
            parent[qID] = pID
        } else {
            parent[pID] = qID
            rank[qID] += 1
        }
    }

    override fun getSize(): Int = size
}