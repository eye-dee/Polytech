package statistic.modeling.lab1.interval.checkers;

import statistic.modeling.lab1.IntervalChecker;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Polytech
 * Created by Игорь on 03.03.2017.
 */
public class IntegerChecker implements IntervalChecker {
    private final int alpha;
    private final int betta;
    private final int m;
    private final int t;
    private List<Integer> sequence;
    private final List<Integer> intervals = new ArrayList<>();

    public IntegerChecker(final int m, final int t){
        this.m = m;
        alpha = 0;
        betta = (int)Math.pow(2,m) - 1;
        this.t = t;
    }

    @Override
    public List<Integer> check(final List<Double> doubles) {
        sequence = collectDoubleListToInt(doubles);

        for (int i = 0; i < t; ++i){
            intervals.add(0);
        }

        for (int i = 0; i < sequence.size(); ++i){
            if (betweenAlphaAndBetta(sequence.get(i))) {
                for (int j = 0; j < intervals.size(); ++j) {
                    int pos = j + i + 1;
                    while (pos >= sequence.size()){
                        pos -= sequence.size();
                    }
                    if (betweenAlphaAndBetta(sequence.get(pos))) {
                        incrementListElement(intervals,j);
                    }
                }
            }
        }

        return intervals;
    }

    private List<Integer> collectDoubleListToInt(final List<Double> doubles){
        return doubles.stream().mapToInt(value-> (int)(value*Math.pow(10,1))).boxed().collect(Collectors.toList());
    }
    private boolean betweenAlphaAndBetta(final int value){
        return (value >= alpha && value < betta);
    }
    private void incrementListElement(final List<Integer> list, final int i){
        if (i > 0 && i < t) {
            list.set(i, list.get(i) + 1);
        }
    }
}
