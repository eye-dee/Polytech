package statistic.modeling.lab3.dto;

import java.util.List;

/**
 * Polytech
 * Created by igor on 25.03.17.
 */
public class DistributionToFunction extends DistributionToDensity {

    public static List<Double> build(final List<Double> sequence) {
        final List<Double> density = DistributionToDensity.build(sequence);

        for (int i = 1; i < density.size(); ++i) {
            density.set(i,density.get(i-1) + density.get(i));
        }

        return density;
    }
}
