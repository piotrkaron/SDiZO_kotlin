import main.Graph
import main.GraphLoader
import main.Utils
import main.algorithms.DijkstrasAlg
import main.algorithms.MST
import java.io.File

//package main
//
//import main.algorithms.DijkstrasAlg
//
//class Main {
//    companion object {
//
//        @JvmStatic
//        fun main(args: Array<String>) {
////            val graf1 = GraphLoader.loadFile("./dane_testowe/dane_mst.txt")
////            val kruskalMst = MST.kruskalUsingQueue(graf1)
////            val kruskalMst2 = MST.primUsingAdjList(graf1)
////            val gp1 = GraphPrinter(graf1)
////            val gp2 = GraphPrinter(kruskalMst)
////            val gp3 = GraphPrinter(kruskalMst2)
////
////            println(graf1.weight())
////            println(kruskalMst.weight())
////            println(kruskalMst2.weight())
////            gp1.print()
////            gp2.print()
////            gp3.print()
//
//            val graf1 = GraphLoader.loadFile("./dane_testowe/usfca.txt")
//            // poprawne -1,0,0,1,2,3,1,5
//            println(graf1.adjList)
//           // val gp4 = GraphPrinter(graf1).print()
//            val dij = DijkstrasAlg(graf1, 0)
//            dij.shortestPathAdjList()
//            println(Utils.arrayToString(dij.predecessors))
//            println(Utils.arrayToStringLines(dij.getPaths()))
//            dij.shortestPathAdjMatrix()
//            println(Utils.arrayToString(dij.predecessors))
//            println(Utils.arrayToStringLines(dij.getPaths()))
//
//            val graf22= GraphLoader.loadFile("./dane_testowe/dane_droga_BF.txt")
//            val dij22 = DijkstrasAlg(graf22, graf22.startVert)
//            dij22.shortestPathAdjList()
//            println(Utils.arrayToString(dij22.predecessors))
//        }
//    }
//}

class Main {

    private var graph: Graph = Graph.randomGraph(10,0.99)
    private var isGenerated = false

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

/*
            val range = IntRange(1, 1000000)
            val counts = intArrayOf(10, 100, 500, 1000, 2000, 3000, 5000, 7000, 10000, 13000, 20000, 30000, 50000, 70_000, 100_000, 200_000, 300_000, 500_000, 800_000, 1_000_000)
            val times = 10

            counts.forEach { print(TestUnit(it, range).arrayAddTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).listAddTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).heapAddTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).bstAddTest(times).toString() + "\n") }
//            counts.forEach { print(TestUnit(it, range).avlAddTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).arrayDeleteTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).listDeleteTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).heapDeleteTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).bstDeleteTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).arrayFindTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).listFindTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).heapFindTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).bstFindTest(times).toString() + "\n") }
 //           counts.forEach { print(TestUnit(it, range).avlFindTest(times).toString() + "\n") }

            counts.forEach { print(TestUnit(it, range).bstAddFixTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).bstDeleteFixTest(times).toString() + "\n") }
            counts.forEach { print(TestUnit(it, range).bstBalanceTest(times).toString() + "\n") }
*/

            val main = Main()
            with(main) {
                var opt: String

                do {
                    printMenu()
                    opt = readOption()
                    when (opt) {
                        "1" -> loadGraph()
                        "2" -> generateGraph()
                        "3" -> printGraph()
                        "4" -> prim()
                        "5" -> kruskal()
                        "6" -> dijkstra()
                        "7" -> bellman()
                    }

                } while (opt != "0")
            }
        }
    }

    private fun loadGraph(){
        val file = askForFile()
        if(file == null || !file.exists()) p("Plik nie istnieje")

        graph = GraphLoader.loadFile(file!!)
        isGenerated = false
    }
    private fun generateGraph(){
        val vertCount = askCount("Podaj liczbę wierzchołków: ")
        val density = askCount("Podaj gęstość (0,100>: ")

        graph = Graph.randomGraph(vertCount, density.toDouble() / 100.0)
        isGenerated = true
    }
    private fun printGraph(){
        println("###### Lista sąsiedztwa #####")
        println(graph.adjList)
        println("\n###### Macierz sąsiedztwa #####")
        println(graph.adjMatrix)
        print("\n")
    }
    private fun prim(){
        val list = MST.primUsingAdjList(graph)
        val matrix = MST.primUsingAdjMatrix(graph)

        println("###### Prim z użciem listy sąsiedztwa #####")
        println(list.adjList)
        println("\n###### Prim z użyciem macierzy sąsiedztwa #####")
        println(matrix.adjList)
        print("\n")
    }
    private fun kruskal(){
        val list = MST.kruskalUsingQueue(graph)
        val matrix = MST.kruskalUsingAdjMatrix(graph)

        println("###### Kruskal z użciem listy sąsiedztwa #####")
        println(list.adjList)
        println("\n###### Kruskal z użyciem macierzy sąsiedztwa #####")
        println(matrix.adjList)
        print("\n")
    }
    private fun dijkstra(){

        val startPoint = if(isGenerated) askCount("Podaj wierzchołek startowy: ")
        else 0

        val dij = DijkstrasAlg(graph, startPoint)

        dij.shortestPathAdjList()
        println("###### Dijkstra z użciem listy sąsiedztwa #####")
        println(Utils.arrayToStringLines(dij.getPaths()))
        println()

        dij.shortestPathAdjMatrix()
        println("\n###### Dijkstra z użyciem macierzy sąsiedztwa #####")
        println(Utils.arrayToStringLines(dij.getPaths()))
        println()
    }
    private fun bellman(){

    }

    private fun askCount(s: String): Int {
        p(s)
        return readOption().toInt()
    }


    private fun readOption(): String {
        return readLine().toString().trim()
    }

    private fun p(any: Any) {
        println(any)
    }

    private fun askForFile(): File? {
        do {
            println("Podaj ścieżkę pliku:")
            val path = readLine()?.trim()

            val file = File(path)

            if (file.exists()) return file
        } while (path != "0")

        return null
    }

    private fun printMenu() {
        print(
                "=======Menu Główne=========\n" +
                        "1. Wczytaj\n" +
                        "2. Generuj\n" +
                        "3. Wyświetl\n" +
                        "4. alg. Prima\n" +
                        "5. alg. Kruskala\n" +
                        "6. alg. Dijkstry\n" +
                        "7. alg. BF\n" +
                        "8. Zakończ\n" +
                        "Podaj opcję: "
        )
    }
}