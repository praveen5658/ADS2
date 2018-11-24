import java.util.ArrayList;
import edu.princeton.cs.algs4.BinaryStdIn;
import edu.princeton.cs.algs4.BinaryStdOut;
public class MoveToFront {
    private static int R = 256;
    public static void encode() {
        ArrayList<Character> list = new ArrayList<Character>();
        for (char i = 0 ; i < R; i++) {
                list.add(i);
            }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            int index = (int) list.indexOf(ch);
            BinaryStdOut.write((char) index);
            list.remove(index);
            list.add(0, ch);
        }
        BinaryStdOut.close();
    }

    public static void decode() {
        ArrayList<Character> list = new ArrayList<Character>();
        for (char i = 0 ; i < R; i++) {
            list.add(i);
        }
        while (!BinaryStdIn.isEmpty()) {
            char ch = BinaryStdIn.readChar();
            char index = list.get(ch);
            BinaryStdOut.write(index);
            list.remove(ch);
            list.add(0, index);
        }
        BinaryStdOut.close();
    }

    public static void main(String[] args) {
        String first = args[0];
        if (first.equals("+")) {
            decode();
        } else {
            encode();
        }
    }
}
