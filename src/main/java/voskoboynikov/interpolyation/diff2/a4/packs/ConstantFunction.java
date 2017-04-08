package voskoboynikov.interpolyation.diff2.a4.packs;

import voskoboynikov.interpolyation.diff2.FunctionPack;

/**
 * Polytech
 * Created by Игорь on 02.03.2017.
 */
public class ConstantFunction implements FunctionPack {
    @Override
    public double getU(final double x, final double y) {
        return 10.0;
    }

    public double getK1(final double x, final double y){
        return 2.0;
    }
    public double getK2(final double x, final double y){
        return 1.0;
    }
    public double q(final double x, final double y){
        return 0;
    }
    public double f(final double x, final double y){
        return 0.0;
    }

    public double psi1(final double y){
        return 1;
    }
    public double nu1(final double y){
        return 10;
    }

    public double psi2(final double y){
        return 1;
    }
    public double nu2(final double y){
        return 10;
    }

    public double psi3(final double x){
        return 0;
    }
    public double nu3(final double x){
        return 10;
    }

    public double psi4(final double x){
        return 0;
    }
    public double nu4(final double x){
        return 10;
    }
}
