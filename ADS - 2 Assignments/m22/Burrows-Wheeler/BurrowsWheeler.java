import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
import edu.princeton.cs.algs4.Queue;
import java.util.HashMap;
import java.util.Arrays;
public class BurrowsWheeler {
    private static final int R = 256;
    public static void transform() {
        String s = BinaryStdIn.readString();
        CircularSuffixArray c = new CircularSuffixArray(s);
        int first = 0;
        while (first < c.length() && c.index(first) != 0) {
            first++;
        }
        BinaryStdOut.write(first);
        for (int i = 0; i < c.length(); i++) {
            BinaryStdOut.write(s.charAt((c.index(i) + s.length() - 1) % s.length()));
        }
        BinaryStdOut.close();
    }

    public static void inverseTransform() {
        int first = BinaryStdIn.readInt();
        String s = BinaryStdIn.readString();
        char[] t = s.toCharArray();
        HashMap<Character, Queue<Integer>> dictionary = new HashMap<Character, Queue<Integer>>();
        for (int i = 0; i < t.length; i++) {
            if (!dictionary.containsKey(t[i])) {
                dictionary.put(t[i], new Queue<Integer>());
            }
            dictionary.get(t[i]).enqueue(i);
        }
        Arrays.sort(t);
        int[] indexes = new int[t.length];
        for (int i = 0; i < indexes.length; i++) {
            indexes[i] = dictionary.get(t[i]).dequeue();
        }

        for (int i = 0; i < indexes.length; i++) {
            BinaryStdOut.write(t[first]);
            first = indexes[first];
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        if (args[0].equals("+")) {
            inverseTransform();
        } else {
            transform();
        }
    }
}
