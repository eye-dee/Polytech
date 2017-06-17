package statistic.modeling.lab1;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created on 08.06.17.
 */
public class MonotoneChecker {
    private List<Double> sequence;
    private List<Integer> nu = new ArrayList<>();

    public MonotoneChecker(List<Double> sequence) {
        this.sequence = sequence;
    }

    private static double pi(int i) {
        final double fact = fact(i);

        return 1 / fact - 1 / (fact) / (i + 1);
    }

    private static double fact(int i) {
        double res = 1.0;

        for (int j = 2; j <= i; ++j) {
            res *= i;
        }

        return res;
    }

    double check() {
        for (int i = 0; i < sequence.size(); ++i) {
            nu.add(0);
        }

        for (int i = 0; i < sequence.size(); ++i) {
            int j = i + 1;
            while (j < sequence.size() && sequence.get(j) <= sequence.get(i)) {
                j++;
            }

            int pos = (j - i) - 1;

            if (pos < 20) {
                nu.set(pos, nu.get(pos) + 1);
            }
        }

        for (int i = nu.size() - 1; nu.get(i) == 0; --i) {
            nu.remove(i);
        }

        return eval();
    }

    private double eval() {
        double sum = 0;

        double nusum = nusum();

        for (int i = 0; i < nu.size(); ++i) {
            double pi = pi(i+1);

            sum += (nu.get(i) - nusum * pi) * (nu.get(i) - nusum * pi) / (nusum * pi);
        }

        return sum;
    }

    public int t() {
        return nu.size();
    }

    private int nusum() {
        return nu.stream().mapToInt(Integer::intValue).sum();
    }
}
