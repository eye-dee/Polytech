package multithread.lesson170310;

/**
 * Polytech
 * Created by igor on 10.03.17.
 */

//install jvisualvm
public class Example01 {
    public static void main(final String[] args) {
        final Thread currentThread = Thread.currentThread();

        System.out.println(currentThread.getName());
        System.out.println(currentThread.getThreadGroup().getName());

        //Программа завершается, когда заканчивается каждый из потоков

        final Thread thread1 = new Thread(() -> {
            System.out.println("hello");
            final Thread currentThread1 = Thread.currentThread();
            System.out.println(currentThread1.getName());
            //Если поток создал другой поток,
            //то группа созданного потока будет такой же, как и у создавшего
            System.out.println(currentThread1.getThreadGroup().getName());
            try {
                //Захватывает ядро
                Thread.sleep(100000);
            } catch (final InterruptedException ie) {
                System.out.println(ie.getCause());
            }
            System.out.println(currentThread1.getName() + " finished");
        });
        thread1.start();

        System.out.println(currentThread.getName() + " finished");
    }
}
