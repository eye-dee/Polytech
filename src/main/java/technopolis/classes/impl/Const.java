package technopolis.classes.impl;

import technopolis.classes.interfaces.Function;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class Const implements Function {
    private final double x;

    public Const(final double x) {
        this.x = x;
    }

    @Override
    public double eval(final double x) {
        return this.x;
    }

    @Override
    public double eval() {
        return x;
    }

    @Override
    public Function withMinus() {
        return new Const(-x);
    }
}
