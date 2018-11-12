/**.
 * Class for quick 3 string.
 */
public class Quick3string {
    /**
     * cutoff variable.
     */
    private static final int CUTOFF =  15;

    /**
     * Constructs the object.
     */
    public Quick3string() { }

    /**.
     * { sort function }
     *
     * @param      a     { parameter_description }
     * Time complexity: O(1.39N log N)
     */
    public static void sort(final String[] a) {
        // StdRandom.shuffle(a);
        sort(a, 0, a.length - 1, 0);
        assert isSorted(a);
    }

    /**.
     * { return the dth character of s, -1 if d = length of s }
     *
     * @param      s     { parameter_description }
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     * Time complexity: O(1)
     */
    private static int charAt(final String s, final int d) {
        assert d >= 0 && d <= s.length();
        if (d == s.length()) {
            return -1;
        }
        return s.charAt(d);
    }


    /**
     * 3-way string quicksort a[lo..hi] starting at dth character.
     *
     * @param      a     { parameter_description }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { parameter_description }
     * Time complexity: O(1.39N log N)
     */
    private static void sort(final String[] a, final int lo,
        final int hi, final int d) {

        // cutoff to insertion sort for small subarrays
        if (hi <= lo + CUTOFF) {
            insertion(a, lo, hi, d);
            return;
        }

        int lt = lo, gt = hi;
        int v = charAt(a[lo], d);
        int i = lo + 1;
        while (i <= gt) {
            int t = charAt(a[i], d);
            if (t < v) {
                exch(a, lt++, i++);
            } else if (t > v) {
                exch(a, i, gt--);
            } else {
                i++;
            }
        }

        // a[lo..lt-1] < v = a[lt..gt] < a[gt+1..hi].
        sort(a, lo, lt - 1, d);
        if (v >= 0) {
            sort(a, lt, gt, d + 1);
        }
        sort(a, gt + 1, hi, d);
    }

    /**
     * sort from a[lo] to a[hi], starting at the dth character.
     *
     * @param      a     { parameter_description }
     * @param      lo    The lower
     * @param      hi    The higher
     * @param      d     { parameter_description }
     * time complexity for this method is O(N^2).
     */
    private static void insertion(final String[] a, final int lo,
        final int hi, final int d) {
        for (int i = lo; i <= hi; i++) {
            for (int j = i; j > lo && less(a[j], a[j - 1], d); j--) {
                exch(a, j, j - 1);
            }
        }
    }

    /**
     * exchange a[i] and a[j].
     *
     * @param      a     { parameter_description }
     * @param      i     { parameter_description }
     * @param      j     { parameter_description }
     * time complexity for this method is O(1)
     */
    private static void exch(final String[] a, final int i, final int j) {
        String temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    // is v less than w, starting at character d
    // DEPRECATED BECAUSE OF SLOW SUBSTRING EXTRACTION IN JAVA 7
    // private static boolean less(String v, String w, int d) {
    //    assert v.substring(0, d).equals(w.substring(0, d));
    //    return v.substring(d).compareTo(w.substring(d)) < 0;
    // }

    // is v less than w, starting at character d

    /**
     * less function.
     *
     * @param      v     { parameter_description }
     * @param      w     { parameter_description }
     * @param      d     { parameter_description }
     *
     * @return     { description_of_the_return_value }
     * time complexity for this method is O(N)
     */
    private static boolean less(final String v, final String w,
        final int d) {
        assert v.substring(0, d).equals(w.substring(0, d));
        for (int i = d; i < Math.min(v.length(), w.length()); i++) {
            if (v.charAt(i) < w.charAt(i)) {
                return true;
            }
            if (v.charAt(i) > w.charAt(i)) {
                return false;
            }
        }
        return v.length() < w.length();
    }

    /**
     * Determines if sorted.
     *
     * @param      a     { parameter_description }
     *
     * @return     True if sorted, False otherwise.
     * time complexity for this method is O(N).
     */
    private static boolean isSorted(final String[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i].compareTo(a[i - 1]) < 0) {
                return false;
            }
        }
        return true;
    }
    /**.
     * Returns a string representation of the object.
     *
     * @param      array  The array
     *
     * @return     String representation of the object.
     * time complexity for this method is O(N).
     */
    public String toString(final String[] array) {
        String string = "[";
        for (int i = 0; i < array.length - 1; i++) {
            string += array[i] + ", ";
        }
        string += array[array.length - 1] + "]";
        return string;
    }
}
