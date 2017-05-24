package statistic.modeling.lab1;

import statistic.modeling.lab1.interval.checkers.IntegerChecker;

import java.util.List;

import static java.lang.Math.abs;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class Lab1Generator {
    public static void main(final String[] args) {
        final Sequencer sequencer = new Sequencer();

        sequencer.readTableParam();
        sequencer.generateSequence();
        sequencer.evaluateParams();
        sequencer.createEmpiricFunctions();
        sequencer.show();

        //sequencer.createTable();

        /*List<SimpleDrawer> drawers = new ArrayList<>(Sequencer.SEQUENCES_N*3);

        for (int i = 0; i < Sequencer.SEQUENCES_N; ++i){
            drawers.add(new SimpleDrawer("Коррелограмма"));

            drawers.get(i).setY(sequencer.getCorrelation().get(i));
            drawers.get(i).createDefaultX();
        }

        for (int i = 0; i < Sequencer.SEQUENCES_N; ++i){
            drawers.add(new SimpleDrawer("Функция плотности"));
            int last = drawers.size()-1;

            drawers.get(last).setY(sequencer.getF().get(i).stream().
                    mapToDouble(Integer::doubleValue).
                    boxed().
                    collect(Collectors.toList()));
            drawers.get(last).createDefaultX();
        }

        for (int i = 0; i < Sequencer.SEQUENCES_N; ++i){
            drawers.add(new SimpleDrawer("Функция распределения"));
            int last = drawers.size()-1;

            drawers.get(last).setY(sequencer.getFF().get(i).stream().
                    collect(Collectors.toList()));
            drawers.get(last).createDefaultX();
        }

        drawers.forEach(correlationDrawer -> correlationDrawer.draw());
*/
        final List<Double> list = sequencer.getSequence().get(0);
        final int MAX_T = 25;

        final double[] ideal = new double[]{3.84, 5.99, 7.81, 9.49, 11.1, 12.6, 14.1, 15.5, 16.9, 18.3, 19.7, 21.0, 22.4, 23.7, 25.0, 26.3, 27.6, 28.9, 30.1, 31.4, 32.7, 33.9, 35.2, 36.4, 37.7};
        for (int t = 1; t < MAX_T; ++t) {
            final IntervalChecker intervalChecker = new IntegerChecker(1, t);

            final List<Integer> interval = intervalChecker.check(list);

            final ChiSquareCriteria chiSquareCriteria = new ChiSquareCriteria(interval, 0, 1, 1);

            System.out.println(" k = " + t + " " + chiSquareCriteria.getChiSquareResult(t) + " dif = " +
                    abs(chiSquareCriteria.getChiSquareResult(t) - ideal[t])
            );
        }
    }
}
