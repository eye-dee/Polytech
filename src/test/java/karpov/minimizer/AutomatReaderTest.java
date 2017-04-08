package karpov.minimizer;

import karpov.minimizer.service.ConfigService;
import org.junit.Test;

/**
 * Created by Игорь on 21.02.2017.
 */
public class AutomatReaderTest {
    @Test
    public void readingTest(){
        final AutomatReader automatReader = new AutomatReader();

        ConfigService.load();
        automatReader.readAutomat();
        automatReader.showAutomat();
    }
}
