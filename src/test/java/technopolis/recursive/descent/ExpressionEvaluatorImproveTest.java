package technopolis.recursive.descent;

import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Polytech
 * Created by igor on 07.04.17.
 */
public class ExpressionEvaluatorImproveTest {
    private static final double pi = 3.1415926535897932385;
    private static final double E = 2.7182818284590452354;
    private static final double VALUE = 5.0;
    final private Map<String, Double> tableValues = new HashMap<>();

    private static final double EPS = 0.000001;

    @Before
    public void setUp() throws Exception {
        tableValues.put("pi", pi);
        tableValues.put("e", E);
        tableValues.put("x", VALUE);
    }

    @Test
    public void simpleEvaluate() throws Exception {
        assertThat(new ExpressionEvaluatorImprove("5+5").evaluate(tableValues).eval())
                .isEqualTo(10.0);

        assertThat(new ExpressionEvaluatorImprove("5+1").evaluate(tableValues).eval())
                .isEqualTo(6.0);

        assertThat(new ExpressionEvaluatorImprove("5-x").evaluate(tableValues).eval())
                .isEqualTo(0.0);

        assertThat(new ExpressionEvaluatorImprove("5+5-x-x").evaluate(tableValues).eval())
                .isEqualTo(0.0);
    }

    @Test
    public void constants() throws Exception {
        assertThat(new ExpressionEvaluatorImprove("pi + 5+5 - pi").evaluate(tableValues).eval())
                .isBetween(10.0 - EPS,10.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("e + 5 -e").evaluate(tableValues).eval())
                .isBetween(5.0 - EPS,5.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("e*pi + 5-x - pi*e").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);

        assertThat(new ExpressionEvaluatorImprove("pi*pi*e + 5+5-x-x - pi*e*pi").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);
    }

    @Test
    public void brackets() throws Exception {
        assertThat(new ExpressionEvaluatorImprove("pi + (5+5) - pi").evaluate(tableValues).eval())
                .isBetween(10.0 - EPS,10.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("(e + 5) -e").evaluate(tableValues).eval())
                .isBetween(5.0 - EPS,5.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("(e*pi) + (5-x) - (pi*e)").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);

        assertThat(new ExpressionEvaluatorImprove("(pi*pi*e) + (5+5-x-x) - (pi*e*pi)").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);
    }

    @Test
    public void multiplyBrackets() throws Exception {
        assertThat(new ExpressionEvaluatorImprove("(x-5*(x-5*(x-5) - 5) - 4)*pi + (5+5) - pi").evaluate(tableValues).eval())
                .isBetween(10.0 - EPS,10.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("(e + 5) - e*(x-5) - e*(1*(1*(1*(1+0))))").evaluate(tableValues).eval())
                .isBetween(5.0 - EPS,5.0 + EPS);

        assertThat(new ExpressionEvaluatorImprove("(e*pi) + (5-4)*(5-x)*(5-4*(5-4)) - (pi*e)").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);

        assertThat(new ExpressionEvaluatorImprove("((((((pi*pi*e)))))) + (5+5-x-x) - 1*(((pi*e*pi)))").evaluate(tableValues).eval())
                .isBetween(-EPS,EPS);
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionEmptyTest() throws IOException {
        new ExpressionEvaluatorImprove("").evaluate(tableValues).eval();
    }

    @Test(expected=IllegalArgumentException.class)
    public void exceptionBracketTest() throws IOException {
        new ExpressionEvaluatorImprove("(").evaluate(tableValues).eval();
    }

    @Test
    public void testDelimeters() throws IOException {
        assertThat(new ExpressionEvaluatorImprove("5     + 5").evaluate(tableValues).eval())
                .isEqualTo(10.0);
    }

    @Test
    public void unaryMinus() throws IOException {
        assertThat(new ExpressionEvaluatorImprove("-5+ -5").evaluate(tableValues).eval())
                .isEqualTo(-10.0);

        assertThat(new ExpressionEvaluatorImprove("-5+1").evaluate(tableValues).eval())
                .isEqualTo(-4.0);

        assertThat(new ExpressionEvaluatorImprove("-5-x").evaluate(tableValues).eval())
                .isEqualTo(-10.0);

        assertThat(new ExpressionEvaluatorImprove("-5+5-x-x").evaluate(tableValues).eval())
                .isEqualTo(-10.0);
    }
}