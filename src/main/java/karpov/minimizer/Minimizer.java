package karpov.minimizer;

import karpov.minimizer.service.ConfigService;

/**
 * Polytech
 * Created by Игорь on 21.02.2017.
 */
public class Minimizer {
    public static void main(final String[] args){
        final AutomatReader automatReader = new AutomatReader();

        ConfigService.load();
        automatReader.readAutomat();
        automatReader.readGroup();

        automatReader.showAutomat();
        automatReader.minimize();
        automatReader.showAutomat();
    }
}
