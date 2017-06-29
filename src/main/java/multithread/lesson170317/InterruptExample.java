package multithread.lesson170317;

import multithread.Utils;

/**
 * Created by igor on 17.03.170.
 * поменять свою говно джаву
 */
public class InterruptExample {

    static class Task implements Runnable {

        @Override
        public void run() {
            long count = 0;

            while (true) {
                ++count;

                //статический метод сбрасывает флаг прерывания
                if (Thread.interrupted()) {
                    System.out.println(count);
                }

                //не сбрасывает
                Thread.currentThread().isInterrupted();
            }
        }
    }

    public static void main(final String[] args) {
        final Thread thread = new Thread(new Task());
        thread.start();

        while (true) {
            Utils.pause(1000);

            thread.interrupt();
        }
    }
}
