package technopolis.recursive.descent;

import java.io.IOException;
import java.io.PushbackInputStream;

import static technopolis.recursive.descent.enumeration.Token.*;


/**
 * Polytech
 * Created by igor on 06.04.17.
 */
public class ExpressionEvaluator extends ExpressionEvaluatorBase<Double> {

    public ExpressionEvaluator(final String expression) {
        super(expression);
    }

    protected Double prim(final PushbackInputStream inputStream, final boolean get) throws IOException {
        if (get) {
            getToken(inputStream);
        }

        switch (currentToken) {
            case NUMBER: {
                final double v = numberValue;
                getToken(inputStream);
                return v;
            }
            case NAME: {
                final double v = currentValues.get(name);
                if (getToken(inputStream) == ASSIGN) {
                    currentValues.replace(name,expr(inputStream,true));
                }
                return v;
            }
            case MINUS:
                return -prim(inputStream,true);
            case LP: {
                final double e = expr(inputStream,true);
                if (currentToken != RP) {
                    throw new IllegalArgumentException("')' expected");
                }
                getToken(inputStream);
                return e;
            }
            default:
                throw new IllegalArgumentException("primary expected");
        }
    }

    protected Double term(final PushbackInputStream inputStream, final boolean get) throws IOException {
        double left = prim(inputStream,get);

        for (;;){
            switch (currentToken) {
                case MUL:
                    left *= prim(inputStream,true);
                    break;
                case DIV: {
                    final double d = prim(inputStream, true);
                    left /= d;
                    break;
                }
                default:
                    return left;
            }
        }
    }

    protected Double expr(final PushbackInputStream inputStream, final boolean get) throws IOException {
        double left = term(inputStream,get);

        for (;;) {
            switch (currentToken) {
                case PLUS:
                    left += term(inputStream,true);
                    break;
                case MINUS:
                    left -= term(inputStream,true);
                    break;
                default:
                    return left;
            }
        }
    }
}
