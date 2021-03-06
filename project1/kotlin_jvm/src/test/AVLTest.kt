import org.junit.Test
import structures.avl.AVLNode
import structures.avl.AVLTree
import structures.avl.AbstractAVLNode
import structures.avl.NilAVL
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class AVLTest {
    @Test
    fun insertTest() {
        val avl = AVLTree()

        with(avl) {
            insert(12)
            insert(24)
            insert(13)
            insert(4)
            insert(5)
            insert(6)
            insert(9)

            with(root) {
                assertEquals(this.key, 12)

                assertEquals(leftChild.key, 5)
                assertEquals(leftChild.leftChild.key, 4)
                assertEquals(leftChild.rightChild.key, 6)
                assertEquals(leftChild.rightChild.rightChild.key, 9)

                assertEquals(rightChild.key, 13)
                assertEquals(rightChild.rightChild.key, 24)
            }

            printTree()
        }

    }

    @Test
    fun findTest(){
        val avl = AVLTree()

        with(avl){
            root = AVLNode(10,0)
            val node = AVLNode(22,0)
            root.rightChild = node
            node.parent = root

            assertEquals(find(22), node)
            assertTrue(find(1000) is NilAVL)
        }
    }

    @Test
    fun deleteTest(){
        val avl = AVLTree()

        with(avl) {
            insert(12)
            insert(24)
            insert(13)
            insert(4)
            insert(5)
            insert(6)
            insert(9)

            with(root) {
                assertEquals(key, 12)

                assertEquals(leftChild.key, 5)
                assertEquals(leftChild.leftChild.key, 4)
                assertEquals(leftChild.rightChild.key, 6)
                assertEquals(leftChild.rightChild.rightChild.key, 9)

                assertEquals(rightChild.key, 13)
                assertEquals(rightChild.rightChild.key, 24)
            }
        }

        avl.printTree()

        val deleted : AbstractAVLNode = avl.root.leftChild.rightChild.rightChild
        avl.delete(13)

//        with(avl.root) {
//                        assertEquals(key, 12)
//
//            assertEquals(leftChild.key, 6)
//            assertEquals(leftChild.leftChild.key, 4)
//            assertEquals(leftChild.rightChild.key, 9)
//
//            assertEquals(rightChild.key, 13)
//            assertEquals(rightChild.rightChild.key, 24)
//        }
        print("\n")
        avl.printTree()

    }

}

/*
package test

import org.junit.Test
import structures.avl.AVLNode
import structures.avl.AVLTree
import structures.avl.AbstractAVLNode
import structures.avl.NilAVL
import kotlin.test.assertEquals

class AVLTest {

    @Test
    fun insertTest() {
        val avl = AVLTree()

        with(avl) {
            insert(12)
            insert(24)
            insert(13)
            insert(4)
            insert(5)
            insert(6)
            insert(9)

            with(root) {
                assertEquals(key, 12)

                assertEquals(leftChild.key, 5)
                assertEquals(leftChild.leftChild.key, 4)
                assertEquals(leftChild.rightChild.key, 6)
                assertEquals(leftChild.rightChild.rightChild.key, 9)

                assertEquals(rightChild.key, 13)
                assertEquals(rightChild.rightChild.key, 24)
            }
        }

        avl.printTree()
    }


    @Test
    fun deleteTest(){
        val avl = AVLTree()

        with(avl) {
            insert(12)
            insert(24)
            insert(13)
            insert(4)
            insert(5)
            insert(6)
            insert(9)

            with(root) {
                assertEquals(key, 12)

                assertEquals(leftChild.key, 5)
                assertEquals(leftChild.leftChild.key, 4)
                assertEquals(leftChild.rightChild.key, 6)
                assertEquals(leftChild.rightChild.rightChild.key, 9)

                assertEquals(rightChild.key, 13)
                assertEquals(rightChild.rightChild.key, 24)
            }
        }

        avl.printTree()

        val deleted : AbstractAVLNode = avl.root.leftChild.rightChild.rightChild
        assertEquals(deleted,avl.delete(9))

        with(avl.root) {
//            assertEquals(key, 12)
//
//            assertEquals(leftChild.key, 6)
//            assertEquals(leftChild.leftChild.key, 4)
//            assertEquals(leftChild.rightChild.key, 9)
//
//            assertEquals(rightChild.key, 13)
//            assertEquals(rightChild.rightChild.key, 24)
        }
        avl.printTree()

    }

    @Test
    fun rrTest() {
        val tree = AVLTree()

        val node1 = AVLNode(1, -2, NilAVL, NilAVL, NilAVL)
        val node2 = AVLNode(2, -1, node1, NilAVL, NilAVL)
        val node3 = AVLNode(3, 0, node2, NilAVL, NilAVL)

        node1.rightChild = node2
        node2.rightChild = node3
        tree.root = node1

        tree.printTree()
        println("\n--------\n")
        tree.rotateRR(node1)
        tree.printTree()

        with(tree) {
            assertEquals(root, node2)
            assertEquals(root.rightChild, node3)
            assertEquals(root.leftChild, node1)

            assertEquals(0, root.balance)
            assertEquals(0, root.rightChild.balance)
            assertEquals(0, root.leftChild.balance)
        }

    }

    @Test
    fun llTest() {
        val tree = AVLTree()

        val node1 = AVLNode(1, 2, NilAVL, NilAVL, NilAVL)
        val node2 = AVLNode(2, 1, node1, NilAVL, NilAVL)
        val node3 = AVLNode(3, 0, node2, NilAVL, NilAVL)

        node1.leftChild = node2
        node2.leftChild = node3
        tree.root = node1

        tree.printTree()
        println("\n--------\n")
        tree.rotateLL(node1)
        tree.printTree()

        with(tree) {
            assertEquals(root, node2)
            assertEquals(root.rightChild, node1)
            assertEquals(root.leftChild, node3)

            assertEquals(0, root.balance)
            assertEquals(0, root.rightChild.balance)
            assertEquals(0, root.leftChild.balance)
        }

    }

    @Test
    fun rlTest() {
        val tree = AVLTree()

        val node1 = AVLNode(1, -2, NilAVL, NilAVL, NilAVL)
        val node2 = AVLNode(2, -1, node1, NilAVL, NilAVL)
        val node3 = AVLNode(3, 0, node2, NilAVL, NilAVL)

        node1.rightChild = node2
        node2.leftChild = node3
        tree.root = node1

        tree.printTree()
        println("\n--------\n")
        tree.rotateRL(node1)
        tree.printTree()

        with(tree) {
            assertEquals(root, node3)
            assertEquals(root.rightChild, node2)
            assertEquals(root.leftChild, node1)

            assertEquals(0, root.balance)
            assertEquals(0, root.rightChild.balance)
            assertEquals(0, root.leftChild.balance)
        }
    }

    @Test
    fun lrTest() {
        val tree = AVLTree()

        val node1 = AVLNode(1, -2, NilAVL, NilAVL, NilAVL)
        val node2 = AVLNode(2, -1, node1, NilAVL, NilAVL)
        val node3 = AVLNode(3, 0, node2, NilAVL, NilAVL)

        node1.leftChild = node2
        node2.rightChild = node3
        tree.root = node1

        tree.printTree()
        println("\n--------\n")
        tree.rotateLR(node1)
        tree.printTree()

        with(tree) {
            assertEquals(root, node3)
            assertEquals(root.rightChild, node1)
            assertEquals(root.leftChild, node2)

            assertEquals(0, root.balance)
            assertEquals(0, root.rightChild.balance)
            assertEquals(0, root.leftChild.balance)
        }
    }

    @Test
    fun rlBalanceFactorTest() {
        val tree = AVLTree()

        val node1 = AVLNode(1, -2, NilAVL, NilAVL, NilAVL)
        val node2 = AVLNode(2, 1, node1, NilAVL, NilAVL)
        val node3 = AVLNode(3, 1, node2, NilAVL, NilAVL)

        node1.rightChild = node2
        node2.leftChild = node3
        tree.root = node1

        tree.printTree()

        tree.rotateRL(node1)

        tree.printTree()

        with(tree) {
            assertEquals(root, node3)
            assertEquals(root.rightChild, node2)
            assertEquals(root.leftChild, node1)

            assertEquals(0, root.balance)
            assertEquals(-1, root.rightChild.balance)
            assertEquals(0, root.leftChild.balance)
        }
    }

}*/
