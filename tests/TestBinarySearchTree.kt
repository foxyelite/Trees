package BinarySearchTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Binary Search Tree")
internal class TestBinarySearchTree {
    private val BSTree = BinarySearchTree<Int, Int>()

    @DisplayName("Search for an existing key 👍🏻passed successfully")
    @Test
    fun testFindExistenKey() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        for (x in a) {
            assertEquals(BSTree.find(x), Pair(x, x))
        }
    }

    @DisplayName("Search for an non existing key 👍🏻passed successfully")
    @Test
    fun testFindNonExistenKey() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        assertNull(BSTree.find(0))
        assertNull(BSTree.find(101))
    }

    @DisplayName("Add validation check 👍🏻passed successfully")
    @Test
    fun testInsert() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        for (x in a) {
            assertEquals(BSTree.find(x), Pair(x, x))
        }
    }

    @DisplayName("Delete validation check 👍🏻passed successfully")
    @Test
    fun testDelete() {
        BSTree.insert(1, 1)
        BSTree.delete(1)
        assertNull(BSTree.root)
        val a: MutableList<Int> = MutableList(200) { it + 1 }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        val m = (49..99).random()
        for (i in 0..m) {
            BSTree.delete(a[i])
        }
        for (i in 0..m) {
            assertNull(BSTree.find(a[i]))
        }
        for (i in (m + 1)..199) {
            assertNotNull(BSTree.find(a[i]))
        }
    }

    @DisplayName("Search for an minimum in tree 👍🏻passed successfully")
    @Test
    fun testMin() {
        val a: MutableList<Int> = MutableList(100) { (1..1000).random() }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        assertEquals(BSTree.min(BSTree.root)?.value, a.min())
    }

    @DisplayName("Search for an maximum in tree 👍🏻passed successfully")
    @Test
    fun testMax() {
        val a: MutableList<Int> = MutableList(100) { (1..1000).random() }
        a.shuffle()
        for (x in a) {
            BSTree.insert(x, x)
        }
        assertEquals(BSTree.max(BSTree.root)?.value, a.max())
    }
}

