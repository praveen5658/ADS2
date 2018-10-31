import java.util.Scanner;
/**
 * Class for directed cycle.
 */
class DirectedCycle {
    /**
     * marked[v] = has vertex v been marked?.
     */
    private boolean[] marked;
    /**
     * edgeTo[v] = previous vertex on path to v.
     */
    private int[] edgeTo;
    /**
     * onStack[v] = is vertex on the stack?.
     */
    private boolean[] onStack;
    /**
     * directed cycle (or null if no such cycle).
     */
    private Stack<Integer> cycle;

    /**
     * Constructs the object.
     *
     * @param      g1     { graph }
     */
    DirectedCycle(final Digraph g1) {
        marked  = new boolean[g1.vertex()];
        onStack = new boolean[g1.vertex()];
        edgeTo  = new int[g1.vertex()];
        for (int v = 0; v < g1.vertex(); v++) {
            if (!marked[v] && cycle == null) {
                dfs(g1, v);
            }
        }
    }

    // check that algorithm computes either the topological
    // order or finds a directed cycle

    /**
     * depth first search.
     *
     * @param      g2     { graph }
     * @param      v     { vertex }
     */
    private void dfs(final Digraph g2, final int v) {
        onStack[v] = true;
        marked[v] = true;
        for (int w : g2.adj(v)) {

            // short circuit if directed cycle found
            if (cycle != null) {
                return;
            } else if (!marked[w]) {
                edgeTo[w] = v;
                dfs(g2, w);
            } else if (onStack[w]) {
                cycle = new Stack<Integer>();
                for (int x = v; x != w; x = edgeTo[x]) {
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    /**
     * Determines if it has cycle.
     *
     * @return     True if has cycle, False otherwise.
     */
    public boolean hasCycle() {
        return cycle != null;
    }



}

/////////////////////////////////////////////////////////////////////////

/**
 * Class for solution.
 */
final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() {

    }
    /**
     * main method.
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int vertexes = Integer.parseInt(scan.nextLine());
        int edges = Integer.parseInt(scan.nextLine());
        Digraph graphobj = new Digraph(vertexes);
        while (scan.hasNext()) {
            String[] lines = scan.nextLine().split(" ");
graphobj.addEdge(Integer.parseInt(lines[0]), Integer.parseInt(lines[1]));
        }
        DirectedCycle finder = new DirectedCycle(graphobj);
        if (finder.hasCycle()) {
            System.out.println("Cycle exists.");

        } else {
            System.out.println("Cycle doesn't exists.");
        }
    }
}
