package technopolis.classes.interfaces;

/**
 * Polytech
 * Created by igor on 31.03.17.
 */
public interface Function {
    double eval(double x);
    double eval();

    Function withMinus();
}
