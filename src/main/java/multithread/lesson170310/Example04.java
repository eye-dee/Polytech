package multithread.lesson170310;

import multithread.Utils;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */
public class Example04 {
    public static void main(final String[] args) {
        //Thread находится в пакете java.lang
        new Thread(() -> {
            int count = 0;

            while(true) {
                System.out.println(count++);
                Utils.pause(1000);
            }

        },"Counter").start();
    }
}
