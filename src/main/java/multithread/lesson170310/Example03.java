package multithread.lesson170310;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */
public class Example03 {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("Hello");
        }
    }

    public static void main(final String[] args) {
        new MyThread().start();
    }
}
