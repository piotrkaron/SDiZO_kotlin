package main.algorithms

import main.Graph

class BellmanFordAlg(private val graph: Graph, private val startPoint: Int) {

    private var d = IntArray(graph.V){Int.MAX_VALUE}
    var p = IntArray(graph.V){-1}

    private fun clear(){
         d = IntArray(graph.V){Int.MAX_VALUE}
         p = IntArray(graph.V){-1}
    }

    fun shortestPathUsingAdjList(): Boolean{
        clear()
        var test = false

        d[startPoint] = 0

        for(i in 1 until graph.V){
            test = true

            for(x in 0 until graph.V){
                val neighbors = graph.adjList[x]

                for(y in neighbors){
                    if(d[x] != Int.MAX_VALUE && d[y.name] > (d[x] + y.costTo)) {

                        test = false
                        d[y.name] = d[x] + y.costTo
                        p[y.name] = x
                    }
                }
            }
            if(test) return true
        }

        for(x in 0 until graph.V){
            val neighbors = graph.adjList[x]

            for(y in neighbors){
                if (d[x] != Int.MAX_VALUE && d[y.name] > (d[x] + y.costTo))
                    return false
            }
        }

        return true
    }

    fun shortestPathUsingAdjMatrix(): Boolean{
        clear()
        var test = false

        d[startPoint] = 0

        for(i in 0 until graph.V){
            test = true

            for(x in 0 until graph.V){
                val neighbors = graph.adjMatrix[x]

                for((vert,cost) in neighbors.withIndex()){
                    if(d[vert] != Int.MAX_VALUE && d[x] != Int.MAX_VALUE && d[vert] > d[x] + cost) {

                        test = false
                        d[vert] = d[x] + cost
                        p[vert] = x
                    }
                }
            }
            if(test) return true
        }

        for(x in 0 until graph.V){
            val neighbors = graph.adjMatrix[x]

            for((vert,cost) in neighbors.withIndex()){
                if(d[vert] > d[x] + cost) {
                    return false;
                }
            }
        }

        return true
    }

    fun getPaths(): Array<MutableList<String>>{
        val result = Array(graph.V){mutableListOf<String>()}

        for(i in p.indices){

            var ind: Int = i

            do {
                ind = p[ind]
                if(ind > -1) result[i].add("$ind")
            }while (ind > -1)
            result[i].add("cost:${d[i]}")
        }

        return result
    }
}