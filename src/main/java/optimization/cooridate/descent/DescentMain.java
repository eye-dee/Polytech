package optimization.cooridate.descent;

import java.util.LinkedList;
import java.util.List;
import java.util.function.Function;

import static optimization.cooridate.descent.CoefficientPack.*;

/**
 * Polytech
 * Created by igor on 11.04.17.
 */
public class DescentMain {
    public static void main(final String[] args) {
        CoefficientPack coefficientPack = SEMEN;

        /*Igor*/
        final double a1 = coefficientPack.getA1();
        final double a2 = coefficientPack.getA2();
        final double a3 = coefficientPack.getA3();
        final double b1 = coefficientPack.getB1();
        final double b2 = coefficientPack.getB2();
        final double x0 = coefficientPack.getX0();
        final double y0 = coefficientPack.getY0();
        /**/

        List<CoordinateDescent.Pair<Double,Double>> pairs = new LinkedList<>();

        Function<CoordinateDescent.Pair<Double,Double>,Double> mainFunction =
                doubleDoublePair -> a1*doubleDoublePair.getFirst()*doubleDoublePair.getFirst() +
                        a2*doubleDoublePair.getFirst()*doubleDoublePair.getSecond() +
                        a3*doubleDoublePair.getSecond()*doubleDoublePair.getSecond() +
                        b1*doubleDoublePair.getFirst() +
                        b2*doubleDoublePair.getSecond();

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
                new CoordinateDescent.Pair<>(coefficientPack.getX0(),coefficientPack.getY0())
        );

        System.out.println("Coordinate descent");
        final CoordinateDescent.Pair<Double,Double> realAnswer = coordinateDescent.make();
        final CoordinateDescent.Pair<Double,Double> oneStepAnswer = coordinateDescent.oneStepPoint();

        System.out.println("Rosenbrok descent");
        final RosenbrokDescent rosenbrokDescent = new RosenbrokDescent(
                mainFunction,
                pair -> - (a2*pair.getSecond() + 2*a3*pair.getFirst()*pair.getSecond() + b1 + pair.getFirst()*b2) * 0.5 /
                        (a1 + pair.getFirst() * a2 + a3* pair.getFirst() * pair.getFirst()),
                new CoordinateDescent.Pair<>(x0,y0),
                oneStepAnswer,
                realAnswer
        );

        final CoordinateDescent.Pair<Double,Double> idealLineAnswer = rosenbrokDescent.make();

        final CommonCoordinateDescent commonCoordinateDescent
                = new CommonCoordinateDescent(
                        new CoordinateDescent.Pair<>(-4.0,-3.0),
                quartet -> -(2.0*a1*quartet.getX1()*quartet.getU1() +
                        a2*quartet.getU1()*quartet.getX2() +
                        a2*quartet.getU2()*quartet.getX1() +
                        2*a3*quartet.getU2()*quartet.getX2() +
                        b1*quartet.getU1() + b2*quartet.getU2()
                ) / (2*a1*quartet.getU1()*quartet.getU1() +
                        2*a2*quartet.getU1()*quartet.getU2() +
                        2*a3*quartet.getU2()*quartet.getU2()
                ),
                mainFunction
        );

        commonCoordinateDescent.make(idealLineAnswer.getFirst(),idealLineAnswer.getSecond());
    }
}
