package RedBlackTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Red Black Tree")
internal class TestRedBlackTree {
    private val RBTree = RedBlackTree<Int, Int>()

    @DisplayName("Search for an existing key 👍🏻passed successfully")
    @Test
    fun testFindExistenKey() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        for (x in a) {
            assertEquals(RBTree.find(x), Pair(x, x))
        }
    }

    @DisplayName("Search for an non existing key 👍🏻passed successfully")
    @Test
    fun testFindNonExistenKey() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        assertNull(RBTree.find(0))
        assertNull(RBTree.find(101))
    }

    @DisplayName("Add validation check 👍🏻passed successfully")
    @Test
    fun testInsert() {
        val a: MutableList<Int> = MutableList(100) { it + 1 }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        for (x in a) {
            assertEquals(RBTree.find(x), Pair(x, x))
        }
    }

    @DisplayName("Delete validation check 👍🏻passed successfully")
    @Test
    fun testDelete() {
        RBTree.insert(1, 1)
        RBTree.delete(1)
        assertNull(RBTree.root)
        val a: MutableList<Int> = MutableList(200) { it + 1 }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        val m = (49..99).random()
        for (i in 0..m) {
            RBTree.delete(a[i])
        }
        for (i in 0..m) {
            assertNull(RBTree.find(a[i]))
        }
        for (i in (m + 1)..199) {
            assertNotNull(RBTree.find(a[i]))
        }
    }

    @DisplayName("Search for an minimum in tree 👍🏻passed successfully")
    @Test
    fun testMin() {
        val a: MutableList<Int> = MutableList(100) { (1..1000).random() }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        assertEquals(RBTree.min(RBTree.root)?.value, a.min())
    }

    @DisplayName("Search for an maximum in tree 👍🏻passed successfully")
    @Test
    fun testMax() {
        val a: MutableList<Int> = MutableList(100) { (1..1000).random() }
        a.shuffle()
        for (x in a) {
            RBTree.insert(x, x)
        }
        assertEquals(RBTree.max(RBTree.root)?.value, a.max())
    }
}

