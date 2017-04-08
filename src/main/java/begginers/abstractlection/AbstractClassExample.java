package begginers.abstractlection;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */

abstract public class AbstractClassExample {
    int par;

    public AbstractClassExample() {
        this.par = 1;
    }

    public void print() {
        System.out.println("par = " + par);
    }
}
