package multithread.lesson170310;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */
public class Example05 {
    static class Task implements Runnable {
        private final String name;

        Task(final String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);

            int count = 0;
            while (true) {
                Utils.pause(1000);
                System.out.println(count++);
            }
        }
    }

    public static void main(final String[] args) {
        new Thread(new Task("Counter1")).start();
        new Thread(new Task("Counter2")).start();
    }
}
