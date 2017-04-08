package technopolis.recursive.descent;

import technopolis.classes.impl.Add;
import technopolis.classes.impl.Const;
import technopolis.classes.impl.Divide;
import technopolis.classes.impl.Multiply;
import technopolis.classes.interfaces.Function;

import java.io.IOException;
import java.io.PushbackInputStream;

import static technopolis.recursive.descent.enumeration.Token.RP;


/**
 * Polytech
 * Created by igor on 06.04.17.
 */
public class ExpressionEvaluatorImprove extends ExpressionEvaluatorBase<Function> {
    public ExpressionEvaluatorImprove(final String expression) {
        super(expression);
    }

    protected Function prim(final PushbackInputStream inputStream, final boolean get) throws IOException {
        if (get) {
            getToken(inputStream);
        }

        switch (currentToken) {
            case NUMBER: {
                final double v = numberValue;
                getToken(inputStream);
                return new Const(v);
            }
            case NAME: {
                final double v = currentValues.get(name);
                getToken(inputStream);
                /*if (getToken(inputStream) == ASSIGN) {
                    currentValues.replace(name,expr(inputStream,true));
                }*/
                return new Const(v);
            }
            case MINUS:
                return prim(inputStream,true).withMinus();
            case LP: {
                final Function expr = expr(inputStream,true);
                if (currentToken != RP) {
                    throw new IllegalArgumentException("Что-то не то со скобками, ')' expected");
                }
                getToken(inputStream);
                return expr;
            }
            default:
                throw new IllegalArgumentException("primary excpected");
        }
    }

    protected Function term(final PushbackInputStream inputStream, final boolean get) throws IOException {
        Function left = prim(inputStream,get);

        for (;;){
            switch (currentToken) {
                case MUL:
                    left = new Multiply(left,prim(inputStream,true));
                    break;
                case DIV: {
                    left = new Divide(left,prim(inputStream, true));
                    break;
                }
                default:
                    return left;
            }
        }
    }

    protected Function expr(final PushbackInputStream inputStream, final boolean get) throws IOException {
        Function left = term(inputStream,get);

        for (;;) {
            switch (currentToken) {
                case PLUS:
                    left = new Add(left, term(inputStream,true));
                    break;
                case MINUS:
                    left = new Add(left, term(inputStream,true).withMinus());
                    break;
                default:
                    return left;
            }
        }
    }
}
