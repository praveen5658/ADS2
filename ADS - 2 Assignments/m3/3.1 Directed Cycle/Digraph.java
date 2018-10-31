/**
 * Class for digraph.
 */
public class Digraph {
    /**
     *  number of vertices in this digraph.
     */

    private final int v;
    /**
     * number of edges in this digraph.
     */
    private int e;
    /**
     * adj[v] = adjacency list for vertex v.
     */
    private Bag<Integer>[] adj;
    /**
     * Constructs the object.
     *
     * @param      v1     { vertex }
     */
    public Digraph(final int v1) {

        this.v = v1;
        this.e = 0;
        adj = (Bag<Integer>[]) new Bag[v];
        for (int i = 0; i < v; i++) {
            adj[i] = new Bag<Integer>();
        }
    }

    /**
     * returns no. of vertexes.
     *
     * @return     { vertexes }
     */
    public int vertex() {
        return v;
    }

    /**
     * no. of edges.
     *
     * @return     { edges }
     */
    public int edges() {
        return e;
    }

    /**
     * Adds an edge.
     *
     * @param      v1    { vertex1 }
     * @param      w1     { vertex2 }
     */
    public void addEdge(final int v1, final int w1) {

        adj[v1].add(w1);
        e++;
    }

    /**
     * returns every value using iterator.
     *
     * @param      v1     { vertex }
     *
     * @return     { a value }
     */
    public Iterable<Integer> adj(final int v1) {

        return adj[v1];
    }
}
