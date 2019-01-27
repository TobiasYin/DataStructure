package structure.kotlin

import java.util.TreeMap

class HashTable<K, V>(private var M: Int = 7) {
    private val upperTol = 10
    private val lowerTol = 2

    private var hashTable = Array<TreeMap<K, V>>(M) { TreeMap() }
    var size = 0
        private set

    private fun hash(key: K): Int = (key.hashCode() and 0x7fffffff) % M

    fun add(key: K, value: V) {
        val map = hashTable[hash(key)]
        if (!map.containsKey(key)) size++
        map[key] = value
        if (size >= upperTol * M) resize(2 * M)
    }

    fun remove(key: K): V? {
        val map = hashTable[hash(key)]
        if (!map.containsKey(key)) return null
        val v = map[key]!!
        map.remove(key)
        size--
        if (size < lowerTol * M && M / 2 > 7) resize(M / 2)
        return v
    }

    operator fun set(key: K, value: V) {
        val map = hashTable[hash(key)]
        if (!map.containsKey(key)) size++
        map[key] = value
    }

    operator fun get(key: K): V? = hashTable[hash(key)][key]

    operator fun contains(key: K): Boolean = hashTable[hash(key)].containsKey(key)

    private fun resize(length: Int) {
        val newMap = Array<TreeMap<K, V>>(length) { TreeMap() }
        M = length
        for (map in hashTable)
            for (item in map)
                newMap[hash((item.key))][item.key] = item.value
        hashTable = newMap
    }
}