/**
 * Class for sequential search st.
 *
 * @param      <Key>    The key
 * @param      <Value>  The value
 */
class SequentialSearchST<Key, Value> {
    /**
     * { variable for number of key-value pairs }.
     */
    private int n;
    /**
     * { the linked list of key-value pairs }.
     */
    private Node first;
    /**
     * Class for node.
     * a helper linked list data type
     */
    private class Node {
        /**
         * { variable for key }.
         */
        private Key key;
        /**
         * { variable for value }.
         */
        private Value val;
        /**
         * { variable for node next }.
         */
        private Node next;
        /**
         * Constructs the object.
         *
         * @param      key1   The key
         * @param      val1   The value
         * @param      next1  The next
         */
        Node(final Key key1, final Value val1, final Node next1)  {
            this.key  = key1;
            this.val  = val1;
            this.next = next1;
        }
    }
    /**
     * Constructs the object.
     * Initializes an empty symbol table.
     */
    SequentialSearchST() {
    }

    /**
     * Returns the number of key-value pairs in this symbol table.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return the number of key-value pairs in this symbol table
     */
    public int size() {
        return n;
    }
    /**
     * Determines if empty.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return size() == 0;
    }

    /**
     * { returns whether the key is present in the table or not }.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public boolean contains(final Key key) {
        return get(key) != null;
    }

    /**
     * { Returns the value associated with the given key. }.
     *
     * Complexity :
     *              Best Case : O(N)
     *              Average Case : O(N)
     *              Worst Case : O(N)
     *
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    public Value get(final Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    /**
     * { Inserts the key-value pair into the symbol table,
     *   overwriting the old value
     *   with the new value if the key is already in the symbol table.
     *   If the value is null this effectively deletes the key from
     *   the symbol table }.
     *
     *   Complexity :
     *              Best Case : O(logN)
     *              Average Case : O(logN)
     *              Worst Case : O(logN)
     *
     * @param      key   The key
     * @param      val   The value
     */
    public void put(final Key key, final Value val) {
        if (val == null) {
            delete(key);
            return;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.val = val;
                return;
            }
        }
        first = new Node(key, val, first);
        n++;
    }

    /**
     * { Removes the key and associated value from the symbol table
     *   (if the key is in the symbol table) }.
     *
     *   Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @param      key   The key
     */
    public void delete(final Key key) {
        first = delete(first, key);
    }

    /**
     * { delete key in linked list beginning at Node x }.
     *
     * Complexity :
     *              Best Case : O(1)
     *              Average Case : O(1)
     *              Worst Case : O(1)
     *
     * @param      x     { parameter_description }
     * @param      key   The key
     *
     * @return     { description_of_the_return_value }
     */
    private Node delete(final Node x, final Key key) {
        if (x == null) {
            return null;
        }
        if (key.equals(x.key)) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    /**
     * { Returns all keys in the symbol table as an.
     *   To iterate over all of the keys in the symbol table named,
     *   use the foreach notation }.
     *
     *   Complexity :
     *              Best Case : O(N)
     *              Average Case : O(N)
     *              Worst Case : O(N)
     *
     * @return     { description_of_the_return_value }
     */
    public Iterable<Key> keys()  {
        Queue<Key> queue = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            queue.enqueue(x.key);
        }
        return queue;
    }
    // /**
    //  * Unit tests the {@code SequentialSearchST} data type.
    //  *
    //  * @param args the command-line arguments
    //  */
    // public static void main(String[] args) {
    //     SequentialSearchST<String, Integer> st =
    //     new SequentialSearchST<String, Integer>();
    //     for (int i = 0; !StdIn.isEmpty(); i++) {
    //         String key = StdIn.readString();
    //         st.put(key, i);
    //     }
    //     for (String s : st.keys())
    //         StdOut.println(s + " " + st.get(s));
    // }
}
