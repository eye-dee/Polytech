package jcstress; /**
 * Polytech
 * Created by igor on 24.03.17.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Utility methods on string objects.
 */
public class JCStressExample {

    /**
     * A pattern to match on a signed integer value.
     */
    private static final Pattern decimalPattern = Pattern.compile("^-?\\d+$");

    /**
     * The matcher for the decimal pattern regex.
     */
    private static final Matcher decimalMatcher = decimalPattern.matcher("");

    /**
     * Maximum supported key length.
     */
    private static final int MAX_KEY_LENGTH = 250;

    /**
     * Exception thrown if the input key is too long.
     */
    private static final IllegalArgumentException KEY_TOO_LONG_EXCEPTION =
            new IllegalArgumentException("Key is too long (maxlen = "
                    + MAX_KEY_LENGTH + ")");

    /**
     * Exception thrown if the input key is empty.
     */
    private static final IllegalArgumentException KEY_EMPTY_EXCEPTION =
            new IllegalArgumentException("Key must contain at least one character.");

    /**
     * Preset the stack traces for the static exceptions.
     */
    static {
        KEY_TOO_LONG_EXCEPTION.setStackTrace(new StackTraceElement[0]);
        KEY_EMPTY_EXCEPTION.setStackTrace(new StackTraceElement[0]);
    }

    /**
     * Private constructor, since this is a purely static class.
     */
    private JCStressExample() {
        throw new UnsupportedOperationException();
    }

    /**
     * Check if a given string is a JSON object.
     *
     * @param s the input string.
     * @return true if it is a JSON object, false otherwise.
     */
    public static boolean isJsonObject(final String s) {
        if (s == null || s.isEmpty()) {
            return false;
        }

        if (s.startsWith("{") || s.startsWith("[")
                || "true".equals(s) || "false".equals(s)
                || "null".equals(s) || decimalMatcher.reset(s).matches()) {
            return true;
        }

        return false;
    }

}
