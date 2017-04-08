package statistic.modeling.lab2.check;

import lombok.Data;
import statistic.modeling.interfaces.DistributionChecker;

import java.util.List;

/**
 * Polytech
 * Created by igor on 25.03.17.
 */

@Data
public class PirsonChecker implements DistributionChecker{
    private List<Double> distributionFunc;
    private List<Double> realDistributionFunc;
    private int N;

    private double chiSquareStatistic;
    @Override
    public void check() {
        final int k = distributionFunc.size();

        double sum = 0.0;
        for (int i = 0; i < k; ++i) {
            sum += Math.pow(realDistributionFunc.get(i) - distributionFunc.get(i),2.0)/realDistributionFunc.get(i);
        }

        chiSquareStatistic = N*sum;
    }

    @Override
    public double getStatistic() {
        return chiSquareStatistic;
    }
}
