package AVLTree

class Node<K : Comparable<K>, V>(
        var key: K,
        var value: V,
        var parent: Node<K, V>? = null,
        var height: Int = 1
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
            height != other.height -> false
            left != other.left -> false
            right != other.right -> false
            parent != other.parent -> false
            else -> true
        }

    }

    override fun hashCode(): Int {

        var result = key.hashCode()

        result = 31 * result + (value?.hashCode() ?: 0)
        result = 31 * result + (height.hashCode())
        result = 31 * result + (parent?.hashCode() ?: 0)
        result = 31 * result + (left?.hashCode() ?: 0)
        result = 31 * result + (right?.hashCode() ?: 0)

        return result

    }

    fun height(node: Node<K, V>?): Int = node?.height ?: 0

    fun fixHeight() {

        this.height = Math.max(height(this.left), height(this.right)) + 1

    }

    fun balanceFactor(node: Node<K, V>): Int = height(node.right) - height(node.left)

    fun rotateLeft() {

        val rightChild = this.right ?: return
        val parent = this.parent

        rightChild.left?.parent = this
        this.right = rightChild.left
        rightChild.left = this

        when (this) {
            parent?.left -> parent.left = rightChild
            parent?.right -> parent.right = rightChild
        }

        this.parent = rightChild
        rightChild.parent = parent

        this.fixHeight()
        rightChild.fixHeight()

    }

    fun rotateRight() {

        val leftChild = this.left ?: return
        val parent = this.parent

        leftChild.right?.parent = this
        this.left = leftChild.right
        leftChild.right = this

        when (this) {
            parent?.left -> parent.left = leftChild
            parent?.right -> parent.right = leftChild
        }

        this.parent = leftChild
        leftChild.parent = parent

        this.fixHeight()
        leftChild.fixHeight()

    }

    fun rotateLeftThenRight() {

        this.left?.rotateLeft()
        this.rotateRight()

    }

    fun rotateRightThenLeft() {

        this.right?.rotateRight()
        this.rotateLeft()

    }

}