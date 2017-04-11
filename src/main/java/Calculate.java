/**
 * Polytech
 * Created by igor on 05.04.17.
 */
public class Calculate {
    public static void main(final String[] args) {
        final double x = -1.3398627505831153;
        final double y = -3.203580203276822;

        final double a1 = 245.45;
        final double a2 = -207.9;
        final double a3 = 47.45;
        final double b1 = -8.2857;
        final double b2 = -8.2857;

        System.out.println(-(a2*y+b1)/2/a1);
        System.out.println(-(a2*x+b2)/2/a3);
        System.out.println(a1*x*x + a2*x*y + a3*y*y + b1*x + b2*y);
    }
}
