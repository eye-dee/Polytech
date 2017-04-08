package statistic.modeling.lab2.distribution;

import java.util.Random;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */
public class GeometricalDistributionGenerator2 extends GeometricalDistributionGenerator {
    private final Random random = new Random();
    public GeometricalDistributionGenerator2(final double p){
        super(p);
    }

    public double next(){
        ++r;

        int k = 0;
        double sum = p;
        double prod = p;
        final double q = 1 - p;
        final double U = random.nextDouble();
        while (U > sum) {
            prod *= q;
            sum += prod;
            ++k;
        }

        return k;
    }
}
