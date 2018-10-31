import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for graph.
 */
public class Graph {
    /**
     * { vertexes }.
     */
    private final int v;
    /**
     * { edges }.
     */
    private int e;
    /**
     * { matrix of two dimensional }.
     */
    private boolean[][] adj;

    // empty graph with V vertices

    /**
     * Constructs the object.
     *
     * @param      v     { parameter_description }
     */
    public Graph(final int v1) {
// if (V < 0) throw new IllegalArgumentException("Too few vertices");
        this.v = v1;
        this.e = 0;
        this.adj = new boolean[v][v];
    }

    // and edges

    /**
     * {  number of vertices }.
     * its complexity is O(1).
     *
     * @return     { v }
     */
    public int V() {
        return v;
    }
    /**
     * {  number of edges }.
     * its complexity is O(1).
     *
     * @return     { e }
     */
    public int E() { return e; }


    // add undirected edge v-w

    /**
     * Adds an edge.
     * its complexity is O(1).
     *
     * @param      v1     { vertex1 }
     * @param      w1     { vertex2 }
     */
    public void addEdge(final int v1, final int w1) {
        if (!adj[v1][w1]) e++;
        adj[v1][w1] = true;
        adj[w1][v1] = true;
    }


    /**
     * return list of neighbors of v.
     *
     * @param      v1     { vertex }
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Integer> adj(final int v1) {
        return new AdjIterator(v1);
    }

    // support iteration over graph vertices

    /**
     * Class for adj iterator.
     */
    private class AdjIterator implements Iterator<Integer>, Iterable<Integer> {
        /**
         * { vertex }
         */
        private int v1;
        /**
         * { edge }
         */
        private int w = 0;
        /**
         * Constructs the object.
         *
         * @param      v     { vertex }
         */
        AdjIterator(int v2) {
            this.v1 = v2;
        }

        /**
         * { iterates through the graph }.
         *
         * @return     { item present }
         */
        public Iterator<Integer> iterator() {
            return this;
        }

        /**
         * Determines if it has next.
         * its complexity is O(n).
         *
         * @return     True if has next, False otherwise.
         */
        public boolean hasNext() {
            while (w < v) {
                if (adj[v1][w]) return true;
                w++;
            }
            return false;
        }

        /**
         * { returns next value }
         *
         * @return     { integer }
         */
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return w++;
        }

    }



}
