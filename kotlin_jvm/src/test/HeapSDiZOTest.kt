package sample.helloworld.test

import org.junit.Before
import org.junit.Test
import sample.helloworld.structures.FileLoader
import sample.helloworld.structures.heap.HeapSDiZO
import java.io.File
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class HeapSDiZOTest{

    var heap = HeapSDiZO(5)

    @Before
    fun setUp(){
        heap = FileLoader.heapOf(File("heap1.txt"))

    }

    @Test
    fun loadTest(){
        val heap = FileLoader.heapOf(File("heap1.txt"))

        heap.printTree()
    }

    @Test
    fun findTest(){
        assertNotEquals(Integer.MAX_VALUE, heap.find(7))
    }

    @Test
    fun findFarthestRight(){
        assertEquals(8, heap.findFarthestRight(0))
    }

    @Test
    fun deleteTest(){
        heap.printTree()
        println()
        heap.delete(10)
        heap.printTree()

        heap.delete(11)
        println()
        heap.printTree()
    }

    @Test
    fun testowanieZ1(){
        with(heap){
            delete(11)
            printTree()
            println("Del 11")

            delete(1)
            printTree()
            println("Del 1")

            delete(9)
            printTree()
            println("Del 9")

            insert(11)
            printTree()
            println("Add 11")

            insert(12)
            printTree()
            println("Add 12")
        }
    }

    @Test
    fun heap2(){
        heap = FileLoader.heapOf(File("heap2.txt"))

        with(heap){
            printTree()
            println()

            delete(7)
            printTree()
            println()

            delete(33)
            printTree()
            println()
        }
    }
}