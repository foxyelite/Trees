package BinarySearchTree

class Node<K : Comparable<K>, V>(
        var key: K,
        var value: V,
        var parent: Node<K, V>? = null
) {

    var left: Node<K, V>? = null
    var right: Node<K, V>? = null

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Node<*, *>

        return when {
            value != other.value -> false
            parent != other.parent -> false
            left != other.left -> false
            key != other.key -> false
            right != other.right -> false
            else -> true
        }

    }

    override fun hashCode(): Int {

        var result = key.hashCode()

        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)

        return result

    }
}