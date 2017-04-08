package multithread.pools.calculators;

import java.util.function.DoubleUnaryOperator;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class SequentialCalculate extends BaseCalculator {
    public SequentialCalculate(final DoubleUnaryOperator function) {
        super(function);
    }

    public double calculate(final double start, final double end, final double step) {
        double result = 0.0;
        double x = start;

        while (x < end) {
            result += step*function.applyAsDouble(x);

            x += step;
        }

        return result;
    }
}
