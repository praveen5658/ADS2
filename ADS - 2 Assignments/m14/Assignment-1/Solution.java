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
    private Solution() { }
    /**.
     * { function_description }
     *
     * @param      args  The arguments
     */
    public static void main(final String[] args) {
        String[] words = loadWords();
        Scanner sc = new Scanner(System.in);
        TST<Integer> tst = new TST<Integer>();
        for (int i = 0; i < words.length; i++) {
            SuffixArray sarr = new SuffixArray(words[i], tst);
        }
        String line = sc.nextLine();
        for (String str : tst.keysWithPrefix(line)) {
            System.out.println(str);
        }
    }
    /**.
     * Loads words.
     *
     * @return     { description_of_the_return_value }
     */
    public static String[] loadWords() {
        In in = new In("/Files/dictionary-algs4.txt");
        String[] words = in.readAllStrings();
        return words;
    }
}
