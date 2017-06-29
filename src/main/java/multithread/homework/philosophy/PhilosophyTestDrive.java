package multithread.homework.philosophy;

import static multithread.Utils.startAndJoinAllThreads;

/**
 * Polytech
 * Created on 27.06.17.
 */
public class PhilosophyTestDrive {
    private static final int N = 5;

    public static void main(final String[] args) {
        final Philosopher[] philosophers = new Philosopher[N];
        final Pork[] porks = new Pork[N];

        for (int i = 0; i < N; ++i) {
            porks[i] = new Pork();
        }

        for (int i = 0; i < N; ++i) {
            final int nextPork;

            if (i + 1 == N) {
                nextPork = 1;
            } else {
                nextPork = i + 1;
            }

            philosophers[i] = new Philosopher("Philosopher " + i, porks[i], porks[nextPork]);
        }

        final Thread[] threads = new Thread[N];

        for (int i = 0; i < N; ++i) {
            threads[i] = new Thread(philosophers[i]);
        }

        startAndJoinAllThreads(threads);
    }
}
