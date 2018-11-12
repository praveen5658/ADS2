/*************************************************************************
 *  Compilation:  javac StdOut.java
 *  Execution:    java StdOut
 *
 *  Writes data of various types to standard output.
 *
 *************************************************************************/

import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Locale;
/**
 * { StdOut }.
 */
public final class StdOut {
    /**
     * { UTF8 }.
     */
    private static final String UTF8 = "UTF-8";
    /**
     * { US_LOCALE }.
     */
    private static final Locale US_LOCALE = new Locale("en", "US");
    /**
     * { out }.
     */
    private static PrintWriter out;

    static {
        try {
            out = new PrintWriter(
                new OutputStreamWriter(
                    System.out, UTF8), true);
        } catch (UnsupportedEncodingException e) {
            System.out.println(e);
        }
    }
    /**
     * Constructs the object.
     */
    private StdOut() { }
    /**
     * { close() }.
     * Complexity: O(1)
     */
    public static void close() {
        out.close();
    }
    /**
     * { println() }.
     * Complexity: O(1)
     */
    public static void println() {
        out.println();
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final Object x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final boolean x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final char x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final double x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final float x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final int x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final long x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final short x) {
        out.println(x);
    }
    /**
     * { println() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void println(final byte x) {
        out.println(x);
    }
    /**
     * { print() }.
     * Complexity: O(1)
     */
    public static void print() {
        out.flush();
    }
    /**
     * { print() }.
     *Complexity: O(1)
     * @param      x     { parameter_description }
     */
    public static void print(final Object x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final boolean x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final char x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final double x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final float x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     * @param      x     { parameter_description }
     */
    public static void print(final int x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final long x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final short x) {
        out.print(x);
        out.flush();
    }
    /**
     * { print() }.
     * Complexity: O(1)
     *
     * @param      x     { parameter_description }
     */
    public static void print(final byte x) {
        out.print(x);
        out.flush();
    }
    /**
     * { printf() }.
     *Complexity: O(1)
     * @param      format  The format
     * @param      args    The arguments
     */
    public static void printf(final String format, final Object... args) {
        out.printf(US_LOCALE, format, args);
        out.flush();
    }
    /**
     * { printf() }.
     *Complexity: O(1)
     * @param      locale  The locale
     * @param      format  The format
     * @param      args    The arguments
     */
    public static void printf(
        final Locale locale,
        final String format,
        final Object... args) {
        out.printf(locale, format, args);
        out.flush();
    }
}
