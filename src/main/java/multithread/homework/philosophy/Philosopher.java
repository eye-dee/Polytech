package multithread.homework.philosophy;

import lombok.RequiredArgsConstructor;
import multithread.Utils;

/**
 * Polytech
 * Created on 27.06.17.
 */

@RequiredArgsConstructor
public class Philosopher implements Runnable {
    private final String name;
    private final Pork leftPork;
    private final Pork rightPork;

    @Override
    public void run() {

        while (true) {
            waitForPork(leftPork);
            waitForPork(rightPork);

            System.out.println(name + "is eating");
            Utils.pause(100);

            freePork(leftPork);
            freePork(rightPork);
        }
    }

    public void waitForPork(final Pork pork) {
        synchronized (pork) {
            while (!pork.isFree()) {
                try {
                    System.out.println(name + "is thinking");
                    pork.wait();
                } catch (final InterruptedException e) {
                    System.out.println("Interrupted!");
                }
            }
            pork.setFree(false);
        }
    }

    public static void freePork(final Pork pork) {
        synchronized (pork) {
            pork.setFree(true);
        }
    }
}
