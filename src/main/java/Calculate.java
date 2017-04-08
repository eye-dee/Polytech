/**
 * Polytech
 * Created by igor on 05.04.17.
 */
public class Calculate {
    public static void main(final String[] args) {
        final double x = -2;
        final double y = -4;

        final double a1 = 245.45;
        final double a2 = -207.9;
        final double a3 = 47.45;
        final double b1 = -8.2857;
        final double b2 = -8.2857;

        System.out.println(a1*x*x + a2*x*y + a3*y*y + b1*x + b2*y);
    }
}
