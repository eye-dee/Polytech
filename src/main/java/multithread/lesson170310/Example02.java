package multithread.lesson170310;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */

public class Example02 {

    static class Task implements Runnable {
        @Override
        public void run() {
            System.out.println("Hello");
        }
    }

    public static void main(final String[] args) {
        final Task task = new Task();

        //передаем в параметры реализаются интерфейса Runnable
        final Thread t = new Thread(task);
        t.start();

        new Thread(() -> System.out.println("Hello2")).start();
    }
}
