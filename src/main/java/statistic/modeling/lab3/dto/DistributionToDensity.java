package statistic.modeling.lab3.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */
public class DistributionToDensity {

    public static List<Integer> buildWithoutDividing(final List<Double> sequence) {
        final int DIVIDE_N = 20;

        final List<Integer> density = new ArrayList<>(DIVIDE_N);

        for (int j = 0; j < DIVIDE_N; ++j){
            density.add(0);
        }

        final double MAX = sequence.stream().max(Double::compareTo).get();
        final double MIN = sequence.stream().min(Double::compareTo).get();
        final double LENGTH = MAX-MIN;
        final double PART = LENGTH/DIVIDE_N;

        for (final Double d : sequence){
            int pos = (int)((d-MIN)/PART);
            if (pos >= DIVIDE_N) {
                --pos;
            }
            density.set(pos,density.get(pos) + 1);
        }

        return density;
    }

    public static List<Double> build(final List<Double> sequence) {
        final List<Integer> density = buildWithoutDividing(sequence);
        final List<Double> result = new ArrayList<>();
        for (int i = 0; i < density.size(); ++i) {
            result.add(density.get(i).doubleValue()/sequence.size());
        }

        return result;
    }
}
