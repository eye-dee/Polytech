package statistic.modeling.lab2.sequence;

import statistic.modeling.lab2.DistributionGenerator;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class SimpleRandomSequenceGeneratorWithDensityParam extends SimpleRandomSequenceGenerator {
    //границы интегрирования
    private final int a;
    private final int b;
    public SimpleRandomSequenceGeneratorWithDensityParam(final DistributionGenerator distributionGenerator, final int a, final int b){
        super(distributionGenerator);
        this.a = a;
        this.b = b;
    }

    public SimpleRandomSequenceGeneratorWithDensityParam(final DistributionGenerator distributionGenerator, final int a, final int b, final int N){
        super(distributionGenerator,N);
        this.a = a;
        this.b = b;
    }

    @Override
    public double getE(){
        double sum = 0;
        for (int i = 0; (i+a <= b || b == -1) && i < sequence.size(); ++i) {
            sum += (a + i)*sequence.get(i);
        }
        return sum;
    }

    @Override
    public double getV(){
        final double m = getE();
        double sum = 0;
        for (int i = 0; (i+a <= b || b == -1) && i < sequence.size(); ++i) {
            sum += (a+i - m)*(a+i - m)*sequence.get(i);
        }
        return sum;
    }
}
