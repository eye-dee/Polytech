package statistic.modeling.lab2;

import java.util.List;

/**
 * Polytech
 * Created by Игорь on 22.02.2017.
 */
public interface RandomSequenceGenerator {
    //сгенерировать последовательность из N символов
    void generate();
    //возвратить математическое ожидание
    double getE();
    //возвратить дисперсию
    double getV();
    //возвратить сумму
    double getSum();


    //возвратить математическое ожидание
    double getCurrentIdealE();
    //возвратить дисперсию
    double getCurrentIdealV();

    void showSequence();

    List<Double> getSequence();
}
