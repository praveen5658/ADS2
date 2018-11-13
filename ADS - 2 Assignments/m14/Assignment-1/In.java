/**.
 * { item_description }
 */
import java.io.BufferedInputStream;
/**.
 * { item_description }
 */
import java.io.File;
/**.
 * { item_description }
 */
import java.io.FileInputStream;
/**.
 * { item_description }
 */
import java.io.IOException;
/**.
 * { item_description }
 */
import java.io.InputStream;
/**.
 * { item_description }
 */
import java.net.URL;
/**.
 * { item_description }
 */
import java.net.Socket;
/**.
 * { item_description }
 */
import java.net.URLConnection;
/**.
 * { item_description }
 */
import java.util.ArrayList;
/**.
 * { item_description }
 */
import java.util.InputMismatchException;
/**.
 * { item_description }
 */
import java.util.Locale;
/**.
 * { item_description }
 */
import java.util.NoSuchElementException;
/**.
 * { item_description }
 */
import java.util.Scanner;
/**.
 * { item_description }
 */
import java.util.regex.Pattern;
/**.
 * { item_description }
 */
public final class In {
    /**.
     * { var_description }
     */
    private static final String CHARSET_NAME = "UTF-8";
    /**.
     * { var_description }
     */
    private static final Locale LOCALE = Locale.US;
    /**.
     * { var_description }
     */
    private static final Pattern WHITESPACE_PATTERN =
                Pattern.compile("\\p{javaWhitespace}+");
    /**.
     * { var_description }
     */
    private static final Pattern EMPTY_PATTERN =
         Pattern.compile("");
    /**.
     * { var_description }
     */
    private static final Pattern EVERYTHING_PATTERN =
          Pattern.compile("\\A");
    /**.
     * { var_description }
     */
    private Scanner scanner;
    /**.
     * Constructs the object.
     */
    public In() {
        scanner = new Scanner(new BufferedInputStream(
            System.in), CHARSET_NAME);
        scanner.useLocale(LOCALE);
    }
    /**.
     * Constructs the object.
     *
     * @param      socket  The socket
     */
    public In(final Socket socket) {
        if (socket == null) {
            throw new IllegalArgumentException(
                "socket argument is null");
        }
        try {
            InputStream is = socket.getInputStream();
            scanner = new Scanner(
                new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(
                "Could not open " + socket, ioe);
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      url   The url
     */
    public In(final URL url) {
        if (url == null) {
            throw new IllegalArgumentException("url argument is null");
        }
        try {
            URLConnection site = url.openConnection();
            InputStream is = site.getInputStream();
            scanner = new Scanner(
                new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(
                "Could not open " + url, ioe);
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      file  The file
     */
    public In(final File file) {
        if (file == null) {
            throw new IllegalArgumentException("file argument is null");
        }
        try {
            FileInputStream fis = new FileInputStream(file);
            scanner = new Scanner(new BufferedInputStream(fis), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException("Could not open " + file, ioe);
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      name  The name
     */
    public In(final String name) {
        if (name == null) {
            throw new IllegalArgumentException("argument is null");
        }
        try {
            File file = new File(name);
            if (file.exists()) {
                FileInputStream fis = new FileInputStream(file);
                scanner = new Scanner(
                    new BufferedInputStream(fis), CHARSET_NAME);
                scanner.useLocale(LOCALE);
                return;
            }
            URL url = getClass().getResource(name);
            if (url == null) {
                url = getClass().getClassLoader().getResource(name);
            }
            if (url == null) {
                url = new URL(name);
            }
            URLConnection site = url.openConnection();
            InputStream is = site.getInputStream();
            scanner = new Scanner(
                new BufferedInputStream(is), CHARSET_NAME);
            scanner.useLocale(LOCALE);
        } catch (IOException ioe) {
            throw new IllegalArgumentException(
                "Could not open " + name, ioe);
        }
    }
    /**.
     * Constructs the object.
     *
     * @param      sc  The scanner
     */
    public In(final Scanner sc) {
        if (sc == null) {
            throw new IllegalArgumentException(
                "scanner argument is null");
        }
        this.scanner = sc;
    }
    /**.
     * { function_description }
     *
     * @return     { description_of_the_return_value }
     */
    public boolean exists()  {
        return scanner != null;
    }
    /**.
     * Determines if empty.
     *
     * @return     True if empty, False otherwise.
     */
    public boolean isEmpty() {
        return !scanner.hasNext();
    }
    /**.
     *Determines if it has next line.
     *
     * @return     True if has next line, False otherwise.
     */
    public boolean hasNextLine() {
        return scanner.hasNextLine();
    }
    /**.
     * Determines if it has next character.
     *
     * @return     True if has next character, False otherwise.
     */
    public boolean hasNextChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        boolean result = scanner.hasNext();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }
   /**.
     * Reads and returns the next line in this input stream.
     *
     * @return the next line in this input stream; {@code null} if no such line
     */
    public String readLine() {
        String line;
        try {
            line = scanner.nextLine();
        } catch (NoSuchElementException e) {
            line = null;
        }
        return line;
    }
    /**.
     * Reads and returns the next character in this input stream.
     *
     * @return the next {@code char} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     */
    public char readChar() {
        scanner.useDelimiter(EMPTY_PATTERN);
        try {
            String ch = scanner.next();
            assert ch.length() == 1 : "Internal (Std)In.readChar() error!"
                + " Please contact the authors.";
            scanner.useDelimiter(WHITESPACE_PATTERN);
            return ch.charAt(0);
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'char' value from the input stream, "
                 + "but no more tokens are available");
        }
    }
   /**.
     * Reads and returns the remainder of this input stream, as a string.
     *
     * @return the remainder of this input stream, as a string
     */
    public String readAll() {
        if (!scanner.hasNextLine()) {
            return "";
        }
        String result = scanner.useDelimiter(EVERYTHING_PATTERN).next();
        scanner.useDelimiter(WHITESPACE_PATTERN);
        return result;
    }
   /**.
     * Reads the next token from
     * this input stream and returns it as a {@code String}.
     *
     * @return the next {@code String} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     */
    public String readString() {
        try {
            return scanner.next();
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'String' value from the input stream, "
                 + "but no more tokens are available");
        }
    }
   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code int},
     * and returns the {@code int}.
     *
     * @return the next {@code int} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as an {@code int}
     */
    public int readInt() {
        try {
            return scanner.nextInt();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read an 'int' value from the input stream, "
                 + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read an 'int' value from the input stream, "
                 + "but no more tokens are available");
        }
    }
   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code double},
     * and returns the {@code double}.
     *
     * @return the next {@code double} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as a {@code double}
     */
    public double readDouble() {
        try {
            return scanner.nextDouble();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'double' value from the input stream, "
                 + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'double' value from the input stream, "
                 + "but no more tokens are available");
        }
    }
   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code float},
     * and returns the {@code float}.
     *
     * @return the next {@code float} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as a {@code float}
     */
    public float readFloat() {
        try {
            return scanner.nextFloat();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'float' value from the input stream, "
                 + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'float' value from the input stream, "
                 + "but no more tokens are available");
        }
    }
   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code long},
     * and returns the {@code long}.
     *
     * @return the next {@code long} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next token
     * cannot be parsed as a {@code long}
     */
    public long readLong() {
        try {
            return scanner.nextLong();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'long' value from the input stream, "
                 + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'long' value from the input stream, "
                 + "but no more tokens are available");
        }
    }

   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code short},
     * and returns the {@code short}.
     *
     * @return the next {@code short} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as a {@code short}
     */
    public short readShort() {
        try {
            return scanner.nextShort();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'short' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'short' value from the input stream, "
                + "but no more tokens are available");
        }
    }
   /**.
     * Reads the next token from this input stream,
     * parses it as a {@code byte},
     * and returns the {@code byte}.
     * <p>
     * To read binary data, use {@link BinaryIn}.
     *
     * @return the next {@code byte} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as a {@code byte}
     */
    public byte readByte() {
        try {
            return scanner.nextByte();
        } catch (InputMismatchException e) {
            String token = scanner.next();
            throw new InputMismatchException(
                "attempts to read a 'byte' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attemps to read a 'byte' value from the input stream, "
                + "but no more tokens are available");
        }
    }
    /**.
     * Reads the next token from this input stream,
     * parses it as a {@code boolean}
     * (interpreting either {@code "true"} or {@code "1"} as {@code true},
     * and either {@code "false"} or {@code "0"} as {@code false}).
     *
     * @return the next {@code boolean} in this input stream
     * @throws NoSuchElementException if the input stream is empty
     * @throws InputMismatchException if the next
     * token cannot be parsed as a {@code boolean}
     */
    public boolean readBoolean() {
        try {
            String token = readString();
            if ("true".equalsIgnoreCase(token)) {
                return true;
            }
            if ("false".equalsIgnoreCase(token)) {
                return false;
            }
            if ("1".equals(token)) {
                return true;
            }
            if ("0".equals(token)) {
                return false;
            }
            throw new InputMismatchException(
                "attempts to read a 'boolean' value from the input stream, "
                + "but the next token is \"" + token + "\"");
        } catch (NoSuchElementException e) {
            throw new NoSuchElementException(
                "attempts to read a 'boolean' value from the input stream, "
               + "but no more tokens are available");
        }
    }
    /**.
     * Reads all remaining tokens from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining tokens in this input stream,
     * as an array of strings
     */
    public String[] readAllStrings() {
        String[] tokens = WHITESPACE_PATTERN.split(readAll());
        if (tokens.length == 0 || tokens[0].length() > 0) {
            return tokens;
        }
        String[] decapitokens = new String[tokens.length - 1];
        for (int i = 0; i < tokens.length - 1; i++) {
            decapitokens[i] = tokens[i + 1];
        }
        return decapitokens;
    }
    /**.
     * Reads all remaining lines from this input stream and returns them as
     * an array of strings.
     *
     * @return all remaining lines in this input stream,
     * as an array of strings
     */
    public String[] readAllLines() {
        ArrayList<String> lines = new ArrayList<String>();
        while (hasNextLine()) {
            lines.add(readLine());
        }
        return lines.toArray(new String[lines.size()]);
    }
    /**.
     * Reads all remaining tokens from this input stream,
     * parses them as integers,
     * and returns them as an array of integers.
     *
     * @return all remaining lines in this input stream,
     * as an array of integers
     */
    public int[] readAllInts() {
        String[] fields = readAllStrings();
        int[] vals = new int[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Integer.parseInt(fields[i]);
        }
        return vals;
    }
    /**.
     * Reads all remaining tokens from this input stream, parses them as longs,
     * and returns them as an array of longs.
     *
     * @return all remaining lines in this input stream, as an array of longs
     */
    public long[] readAllLongs() {
        String[] fields = readAllStrings();
        long[] vals = new long[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Long.parseLong(fields[i]);
        }
        return vals;
    }
    /**.
     * Reads all remaining tokens from this input stream,
     * parses them as doubles,
     * and returns them as an array of doubles.
     *
     * @return all remaining lines in this input stream,
     * as an array of doubles
     */
    public double[] readAllDoubles() {
        String[] fields = readAllStrings();
        double[] vals = new double[fields.length];
        for (int i = 0; i < fields.length; i++) {
            vals[i] = Double.parseDouble(fields[i]);
        }
        return vals;
    }
   /**.
     * Closes this input stream.
     */
    public void close() {
        scanner.close();
    }
    /**.
     * Reads all integers from a file and returns them as
     * an array of integers.
     *
     * @param      filename the name of the file
     * @return     the integers in the file
     * @deprecated Replaced by {@code new In(filename)}.
     * {@link #readAllInts()}.
     */
    @Deprecated
    public static int[] readInts(final String filename) {
        return new In(filename).readAllInts();
    }
   /**.
     * Reads all doubles from a file and returns them as
     * an array of doubles.
     *
     * @param      filename the name of the file
     * @return     the doubles in the file
     * @deprecated Replaced by {@code new In(filename)}.
     * {@link #readAllDoubles()}.
     */
    @Deprecated
    public static double[] readDoubles(final String filename) {
        return new In(filename).readAllDoubles();
    }
   /**.
     * Reads all strings from a file and returns them as
     * an array of strings.
     *
     * @param      filename the name of the file
     * @return     the strings in the file
     * @deprecated Replaced by {@code new In(filename)}.
     * {@link #readAllStrings()}.
     */
    @Deprecated
    public static String[] readStrings(final String filename) {
        return new In(filename).readAllStrings();
    }
    /**.
     * Reads all integers from standard input and returns them
     * an array of integers.
     *
     * @return     the integers on standard input
     * @deprecated Replaced by {@link StdIn#readAllInts()}.
     */
    @Deprecated
    public static int[] readInts() {
        return new In().readAllInts();
    }
   /**.
     * Reads all doubles from standard input and returns them as
     * an array of doubles.
     *
     * @return     the doubles on standard input
     * @deprecated Replaced by {@link StdIn#readAllDoubles()}.
     */
    @Deprecated
    public static double[] readDoubles() {
        return new In().readAllDoubles();
    }
   /**.
     * Reads all strings from standard input and returns them as
     * an array of strings.
     *
     * @return     the strings on standard input
     * @deprecated Replaced by {@link StdIn#readAllStrings()}.
     */
    @Deprecated
    public static String[] readStrings() {
        return new In().readAllStrings();
    }
}
