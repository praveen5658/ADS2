/**
 * Class for edge weighted digraph.
 */
public class EdgeWeightedDigraph {
    /**
     * { NEWLINE var }.
     */
    private static final String NEWLINE = System.getProperty("line.separator");
    /**
     * { Vertices variable }.
     */
    private final int vertices;
    /**
     * { E variable }.
     */
    private int edges;
    /**
     * { Bag of directed edges }.
     */
    private Bag<DirectedEdge>[] adj;
    /**
     * { indegree variable }.
     */
    private int[] indegree;
    /**
     * Constructs the object.
     *
     * @param      inputvertices  The inputvertices
     */
    public EdgeWeightedDigraph(final int inputvertices) {
        if (inputvertices < 0) {
            throw new IllegalArgumentException(
                "Number of vertices in a Digraph must be nonnegative");
        }
        this.vertices = inputvertices;
        this.edges = 0;
        this.indegree = new int[vertices];
        adj = (Bag<DirectedEdge>[]) new Bag[vertices];
        for (int v = 0; v < vertices; v++) {
            adj[v] = new Bag<DirectedEdge>();
        }
    }

    // /**
    //  * Initializes a random edge-weighted digraph with
    //   {@code V} vertices and <em>E</em> edges.
    //  *
    //  * @param  V the number of vertices
    //  * @param  E the number of edges
    //  * @throws IllegalArgumentException if {@code V < 0}
    //  * @throws IllegalArgumentException if {@code E < 0}
    //  */
    // public EdgeWeightedDigraph(int V, int E) {
    //     this(V);
    //     if (E < 0) {
    //          throw new IllegalArgumentException(
    //          "Number of edges in
    //          a Digraph must be
    //          nonnegative");
    //     }
    //     for (int i = 0; i < E; i++) {
    //         int v = StdRandom.uniform(V);
    //         int w = StdRandom.uniform(V);
    //         double weight = 0.01 * StdRandom.uniform(100);
    //         DirectedEdge e = new DirectedEdge(v, w, weight);
    //         addEdge(e);
    //     }
    // }

    // /**
    //  * Initializes an edge-weighted digraph from the specified input stream.
    //  * The format is the number of vertices <em>V</em>,
    //  * followed by the number of edges <em>E</em>,
    //  * followed by <em>E</em> pairs of vertices and edge weights,
    //  * with each entry separated by whitespace.
    //  *
    //  * @param  in the input stream
    //  * @throws IllegalArgumentException
    //    if the endpoints of any
    //    edge are not in prescribed range
    //  * @throws IllegalArgumentException
    //    if the number of vertices
    //    or edges is negative
    //  */
    // public EdgeWeightedDigraph(In in) {
    //     this(in.readInt());
    //     int E = in.readInt();
    //     if (E < 0) {
    //     throw new IllegalArgumentException(
    //     "Number of edges must be nonnegative");
    //     }
    //     for (int i = 0; i < E; i++) {
    //         int v = in.readInt();
    //         int w = in.readInt();
    //         validateVertex(v);
    //         validateVertex(w);
    //         double weight = in.readDouble();
    //         addEdge(new DirectedEdge(v, w, weight));
    //     }
    // }

    // /**
    //  * Initializes a new
    //    edge-weighted digraph
    //    that is a deep copy of {@code G}.
    //  *
    //  * @param  G the edge-weighted digraph to copy
    //  */
    // public EdgeWeightedDigraph(EdgeWeightedDigraph G) {
    //     this(G.V());
    //     this.E = G.E();
    //     for (int v = 0; v < G.V(); v++)
    //         this.indegree[v] = G.indegree(v);
    //     for (int v = 0; v < G.V(); v++) {
    //         // reverse so that adjacency list is in same order as original
    //         Stack<DirectedEdge> reverse = new Stack<DirectedEdge>();
    //         for (DirectedEdge e : G.adj[v]) {
    //             reverse.push(e);
    //         }
    //         for (DirectedEdge e : reverse) {
    //             adj[v].add(e);
    //         }
    //     }
    // }

    /**
     * Returns the number of vertices in this edge-weighted digraph.
     *
     * @return the number of vertices in this edge-weighted digraph
     */
    public int verticescount() {
        return this.vertices;
    }

    /**
     * Returns the number of edges in this edge-weighted digraph.
     *
     * @return the number of edges in this edge-weighted digraph
     */
    public int edgescount() {
        return edges;
    }
    /**
     * { validate Vertex }.
     *
     * @param      v     { int type }
     */
    private void validateVertex(final int v) {
        if (v < 0 || v >= vertices) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and "
                + (vertices - 1));
        }
    }

    /**
     * Adds the directed edge {@code e} to this edge-weighted digraph.
     *
     * @param  e the edge
     * @throws IllegalArgumentException
     *  unless endpoints of edge are between {
     *  @code 0}
     *         and {@code V-1}
     */
    public void addEdge(final DirectedEdge e) {
        int v = e.from();
        int w = e.to();
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        indegree[w]++;
        edges++;
    }


    /**
     * Returns the directed edges incident from vertex {@code v}.
     *
     * @param  v the vertex
     * @return the directed edges incident from vertex {@code v} as an Iterable
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public Iterable<DirectedEdge> adj(final int v) {
        validateVertex(v);
        return adj[v];
    }

    /**
     * Returns the number of directed edges incident from vertex {@code v}.
     * This is known as the <em>outdegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the outdegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int outdegree(final int v) {
        validateVertex(v);
        return adj[v].size();
    }

    /**
     * Returns the number of directed edges incident to vertex {@code v}.
     * This is known as the <em>indegree</em> of vertex {@code v}.
     *
     * @param  v the vertex
     * @return the indegree of vertex {@code v}
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     */
    public int indegree(final int v) {
        validateVertex(v);
        return indegree[v];
    }

    /**
     * Returns all directed edges in this edge-weighted digraph.
     * To iterate over the edges
     * in this edge-weighted digraph,
     * use foreach notation:
     * {@code for (DirectedEdge e : G.edges())}.
     *
     * @return all edges in this edge-weighted digraph, as an iterable
     */
    public Iterable<DirectedEdge> edges() {
        Bag<DirectedEdge> list = new Bag<DirectedEdge>();
        for (int v = 0; v < vertices; v++) {
            for (DirectedEdge e : adj(v)) {
                list.add(e);
            }
        }
        return list;
    }

    /**
     * Returns a string representation of this edge-weighted digraph.
     *
     * @return the number of vertices
     *  <em>V</em>, followed by the
     *   number of edges <em>E</em>,
     *         followed by the <em>V</em> adjacency lists of edges
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(vertices + " " + edges + NEWLINE);
        for (int v = 0; v < vertices; v++) {
            s.append(v + ": ");
            for (DirectedEdge e : adj[v]) {
                s.append(e + "  ");
            }
            s.append(NEWLINE);
        }
        return s.toString();
    }
}
