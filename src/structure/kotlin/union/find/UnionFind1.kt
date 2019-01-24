package structure.kotlin.union.find

import structure.UnionFind

class UnionFind1(private val size: Int) : UnionFind {
    private val id = IntArray(size)

    init {
        for (i in 0 until size) {
            id[i] = i
        }
    }

    private fun find(p: Int): Int {
        if (p < 0 || p >= size) {
            throw IndexOutOfBoundsException()
        }
        return id[p]
    }

    override fun isConnected(p: Int, q: Int): Boolean = find(p) == find(q)

    override fun unionElements(p: Int, q: Int) {
        if(find(p) == find(q)) return
        for ((index,i) in id.withIndex()){
            if (i == p){
                id[index] = find(q)
            }
        }
    }

    override fun getSize() = size
}