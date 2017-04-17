package optimization.cooridate.descent;

/**
 * Polytech
 * Created by igor on 12.04.17.
 */

public enum CoefficientPack {
    IGOR(245.45,-207.9,47.45,-8.2857,-8.2857,-4,-3),
    SEMEN(125.45,-148.5,46.25,-6.8,-6.8,-6,-7),
    KATE(80.45,-118.8,45.8,-6.25,-6.25,-7,-7),
    FEDIA(605.45,-326.7,51.05,-11.8,-11.8,-5,-7);

    private final double a1;
    private final double a2;
    private final double a3;
    private final double b1;
    private final double b2;

    private final double x0;
    private final double y0;

    CoefficientPack(final double a1, final double a2, final double a3, final double b1, final double b2, final double x0, final double y0) {
        this.a1 = a1;
        this.a2 = a2;
        this.a3 = a3;
        this.b1 = b1;
        this.b2 = b2;
        this.x0 = x0;
        this.y0 = y0;
    }

    public double getA1() {
        return a1;
    }

    public double getA2() {
        return a2;
    }

    public double getA3() {
        return a3;
    }

    public double getB1() {
        return b1;
    }

    public double getB2() {
        return b2;
    }

    public double getX0() {
        return x0;
    }

    public double getY0() {
        return y0;
    }
}
