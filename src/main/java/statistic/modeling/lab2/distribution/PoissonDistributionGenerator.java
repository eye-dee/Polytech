package statistic.modeling.lab2.distribution;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class PoissonDistributionGenerator implements DistributionGenerator {
    private int r;
    private final double mu;
    private double prev;


    public PoissonDistributionGenerator(final double mu){
        this.mu = mu;
        r = 0;
    }

    @Override
    public double next() {
        ++r;
        if (r == 1){
            return (prev = (mu*Math.exp(-mu)));
        } else {
            return (prev = (prev*mu/r));
        }
    }
    public double getIdealE(){
        return mu;
    }
    public double getIdealV(){
        return  mu;
    }
}
