/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * { item_description }
 */
public final class Solution {
    /**
     * Constructs the object.
     */
    private Solution() { }
    /**.
     * { function_description }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        Scanner scan = new Scanner(System.in);
        int n = Integer.parseInt(scan.nextLine());
        String[] array = new String[n];
        for (int i = 0; i < n; i++) {
            array[i] = scan.nextLine();
        }
        Quick3string quickobj = new Quick3string();
        quickobj.sort(array);
        System.out.println(quickobj.toString(array));
    }
}
