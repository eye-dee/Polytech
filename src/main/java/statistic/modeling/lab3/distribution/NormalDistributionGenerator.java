package statistic.modeling.lab3.distribution;

import statistic.modeling.lab2.DistributionGenerator;
import statistic.modeling.lab2.distribution.UniformDistributionGenerator;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGenerator;

import java.util.Random;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class NormalDistributionGenerator implements DistributionGenerator {
    protected double mu;
    protected double sigma;
    private final Random random = new Random();

    SimpleRandomSequenceGenerator simpleRandomSequenceGenerator;

    public NormalDistributionGenerator(final double mu, final double sigma){
        this.mu = mu;
        this.sigma = sigma;

        simpleRandomSequenceGenerator = new SimpleRandomSequenceGenerator(new UniformDistributionGenerator(0.0,1.0),12);
    }

    public double next(){
        simpleRandomSequenceGenerator.generate();
        //return random.nextGaussian();
        return mu + sigma*(simpleRandomSequenceGenerator.getSum() - 6.0);
    }

    public double getIdealE(){
        return mu;
    }
    public double getIdealV(){
        return sigma*sigma;
    }
}
