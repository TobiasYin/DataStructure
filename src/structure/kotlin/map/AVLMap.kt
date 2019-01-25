package structure.kotlin.map

import structure.Map
import structure.kotlin.AVLTree

class AVLMap<K : Comparable<K>, V> : Map<K, V> {

    private val map = AVLTree<K, V>()

    override fun add(key: K, value: V) = map.add(key, value)

    override fun remove(key: K): V? {
        val v = map[key]
        map.remove(key)
        return v
    }

    override fun contains(key: K): Boolean = map.isContains(key)

    override operator fun get(key: K): V? = map[key]

    override operator fun set(key: K, value: V) {
        map[key] = value
    }

    override fun getSize(): Int = map.size

    override fun isEmpty(): Boolean = map.empty

}