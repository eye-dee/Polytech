package optimization.cooridate.descent;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 11.04.17.
 */

@Data
public class CoordinateDescent {
    private List<Pair> pairs = new LinkedList<>();

    @Data
    @AllArgsConstructor
    public static class Pair<T1,T2> {
        T1 first;
        T2 second;

        Pair() {

        }

        @Override
        protected Pair<T1,T2> clone() throws CloneNotSupportedException {
            return new Pair<T1,T2>(first,second);
        }
    }

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
        return new Pair<>(
                df1.apply(startPoint.getSecond()),
                df2.apply(startPoint.getFirst())
        );
    }

    public Pair<Double,Double> make() {
        final Pair<Double,Double> currentPoint = new Pair<>(startPoint.getFirst(),startPoint.getSecond());
        for (int i = 0; i < 3; ++i) {
            System.out.println(String.format("(%s;%s)",currentPoint.getFirst(),currentPoint.getSecond()));
            System.out.println("current F = " + f.apply(currentPoint));
            double prev = currentPoint.getFirst();
            currentPoint.setFirst(df1.apply(currentPoint.getSecond()));
            try {
                pairs.add(currentPoint.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("F after change x1 = " + f.apply(currentPoint));
            System.out.println("h по x1 = " + (currentPoint.getFirst() - prev));
            System.out.println("-------------------------------------------");

            System.out.println(String.format("(%s;%s)",currentPoint.getFirst(),currentPoint.getSecond()));
            prev = currentPoint.getSecond();
            currentPoint.setSecond(df2.apply(currentPoint.getFirst()));
            try {
                pairs.add(currentPoint.clone());
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            System.out.println("F after change x2 = " + f.apply(currentPoint));
            System.out.println("h по x2 = " + (currentPoint.getSecond() - prev));
            System.out.println(String.format("(%s;%s)",currentPoint.getFirst(),currentPoint.getSecond()));
            System.out.println("-------------------------------------------");
        }


        for (int i = 0; i < 1000; ++i) {
            currentPoint.setFirst(df1.apply(currentPoint.getSecond()));
            currentPoint.setSecond(df2.apply(currentPoint.getFirst()));
        }

        return currentPoint;
    }
}
