package structure.test

fun main() {
    val s = "yelloworld"
    var hash = 0
    for (i in s) hash = (hash * 26 + i.toInt()) % 977898623
    println(hash)
    val n = 23213212132137213
    println(n.hashCode())
    println(n/44049232)
}
