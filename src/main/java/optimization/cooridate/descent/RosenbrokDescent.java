package optimization.cooridate.descent;

import lombok.AllArgsConstructor;

import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 12.04.17.
 */

public class RosenbrokDescent {
    private final Function<CoordinateDescent.Pair<Double,Double>,Double> f;
    private final Function<CoordinateDescent.Pair<Double,Double>,Double> dfx;
    private CoordinateDescent.Pair<Double,Double> curP0;
    private CoordinateDescent.Pair<Double,Double> curP1;
    private final CoordinateDescent.Pair<Double, Double> answer;
    private CoordinateDescent.Pair<Double,Double> curP2 = new CoordinateDescent.Pair<>();
    private CoordinateDescent.Pair<Double,Double> curP3 = new CoordinateDescent.Pair<>();


    public RosenbrokDescent(final Function<CoordinateDescent.Pair<Double,Double>,Double> f,
                            final Function<CoordinateDescent.Pair<Double, Double>, Double> dfx,
                            final CoordinateDescent.Pair<Double,Double> p0,
                            final CoordinateDescent.Pair<Double,Double> p1,
                            final CoordinateDescent.Pair<Double,Double> answer) {
        this.f = f;
        this.dfx = dfx;
        this.curP0 = p0;
        this.curP1 = p1;
        this.answer = answer;
    }

    private void step() {
        double k = getK(curP0,curP1);
        double b = getB(curP0,curP1);

        System.out.println(String.format("y = %s*x + %s",k,b));
        curP2.setFirst(dfx.apply(new CoordinateDescent.Pair<>(k,b)));
        curP2.setSecond(k*curP2.getFirst() + b);
        System.out.println(String.format("gotten point (%s,%s)",curP2.getFirst(),curP2.getSecond()));
        System.out.println("F = " + f.apply(curP2));

        System.out.println("__________________________________________");

        k = -1 / k;
        b = curP2.getSecond() - k*curP2.getFirst();
        System.out.println(String.format("ortogonalic y = %s*x + %s",k,b));

        curP3.setFirst(dfx.apply(new CoordinateDescent.Pair<>(k,b)));
        curP3.setSecond(k*curP3.getFirst() + b);
        System.out.println(String.format("gotten point (%s,%s)",curP3.getFirst(),curP3.getSecond()));
        System.out.println("F = " + f.apply(curP3));

    }

    private static double getK(final CoordinateDescent.Pair<Double,Double> p0, final CoordinateDescent.Pair<Double,Double> p1) {
        return (p1.getSecond() - p0.getSecond())/(p1.getFirst() - p0.getFirst());
    }
    private static double getB(final CoordinateDescent.Pair<Double,Double> p0, final CoordinateDescent.Pair<Double,Double> p1) {
        return (p1.getFirst()*p0.getSecond() - p1.getSecond()*p0.getFirst())/(p1.getFirst() - p0.getFirst());
    }

    public CoordinateDescent.Pair<Double,Double> make() {
        CoordinateDescent.Pair<Double,Double> idealLineAnswer =
                new CoordinateDescent.Pair<>(
                        getK(curP0,answer),
                        getB(curP0,answer)
        );
        for (int i = 0; i < 5; ++i){
            step();
            try {
                curP0 = (CoordinateDescent.Pair)curP1.clone();
                curP1 = (CoordinateDescent.Pair)curP3.clone();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
        }

        return idealLineAnswer;
    }
}
