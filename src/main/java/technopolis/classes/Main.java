package technopolis.classes;

import technopolis.classes.impl.*;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public class Main {
    public static void main(final String[] args) {

        final double x = 5;

        System.out.println(
                new Divide(new Add(new Add( new Multiply(new Variable(),new Variable()),new Multiply(new Variable(),new Const(-5))),
                        new Const(40)),
                        new Const(100)).eval());

        //(x^2 -5*x + 40)/100
    }
}
