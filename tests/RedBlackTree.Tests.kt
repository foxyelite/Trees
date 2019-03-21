package RedBlackTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Binary Search Tree")
internal class TestBinarySearchTree {

    private val RBTree = RedBlackTree<Int, Int>()

    @DisplayName("SearchExistingKey")
    @Test fun testSearchExistenKey() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            RBTree.insert(x, x)
        }

        for (x in a) {
            assertEquals(RBTree.find(x), Pair(x, x))
        }

    }

    @DisplayName("SearchNonExistingKey")
    @Test fun testSearchNonExistenKey() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            RBTree.insert(x, x)
        }

        assertNull(RBTree.find(0))
        assertNull(RBTree.find(101))

    }

    @DisplayName("InsertValidationCheck")
    @Test fun testInsert() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            RBTree.insert(x, x)
        }

        for (x in a) {
            assertEquals(RBTree.find(x), Pair(x, x))
        }

    }

    @DisplayName("DeleteRootValidationCheck")
    @Test fun testDeleteRoot() {

        RBTree.insert(1, 1)
        RBTree.delete(1)
        assertNull(RBTree.root)

    }

    @DisplayName("DeleteValidationCheck")
    @Test fun testDelete() {

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

    @DisplayName("IterateEmptyTreeValidation")
    @Test fun testIterateEmptyTree() {

        for (i in RBTree)
            assertEquals(-1, 1)

    }

    @DisplayName("IterateNormalTree")
    @Test fun testIterateNormal() {

        val a: MutableList<Int> = MutableList(200) { it + 1 }

        a.shuffle()

        for (x in a) {
            RBTree.insert(x, x)
        }

        var cur = 0

        for (i in RBTree) {
            ++cur
            assertEquals(i, Pair(cur, cur))
        }

    }

}

