package optimization.cooridate.descent;


import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 11.04.17.
 */

@Data
public class CoordinateDescent {
    private List<Pair<Double,Double>> pairs = new LinkedList<>();

    private final Function<Pair<Double,Double>,Double> f;
    private final Function<Double,Double> df1;
    private final Function<Double,Double> df2;

    private final Pair<Double,Double> startPoint;

    public CoordinateDescent(final Function<Pair<Double, Double>, Double> f,
                             final Function<Double, Double> df1,
                             final Function<Double, Double> df2,
                             final Pair<Double, Double> startPoint) {
        this.f = f;
        this.df1 = df1;
        this.df2 = df2;
        this.startPoint = startPoint;
    }

    public Pair<Double,Double> oneStepPoint() {
        return Pair.of(
                df1.apply(startPoint.getRight()),
                df2.apply(startPoint.getLeft())
        );
    }

    public Pair<Double,Double> make() {
        Pair<Double,Double> currentPoint = Pair.of(startPoint.getLeft(),startPoint.getRight());
        for (int i = 0; i < 3; ++i) {
            System.out.println(String.format("(%s;%s)",currentPoint.getLeft(),currentPoint.getRight()));
            System.out.println("current F = " + f.apply(currentPoint));
            double prev = currentPoint.getLeft();
            currentPoint = Pair.of(df1.apply(currentPoint.getRight()),currentPoint.getRight());

            pairs.add(Pair.of(currentPoint.getLeft(),currentPoint.getRight()));

            System.out.println("F after change x1 = " + f.apply(currentPoint));
            System.out.println("h по x1 = " + (currentPoint.getLeft() - prev));
            System.out.println("-------------------------------------------");

            System.out.println(String.format("(%s;%s)",currentPoint.getLeft(),currentPoint.getRight()));
            prev = currentPoint.getRight();
            currentPoint = Pair.of(currentPoint.getLeft(),df2.apply(currentPoint.getLeft()));

            pairs.add(Pair.of(currentPoint.getLeft(),currentPoint.getRight()));

            System.out.println("F after change x2 = " + f.apply(currentPoint));
            System.out.println("h по x2 = " + (currentPoint.getRight() - prev));
            System.out.println(String.format("(%s;%s)",currentPoint.getLeft(),currentPoint.getRight()));
            System.out.println("-------------------------------------------");
        }


        for (int i = 0; i < 1000; ++i) {

            currentPoint = Pair.of(
                    df1.apply(currentPoint.getRight()),
                    df2.apply(currentPoint.getLeft())
            );
        }

        return currentPoint;
    }
}
