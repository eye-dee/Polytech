package statistic.modeling.lab2;

import statistic.modeling.lab1.SimpleDrawer;
import statistic.modeling.lab2.check.PirsonChecker;
import statistic.modeling.lab2.distribution.GeometricalDistributionGenerator;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGeneratorWithDensityParam;
import statistic.modeling.lab3.dto.DistributionToDensity;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 25.03.17.
 */
public class Lab2DopGenerator {

    public static void main(final String[] args) {
        final int N = 100;
        final RandomSequenceGenerator randomSequenceGenerator = new SimpleRandomSequenceGeneratorWithDensityParam(
            new GeometricalDistributionGenerator(0.5),1,-1,N);
        randomSequenceGenerator.generate();
        randomSequenceGenerator.showSequence();

        final List<Double> tempRealDistributionFunction = new ArrayList<>();
        final List<Double> distributionFunction = DistributionToDensity.build(randomSequenceGenerator.getSequence());

        final double q = 0.5;
        double sumQ = 1.0;
        final double p = 1-q;
        for (int i = 0; i < distributionFunction.size(); ++i) {
            tempRealDistributionFunction.add(sumQ*p);
            sumQ *= q;
        }

        final List<Double> realDistributionFunction = DistributionToDensity.build(tempRealDistributionFunction);

        final SimpleDrawer simpleDrawer = new SimpleDrawer("Sdasd");
        simpleDrawer.setY(realDistributionFunction);
        simpleDrawer.createDefaultX();

        simpleDrawer.draw();

        final SimpleDrawer simpleDrawer2 = new SimpleDrawer("Sdasd");
        simpleDrawer2.setY(distributionFunction);
        simpleDrawer2.createDefaultX();

        simpleDrawer2.draw();

        final PirsonChecker pirsonChecker = new PirsonChecker();
        pirsonChecker.setDistributionFunc(distributionFunction);
        pirsonChecker.setRealDistributionFunc(realDistributionFunction);
        pirsonChecker.setN(N);

        pirsonChecker.check();

        System.out.println(pirsonChecker.getStatistic());
    }
}
