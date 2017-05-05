package statistic.modeling.lab2.sequence;

import statistic.modeling.lab2.DistributionGenerator;
import statistic.modeling.lab2.RandomSequenceGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */

public class SimpleRandomSequenceGenerator implements RandomSequenceGenerator {
    protected final int N;
    protected List<Double> sequence;
    protected DistributionGenerator distributionGenerator;

    @Override
    public List<Double> getSequence() {
        return sequence;
    }

    public SimpleRandomSequenceGenerator(final DistributionGenerator distributionGenerator){
        N = 100000;
        sequence = new ArrayList<>(N);
        this.distributionGenerator = distributionGenerator;
    }

    public SimpleRandomSequenceGenerator(final DistributionGenerator distributionGenerator, final int N){
        this.N = N;
        sequence = new ArrayList<>(N);
        this.distributionGenerator = distributionGenerator;
    }

    public void generate(){
        sequence.clear();
        for (int i = 0; i < N; ++i){
            sequence.add(distributionGenerator.next());
        }
    }
    public double getE(){
        return sequence.stream().
                mapToDouble(value -> value).
                average().
                getAsDouble();
    }

    public double getV(){
        final double m = getE();
        return sequence.stream().
                mapToDouble(value -> (value - m)*(value - m)).
                average().
                getAsDouble();
    }

    public double getSum(){
        return sequence.stream()
                .mapToDouble(value -> value)
                .sum();
    }

    public double getCurrentIdealE(){
        return distributionGenerator.getIdealE();
    }
    public double getCurrentIdealV(){
        return distributionGenerator.getIdealV();
    }

    public void showSequence(){
        System.out.println(Arrays.toString(sequence.toArray()));
    }
}
