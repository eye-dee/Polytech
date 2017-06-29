package multithread.homework;

import java.util.Arrays;

import static java.lang.Math.sqrt;
import static multithread.Utils.startAndJoinAllThreads;

/**
 * Polytech
 * Created on 27.06.17.
 */
public class MatrixThreadsTestDrive {

    private static final int N = 1000;

    public static void main(final String[] args) {
        final double[][] matrix = new double[N][];

        for (int i = 0; i < N; ++i) {
            matrix[i] = new double[N];
        }

        final MatrixThreadsTestDrive matrixThreadsTestDrive = new MatrixThreadsTestDrive();
        long start = System.nanoTime();
        matrixThreadsTestDrive.maxSequentially(matrix);
        long end = System.nanoTime();
        System.out.println("Sequential time = " + (end - start));

        start = System.nanoTime();
        matrixThreadsTestDrive.maxParallelAll(matrix);
        end = System.nanoTime();
        System.out.println("Parallel all time = " + (end - start));

        start = System.nanoTime();
        matrixThreadsTestDrive.maxParallelOptimal(matrix);
        end = System.nanoTime();
        System.out.println("Parallel Optimal time = " + (end - start));

        start = System.nanoTime();
        matrixThreadsTestDrive.maxStream(matrix);
        end = System.nanoTime();
        System.out.println("Parallel Optimal time = " + (end - start));
    }

    private static double sqrtSum(final double[][] matrix, final int i) {
        return sqrtSum(matrix[i]);
    }

    private static double sqrtSum(final double[] doubles) {
        double res = 0.0;
        for (final double value : doubles) {
            res += sqrt(value);
        }

        return res;
    }

    private double maxSequentially(final double[][] matrix) {
        double max = 0.0;
        for (int i = 0; i < matrix.length; ++i) {
            final double temp = sqrtSum(matrix, i);
            if (temp > max) {
                max = temp;
            }
        }

        return max;
    }

    private double maxParallelOptimal(final double[][] matrix) {
        final int threadsAmount = 10;
        final int threadStrings = matrix.length / threadsAmount;
        final Thread[] threads = new Thread[threadsAmount];
        final double[] maxes = new double[matrix.length];

        for (int i = 0; i < threadsAmount; ++i) {
            final int curI = i;
            threads[i] = new Thread(() -> {
                for (int j = 0; j < threadStrings; ++j) {
                    final int index = curI * threadStrings + j;
                    maxes[index] = sqrtSum(matrix, index);
                }
            });
        }

        startAndJoinAllThreads(threads);

        return Arrays.stream(maxes).max().getAsDouble();
    }

    private double maxParallelAll(final double[][] matrix) {
        final Thread[] threads = new Thread[matrix.length];
        final double[] maxes = new double[matrix.length];
        for (int i = 0; i < matrix.length; ++i) {
            final int curI = i;
            threads[i] = new Thread(() -> maxes[curI] = sqrtSum(matrix, curI));
        }

        startAndJoinAllThreads(threads);

        return Arrays.stream(maxes).max().getAsDouble();
    }

    private double maxStream(final double[][] matrix) {
        return Arrays.stream(matrix)
                .parallel()
                .map(MatrixThreadsTestDrive::sqrtSum)
                .max(Double::compareTo)
                .get();
    }
}
