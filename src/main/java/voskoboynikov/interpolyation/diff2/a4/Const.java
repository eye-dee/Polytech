package voskoboynikov.interpolyation.diff2.a4;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */
public interface Const {
    int N_X = 10;
    int N_Y =  20;
    double A = 0.9;
    double B = 2.1;
    double C = 3.0;
    double D = 4.0;

    double STEP_X = (B-A) / N_X;
    double STEP_Y = (D-C) / N_Y;

    double INDEPENDENT_VAR = Double.NaN;
}
