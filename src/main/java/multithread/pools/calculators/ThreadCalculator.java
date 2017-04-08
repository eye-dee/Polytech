package multithread.pools.calculators;

import java.util.function.DoubleUnaryOperator;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class ThreadCalculator extends BaseCalculator{
    
    private int chunks = 1000;

    public void setChunks(final int chunks) {
        this.chunks = chunks;
    }

    public class CalcThread extends Thread {
        private final double start;
        private final double end;
        private final double step;

        private double result;

        public double getResult() {
            return result;
        }

        public CalcThread(final double start, final double end, final double step) {
            this.start = start;
            this.end = end;
            this.step = step;
        }

        @Override
        public void run() {
            result = 0;
            double x = start;

            while (x < end) {
                result += step*function.applyAsDouble(x);
                x += step;
            }
        }
    }

    public ThreadCalculator(final DoubleUnaryOperator function) {
        super(function);
    }

    @Override
    public double calculate(final double start, final double end, final double step) {
        final CalcThread[] calcThreads = new CalcThread[chunks];

        final double interval = (end - start)/chunks;
        double st = start;

        for (int i = 0; i < chunks; ++i) {
            calcThreads[i] = new CalcThread(st,st + interval,step);

            calcThreads[i].start();

            st += interval;
        }

        for (int i = 0; i < chunks; ++i) {
            try {
                calcThreads[i].join();
            } catch (final InterruptedException e) {
                e.printStackTrace();
            }
        }

        double result = 0.0;
        for (final CalcThread calcThread : calcThreads) {
            result += calcThread.getResult();
        }

        return result;
    }
}
