package multithread.pools.calculators;

import java.util.function.DoubleUnaryOperator;
import java.util.stream.LongStream;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class StreamCalculator extends BaseCalculator {
    public StreamCalculator(final DoubleUnaryOperator function) {
        super(function);
    }

    @Override
    public double calculate(final double start, final double end, final double step) {
        final DoubleUnaryOperator calcFunction = y -> y * step;

        final DoubleUnaryOperator sqFunction = function.andThen(calcFunction);

        /*return DoubleStream.iterate(start, s -> s + step)
                .limit((long) ((end - start) / step))
                .parallel()
                .map(sqFunction)
                .sum();*/
        return LongStream.range(0,(long)((end - start) / step))
                .parallel()
                .mapToDouble(i -> start + i*step)
                .map(sqFunction)
                .sum();
    }
}
