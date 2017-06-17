package statistic.modeling.lab1;

import java.util.List;

/**
 * Polytech
 * Created on 08.06.17.
 */
public class Dop1Generator {
    public static void main(final String[] args) {
        final Sequencer sequencer = new Sequencer();

        sequencer.readTableParam();
        sequencer.generateSequence();

        final List<Double> lastSequence = sequencer.getSequence().get(0);

        final MonotoneChecker monotoneChecker = new MonotoneChecker(lastSequence);

        System.out.println(monotoneChecker.check());
        System.out.println(monotoneChecker.t());
    }
}
