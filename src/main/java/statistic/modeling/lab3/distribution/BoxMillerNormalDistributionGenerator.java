package statistic.modeling.lab3.distribution;

import java.util.Random;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class BoxMillerNormalDistributionGenerator extends NormalDistributionGenerator {
    final private static int FIRST = 0;
    final private static int SECOND = 1;

    private final Random random = new Random();
    private int state;
    private double first,second;

    public BoxMillerNormalDistributionGenerator(final double mu, final double sigma){
        super(mu,sigma);
        state = FIRST;
    }

    private void regenerate(){
        final double u1 = random.nextDouble();
        final double u2 = random.nextDouble();

        first = Math.sqrt( -2*Math.log(u2) )*Math.cos(2*Math.PI*u1);
        second = Math.sqrt( -2*Math.log(u2) )*Math.sin(2*Math.PI*u1);
    }

    @Override
    public double next() {
        if (state == FIRST){
            regenerate();
            state = SECOND;
            return mu + sigma*first;
        } else if (state == SECOND){
            state = FIRST;
            return mu + sigma*second;
        } else {
            throw new IllegalArgumentException("Unreachable statement");
        }
    }
}
