/**.
 * Class for connected components.
 */
public class ConnectedComponents {
    /**.
     * { var_description }
     */
    private boolean[] marked;
    /**.
     * { var_description }
     */
    private int[] id;
    /**.
     * { var_description }
     */
    private int[] size;
    /**.
     * { var_description }
     */
    private int count;
    /**.
     * Constructs the object.
     *
     * @param      g     { parameter_description }
     */
    public ConnectedComponents(final Graph g) {
        marked = new boolean[g.vert()];
        id = new int[g.vert()];
        size = new int[g.vert()];
        for (int v = 0; v < g.vert(); v++) {
            if (!marked[v]) {
                dfs(g, v);
                count++;
            }
        }
    }
    /**.
     * { function_description }
     *
     * @param      g     { parameter_description }
     * @param      v     { parameter_description }
     */
    private void dfs(final Graph g, final int v) {
        marked[v] = true;
        id[v] = count;
        size[count]++;
        for (int w : g.adj(v)) {
            if (!marked[w]) {
                dfs(g, w);
            }
        }
    }
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int id(final int v) {
        //validateVertex(v);
        return id[v];
    }

    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int size(final int v) {
        //validateVertex(v);
        return size[id[v]];
    }

    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public int count() {
        return count;
    }

    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean connected(final int v, final int w) {
        //validateVertex(v);
        //validateVertex(w);
        return id(v) == id(w);
    }
    /**.
     * { function_description }
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean areConnected(final int v, final int w) {
        return id(v) == id(w);
    }
}
