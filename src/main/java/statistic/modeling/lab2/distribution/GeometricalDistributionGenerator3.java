package statistic.modeling.lab2.distribution;

import java.util.Random;

/**
 * Polytech
 * Created by igor on 19.04.17.
 */
public class GeometricalDistributionGenerator3 extends GeometricalDistributionGenerator {
    private final Random random = new Random();
    public GeometricalDistributionGenerator3(final double p){
        super(p);
    }

    public double next(){
        return Math.floor(-Math.log((1.0-random.nextDouble())/2)/Math.log(2));
    }
}
