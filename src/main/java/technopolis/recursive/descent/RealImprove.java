package technopolis.recursive.descent;

import technopolis.classes.interfaces.Function;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Polytech
 * Created by igor on 02.04.17.
 */

public class RealImprove {
    private static final double PI = 3.1415926535897932385;
    private static final double E = 2.7182818284590452354;
    private static final double VALUE = 5.0;
    static private final Map<String, Double> table = new HashMap<>();

    static {
        table.put("pi", PI);
        table.put("e", E);
        table.put("x", VALUE);
    }

    public static void main(final String[] args) throws IOException {
        final String str = "e-1*(1+1);";

        final Scanner sc = new Scanner(str.replace(";","\n"));
        sc.useDelimiter("\n");

        while (sc.hasNext()) {
            final ExpressionEvaluatorImprove expressionEvaluator = new ExpressionEvaluatorImprove(sc.next() + ";");

            final Function eval = expressionEvaluator.evaluate(table);

            System.out.println(eval.eval());
        }
    }
}
