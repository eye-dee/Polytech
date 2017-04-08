package statistic.modeling.lab3.distribution;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class ChiSquareDistributionGenerator implements DistributionGenerator {
    private final int N;
    NormalDistributionGenerator normalDistributionGenerator;

    public ChiSquareDistributionGenerator(final int N){
        this.N = N;
        normalDistributionGenerator = new NormalDistributionGenerator(0.0,1.0);
    }

    @Override
    public double next() {
        double sum = 0.0;
        for (int i = 0; i < N; ++i){
            final double z = normalDistributionGenerator.next();
            sum += z*z;
        }
        return sum;
    }

    @Override
    public double getIdealE() {
        return N;
    }

    @Override
    public double getIdealV() {
        return 2.0*N;
    }
}
