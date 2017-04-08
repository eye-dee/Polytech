package karpov.regexp;

import java.util.regex.Pattern;

/**
 * Polytech
 * Created by igor on 24.03.17.
 */
public class RegexpTry {
    public static void main(final String[] args) {
        final Pattern pattern = Pattern.compile("^([a-z0-9_\\.-]+)@([a-z0-9_\\.-]+)\\.([a-z\\.]{2,6})$");

        System.out.println(pattern.toString());
    }
}
