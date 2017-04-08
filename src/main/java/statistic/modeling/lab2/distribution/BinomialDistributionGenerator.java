package statistic.modeling.lab2.distribution;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public class BinomialDistributionGenerator implements DistributionGenerator {
    private final double p;
    private final int N;
    private int r;
    private double prev;

    public BinomialDistributionGenerator(final double p, final int N){
        this.p = p;
        this.N = N;
        r = -1;
        prev = -1;

        if (N > 100)
            throw new IllegalArgumentException("Нельзя задавать такое больше число");
    }

    public double next(){
        ++r;
        if (r > N)
            r = 0;
        if (r == 0) {
            return (prev = (fact(N) / fact(r) / fact(N - r) * Math.pow(p, r) * Math.pow(1 - p, N - r)));
        } else {
            return (prev = (prev*(N-(r-1))/(r)*p/(1-p)));
        }
    }

    protected static int fact(final int value){
        int sum = 1;
        for (int i = 1; i <= value; ++i){
            sum *= i;
        }
        return sum;
    }

    public double getIdealE(){
        return N*p;
    }
    public double getIdealV(){
        return  N*p*(1-p);
    }
}
