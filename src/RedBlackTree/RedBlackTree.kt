package RedBlackTree

import Tree

class RedBlackTree<K : Comparable<K>, V> : Tree<K, V> {
    var root: Node<K, V>? = null

    private fun findNode(key: K, cur: Node<K, V>? = root): Node<K, V>? = when {
        cur == null || key == cur.key -> cur
        key < cur.key -> findNode(key, cur.left)
        else -> findNode(key, cur.right)
    }

    override fun find(key: K): Pair<K, V>? {
        val result = findNode(key) ?: return null
        return Pair(result.key, result.value)
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
            root = Node(key, value, father, true)
            return
        }
        if (key < father.key) {
            father.left = Node(key, value, father, false)
            fixInsert(father.left)
        } else {
            father.right = Node(key, value, father, false)
            fixInsert(father.right)
        }
    }

    private fun fixInsert(node: Node<K, V>?) {
        var uncle: Node<K, V>?
        var cur: Node<K, V>? = node
        while (cur?.parent?.isBlack == false) {
            if (cur.parent == cur.parent?.parent?.left) {
                uncle = cur.parent?.parent?.right
                when {
                    uncle?.isBlack == false -> {
                        cur.parent?.isBlack = true
                        uncle.isBlack = true
                        cur.parent?.parent?.isBlack = false
                        cur = cur.parent?.parent
                    }
                    cur == cur.parent?.right -> {
                        cur = cur.parent
                        if (cur!!.parent?.parent == null) {
                            root = cur.parent
                        }
                        cur.leftRotate()
                    }
                    cur == cur.parent?.left -> {
                        if (cur.parent?.parent?.parent == null) {
                            root = cur.parent
                        }
                        cur.parent?.parent?.rightRotate()
                    }
                }
            } else {
                uncle = cur.parent?.parent?.left
                when {
                    uncle?.isBlack == false -> {
                        cur.parent?.isBlack = true
                        uncle.isBlack = true
                        cur.parent?.parent?.isBlack = false
                        cur = cur.parent?.parent
                    }
                    cur == cur.parent?.left -> {
                        cur = cur.parent
                        if (cur!!.parent?.parent == null) {
                            root = cur.parent
                        }
                        cur.rightRotate()
                    }
                    cur == cur.parent?.right -> {
                        if (cur.parent?.parent?.parent == null) {
                            root = cur.parent
                        }
                        cur.parent?.parent?.leftRotate()
                    }
                }
            }
        }
        root?.isBlack = true
    }

    override fun delete(key: K) {
        val node = findNode(key) ?: return
        deleteNode(node)
    }

    private fun deleteNode(node: Node<K, V>) {
        val prev = max(node.left)
        when {
            (node.right != null && node.left != null) -> {
                node.key = prev!!.key
                node.value = prev.value
                deleteNode(prev)
                return
            }
            (node == root && node.left == null && node.right == null) -> {
                root = null
                return
            }
            (!node.isBlack && node.left == null && node.right == null) -> {
                if (node == node.parent!!.left)
                    node.parent!!.left = null
                else
                    node.parent!!.right = null

                return
            }
            (node.isBlack && node.left != null && !node.left!!.isBlack) -> {
                node.key = node.left!!.key
                node.value = node.left!!.value
                node.left = null
                return
            }
            (node.isBlack && node.right != null && !node.right!!.isBlack) -> {
                node.key = node.right!!.key
                node.value = node.right!!.value
                node.right = null
                return
            }
            else -> {
                deleteCase1(node)
            }
        }
        if (node == node.parent!!.left) {
            node.parent!!.left = null
        } else {
            node.parent!!.right = null
        }
    }

    private fun deleteCase1(node: Node<K, V>) {
        if (node.parent != null) {
            deleteCase2(node)
        }
    }

    private fun deleteCase2(node: Node<K, V>) {
        val brother = node.brother()

        if (!brother!!.isBlack) {
            if (node == node.parent!!.left) {
                node.parent!!.leftRotate()
            } else if (node == node.parent!!.right) {
                node.parent!!.rightRotate()
            }
            if (root == node.parent) {
                root = node.parent!!.parent
            }
        }
        deleteCase3(node)
    }

    private fun deleteCase3(node: Node<K, V>) {
        val brother = node.brother()

        val a: Boolean = brother!!.left == null || brother.left!!.isBlack
        val b: Boolean = brother.right == null || brother.right!!.isBlack

        if (a && b && brother.isBlack && node.parent!!.isBlack) {
            brother.isBlack = false
            deleteCase1(node.parent!!)
        } else {
            deleteCase4(node)
        }
    }

    private fun deleteCase4(node: Node<K, V>) {
        val brother = node.brother()

        val a: Boolean = brother!!.left == null || brother.left!!.isBlack
        val b: Boolean = brother.right == null || brother.right!!.isBlack

        if (a && b && brother.isBlack && !node.parent!!.isBlack) {
            brother.isBlack = false
            node.parent!!.isBlack = true
        } else {
            deleteCase5(node)
        }
    }

    private fun deleteCase5(node: Node<K, V>) {
        val brother = node.brother()

        val a: Boolean = brother!!.left == null || brother.left!!.isBlack
        val b: Boolean = brother.right == null || brother.right!!.isBlack

        if (brother.isBlack) {
            if (brother.left?.isBlack == false && b && node == node.parent?.left) {
                brother.rightRotate()
            } else if (brother.right?.isBlack == false && a && node == node.parent?.right) {
                brother.leftRotate()
            }
        }
        deleteCase6(node)
    }

    private fun deleteCase6(node: Node<K, V>) {
        val brother = node.brother()

        if (node == node.parent!!.left) {
            brother?.right?.isBlack = true
            node.parent?.leftRotate()
        } else {
            brother?.left?.isBlack = true
            node.parent?.rightRotate()
        }

        if (root == node.parent)
            root = node.parent!!.parent
    }

    fun min(cur: Node<K, V>?): Node<K, V>? = when {
        cur?.left == null -> cur
        else -> min(cur.left)
    }

    fun max(cur: Node<K, V>?): Node<K, V>? = when {
        cur?.right == null -> cur
        else -> max(cur.right)
    }
}