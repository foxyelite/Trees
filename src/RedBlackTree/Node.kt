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
        if (key != other.key) return false
        if (value != other.value) return false
        if (parent != other.parent) return false
        if (isBlack != other.isBlack) return false
        if (left != other.left) return false
        if (right != other.right) return false
        return true
    }

    override fun hashCode(): Int {
        var result = key.hashCode()
        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + isBlack.hashCode()
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)
        return result
    }

    fun brother(): Node<K, V>? {
        if (this == this.parent?.left)
            return this.parent!!.right
        return this.parent?.left
    }

    private fun swapColors(node: Node<K, V>?) {
        val tmp = this.isBlack
        if (node != null) {
            this.isBlack = node.isBlack
            node.isBlack = tmp
        }
    }

    fun leftRotate() {
        val rightChild = this.right ?: return
        val father = this.parent
        this.swapColors(rightChild)
        rightChild.left?.parent = this
        this.right = rightChild.left
        rightChild.left = this
        when {
            this == father?.left -> father.left = rightChild
            this == father?.right -> father.right = rightChild
        }
        this.parent = rightChild
        rightChild.parent = father
    }

    fun rightRotate() {
        val leftChild = this.left ?: return
        val father = this.parent
        this.swapColors(leftChild)
        leftChild.right?.parent = this
        this.left = leftChild.right
        leftChild.right = this
        when {
            this == father?.left -> father.left = leftChild
            this == father?.right -> father.right = leftChild
        }
        this.parent = leftChild
        leftChild.parent = father
    }
}