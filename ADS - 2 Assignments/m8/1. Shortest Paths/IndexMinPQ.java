import java.util.Iterator;
import java.util.NoSuchElementException;
/**
 * Class for index minimum pq.
 *
 * @param      <Key>   Key.
 */
public class IndexMinPQ<Key extends Comparable<Key>>
implements Iterable<Integer> {
    /**
     * maximum number of elements on PQ.
     */
    private int maxN;
    /**
     * number of elements on PQ.
     */
    private int n;
    /**
     * binary heap using 1-based indexing.
     */
    private int[] pq;
    /**
     * inverse of pq - qp[pq[i]] = pq[qp[i]] = i.
     */
    private int[] qp;
    /**
     * keys[i] = priority of i.
     */
    private Key[] keys;
    /**
     * Initializes an empty indexed priority queue.
     * @param  max the keys
     */
    public IndexMinPQ(final int max) {
       this.maxN = max;
        n = 0;
        keys = (Key[]) new Comparable[maxN + 1];
        pq   = new int[maxN + 1];
        qp   = new int[maxN + 1];
        for (int i = 0; i <= maxN; i++) {
            qp[i] = -1;
        }
    }
    /**
     * Returns true if this priority queue is empty.
     *
     * @return boolean.
     * Time complexity is O(1).
     */
    public boolean isEmpty() {
        return n == 0;
    }
    /**
     * Is  i an index on this priority queue?
     *
     * @param  i an index
     * @return boolean.
     * Time complexity is O(1).
     */
    public boolean contains(final int i) {
        // if (i < 0 || i >= maxN) {
        //     throw new IllegalArgumentException();
        // }
        return qp[i] != -1;
    }

    /**
     * Returns the number of keys on this priority queue.
     *
     * @return the number of keys on this priority queue
     * Time complexity is O(1).
     */
    public int size() {
        return n;
    }

    /**
     * Associates key with index.
     *
     * @param  i an index
     * @param  key the key to associate with index i
     *
     * Time complexity is O(log N).
     */
    public void insert(final int i, final Key key) {
        n++;
        qp[i] = n;
        pq[n] = i;
        keys[i] = key;
        swim(n);
    }

    /**
     * Returns an index associated with a minimum key.
     *
     * @return an index associated with a minimum key
     * Time complexity is O(1).
     */
    public int minIndex() {

        return pq[1];
    }

    /**
     * Returns a minimum key.
     *
     * @return a minimum key
     * Time complexity is O(1).
     */
    public Key minKey() {

        return keys[pq[1]];
    }

    /**
     * Removes a minimum key and returns its associated index.
     * @return an index associated with a minimum key
     * Time complexity is O(log N).
     */
    public int delMin() {

        int min = pq[1];
        exch(1, n--);
        sink(1);
        assert min == pq[n + 1];
        qp[min] = -1;        // delete
        keys[min] = null;    // to help with garbage collection
        pq[n + 1] = -1;      // not needed
        return min;
    }

    /**
     * Returns the key associated with index  i.
     *
     * @param  i the index of the key to return
     * @return the key associated with index i
     * Time complexity is O(1).
     */
    public Key keyOf(final int i) {
        return keys[i];
    }

    /**
     * Change the key associated with index i to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index i to this key
     * Time complexity is O(log N).
     */
    public void changeKey(final int i, final Key key) {
        keys[i] = key;
        swim(qp[i]);
        sink(qp[i]);
    }

    /**
     * Change the key associated with index i to the specified value.
     *
     * @param  i the index of the key to change
     * @param  key change the key associated with index  i to this key
     * @deprecated Replaced by changeKey(int, Key)}.
     * Time complexity is O(log N).
     */
    @Deprecated
    public void change(final int i, final Key key) {
        changeKey(i, key);
    }

    /**
     * Decrease the key associated with index i to the specified value.
     *
     * @param  i the index of the key to decrease
     * @param  key decrease the key associated with index  i to this key
     * Time complexity is O(log N).
     */
    public void decreaseKey(final int i, final Key key) {
        keys[i] = key;
        swim(qp[i]);
    }

    /**
     * Increase the key associated with index i to the specified value.
     *
     * @param  i the index of the key to increase
     * @param  key increase the key associated with index i to this key
     * Time complexity is O(log N).
     */
    public void increaseKey(final int i, final Key key) {
        keys[i] = key;
        sink(qp[i]);
    }

    /**
     * Remove the key associated with index i.
     *
     * @param  i the index of the key to remove
     * Time complexity is O(log N).
     */
    public void delete(final int i) {
        int index = qp[i];
        exch(index, n--);
        swim(index);
        sink(index);
        keys[i] = null;
        qp[i] = -1;
    }
    /**
     * greater method.
     *
     * @param      i     index.
     * @param      j     index.
     *
     * @return     true or false.
     * Time complexity is O(1).
     */
    private boolean greater(final int i, final int j) {
        return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
    }
    /**
     * exch method that swaps the elements.
     * @param      i     index.
     * @param      j     index.
     * Time complexity is O(1).
     */
    private void exch(final int i, final int j) {
        int swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
        qp[pq[i]] = i;
        qp[pq[j]] = j;
    }
    /**
     * swim method.
     *
     * @param      k     value.
     * Time complexity is O(log N).
     */
    private void swim(final int k) {
        int m = k;
        while (k > 1 && greater(k / 2, k)) {
            exch(m, m / 2);
            m = m / 2;
        }
    }
    /**
     * sink method.
     *
     * @param      k     integer.
     * Time complexity is O(log N).
     */
    private void sink(final int k) {
        int m = k;
        while (2 * k <= n) {
            int j = 2 * k;
            if (j < n && greater(j, j + 1)) {
                j++;
            }
            if (!greater(k, j)) {
                break;
            }
            exch(k, j);
            m = j;
        }
    }

    /**
     * Returns an iterator that iterates over the keys on the
     * priority queue in ascending order.
     * The iterator doesn't implement remove() since it's optional.
     * its time complexity is O(1).
     *
     * @return an iterator that iterates
     *  over the keys in ascending order
     */
    public Iterator<Integer> iterator() {
        return new HeapIterator();
    }
    /**
     * Class for heap iterator.
     */
    private class HeapIterator implements Iterator<Integer> {
        /**
         * create a new pq.
         */
        private IndexMinPQ<Key> copy;
        /**
         * Constructs the object.
         */
        HeapIterator() {
            copy = new IndexMinPQ<Key>(pq.length - 1);
            for (int i = 1; i <= n; i++) {
                copy.insert(pq[i], keys[pq[i]]);
            }
        }
        /**
         * Determines if it has next.
         * @return     True if has next, False otherwise.
         * Time complexity is O(1).
         */
        public boolean hasNext() {
            return !copy.isEmpty();
        }
        /**
         * remove method.
         */
        public void remove() {
            throw new UnsupportedOperationException();
        }
        /**
         * next method that returns the next item.
         * @return     Integer.
         * Time complexity is O(log N).
         */
        public Integer next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return copy.delMin();
        }
    }
}
