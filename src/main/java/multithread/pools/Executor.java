package multithread.pools;

import multithread.pools.calculators.*;

import java.util.ArrayList;
import java.util.List;
import java.util.function.DoubleUnaryOperator;

import static java.lang.Math.cos;
import static java.lang.StrictMath.sin;

/**
 * Polytech
 * Created by igor on 08.04.17.
 */
public class Executor {
    private static final double START = 0.0;
    private static final double END = 50.0;
    private static final double STEP = 0.00001;

    public static void main(final String[] args) {
        final DoubleUnaryOperator func = (x) -> sin(x)*sin(x) + cos(x)*cos(x);

        final List<Calculator> calculators = new ArrayList<>();

        calculators.add(new SequentialCalculate(func));
        calculators.add(new ThreadCalculator(func));
        calculators.add(new ThreadPool(func));
        calculators.add(new ForkJoinCalculator(func));
        calculators.add(new StreamCalculator(func));

        calculators.forEach((calculator) -> System.out.println(calculator.calculate(START,END,STEP)));
    }
}
