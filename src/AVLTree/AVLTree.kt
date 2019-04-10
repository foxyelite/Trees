package AVLTree

import Tree

class AVLTree<K : Comparable<K>, V> : Tree<K, V>, Iterable<Pair<K, V>> {

    var root: Node<K, V>? = null

    override fun find(key: K): Pair<K, V>? {

        val result = findNode(key) ?: return null

        return Pair(result.key, result.value)

    }

    private fun findNode(key: K, cur: Node<K, V>? = root): Node<K, V>? = when {

        cur == null || key == cur.key -> cur
        key < cur.key -> findNode(key, cur.left)
        else -> findNode(key, cur.right)

    }

    override fun insert(key: K, value: V) {

        var parent: Node<K, V>? = null
        var cur: Node<K, V>? = root

        while (cur != null) {
            parent = cur
            when {
                key < cur.key -> cur = cur.left
                key > cur.key -> cur = cur.right
                key == cur.key -> {
                    cur.value = value
                    return
                }
            }
        }

        if (parent == null) {
            root = Node(key, value, parent)
            return
        }

        if (key < parent.key) {
            parent.left = Node(key, value, parent)
            rebalance(parent.left!!)
        } else {
            parent.right = Node(key, value, parent)
            rebalance(parent.right!!)
        }

    }

    override fun delete(key: K) {

        if (root == null || find(key) == null) return

        var cur: Node<K, V>? = root
        var parent: Node<K, V>? = root
        var delNode: Node<K, V>? = null
        var child: Node<K, V>? = root

        while (child != null) {
            parent = cur
            cur = child
            child = if (key >= cur.key) cur.right else cur.left
            if (key == cur.key) delNode = cur
        }

        if (delNode == null) return

        delNode.key = cur!!.key
        delNode.value = cur!!.value

        child = if (cur.left != null) cur.left else cur.right
        if (root!!.key == key) {
            root = child
            child?.parent = null
            return
        } else {
            if (parent!!.left == cur) {
                parent.left = child
            } else {
                parent.right = child
            }
            rebalance(parent)
        }

    }

    private fun rebalance(node: Node<K, V>) {
        node.fixHeight()
        var cur = node
        if (cur.balanceFactor(cur) == -2) {
            if (cur.height(cur.left!!.left) >= cur.height(cur.left!!.right)) {
                cur.rotateRight()
            } else {
                cur.rotateLeftThenRight()
            }
        } else if (cur.balanceFactor(cur) == 2) {
            if (cur.height(cur.right!!.right) >= cur.height(cur.right!!.left)) {
                cur.rotateLeft()
            } else {
                cur.rotateRightThenLeft()
            }
        }
        if (cur.parent != null) {
            rebalance(cur.parent!!)
        } else {
            root = cur
        }
    }

    private fun min(cur: Node<K, V>?): Node<K, V>? = if (cur?.left == null) cur else min(cur.left)

    private fun max(cur: Node<K, V>?): Node<K, V>? = if (cur?.right == null) cur else max(cur.right)

    private fun next(cur: Node<K, V>?): Node<K, V>? {

        var next = cur ?: return null

        if (next.right != null) {
            return min(next.right!!)
        } else if (next == next.parent?.right) {
            while (next == next.parent?.right) {
                next = next.parent!!
            }
        }

        return next.parent

    }

    override fun iterator(): Iterator<Pair<K, V>> {

        return (object : Iterator<Pair<K, V>> {

            var cur: Node<K, V>? = min(root)
            var prev: Node<K, V>? = min(root)
            var last: Node<K, V>? = max(root)

            override fun hasNext(): Boolean = cur != null && cur!!.key <= last!!.key

            override fun next(): Pair<K, V> {
                prev = cur
                cur = next(cur)
                return Pair(prev!!.key, prev!!.value)
            }
        })

    }

}