package multithread.lesson170317;

import multithread.lesson170310.Utils;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public class DaemonExample {

    static class Counter implements Runnable {
        @Override
        public void run() {
            int count = 0;

            while (true) {
                Utils.pause(1000);

                System.out.println(++count);
            }
        }
    }

    //Демоны - фоновые потоки, котрые могут остановиться в любой момент
    //Они автоматически завершают свою работу, когда завершается приложение(то есть завершаются все потоки-не демоны)
    //Сервисные потоки, которые могут быть остановлены в любой момент, когда основное приложение завершится
    public static void main(final String[] args) {
        System.out.println("start");
        final Thread thread = new Thread(new Counter());
        thread.setDaemon(true);
        thread.start();

        Utils.pause(5000);
    }
}
