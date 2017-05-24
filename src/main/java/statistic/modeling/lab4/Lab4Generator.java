package statistic.modeling.lab4;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * Polytech
 * Created on 18.05.17.
 */
public class Lab4Generator {
    private final static int M = 3;
    private final static double lam1 = 40e-6;
    private final static double lam2 = 10e-6;
    private final static double lam3 = 80e-6;
    private static final double P = 0.99;
    private static final double T = 8760.0;
    private static final List<Boolean> elements = Arrays.asList(
            true, true, true, true, true, true, true, true
    );
    public static int N = 100000;
    private static Random random = new Random();
    private static List<Double> times = Arrays.asList(
            0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0
    );

    public static boolean F() {
        return ((elements.get(0) && elements.get(1)) || elements.get(2)) &&
                (elements.get(3) && elements.get(4)) &&
                (elements.get(5) || elements.get(6) || elements.get(7));
    }

    public static double fail(final double lam) {
        final double alpha = random.nextDouble();
        return -Math.log(alpha) / lam;
    }

    public static void main(final String[] args) {
        final int PP = 10;
        for (int i = 0; i < PP; ++i) {
            for (int j = 0; j < PP; ++j) {
                for (int k = 0; k < PP; ++k) {

                    double sum = 0.0;

                    for (int c = 0; c < PP; ++c) {
                        sum += timeP(i, j, k);
                    }

                    if (sum/PP > P) {
                        System.out.println("i = " + i + " j = " + j + " k = " + k + " sum = " + sum/PP + "!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                    } else {
                        System.out.println("i = " + i + " j = " + j + " k = " + k + " sum = " + sum/PP);
                    }
                }
            }
        }
    }

    private static double timeP(
            final int parts1,
            final int parts2,
            final int parts3
    ) {
        int d = 0;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < times.size(); ++j) {
                evalTimes(j, times, 0.0);
            }

            for (int k = 0; k < parts1; ++k) {
                final int minIndex = getMinIndex(times.subList(0, 3));
                evalTimes(minIndex, times, times.get(minIndex));
            }
            for (int k = 0; k < parts2; ++k) {
                final int minIndex = getMinIndex(times.subList(3, 5));
                evalTimes(3 + minIndex, times, times.get(minIndex));
            }
            for (int k = 0; k < parts3; ++k) {
                final int minIndex = getMinIndex(times.subList(5, 8));
                evalTimes(5 + minIndex, times, times.get(minIndex));
            }

            for (int j = 0; j < times.size(); ++j) {
                elements.set(j, times.get(j) > T);
            }

            if (!F())
                ++d;
        }

        return 1 - (double) d / N;
    }

    private static void evalTimes(
            final int j,
            final List<Double> times,
            final double v) {
        if (j < 3) {
            times.set(j, v + fail(lam1));
        } else if (j < 5) {
            times.set(j, v + fail(lam2));
        } else {
            times.set(j, v + fail(lam3));
        }
    }

    private static int getMinIndex(final List<Double> times) {
        int minIndex = 0;
        double minValue = times.get(0);
        for (int i = 1; i < times.size(); ++i) {
            if (times.get(i) < minValue) {
                minValue = times.get(i);
                minIndex = i;
            }
        }

        return minIndex;
    }
}
