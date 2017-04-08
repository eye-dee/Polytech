package multithread.lesson170317;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public class JoinToDead {
    public static void main(final String[] args) throws InterruptedException {
        final Thread thread = new Thread();

        thread.start();

        thread.join();
        //Join ничего не сделал
        thread.join();
    }
}
