/**
 * Class for uf.
 */
public class UF {
    /**
     * { variable for parent array }.
     */
    private int[] parent;
    /**
     * { variable for rank array }.
     */
    private byte[] rank;
    /**
     * { variable for count }.
     */
    private int count;

    /**
     * Constructs the object.
     *
     * @param      n     { parameter_description }
     */
    public UF(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        count = n;
        parent = new int[n];
        rank = new byte[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    /**
     * Searches for the first match.
     * its complexity is O(N)
     *
     * @param      p1     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int find(final int p1) {
        int p = p1;
        validate(p);
        while (p != parent[p]) {
            parent[p] = parent[parent[p]];
            p = parent[p];
        }
        return p;
    }

    /**
     * Returns the number of components.
     * its complexity is O(1)
     *
     * @return the number of components
     */
    public int count() {
        return count;
    }

    /**
     * Returns true if the the two sites are in the same component.
     * its complexity is O(1)
     *
     * @param      p     { parameter_description }
     * @param      q     The quarter
     *
     * @return     { description_of_the_return_value }
     */
    public boolean connected(final int p, final int q) {
        return find(p) == find(q);
    }

    /**
     * Merges the component containing site p with the
     * the component containing site q.
     *
     * its complexity is O(1)
     *
     * @param      p     { parameter_description }
     * @param      q     The quarter
     */
    public void union(final int p, final int q) {
        int rootP = find(p);
        int rootQ = find(q);
        if (rootP == rootQ) {
            return;
        }
        // make root of smaller rank point to root of larger rank
        if (rank[rootP] < rank[rootQ]) {
            parent[rootP] = rootQ;
        } else if (rank[rootP] > rank[rootQ]) {
            parent[rootQ] = rootP;
        } else {
            parent[rootQ] = rootP;
            rank[rootP]++;
        }
        count--;
    }

    /**
     * validate that p is a valid index.
     * its complexity is O(1)
     *
     * @param      p     { parameter_description }
     */
    private void validate(final int p) {
        int n = parent.length;
        if (p < 0 || p >= n) {
            throw new IllegalArgumentException("index " + p + " is not"
                                               + "between 0 and " + (n - 1));
        }
    }

    // /**
    //  * Reads in a an integer {@code n} and a sequence of pairs of integers
    //  * (between {@code 0} and {@code n-1}) from standard input,
    //    where each integer
    //  * in the pair represents some site;
    //  * if the sites are in different components, merge the two components
    //  * and print the pair to standard output.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     int n = StdIn.readInt();
    //     UF uf = new UF(n);
    //     while (!StdIn.isEmpty()) {
    //         int p = StdIn.readInt();
    //         int q = StdIn.readInt();
    //         if (uf.connected(p, q)) continue;
    //         uf.union(p, q);
    //         StdOut.println(p + " " + q);
    //     }
    //     StdOut.println(uf.count() + " components");
    // }
}
