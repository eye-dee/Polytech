package statistic.modeling.lab2.distribution;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class LogarithmicDistributionGenerator implements DistributionGenerator {
    private final double p;
    private int r;
    private double prev;
    final private double alpha;

    public LogarithmicDistributionGenerator(final double p){
        alpha = 1/Math.log(p);
        this.p = p;
        r = 0;
    }

    @Override
    public double next() {
        ++r;
        if (r == 1){
            return (prev = (-(1/(Math.log(1-p))*p)));
            //return (prev = (-(1-p)/r/Math.log(p)));
        } else {
            return (prev = (prev*p*(r-1)/r));
            //return (prev = (prev*(1-p)/r*(r-1)));
        }
    }
    public double getIdealE(){
        return -1/Math.log(1-p)*p/(1-p);
        //return -alpha*(1-p)/p;
    }
    public double getIdealV(){
        return -p*(p+Math.log(1-p))/((1-p)*(1-p)*Math.log(1-p)*Math.log(1-p));
        //return  -alpha*(1-p)*(1+alpha*(1-p)/p/p);
    }
}
