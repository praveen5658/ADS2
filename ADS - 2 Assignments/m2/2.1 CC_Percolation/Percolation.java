/**.
 * Class for percolation.
 */
public class Percolation {
    /**.
     * { var_description }
     */
    private boolean[][] grid;
    /**.
     * { var_description }
     */
    private int size;
    /**.
     * { var_description }
     */
    private Graph list;

    /**.
     * Constructs the object.
     *
     * @param      s  The size
     */
    public Percolation(final int s) {
        this.size = s;
        grid = new boolean[size][size];
        list = new Graph(size * size + 2);
    }

    /**.
     * { function_description }
     *
     * @param      row   The row
     * @param      col   The col
     */
    public void open(final int row, final int col) {
        grid[row][col] = true;
        //size += 1;
        if (row == 0) {
            list.addEdge(size * size, percolates(row, col));
        }
        if (row == size - 1) {
            list.addEdge(size * size + 1, percolates(row, col));
        }
        if (row < size - 1 && grid[row + 1][col]) { //bottom element
            list.addEdge(percolates(row, col), percolates(row + 1, col));
        }
        if (row > 0 && grid[row - 1][col]) { //top element
            list.addEdge(percolates(row, col), percolates(row - 1, col));
        }
        if (col > 0 && grid[row][col - 1]) { //left element
            list.addEdge(percolates(row, col), percolates(row, col - 1));
        }
        if (col < size - 1 && grid[row][col + 1]) { //right element
            list.addEdge(percolates(row, col), percolates(row, col + 1));
        }
    }
    /**.
     * { function_description }
     *
     * @param      row   The row
     * @param      col   The col
     *
     * @return     { description_of_the_return_value }
     */
    public int percolates(final int row, final int col) {
        return (size * row) + col;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean isconnected() {
        ConnectedComponents cc = new ConnectedComponents(list);
        if (cc.connected(size * size, size * size + 1)) {
            return true;
        }
        return false;
    }
}
