package multithread.lesson170331;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class A {

    int i = 0;

    synchronized void x() {
        i++;
    }

    synchronized void y() {
        i--;
    }

    synchronized void z() {
        System.out.println(i);
    }


}
