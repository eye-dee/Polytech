package optimization.cooridate.descent;


import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 11.04.17.
 */

public class CoordinateDescent {

    @Data
    @AllArgsConstructor
    public static class Pair<T1,T2> {
        T1 first;
        T2 second;
    }

    private Function<Pair<Double,Double>,Double> f;
    private Function<Double,Double> df1;
    private Function<Double,Double> df2;

    private Pair<Double,Double> currentPoint;

    public CoordinateDescent(Function<Pair<Double, Double>, Double> f,
                             Function<Double, Double> df1,
                             Function<Double, Double> df2,
                             Pair<Double, Double> currentPoint) {
        this.f = f;
        this.df1 = df1;
        this.df2 = df2;
        this.currentPoint = currentPoint;
    }

    public void make() {
        for (int i = 0; i < 5; ++i) {
            System.out.println("current F = " + f.apply(currentPoint));

            double prev = currentPoint.getFirst();
            currentPoint.setFirst(df1.apply(currentPoint.getSecond()));
            System.out.println("F after change x1 = " + f.apply(currentPoint));
            System.out.println("h по x1 = " + (currentPoint.getFirst() - prev));
            System.out.println(String.format("(%s;%s)",currentPoint.getFirst(),currentPoint.getSecond()));

            prev = currentPoint.getSecond();
            currentPoint.setSecond(df2.apply(currentPoint.getFirst()));
            System.out.println("F after change x1 = " + f.apply(currentPoint));
            System.out.println("h по x2 = " + (currentPoint.getSecond() - prev));
            System.out.println(String.format("(%s;%s)",currentPoint.getFirst(),currentPoint.getSecond()));
        }
    }
}
