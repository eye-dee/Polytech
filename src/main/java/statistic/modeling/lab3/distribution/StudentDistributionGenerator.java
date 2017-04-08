package statistic.modeling.lab3.distribution;

import org.apache.commons.math3.distribution.ChiSquaredDistribution;
import statistic.modeling.lab2.DistributionGenerator;

import java.util.Random;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class StudentDistributionGenerator implements DistributionGenerator {
    private final int N;
    private final NormalDistributionGenerator normalDistributionGenerator;
    private final ChiSquareDistributionGenerator chiSquareDistributionGenerator;
    private final Random random = new Random();
    private final ChiSquaredDistribution chiSquaredDistribution;

    public StudentDistributionGenerator(final int N){
        this.N = N;

        normalDistributionGenerator = new NormalDistributionGenerator(0.0,1.0);
        chiSquareDistributionGenerator = new ChiSquareDistributionGenerator(N);
        chiSquaredDistribution = new ChiSquaredDistribution(N);
    }

    @Override
    public double next() {
        return random.nextGaussian()/Math.sqrt(chiSquareDistributionGenerator.next()/N);
        //return normalDistributionGenerator.next()/Math.sqrt(chiSquareDistributionGenerator.next()/N);
    }

    @Override
    public double getIdealE() {
        return 0;
    }

    @Override
    public double getIdealV() {
        return (double)N/(N-2);
    }
}
