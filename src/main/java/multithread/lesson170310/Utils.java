package multithread.lesson170310;

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
}
