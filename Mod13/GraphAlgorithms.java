import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

/**
 * Your implementation of various graph traversal algorithms.
 * 
 * 
 * 
 */
public class GraphAlgorithms {



    ///maybe write a joint helper method to track visited verticies so that time effiecny is 0(1)
    /// 
    /// 
    /**
     * Performs a breadth first search (bfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * You may import/use java.util.Set, java.util.List, java.util.Queue, and
     * any classes that implement the aforementioned interfaces, as long as they
     * are efficient.
     *
     * The only instance of java.util.Map that you should use is the adjacency
     * list from graph. DO NOT create new instances of Map for BFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the bfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> bfs(Vertex<T> start, Graph<T> graph) {
    Set<Vertex<T>> visited = new HashSet<>(); 
     Queue <Vertex<T>> queue = new ArrayDeque<>();
     List <Vertex<T>> result = new ArrayList<>(); //to store our answer. this will be the thing to be returned 
     
     visited.add(start); //add start vertex to the visited set
     queue.add(start); //add start vertex to the queue

     while(!queue.isEmpty()){ //while queue is not empty
      Vertex<T> current = queue.remove();
      result.add(current);
     


     for (VertexDistance<T> neighbor : graph.getAdjList().get(current)) {//for each neighbor 
        Vertex<T> v = neighbor.getVertex();
        if (!visited.contains(v)){
            visited.add(v);
            queue.add(v);
        }
     }

    }
     return result;
        //algo should automatically account for parallel edges and self loops 
      // should have a queue, set, and a list
        // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }

    /**
     * Performs a depth first search (dfs) on the input graph, starting at
     * the parameterized starting vertex.
     *
     * When exploring a vertex, explore in the order of neighbors returned by
     * the adjacency list. Failure to do so may cause you to lose points.
     *
     * NOTE: This method should be implemented recursively. You may need to
     * create a helper method.
     *
     * You may import/use java.util.Set, java.util.List, and any classes that
     * implement the aforementioned interfaces, as long as they are efficient.
     *
     * The only instance of java.util.Map that you may use is the adjacency list
     * from graph. DO NOT create new instances of Map for DFS
     * (storing the adjacency list in a variable is fine).
     *
     * DO NOT modify the structure of the graph. The graph should be unmodified
     * after this method terminates.
     *
     * You may assume that the passed in start vertex and graph will not be null.
     * You may assume that the start vertex exists in the graph.
     *
     * @param <T>   The generic typing of the data.
     * @param start The vertex to begin the dfs on.
     * @param graph The graph to search through.
     * @return List of vertices in visited order.
     */
    public static <T> List<Vertex<T>> dfs(Vertex<T> start, Graph<T> graph) {
       Set<Vertex<T>> visited = new HashSet <>();//initalize a visited set 
       List<Vertex<T>> result = new ArrayList<>(); //stores the order of visited vertexes so that we can return it
       dfsHelper(start, graph, visited, result);
       return result;
        //algo should automatically account for parallel edges and self loops
       //shold have a set and a list 
       // WRITE YOUR CODE HERE (DO NOT MODIFY METHOD HEADER)!
    }


    private static <T> void dfsHelper(Vertex<T> current, Graph<T> graph, Set<Vertex<T>> visited, List<Vertex<T>> result){
        visited.add(current);
        result.add(current);
        for(VertexDistance<T> neighbor: graph.getAdjList().get(current)){ //for each vertex that is adjacent to the current one 
            Vertex<T> v = neighbor.getVertex(); //get the vertex of each neighbor
            if (!visited.contains(v)) { //if we have not visited this vertex yet
                dfsHelper(v, graph, visited, result); //recursive call to dfsHelper
            }
        }
    }
}
