package multithread.homework.philosophy2;

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
            if (leftPork.tryLock()) {
                if (rightPork.tryLock()) {
                    System.out.println(name + "is eating");
                    Utils.pause(100);

                    leftPork.unlock();
                    rightPork.unlock();
                } else {
                    leftPork.unlock();
                    think();
                }
            } else {
                think();
            }
        }
    }

    public void think() {
        System.out.println(name + " is thinking");
        Utils.pause(100);
    }
}
