package multithread.lesson170331;

import multithread.Utils;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class ThreadCommunication {

    final static int NOP = -1;
    final static int STOP = 0;
    final static int STATE = 1;

    static int command = NOP;

    static class Task implements Runnable {
        @Override
        public void run() {
            long count = 0;
            outer : while (true) {
                if (Thread.interrupted()) {
                    switch (command) {
                        case NOP:
                            break;
                        case STOP:
                            break outer;
                        case STATE:
                            System.out.println(count);
                    }
                }
                ++count;
            }
            System.out.println(count);
        }
    }

    public static void main(final String[] args) {
        final Thread thread = new Thread(new Task());
        thread.start();

        Utils.pause(3000);
        System.out.println("interrupt 1");
        thread.interrupt();

        Utils.pause(3000);
        System.out.println("interrupt 2");
        command = STATE;
        thread.interrupt();

        Utils.pause(3000);
        System.out.println("interrupt 3");
        command = STOP;
        thread.interrupt();
    }
}
