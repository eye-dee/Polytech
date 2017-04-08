package technopolis.recursive.descent;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * Polytech
 * Created by igor on 02.04.17.
 */

public class Real {
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
        final String str = "5+(x-5)+5;" +
                "(x+5)*(x+6)*(x+7);" +
                "(x-5);" +
                "5+x;" +
                "x*x;" +
                "5 + 5 + x*(x+2);" +
                "5*(5*(5*(5*(5*(5*(5*(5*(x-5))))))));" +
                "pi*e;" +
                ")";

        final Scanner sc = new Scanner(str.replace(";","\n"));
        sc.useDelimiter("\n");

        while (sc.hasNext()) {
            final ExpressionEvaluator expressionEvaluator = new ExpressionEvaluator(sc.next() + ";");

            try {
                System.out.println(expressionEvaluator.evaluate(table));
            } catch(final IllegalArgumentException iae) {
                System.out.println(iae.getMessage());
            }
        }

//        final PushbackInputStream pushbackInputStream = new PushbackInputStream(new ByteArrayInputStream(str.getBytes(StandardCharsets.UTF_8)));
//
//        final Real real = new Real();
//
//        System.out.println("Количество ошибок = " + real.main(pushbackInputStream));
    }
}
