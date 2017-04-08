package technopolis.classes.impl;

import technopolis.classes.interfaces.Function;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
abstract public class BaseFunction implements Function {
    protected Function first;
    protected Function second;

    protected double withMinus = 1;

    BaseFunction(final Function first, final Function second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public Function withMinus() {
        withMinus *= -1.0;
        return this;
    }
}
