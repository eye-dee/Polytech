package multithread.lesson170331;

import multithread.Utils;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class RaceCondition {
    volatile static int count = 0;

    static class Task implements Runnable {
        @Override
        public void run() {
            while (true) {
                Utils.pause(300);
                incAndPrint();
            }
        }

        private void incAndPrint() {
            synchronized (this) {
                ++count;
                System.out.println(count);
            }
        }
    }

    public static void main(final String[] args) {
        final Task task = new Task();
        final Thread thread1 = new Thread(task);
        final Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();
    }
}
