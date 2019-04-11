package RedBlackTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Binary Search Tree")
internal class TestRedBlackTree {

    private val Tree = RedBlackTree<Int, Int>()

    var maxBlackHeight: Int = -1

    fun checkStructure(node: Node<Int, Int>? = Tree.root, blackHeight: Int = 1): Boolean {

        if (node == null) return true

        if (node.isLeaf()) {
            if (maxBlackHeight == -1) {
                maxBlackHeight = blackHeight
            }
            if (maxBlackHeight != blackHeight) {
                return false
            }
            return true
        }

        if (node.isBlack == false) {
            if (node.left?.isBlack == false || node.right?.isBlack == false) {
                return false
            } else {
                return checkStructure(node.left, blackHeight + 1) &&
                        checkStructure(node.right, blackHeight + 1)
            }
        } else {
            val checkLeft: Boolean
            val checkRight: Boolean
            if (node.left?.isBlack == true) {
                checkLeft = checkStructure(node.left, blackHeight + 1)
            } else {
                checkLeft = checkStructure(node.left, blackHeight)
            }
            if (node.right?.isBlack == true) {
                checkRight = checkStructure(node.right, blackHeight + 1)
            } else {
                checkRight = checkStructure(node.right, blackHeight)
            }
            return checkLeft && checkRight
        }

    }

    @DisplayName("Search existing key")
    @Test
    fun testSearchExistingKey() {

        for (testInputLenght in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLenght) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            for (x in testInput) {
                assertEquals(Tree.find(x), Pair(x, x))
            }
        }

    }

    @DisplayName("Search in root")
    @Test
    fun testSearchInRoot() {

        Tree.insert(1, 1)
        assertEquals(Tree.find(1), Pair(1, 1))

    }

    @DisplayName("Search case 1")
    @Test
    fun testSearchCase1() {

        Tree.insert(1, 1)
        Tree.insert(2, 2)

        assertEquals(Tree.find(2), Pair(2, 2))
        assertEquals(Tree.find(1), Pair(1, 1))

    }

    @DisplayName("Search case 2")
    @Test
    fun testSearchCase2() {

        Tree.insert(2, 2)
        Tree.insert(1, 1)

        assertEquals(Tree.find(2), Pair(2, 2))
        assertEquals(Tree.find(1), Pair(1, 1))

    }

    @DisplayName("Search case 3")
    @Test
    fun testSearchCase3() {

        Tree.insert(1, 1)
        Tree.insert(2, 2)
        Tree.insert(3, 3)

        assertEquals(Tree.find(3), Pair(3, 3))
        assertEquals(Tree.find(2), Pair(2, 2))
        assertEquals(Tree.find(1), Pair(1, 1))

    }

    @DisplayName("Search case 4")
    @Test
    fun testSearchCase4() {

        Tree.insert(3, 3)
        Tree.insert(2, 2)
        Tree.insert(1, 1)

        assertEquals(Tree.find(3), Pair(3, 3))
        assertEquals(Tree.find(2), Pair(2, 2))
        assertEquals(Tree.find(1), Pair(1, 1))

    }


    @DisplayName("Search case 5")
    @Test
    fun testSearchCase5() {

        Tree.insert(2, 2)
        Tree.insert(3, 3)
        Tree.insert(1, 1)

        assertEquals(Tree.find(3), Pair(3, 3))
        assertEquals(Tree.find(2), Pair(2, 2))
        assertEquals(Tree.find(1), Pair(1, 1))

    }

    @DisplayName("Search nonexisting key in empty tree")
    @Test
    fun testSearchNonExistingKeyInEmptyTree() {

        assertNull(Tree.find(0))
        assertNull(Tree.find(101))

    }

    @DisplayName("Search non existing key in nonempty tree")
    @Test
    fun testSearchNonExistingKeyInNonEmptyTree() {

        val testInput: MutableList<Int> = MutableList(100) { it + 1 }

        testInput.shuffle()

        for (x in testInput) {
            Tree.insert(x, x)
        }

        assertNull(Tree.find(0))
        assertNull(Tree.find(101))

    }

    @DisplayName("Insert check")
    @Test
    fun testInsert() {

        for (testInputLenght in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLenght) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            for (x in testInput) {
                assertEquals(Tree.find(x), Pair(x, x))
            }

        }

    }

    @DisplayName("Insert save structure")
    @Test
    fun testInsertSaveStructure() {

        for (testInputLenght in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLenght) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Insert save structure Direct order")
    @Test
    fun testInsertSaveStructureDirectOrder() {

        for (testInputLenght in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLenght) { it + 1 }

            for (x in testInput) {
                Tree.insert(x, x)
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Insert save structure Reverse order")
    @Test
    fun testInsertSaveStructureReverseOrder() {

        for (testInputLenght in 0..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLenght) { testInputLenght - it }

            for (x in testInput) {
                Tree.insert(x, x)
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Delete root check")
    @Test
    fun testDeleteRoot() {

        Tree.insert(1, 1)
        Tree.delete(1)
        assertNull(Tree.root)

    }

    @DisplayName("Delete case 1")
    @Test
    fun testDeleteCase1() {

        Tree.insert(1, 1)
        Tree.insert(2, 2)

        Tree.delete(1)

        assertNotNull(Tree.find(2))
        assertNull(Tree.find(1))

    }

    @DisplayName("Delete case 2")
    @Test
    fun testDeleteCase2() {

        Tree.insert(2, 2)
        Tree.insert(1, 1)

        Tree.delete(2)

        assertNull(Tree.find(2))
        assertNotNull(Tree.find(1))

    }

    @DisplayName("Delete case 3")
    @Test
    fun testDeleteCase3() {

        Tree.insert(1, 1)
        Tree.insert(2, 2)
        Tree.insert(3, 3)

        Tree.delete(2)

        assertNotNull(Tree.find(3))
        assertNull(Tree.find(2))
        assertNotNull(Tree.find(1))

    }

    @DisplayName("Delete case 4")
    @Test
    fun testDeleteCase4() {

        Tree.insert(3, 3)
        Tree.insert(2, 2)
        Tree.insert(1, 1)

        Tree.delete(2)

        assertNotNull(Tree.find(3))
        assertNull(Tree.find(2))
        assertNotNull(Tree.find(1))

    }


    @DisplayName("Delete case 5")
    @Test
    fun testDeleteCase5() {

        Tree.insert(2, 2)
        Tree.insert(3, 3)
        Tree.insert(1, 1)

        Tree.delete(2)

        assertNotNull(Tree.find(3))
        assertNull(Tree.find(2))
        assertNotNull(Tree.find(1))

    }

    @DisplayName("Delete case 6")
    @Test
    fun testDeleteCase6() {

        Tree.insert(1, 1)
        Tree.insert(2, 2)

        Tree.delete(2)

        assertNull(Tree.find(2))
        assertNotNull(Tree.find(1))

    }

    @DisplayName("Delete case 7")
    @Test
    fun testDeleteCase7() {

        Tree.insert(2, 2)
        Tree.insert(1, 1)

        Tree.delete(1)

        assertNull(Tree.find(1))
        assertNotNull(Tree.find(2))

    }

    @DisplayName("Delete case 8")
    @Test
    fun testDeleteCase8() {

        Tree.insert(1, 1)
        Tree.insert(4, 4)
        Tree.insert(3, 3)
        Tree.insert(5, 5)

        Tree.delete(4)

        assertNull(Tree.find(4))
        assertNotNull(Tree.find(1))
        assertNotNull(Tree.find(3))
        assertNotNull(Tree.find(5))

    }

    @DisplayName("Delete check")
    @Test
    fun testDelete() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            val m = testInputLength / 2

            for (i in 0 until m) {
                Tree.delete(testInput[i])
            }

            for (i in 0 until m) {
                assertNull(Tree.find(testInput[i]))
            }

            for (i in m until testInputLength) {
                assertNotNull(Tree.find(testInput[i]))
            }

        }

    }

    @DisplayName("Delete save structure")
    @Test
    fun testDeleteSaveStructure() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            val m = testInputLength / 2

            for (i in 0 until m) {
                Tree.delete(testInput[i])
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Delete save structure, direct order")
    @Test
    fun testDeleteSaveStructureDirectOrder() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            for (x in testInput) {
                Tree.insert(x, x)
            }

            val m = testInputLength / 2

            for (i in 0 until m) {
                Tree.delete(testInput[i])
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Delete save structure, reverse order")
    @Test
    fun testDeleteSaveStructureReverseOrder() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { testInputLength - it }

            for (x in testInput) {
                Tree.insert(x, x)
            }

            val m = testInputLength / 2

            for (i in 0 until m) {
                Tree.delete(testInput[i])
                maxBlackHeight = -1
                assertTrue(checkStructure())
            }

        }

    }

    @DisplayName("Iterate empty tree")
    @Test
    fun testIterateEmptyTree() {

        for (i in Tree)
            assertEquals(-1, 1)

    }

    @DisplayName("Iterate normal tree")
    @Test
    fun testIterateNormal() {

        for (testInputLength in 1..1000) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            var cur = 0

            for (i in Tree) {
                ++cur
                assertEquals(i, Pair(cur, cur))
            }
        }

    }

}


