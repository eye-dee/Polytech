package statistic.modeling.lab2;

import statistic.modeling.lab1.SimpleDrawer;
import statistic.modeling.lab2.distribution.*;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGenerator;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGeneratorWithDensityParam;
import statistic.modeling.lab3.dto.DistributionToDensity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */

public class Lab2Generator {
    public static void main(final String[] args){
        final List<RandomSequenceGenerator> distributionGenerators = new ArrayList<>();
        final List<String> names = new ArrayList<>();

        distributionGenerators.add( new SimpleRandomSequenceGenerator(
                new UniformDistributionGenerator(1.0,100.0)));
        names.add("Uniform");
        distributionGenerators.add( new SimpleRandomSequenceGeneratorWithDensityParam(
                new BinomialDistributionGenerator(0.5,10),0,10));
        names.add("Binomial");
        distributionGenerators.add( new SimpleRandomSequenceGeneratorWithDensityParam(
                new GeometricalDistributionGenerator(0.5),1,-1));
        names.add("Geometrical");
        distributionGenerators.add( new SimpleRandomSequenceGeneratorWithDensityParam(
                new PoissonDistributionGenerator(10.0),1,-1));
        names.add("Poisson");
        distributionGenerators.add( new SimpleRandomSequenceGeneratorWithDensityParam(
                new LogarithmicDistributionGenerator(0.5),1,-1));
        names.add("Logarithmic");

        distributionGenerators.add( new SimpleRandomSequenceGeneratorWithDensityParam(
                new DirectGeometricalDistribution(0.5),1,-1));
        names.add("DirectGeometrical");

        final List<SimpleDrawer> drawers = new ArrayList<>();

        final Iterator<String> iterator = names.iterator();
        distributionGenerators.stream().forEach(
                randomSequenceGenerator -> {
                    randomSequenceGenerator.generate();
                    randomSequenceGenerator.showSequence();
                    System.out.println("E = " + randomSequenceGenerator.getE() + " V = " + randomSequenceGenerator.getV());
                    System.out.println("idealE = " + randomSequenceGenerator.getCurrentIdealE() +
                            " idealV = " + randomSequenceGenerator.getCurrentIdealV());

                    final SimpleDrawer simpleDrawer = new SimpleDrawer(iterator.next());

                    final List<Double> density = DistributionToDensity.build(randomSequenceGenerator.getSequence());
                    //List<Double> densityDouble = density.stream().mapToDouble(Integer::doubleValue).boxed().collect(Collectors.toList());

                    simpleDrawer.setY(/*randomSequenceGenerator.getSequence().stream().limit(50).collect(Collectors.toList())*/density);
                    simpleDrawer.createDefaultX();

                    drawers.add(simpleDrawer);
                }
        );

        drawers.forEach(SimpleDrawer::draw);
    }
}
