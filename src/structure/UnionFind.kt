package structure

interface UnionFind {
    fun isConnected(p:Int,q:Int):Boolean
    fun unionElements(p:Int,q:Int)
    fun getSize():Int
}