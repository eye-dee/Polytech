package multithread.pools.calculators;

import java.util.function.DoubleUnaryOperator;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
abstract public class BaseCalculator implements Calculator {
    protected final DoubleUnaryOperator function;
    public BaseCalculator(final DoubleUnaryOperator function) {
        this.function = function;
    }
}
