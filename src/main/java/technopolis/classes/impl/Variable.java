package technopolis.classes.impl;

import technopolis.classes.interfaces.Function;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class Variable implements Function {
    @Override
    public double eval(final double x) {
        return x;
    }

    @Override
    public double eval() {
        return 0;
    }

    @Override
    public Function withMinus() {
        return new Variable();
    }
}
