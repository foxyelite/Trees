package BinarySearchTree

import Tree

class BinarySearchTree<K: Comparable<K>, V> : Tree<K, V> {
    var root: Node<K, V>? = null

    private fun findNode(key: K, cur: Node<K, V>? = root): Node<K, V>? = when {
        cur == null || key == cur.key -> cur
        key < cur.key -> findNode(key, cur.left)
        else -> findNode(key, cur.right)
    }

    override fun find(key: K): Pair<K, V>? {
        val result = findNode(key)
        return when (result) {
            null -> null
            else -> Pair(result.key, result.value)
        }
    }

    override fun insert(key: K, value: V) {
        var father: Node<K, V>? = null
        var cur: Node<K, V>? = root
        while (cur != null) {
            father = cur
            when {
                key < cur.key -> cur = cur.left
                key > cur.key -> cur = cur.right
                key == cur.key -> {
                    cur.value = value
                    return
                }
            }
        }
        if (father == null) {
            root = Node(key, value)
            return
        }
        if (key < father.key) {
            father.left = Node(key, value, father)
        }
        else {
            father.right = Node(key, value, father)
        }
    }

    override fun delete(key: K) {
        val cur: Node<K, V> = findNode(key) ?: return
        val father: Node<K, V>? = cur.parent
        if (cur.left == null && cur.right == null) {
            if (father == null) {
                root = null
                return
            }
            if (cur == father?.left) {
                father.left = null
            }
            if (cur == father?.right) {
                father.right = null
            }
        }
        else if (cur.left == null || cur.right == null) {
            if (cur.left == null) {
                if (cur == father?.left) {
                    father.left = cur.right
                }
                else {
                    father?.right = cur.right
                }
                cur.right?.parent = father
            }
            else {
                if (cur == father?.left) {
                    father.left = cur.left
                }
                else {
                    father?.right = cur.left
                }
                cur.left?.parent = father
            }
        }
        else {
            val succesor: Node<K, V> = min(cur.right)!!
            cur.key = succesor.key
            if (succesor == succesor.parent?.left) {
                succesor.parent?.left = succesor.right
                if (succesor.right != null) {
                    succesor.right!!.parent = succesor.parent
                }
            }
            else {
                succesor.parent?.right = succesor.right
                if (succesor.right != null)
                    succesor.right!!.parent = succesor.parent
            }
        }
    }

    fun min(cur: Node<K, V>?): Node<K, V>? = when {
        cur?.left == null -> cur
        else -> min(cur.left)
    }

    fun max(cur: Node<K, V>?): Node<K, V>? = when {
        cur?.right == null -> cur
        else -> max(cur.right)
    }

    private fun next(cur: Node<K, V>?): Node<K, V>? {
        var nxt = cur ?: return null
        if (nxt.right != null) {
            return min(nxt.right!!)
        }
        else if (nxt == nxt.parent?.right) {
            while (nxt == nxt.parent?.right) {
                nxt = nxt.parent!!
            }
        }
        return nxt.parent
    }

    private fun prev(cur: Node<K, V>?): Node<K, V>? {
        var prv = cur ?: return null
        if (prv.left != null) {
            return max(prv.left!!)
        }
        else if (prv == prv.parent?.left) {
            while (prv == prv.parent?.left) {
                prv = prv.parent!!
            }
        }
        return prv.parent
    }
}