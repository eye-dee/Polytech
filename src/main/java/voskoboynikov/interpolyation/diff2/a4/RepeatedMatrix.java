package voskoboynikov.interpolyation.diff2.a4;

import voskoboynikov.interpolyation.diff2.FunctionPack;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static voskoboynikov.interpolyation.diff2.a4.Const.*;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */
public class RepeatedMatrix {
    private final List<Double> mainDiagonal = new ArrayList<>();
    private final List<Double> upDiagonal = new ArrayList<>();
    private final List<Double> downDiagonal = new ArrayList<>();

    private FunctionPack functionPack;

    private final List<Double> x;

    public RepeatedMatrix() {
        x = XGenerator.generate();
    }

    public void setFunctionPack(final FunctionPack functionPack) {
        this.functionPack = functionPack;
    }

    void fill() {
        for (int i = 0; i < N_X; ++i) {
            if (i == 0) {
                //todo evaluate
                mainDiagonal.add(0.0);
                downDiagonal.add(0.0);
                upDiagonal.add(0.0);

            } else if (i == N_X - 1) {
                //todo evaluate
                mainDiagonal.add(0.0);
                downDiagonal.add(0.0);
                upDiagonal.add(0.0);
            } else {
                final double ui = STEP_Y/STEP_X *
                        (functionPack.getK1(getXP(i), INDEPENDENT_VAR) - functionPack.getK1(getXM(i), INDEPENDENT_VAR));
                final double uim = -STEP_Y/STEP_X*functionPack.getK1(getXM(i),INDEPENDENT_VAR);
                final double uip = -STEP_Y/STEP_X*functionPack.getK1(getXP(i),INDEPENDENT_VAR);

                mainDiagonal.add(ui);
                downDiagonal.add(uim);
                upDiagonal.add(uip);
            }
        }
    }

    public void showMatrix() {
        System.out.println(Arrays.toString(mainDiagonal.toArray()));
        System.out.println(Arrays.toString(downDiagonal.toArray()));
        System.out.println(Arrays.toString(upDiagonal.toArray()));
    }

    private double getXM(final int i) {
        return x.get(i) - STEP_X/2.0;
    }
    private double getXP(final int i) {
        return x.get(i) + STEP_X/2.0;
    }
    private double getX(final int i) {
        return x.get(i);
    }
}
