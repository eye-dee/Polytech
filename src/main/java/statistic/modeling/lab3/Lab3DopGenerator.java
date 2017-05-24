package statistic.modeling.lab3;

import statistic.modeling.lab2.RandomSequenceGenerator;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGenerator;
import statistic.modeling.lab3.checker.KolmogorovCheck;
import statistic.modeling.lab3.distribution.NormalDistributionGenerator;
import statistic.modeling.lab3.dto.DistributionToDensity;
import statistic.modeling.lab3.dto.DistributionToFunction;

import java.util.Arrays;
import java.util.List;

/**
 * Polytech
 * Created by igor on 25.03.17.
 */
public class Lab3DopGenerator {

    public static void main(final String[] args) {
    final RandomSequenceGenerator randomSequenceGenerator = new SimpleRandomSequenceGenerator(
                new NormalDistributionGenerator(0.0,5.0));
        randomSequenceGenerator.generate();
        //randomSequenceGenerator.showSequence();

        final List<Double> density = DistributionToDensity.build(randomSequenceGenerator.getSequence());

        /*SimpleDrawer drawer = new SimpleDrawer("sim");
        drawer.setY(density);
        drawer.createDefaultX();

        drawer.draw();*/

        final List<Double> temp = DistributionToFunction.build(randomSequenceGenerator.getSequence());

        /*for (int i = 0; i < temp.size(); ++i) {
            double x = -0.2 + i*0.05;
            System.out.println("x " + x + " F = " + 0.5*(1.0 + Erf.erf(x)));
            temp.set(i, Erf.erf(x));
        }*/

        /*SimpleDrawer drawer = new SimpleDrawer("sim");
        drawer.setY(temp);
        drawer.createDefaultX();

        drawer.draw();*/

        System.out.println(temp.size());
        System.out.println(Arrays.toString(temp.toArray()));

        final KolmogorovCheck kolmogorovCheck = new KolmogorovCheck();
        kolmogorovCheck.setDistributionFunc(temp);

        kolmogorovCheck.check();
        System.out.println(kolmogorovCheck.getStatistic());
    }
}
