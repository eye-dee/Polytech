package voskoboynikov.interpolyation.diff2;

public interface FunctionPack {
    double getU(double x,double y);
    double getK1(double x,double y);
    double getK2(double x,double y);
    double q(double x,double y);
    double f(double x,double y);

    double psi1(double y);
    double nu1(double y);

    double psi2(double y);
    double nu2(double y);

    double psi3(double x);
    double nu3(double x);

    double psi4(double x);
    double nu4(double x);
}
