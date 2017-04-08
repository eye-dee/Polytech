package statistic.modeling.lab1;

import statistic.modeling.lab1.interval.checkers.IntegerChecker;

import java.util.List;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class Lab1Generator {
    public static void main(final String[] args){
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
        for (int t = 1; t < MAX_T; ++t) {
            final IntervalChecker intervalChecker = new IntegerChecker(1, t);

            final List<Integer> interval = intervalChecker.check(list);

            final ChiSquareCriteria chiSquareCriteria = new ChiSquareCriteria(interval, 0, 1, 1);

            System.out.println("chiSquareCriteria : " + chiSquareCriteria.getChiSquareResult());
        }
    }
}
