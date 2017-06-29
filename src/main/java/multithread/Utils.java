package multithread;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */
public class Utils {

    public static void pause(final int millis) {
        try {
            Thread.sleep(millis);
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startAndJoinAllThreads(final Thread[] threads) {
        for (final Thread thread : threads) {
            thread.start();
        }
        try {
            for (final Thread thread : threads) {
                thread.join();
            }
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }
    }
}
