package BinarySearchTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Binary Search Tree")
internal class TestBinarySearchTree {

    private val BSTree = BinarySearchTree<Int, Int>()

    @DisplayName("SearchExistingKey")
    @Test fun testSearchExistenKey() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            BSTree.insert(x, x)
        }

        for (x in a) {
            assertEquals(BSTree.find(x), Pair(x, x))
        }

    }

    @DisplayName("SearchNonExistingKey")
    @Test fun testSearchNonExistenKey() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            BSTree.insert(x, x)
        }

        assertNull(BSTree.find(0))
        assertNull(BSTree.find(101))

    }

    @DisplayName("InsertValidationCheck")
    @Test fun testInsert() {

        val a: MutableList<Int> = MutableList(100) { it + 1 }

        a.shuffle()

        for (x in a) {
            BSTree.insert(x, x)
        }

        for (x in a) {
            assertEquals(BSTree.find(x), Pair(x, x))
        }

    }

    @DisplayName("DeleteRootValidationCheck")
    @Test fun testDeleteRoot() {

        BSTree.insert(1, 1)
        BSTree.delete(1)
        assertNull(BSTree.root)

    }

    @DisplayName("DeleteValidationCheck")
    @Test fun testDelete() {

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

    @DisplayName("IterateEmptyTreeValidation")
    @Test fun testIterateEmptyTree() {

        for (i in BSTree)
            assertEquals(-1, 1)

    }

    @DisplayName("IterateNormalTree")
    @Test fun testIterateNormal() {

        val a: MutableList<Int> = MutableList(200) { it + 1 }

        a.shuffle()

        for (x in a) {
            BSTree.insert(x, x)
        }

        var cur = 0

        for (i in BSTree) {
            ++cur
            assertEquals(i, Pair(cur, cur))
        }

    }

}

