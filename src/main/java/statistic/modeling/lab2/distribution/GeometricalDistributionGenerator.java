package statistic.modeling.lab2.distribution;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class GeometricalDistributionGenerator implements DistributionGenerator {
    protected double p;
    protected int r;
    private double prev;

    public GeometricalDistributionGenerator(final double p){
        this.p = p;
        r = 0;
    }

    public double next(){
        ++r;
        if (r == 1){
            return (prev = p);
        } else {
            return (prev = (prev*(1-p)));
        }
    }
    public double getIdealE(){
        return 1/p;
    }
    public double getIdealV(){
        return  (1-p)/p/p;
    }
}
