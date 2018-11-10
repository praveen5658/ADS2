/**
 * Class for dijkstra sp.
 */
public class DijkstraSP {
    /**
     * Value.
     */
    private double[] distTo;
    // distTo[v] = distance  of shortest s->v path
    /**
     * Value.
     */
    private DirectedEdge[] edgeTo;
    // edgeTo[v] = last edge on shortest s->v path
    /**
     * Value.
     */
    private IndexMinPQ<Double> pq;
    // priority queue of vertices

    /**
     * Computes a shortest-paths tree from the
     * source vertex {@code s} to every other
     * vertex in the edge-weighted digraph {@code G}.
     *
     * @param  graph the edge-weighted digraph
     * @param  s the source vertex
     * @throws IllegalArgumentException if an edge weight is negative
     * @throws IllegalArgumentException unless {@code 0 <= s < V}
     * Time Complexity : O(E logE)
     */
    public DijkstraSP(final EdgeWeightedDigraph graph, final int s) {
        for (DirectedEdge e : graph.edges()) {
            if (e.weight() < 0) {
                throw new IllegalArgumentException(
                    "edge " + e + " has negative weight");
            }
        }

        distTo = new double[graph.verticescount()];
        edgeTo = new DirectedEdge[graph.verticescount()];

        validateVertex(s);

        for (int v = 0; v < graph.verticescount(); v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[s] = 0.0;

        // relax vertices in order of distance from s
        pq = new IndexMinPQ<Double>(graph.verticescount());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (DirectedEdge e : graph.adj(v)) {
                relax(e);
            }
        }

        // check optimality conditions
        assert check(graph, s);
    }

    // relax edge e and update pq if changed
    /**
     * { function_description }.
     *
     * @param      e     { parameter_description }
     * Time Complexity : O(1)
     */
    private void relax(final DirectedEdge e) {
        int v = e.from(), w = e.to();
        if (distTo[w] > distTo[v] + e.weight()) {
            distTo[w] = distTo[v] + e.weight();
            edgeTo[w] = e;
            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);
            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    /**
     * Returns the length of a shortest path from the
     * source vertex {@code s} to vertex {@code v}.
     * @param  v the destination vertex
     * @return the length of a shortest path from the
     * source vertex {@code s} to vertex {@code v};
     *         {@code Double.POSITIVE_INFINITY} if no such path
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * Time Complexity : O(1)
     */
    public double distTo(final int v) {
        validateVertex(v);
        return distTo[v];
    }

    /**
     * Returns true if there is a path from the source vertex
     * {@code s} to vertex {@code v}.
     *
     * @param  v the destination vertex
     * @return {@code true} if there is a path from the source vertex
     *         {@code s} to vertex {@code v}; {@code false} otherwise
     * @throws IllegalArgumentException unless {@code 0 <= v < V}
     * Time Complexity : O(1)
     */
    public boolean hasPathTo(final int v) {
        validateVertex(v);
        return distTo[v] < Double.POSITIVE_INFINITY;
    }
    /**
     * { function_description }.
     *
     * @param      graph     { parameter_description }
     * @param      s     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     * Time Complexity : O(E)
     */
    private boolean check(final EdgeWeightedDigraph graph, final int s) {

        // check that edge weights are nonnegative
        for (DirectedEdge e : graph.edges()) {
            if (e.weight() < 0) {
                System.err.println("negative edge weight detected");
                return false;
            }
        }

        // check that distTo[v] and edgeTo[v] are consistent
        if (distTo[s] != 0.0 || edgeTo[s] != null) {
            System.err.println("distTo[s] and edgeTo[s] inconsistent");
            return false;
        }
        for (int v = 0; v < graph.verticescount(); v++) {
            if (v == s) {
                continue;
            }
            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
                System.err.println("distTo[] and edgeTo[] inconsistent");
                return false;
            }
        }

        // check that all edges e = v->w satisfy
        //distTo[w] <= distTo[v] + e.weight()
        for (int v = 0; v < graph.verticescount(); v++) {
            for (DirectedEdge e : graph.adj(v)) {
                int w = e.to();
                if (distTo[v] + e.weight() < distTo[w]) {
                    System.err.println("edge " + e + " not relaxed");
                    return false;
                }
            }
        }

        // check that all edges e = v->w on SPT
        //satisfy distTo[w] == distTo[v] + e.weight()
        for (int w = 0; w < graph.verticescount(); w++) {
            if (edgeTo[w] == null) {
                continue;
            }
            DirectedEdge e = edgeTo[w];
            int v = e.from();
            if (w != e.to()) {
                return false;
            }
            if (distTo[v] + e.weight() != distTo[w]) {
                System.err.println("edge " + e + " on shortest path not tight");
                return false;
            }
        }
        return true;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    /**
     * { function_description }.
     *
     * @param      v     { parameter_description }
     * Time Complexity : O(1)
     */
    private void validateVertex(final int v) {
        int vert = distTo.length;
        if (v < 0 || v >= vert) {
            throw new IllegalArgumentException(
                "vertex " + v + " is not between 0 and " + (vert - 1));
        }
    }
}
