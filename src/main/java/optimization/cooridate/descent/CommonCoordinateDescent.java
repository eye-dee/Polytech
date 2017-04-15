package optimization.cooridate.descent;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Random;
import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 12.04.17.
 */

@Data
@AllArgsConstructor
public class CommonCoordinateDescent {
    private CoordinateDescent.Pair<Double,Double> point;

    @Data
    @AllArgsConstructor
    public static class Quartet {
        private double x1;
        private double x2;
        private double u1;
        private double u2;
    }

    Function<Quartet,Double> getL;
    Function<CoordinateDescent.Pair<Double,Double>, Double> f;

    public void make(final double u1, final double u2) {
        {
            final Quartet quartet = new Quartet(point.getFirst(),point.getSecond(),u1,u2);
            final double h = getL.apply(quartet);

            System.out.println("F = " + f.apply(point));

            point.setFirst(point.getFirst() + h*u1);
            point.setSecond(point.getSecond() + h*u2);
            System.out.println(String.format("(%s;%s)",point.getFirst(),point.getSecond()));

            System.out.println("F = " + f.apply(point));
        }
        {
            final Quartet quartet = new Quartet(point.getFirst(),point.getSecond(),-1/u1,u2);
            final double h = getL.apply(quartet);

            System.out.println("F = " + f.apply(point));

            point.setFirst(point.getFirst() + h*u1);
            point.setSecond(point.getSecond() + h*u2);
            System.out.println(String.format("(%s;%s)",point.getFirst(),point.getSecond()));

            System.out.println("F = " + f.apply(point));
        }
    }
}
