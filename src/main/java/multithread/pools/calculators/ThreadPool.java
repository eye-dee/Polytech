package multithread.pools.calculators;

import java.util.concurrent.*;
import java.util.function.DoubleUnaryOperator;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class ThreadPool extends BaseCalculator {

    private int chunks = 10;

    public void setChunks(final int chunks) {
        this.chunks = chunks;
    }

    public ThreadPool(final DoubleUnaryOperator function) {
        super(function);
    }

    public class CalctThread implements Callable<Double>{
        private final double start;
        private final double end;
        private final double step;

        public CalctThread(final double start, final double end, final double step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        @Override
        public Double call() throws Exception {
            double result = 0;
            double x = start;

            while (x < end) {
                result += step*function.applyAsDouble(x);
                x += step;
            }

            return result;
        }
    }

    @Override
    public double calculate(final double start, final double end, final double step) {
        final ExecutorService executorService = Executors.newFixedThreadPool(chunks);

        final Future<Double>[] futures = new Future[chunks];

        final double interval = (end - start)/chunks;
        double st = start;

        for (int i = 0; i < chunks; ++i) {
            futures[i] = executorService.submit(new CalctThread(st,st + interval,step));

            st += interval;
        }

        executorService.shutdown();

        double result = 0.0;
        for (final Future<Double> doubleFuture : futures) {
            try {
                result += doubleFuture.get();
            } catch (final InterruptedException | ExecutionException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

}
