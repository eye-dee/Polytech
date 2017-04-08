package statistic.modeling.lab2;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public interface DistributionGenerator {
    //очередное случайное число
    double next();

    double getIdealE();
    double getIdealV();
}
