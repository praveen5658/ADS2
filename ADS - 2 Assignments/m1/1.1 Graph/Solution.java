/**
 * @author : Praveen N
 * Date : 29th October,2018.
 */
import java.util.Scanner;
/**
 * Interface for graph.
 */
interface Graph {
    /**
     * { function for number of vertices }.
     *
     * @return     { description_of_the_return_value }
     */
    int numberofVertices();
    /**
     * { function for number of edges }.
     *
     * @return     { description_of_the_return_value }
     */
    int numberofEdges();
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    void addEdge(int v, int w);
    /**
     * { function to get vertices adjacent to v }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    Iterable<Integer> adj(int v);
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    boolean hasEdge(int v, int w);
}

/**==========================================================================
*          List of adjacencies.
*==========================================================================*/
class AdjacencyList implements Graph {
    /**
     * { variable for Bags array }.
     */
    private Bag<Integer>[] bags;
    /**
     * { variable for vertex value }.
     */
    private int vertexval;
    /**
     * { variable for edge num }.
     */
    private int edgenum;
    /**
     * Constructs the object.
     *
     * @param      vertex  The vertex
     */
    AdjacencyList(final int vertex) {
        this.vertexval = vertex;
        bags = (Bag<Integer>[]) new Bag[vertex];
        for (int l = 0; l < vertex; l++) {
            bags[l] = new Bag();
        }
        this.edgenum = 0;
    }
    /**
     * { function for number of vertices }.
     *
     * @return     { description_of_the_return_value }
     */
    public int numberofVertices() {
        return this.vertexval;
    }
    /**
     * { function for number of edges }.
     *
     * @return     { description_of_the_return_value }
     */
    public int numberofEdges() {
        return this.edgenum;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        edgenum++;
        if (v == w || hasEdge(v, w)) {
            edgenum--;
        }
        bags[v].add(w);
        bags[w].add(v);
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        for (Integer eachval : bags[v]) {
            if (eachval == w) {
                return true;
            }
        }
        return false;
    }
    /**
     * { function for Iterator }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        Queue<Integer> queue = new Queue<>();
        for (Integer eachval : bags[v]) {
            queue.enqueue(eachval);
        }
        return queue;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        if (edgenum == 0) {
            System.out.println(vertexval + " vertices, "
                               + edgenum + " edges");
            System.out.println("No edges");
            return null;
        }
        System.out.println(vertexval + " vertices, "
                           + edgenum + " edges");
        return null;
    }
}
/**==========================================================================
*          Class for adjacency matrix.
*==========================================================================*/
class AdjacencyMatrix implements Graph {
    /**
     * { variable for edgenum }.
     */
    private int edgenum;
    /**
     * { variable for adj matrix }.
     */
    private int[][] adjmatrix;
    /**
     * { variable for vertex val }.
     */
    private int vertexval;
    /**
     * Constructs the object.
     *
     * @param      vertex  The vertex
     */
    AdjacencyMatrix(final int vertex) {
        this.vertexval = vertex;
        adjmatrix = new int[vertex][vertex];
        this.edgenum = 0;
    }
    /**
     * { function for number of vertices }.
     *
     * @return     { description_of_the_return_value }
     */
    public int numberofVertices() {
        return this.vertexval;
    }
    /**
     * { function for number of edges }.
     *
     * @return     { description_of_the_return_value }
     */
    public int numberofEdges() {
        return this.edgenum;
    }
    /**
     * Adds an edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     */
    public void addEdge(final int v, final int w) {
        if (v == w || hasEdge(v, w)) {
            return;
        }
        adjmatrix[v][w] = 1;
        adjmatrix[w][v] = 1;
        edgenum++;
    }
    /**
     * Determines if it has edge.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     True if has edge, False otherwise.
     */
    public boolean hasEdge(final int v, final int w) {
        return adjmatrix[v][w] == 1;
    }
    /**
     * { function for Iterator }.
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v) {
        return null;
    }
    /**
     * Returns a string representation of the object.
     *
     * @return     String representation of the object.
     */
    public String toString() {
        if (edgenum == 0) {
            System.out.println(vertexval + " vertices, "
                               + edgenum + " edges");
            System.out.println("No edges");
            return null;
        }
        System.out.println(vertexval + " vertices, "
                           + edgenum + " edges");
        for (int i = 0; i < vertexval; i++) {
            String str = "";
            for (int j = 0; j < vertexval; j++) {
                str = str + adjmatrix[i][j] + " ";
            }
            System.out.println(str);
        }
        return null;
    }
}
/**==========================================================================
*          Class for adjacency matrix.
*==========================================================================*/
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**
     * Main Function.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        String lineone = scan.nextLine();
        int vertexnum = Integer.parseInt(scan.nextLine());
        int edgenum = Integer.parseInt(scan.nextLine());
        String[] placenames = scan.nextLine().split(",");
        // SeparateChainingHashST<Integer, String> schsobj =
        //                   new SeparateChainingHashST<>();
        // int count = 0;
        // for (String eachname : placenames) {
        //  schsobj.put(count, eachname);
        //  count++;
        // }
        switch (lineone) {
        case "List":
            AdjacencyList listobj = new AdjacencyList(vertexnum);
            for (int k = 0; k < edgenum; k++) {
                String[] edges = scan.nextLine().split(" ");
                listobj.addEdge(Integer.parseInt(edges[0]),
                                Integer.parseInt(edges[1]));
            }
            listobj.toString();

            for (int j = 0; j < vertexnum; j++) {
                String str = "";
                if (listobj.numberofEdges() == 0) {
                    break;
                }
                str = str + placenames[j] + ": ";
                for (Integer each : listobj.adj(j)) {
                    str = str + placenames[each] + " ";
                }
                System.out.println(str);
            }
            break;
        case "Matrix":
            AdjacencyMatrix matrixobj =
                new AdjacencyMatrix(vertexnum);
            for (int i = 0; i < edgenum; i++) {
                String[] edges = scan.nextLine().split(" ");
                matrixobj.addEdge(Integer.parseInt(edges[0]),
                                  Integer.parseInt(edges[1]));
            }
            matrixobj.toString();
            break;
        default:
            break;
        }
    }
}

