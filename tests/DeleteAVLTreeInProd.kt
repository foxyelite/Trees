package AVLTree

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test

@DisplayName("Tests for Binary Search Tree")
internal class TestDeleteAVLTree {

    private val Tree = AVLTree<Int, Int>()

    @DisplayName("Delete check")
    @Test fun testDelete() {

        for (testInputLength in 1..10) {

            val testInput: MutableList<Int> = MutableList(testInputLength) { it + 1 }

            testInput.shuffle()

            for (x in testInput) {
                Tree.insert(x, x)
            }

            val m = testInputLength / 2

            println()
            println("$m $testInputLength:")

            for (x in testInput)
                print("$x ")

            println()

            for (i in 0 until m) {
                print("${testInput[i]} ")
                Tree.delete(testInput[i])
            }

            println()

            for (x in Tree) {
                println("$x")
            }

            println()
            println()

            for (i in 0 until m) {
                assertNull(Tree.find(testInput[i]))
            }

            for (i in m until testInputLength) {
                assertNotNull(Tree.find(testInput[i]))
            }

        }

    }
}


