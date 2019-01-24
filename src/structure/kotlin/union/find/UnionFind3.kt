package structure.kotlin.union.find

import structure.UnionFind

class UnionFind3(private val size: Int) : UnionFind {
    private val parent = IntArray(size)
    private val sz = IntArray(size)

    init {
        for (i in 0 until size) {
            parent[i] = i
            sz[i] = 1
        }
    }

    private fun find(p: Int): Int {
        if (p < 0 || p >= size) throw  IndexOutOfBoundsException()
        var now = parent[p]
        while (now != parent[now])
            now = parent[now]
        return now
    }

    override fun isConnected(p: Int, q: Int): Boolean = find(p) == find(q)

    override fun unionElements(p: Int, q: Int) {
        val pID = find(p)
        val qID = find(q)
        if (pID == qID)
            return
        if (sz[pID] < sz[qID]) {
            parent[pID] = qID
            sz[qID] += sz[pID]
        } else {
            parent[qID] = pID
            sz[pID] += sz[qID]
        }
    }

    override fun getSize(): Int = size
}