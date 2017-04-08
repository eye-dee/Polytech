package statistic.modeling.lab2.distribution;

import java.util.Random;

/**
 * Polytech
 * Created by igor on 16.03.17.
 */
public class DirectGeometricalDistribution extends GeometricalDistributionGenerator {
    Random random = new Random();
    public DirectGeometricalDistribution(final double p) {
        super(p);
    }

    public double next() {
        double u,sum = 0.0;
        while ( (u = random.nextDouble()) > p) {
            sum += u;
        }

        return sum;
    }
}
