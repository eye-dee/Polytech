package multithread.lesson170317;

import multithread.Utils;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public class JoinExample {

    //todo static class in public class what is it
    static class Task implements Runnable {
        @Override
        public void run() {
            final int MAX = 1000_000_000;
            System.out.println("start");
//            for (int i = 0; i < MAX; ++i) {
//                try {
//                    //на вызов метода тоже уходит время
//                    Thread.sleep(0,1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
            Utils.pause(30000);
            System.out.println("finish");
        }
    }

    public static void main(final String[] args) {
        final Thread thread = new Thread(new Task());
        thread.start();

        try {
            System.out.println("thread started");
            thread.join(25000);
            System.out.println("thread ended");
        } catch (final InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("main end");
        Utils.pause(10000);
    }
}
