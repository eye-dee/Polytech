package statistic.modeling.lab1;

import java.util.List;

/**
 * Polytech
 * Created by Игорь on 03.03.2017.
 */
public class ChiSquareCriteria {
    final private List<Integer> intervals;
    final private int alpha;
    final private int betta;
    final private int m;
    final private int sumIntervals;

    public ChiSquareCriteria(final List<Integer> intervals, final int alpha, final int betta, final int m) {
        this.intervals = intervals;
        this.alpha = alpha;
        this.betta = betta;
        this.m = m;
        this.sumIntervals = intervals.stream().mapToInt(Integer::intValue).sum();
    }

    public double getChiSquareResult(){
        double resultSum = 0;
        final double pow2m = Math.pow(2,m);
        final double difference = betta - alpha;
        for (int i = 0; i < intervals.size(); ++i){
            final Integer interval = intervals.get(i);
            final double var = Math.pow((1-difference/pow2m),i);
            double temp = interval - sumIntervals*difference/pow2m*var;
            temp = temp*temp;

            resultSum += temp/(sumIntervals*difference/pow2m*var);
        }
        return resultSum;
    }
}
