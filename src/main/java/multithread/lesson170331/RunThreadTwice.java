package multithread.lesson170331;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class RunThreadTwice {
    public static void main(final String[] args) {
        final Thread thread = new Thread();

        thread.start();
        System.out.println(thread.getState());

        try {
            thread.join();
            System.out.println(thread.getState());
        } catch (final InterruptedException e) {
            e.printStackTrace();

        }

        thread.start();
        System.out.println(thread.getState());
    }
}
