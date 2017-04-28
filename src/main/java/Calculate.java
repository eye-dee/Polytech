/**
 * Polytech
 * Created by igor on 05.04.17.
 */
public class Calculate {
    public static void main(final String[] args) {
        final double x = -1.3398627505831153;
        final double y = -3.203580203276822;

        /*Igor*
        final double a1 = 245.45;
        final double a2 = -207.9;
        final double a3 = 47.45;
        final double b1 = -8.2857;
        final double b2 = -8.2857;
        /**/

        /*Semen*/
        final double a1 = 125.45;
        final double a2 = -148.5;
        final double a3 = 46.25;
        final double b1 = -6.8;
        final double b2 = -6.8;
        /**/

        /*Kate
        final double a1 = 80.45;
        final double a2 = -118.8;
        final double a3 = 45.8;
        final double b1 = -6.25;
        final double b2 = -6.25;
        /**/

        System.out.println(-(a2*y+b1)/2/a1);
        System.out.println(-(a2*x+b2)/2/a3);
        System.out.println(a1*x*x + a2*x*y + a3*y*y + b1*x + b2*y);

        System.out.println((Math.sqrt(6818641)-1580)/2079);
        System.out.println((-Math.sqrt(6818641)-1580)/2079);
        System.out.println((-Math.sqrt(6818641)+2529)/10);
        System.out.println((Math.sqrt(6818641)+2529)/10);
    }
}
