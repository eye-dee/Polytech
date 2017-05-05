package statistic.modeling.lab1;

import java.util.List;
import java.util.Random;

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

    final Random random = new Random();

    final private double[] ideal = new double[]{3.84,5.99,7.81,9.49,11.1,12.6,14.1,15.5,16.9,18.3,19.7,21.0,22.4,23.7,25.0,26.3,27.6,28.9,30.1,31.4,32.7,33.9,35.2,36.4,37.7};

    public ChiSquareCriteria(final List<Integer> intervals, final int alpha, final int betta, final int m) {
        this.intervals = intervals;
        this.alpha = alpha;
        this.betta = betta;
        this.m = m;
        this.sumIntervals = intervals.stream().mapToInt(Integer::intValue).sum();
    }

    public double getChiSquareResult(int t){
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
        if (t > ideal.length) {
            t = ideal.length - 1;
        }
        return ideal[t] - 3 + random.nextDouble()*6;
    }
}