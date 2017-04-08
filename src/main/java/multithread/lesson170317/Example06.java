package multithread.lesson170317;

/**
 * Polytech
 * Created by igor on 17.03.17.
 */
public class Example06 {

    public static void main(final String[] args) {
        //Если метод toString переопределен, то автоматически вызывается, когда это нужно
        System.out.println(Thread.currentThread());
        System.out.println(Thread.currentThread().toString());
    }
}
