package optimization.cooridate.descent;

import lombok.Data;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/**
 * Polytech
 * Created by igor on 12.04.17.
 */

@Data
public class RosenbrokDescent {
    private final Function<Pair<Double,Double>,Double> f;
    private final Function<Pair<Double,Double>,Double> dfx;
    private Pair<Double,Double> curP0;
    private Pair<Double,Double> curP1;
    private final Pair<Double, Double> answer;
    private Pair<Double,Double> curP2;
    private Pair<Double,Double> curP3;

    private List<Pair<Double,Double>> pairs = new ArrayList<>();


    public RosenbrokDescent(final Function<Pair<Double,Double>,Double> f,
                            final Function<Pair<Double, Double>, Double> dfx,
                            final Pair<Double,Double> p0,
                            final Pair<Double,Double> p1,
                            final Pair<Double,Double> answer) {
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

        double left = dfx.apply(Pair.of(k,b));

        curP2 = Pair.of(
                left,
                k*left + b
        );

        System.out.println(String.format("gotten point (%s,%s)",curP2.getLeft(),curP2.getRight()));
        System.out.println("F = " + f.apply(curP2));

        System.out.println("__________________________________________");

        k = -1 / k;
        b = curP2.getRight() - k*curP2.getLeft();
        System.out.println(String.format("ortogonalic y = %s*x + %s",k,b));

        left = dfx.apply(Pair.of(k, b));

        curP3 = Pair.of(
                left,
                k*left + b
        );

        System.out.println(String.format("gotten point (%s,%s)",curP3.getLeft(),curP3.getRight()));
        System.out.println("F = " + f.apply(curP3));

    }

    private static double getK(final Pair<Double,Double> p0, final Pair<Double,Double> p1) {
        return (p1.getRight() - p0.getRight())/(p1.getLeft() - p0.getLeft());
    }
    private static double getB(final Pair<Double,Double> p0, final Pair<Double,Double> p1) {
        return (p1.getLeft()*p0.getRight() - p1.getRight()*p0.getLeft())/(p1.getLeft() - p0.getLeft());
    }

    public Pair<Double,Double> make() {
        final Pair<Double,Double> idealLineAnswer =
                Pair.of(
                        getK(curP0,answer),
                        getB(curP0,answer)
        );
        for (int i = 0; i < 5; ++i){
            step();


            pairs.add(curP0);
            pairs.add(curP1);
            pairs.add(curP2);
            pairs.add(curP3);

            curP0 = Pair.of(curP1.getLeft(),curP1.getRight());
            curP1 = Pair.of(curP3.getLeft(),curP3.getRight());
        }

        return idealLineAnswer;
    }
}
