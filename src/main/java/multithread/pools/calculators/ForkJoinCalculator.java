package multithread.pools.calculators;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.function.DoubleUnaryOperator;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class ForkJoinCalculator extends BaseCalculator {
    public ForkJoinCalculator(final DoubleUnaryOperator function) {
        super(function);
    }

    public class ForkJoinCalculate extends RecursiveTask<Double> {
        private final double start;
        private final double end;
        private final double step;

        public ForkJoinCalculate(final double start, final double end, final double step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        static final long SEQUENTIAL_THRESHOLD = 100;

        @Override
        protected Double compute() {
            if ((end - start) / step > SEQUENTIAL_THRESHOLD) {
                return sequentialCompute();
            }

            final double mid = start + (end - start) / 2.0;

            final ForkJoinCalculate left = new ForkJoinCalculate(start, mid, step);
            final ForkJoinCalculate right = new ForkJoinCalculate(mid, end, step);

            left.fork();
            final double rightAns = right.compute();
            final double leftAns = left.join();

            return leftAns + rightAns;

            /*left.fork();
            right.fork();

            return left.join() + right.join();*/
        }

        private double sequentialCompute() {
            double x = start;
            double result = 0.0;

            while (x < end) {
                result += step * function.applyAsDouble(x);
                x += step;
            }

            return result;
        }
    }

    @Override
    public double calculate(final double start, final double end, final double step) {
        final ForkJoinPool forkJoinPool = new ForkJoinPool();

        final ForkJoinCalculate calculate = new ForkJoinCalculate(start,end,step);

        return forkJoinPool.invoke(calculate);
    }
}

