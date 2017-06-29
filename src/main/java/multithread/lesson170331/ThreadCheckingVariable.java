package multithread.lesson170331;

import multithread.Utils;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class ThreadCheckingVariable {

    volatile static boolean RUNNING = true;

    static class Task implements Runnable {
        @Override
        public void run() {
            long count = 0;

            while (RUNNING) {
                count++;
            }

            System.out.println(count);
        }
    }

    public static void main(final String[] args) {
        System.out.println("start");
        final Thread thread = new Thread(new Task());
        thread.start();

        Utils.pause(3000);
        System.out.println("stop");
        RUNNING = false;
    }
}
