package RedBlackTree

class Node<K : Comparable<K>, V>(
        var key: K,
        var value: V,
        var parent: Node<K, V>? = null,
        var isBlack: Boolean = false
) {

    var left: Node<K, V>? = null
    var right: Node<K, V>? = null

    override fun equals(other: Any?): Boolean {

        if (this === other) return true
        if (other?.javaClass != javaClass) return false

        other as Node<*, *>

        return when {
            key != other.key -> false
            value != other.value -> false
            isBlack != other.isBlack -> false
            left != other.left -> false
            right != other.right -> false
            parent != other.parent -> false
            else -> true
        }

    }

    override fun hashCode(): Int {

        var result = key.hashCode()

        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + (isBlack.hashCode())
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)

        return result

    }

    fun sibling(): Node<K, V>? = when (this) {

        parent?.left -> parent!!.right
        else -> parent?.left

    }

    private fun grandparent(): Node<K, V>? = this.parent?.parent

    fun uncle(): Node<K, V>? = when (this.parent) {

        this.grandparent()?.left -> this.grandparent()!!.right
        else -> this.grandparent()?.left

    }

    fun isLeaf() = this.left == null && this.right == null

    private fun swapColors(node: Node<K, V>?) {

        val tmp = this.isBlack

        if (node != null) {
            this.isBlack = node.isBlack
            node.isBlack = tmp
        }

    }

    fun leftRotate() {

        val rightChild = this.right ?: return
        val parent = this.parent

        this.swapColors(rightChild)
        rightChild.left?.parent = this
        this.right = rightChild.left
        rightChild.left = this

        when (this) {
            parent?.left -> parent.left = rightChild
            parent?.right -> parent.right = rightChild
        }

        this.parent = rightChild
        rightChild.parent = parent

    }

    fun rightRotate() {

        val leftChild = this.left ?: return
        val parent = this.parent

        this.swapColors(leftChild)
        leftChild.right?.parent = this
        this.left = leftChild.right
        leftChild.right = this

        when (this) {
            parent?.left -> parent.left = leftChild
            parent?.right -> parent.right = leftChild
        }

        this.parent = leftChild
        leftChild.parent = parent

    }

}