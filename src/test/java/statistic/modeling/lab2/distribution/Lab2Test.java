package statistic.modeling.lab2.distribution;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

/**
 * Created by Игорь on 22.02.2017.
 */
public class Lab2Test {
    @Test
    public void factorialTest(){
        assertThat(BinomialDistributionGenerator.fact(0)).isEqualTo(1);
        assertThat(BinomialDistributionGenerator.fact(1)).isEqualTo(1);
        assertThat(BinomialDistributionGenerator.fact(2)).isEqualTo(2);
        assertThat(BinomialDistributionGenerator.fact(3)).isEqualTo(6);
        assertThat(BinomialDistributionGenerator.fact(4)).isEqualTo(24);
        assertThat(BinomialDistributionGenerator.fact(5)).isEqualTo(120);
        assertThat(BinomialDistributionGenerator.fact(6)).isEqualTo(720);
        assertThat(BinomialDistributionGenerator.fact(7)).isEqualTo(5040);
        assertThat(BinomialDistributionGenerator.fact(8)).isEqualTo(40320);
        assertThat(BinomialDistributionGenerator.fact(9)).isEqualTo(362880);
        assertThat(BinomialDistributionGenerator.fact(10)).isEqualTo(3628800);
    }
}
