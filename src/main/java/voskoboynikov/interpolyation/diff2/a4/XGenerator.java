package voskoboynikov.interpolyation.diff2.a4;

import java.util.ArrayList;
import java.util.List;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */
public class XGenerator {
    public static List<Double> generate() {
        final List<Double> res = new ArrayList<>(Const.N_X);

        for (int i = 0; i < Const.N_X; ++i) {
            res.add(Const.A + i*Const.STEP_X);
        }

        return res;
    }
}
