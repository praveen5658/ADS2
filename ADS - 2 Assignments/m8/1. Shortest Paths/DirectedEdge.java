/**
 * Class for directed edge.
 */
public class DirectedEdge {
    /**
     * v.
     */
    private final int v;
    /**
     * w.
     */
    private final int w;
    /**
     * weight.
     */
    private final double weight;

    /**
     * Constructs the object.
     *
     * @param      vv    { parameter_description }
     * @param      ww    { parameter_description }
     * @param      wei   The wei
     */
    public DirectedEdge(final int vv, final int ww, final double wei) {
        if (vv < 0) {
            throw new IllegalArgumentException(
                "Vertex names must be nonnegative integers");
        }
        if (ww < 0) {
            throw new IllegalArgumentException(
                "Vertex names must be nonnegative integers");
        }
        if (Double.isNaN(wei)) {
            throw new IllegalArgumentException(
                "Weight is NaN");
        }
        this.v = vv;
        this.w = ww;
        this.weight = wei;
    }

    /**
     * Returns the tail vertex of the directed edge.
     * @return the tail vertex of the directed edge
     */
    public int from() {
        return v;
    }

    /**
     * Returns the head vertex of the directed edge.
     * @return the head vertex of the directed edge
     */
    public int to() {
        return w;
    }

    /**
     * Returns the weight of the directed edge.
     * @return the weight of the directed edge
     */
    public double weight() {
        return weight;
    }

    /**
     * Returns a string representation of the directed edge.
     * @return a string representation of the directed edge
     */
    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }
}
