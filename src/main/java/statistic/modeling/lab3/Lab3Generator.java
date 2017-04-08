package statistic.modeling.lab3;

import statistic.modeling.lab1.SimpleDrawer;
import statistic.modeling.lab2.RandomSequenceGenerator;
import statistic.modeling.lab2.distribution.UniformDistributionGenerator;
import statistic.modeling.lab2.sequence.SimpleRandomSequenceGenerator;
import statistic.modeling.lab3.distribution.*;
import statistic.modeling.lab3.dto.DistributionToDensity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Polytech
 * Created by Игорь on 23.02.2017.
 */
public class Lab3Generator {
    public static void main(final String[] args){
        final List<RandomSequenceGenerator> randomSequenceGenerators = new ArrayList<>();
        final List<String> names = new ArrayList<>();

        randomSequenceGenerators.add( new SimpleRandomSequenceGenerator(
                new UniformDistributionGenerator(1.0,2.0)
        ));
        names.add("Uniform");
        randomSequenceGenerators.add(new SimpleRandomSequenceGenerator(
                new NormalDistributionGenerator(2.0,1.0)));
        names.add("Normal");
        randomSequenceGenerators.add(new SimpleRandomSequenceGenerator(
                new BoxMillerNormalDistributionGenerator(0.0,5.0)
        ));
        names.add("BoxMiller");
        randomSequenceGenerators.add(new SimpleRandomSequenceGenerator(
                new ExponentialDistributionGenerator(10.0)
        ));
        names.add("Exponential");
        randomSequenceGenerators.add(new SimpleRandomSequenceGenerator(
                new ChiSquareDistributionGenerator(10)
        ));
        names.add("ChiSquare");

        randomSequenceGenerators.add(new SimpleRandomSequenceGenerator(
                new StudentDistributionGenerator(10)
        ));
        names.add("Student");

        randomSequenceGenerators.stream().forEach(
                randomSequenceGenerator -> {
                    randomSequenceGenerator.generate();
                    randomSequenceGenerator.showSequence();
                    System.out.println("E = " + randomSequenceGenerator.getE() + " V = " + randomSequenceGenerator.getV());
                    System.out.println("idealE = " + randomSequenceGenerator.getCurrentIdealE() +
                            " idealV = " + randomSequenceGenerator.getCurrentIdealV());
                }
        );

        final List<SimpleDrawer> drawers = new ArrayList<>(randomSequenceGenerators.size());

        final Iterator<String> iterator = names.iterator();
        for (int i = 0; i < randomSequenceGenerators.size(); ++i){
            drawers.add(new SimpleDrawer(iterator.next()));

            final List<Double> density = DistributionToDensity.build(randomSequenceGenerators.get(i).getSequence());

            drawers.get(i).setY(density/*.stream().mapToDouble(Integer::doubleValue).boxed().collect(Collectors.toList())*/);
            drawers.get(i).createDefaultX();
        }
        drawers.forEach(correlationDrawer -> correlationDrawer.draw());
    }
}
