package statistic.modeling.lab3.checker;

import lombok.Data;
import statistic.modeling.interfaces.DistributionChecker;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 25.03.17.
 */

@Data
public class KolmogorovCheck implements DistributionChecker {
    private List<Double> distributionFunc;
    private double maxD;

    public void check() {
        final List<Double> Dnm= new ArrayList<>(distributionFunc.size());
        final List<Double> Dnp= new ArrayList<>(distributionFunc.size());

        final int N = distributionFunc.size();
        for (int i = 0; i < N; ++i) {
            Dnm.add( (double)i/N - distributionFunc.get(i));
            Dnp.add( distributionFunc.get(i) - (double)(i-1)/N);
        }

        final double maxDnm = getMaxFromList(Dnm);
        final double maxDnp = getMaxFromList(Dnp);

        maxD = maxDnm;

        if (maxDnm < maxDnp) {
            maxD = maxDnp;
        }

        System.out.println(maxD);
    }

    private double getMaxFromList(final List<Double> list) {
        return list.stream().max(Double::compareTo).get();
    }

    public double getStatistic() {
        return maxD*Math.sqrt(distributionFunc.size());
        //return (6*distributionFunc.size()*maxD+1)/(6* Math.sqrt(distributionFunc.size()));
    }
}
