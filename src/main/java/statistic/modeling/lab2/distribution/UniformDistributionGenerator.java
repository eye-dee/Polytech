package statistic.modeling.lab2.distribution;

import statistic.modeling.lab2.DistributionGenerator;

import java.util.Random;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class UniformDistributionGenerator implements DistributionGenerator {
    private final double low;
    private final double up;

    Random random = new Random();

    public UniformDistributionGenerator(final double low, final double up){
        this.low = low;
        this.up = up;
    }

    public double next(){
        return ((up - low)*random.nextDouble() + low);
    }

    public double getIdealE(){
        return (low + up)/2.0;
    }
    public double getIdealV(){
        return  ((up - low)*(up - low) - 1)/12;
    }
}
