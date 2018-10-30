/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * Class for solution.
 */
public final class Solution {
    /**.
     * Constructs the object.
     */
    private Solution() {

    }
    /**.
     * { function_description }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Percolation p = new Percolation(n);
        while (sc.hasNextLine()) {
          String line = sc.nextLine();
          String[] input = line.split(" ");
          p.open(Integer.parseInt(input[0]) - 1,
            Integer.parseInt(input[input.length - 1]) - 1);
        }
        System.out.println(p.isconnected());
    }
}
