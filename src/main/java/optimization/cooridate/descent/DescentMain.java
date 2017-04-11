package optimization.cooridate.descent;

/**
 * Polytech
 * Created by igor on 11.04.17.
 */
public class DescentMain {
    public static void main(final String[] args) {
        final double a1 = 245.45;
        final double a2 = -207.9;
        final double a3 = 47.45;
        final double b1 = -8.2857;
        final double b2 = -8.2857;

        final CoordinateDescent coordinateDescent = new CoordinateDescent(
                pair ->
                    a1*pair.getFirst()*pair.getFirst() +
                            a2*pair.getFirst()*pair.getSecond() +
                            a3*pair.getSecond()*pair.getSecond() +
                            b1*pair.getFirst() +
                            b2*pair.getSecond()
                ,
                x -> -(a2*x+b1)/2/a1,
                x -> -(a2*x+b2)/2/a3,
                new CoordinateDescent.Pair<>(-3.0,-4.0)
        );


        coordinateDescent.make();
    }
}
