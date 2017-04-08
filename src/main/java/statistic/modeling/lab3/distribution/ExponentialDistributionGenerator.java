package statistic.modeling.lab3.distribution;

import statistic.modeling.lab2.DistributionGenerator;

import java.util.Random;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class ExponentialDistributionGenerator implements DistributionGenerator{
    private final double beta;
    private final Random random = new Random();

    public ExponentialDistributionGenerator(final double beta){
        this.beta = beta;
    }

    @Override
    public double next() {
        return -beta*Math.log(random.nextDouble());
    }

    @Override
    public double getIdealE() {
        return beta;
    }

    @Override
    public double getIdealV() {
        return beta*beta;
    }
}
