package technopolis.recursive.descent;

import technopolis.recursive.descent.enumeration.Token;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;
import java.nio.charset.StandardCharsets;
import java.util.Map;

import static technopolis.recursive.descent.enumeration.Number.isNumber;
import static technopolis.recursive.descent.enumeration.Token.*;


/**
 * Polytech
 * Created by igor on 06.04.17.
 */
abstract public class ExpressionEvaluatorBase<T> {
    protected static final int END_STREAM_SYMBOL = 255;
    protected final String expression;
    protected Map<String , Double> currentValues;
    protected Token currentToken = PRINT;
    protected double numberValue;
    protected String name;

    public ExpressionEvaluatorBase(final String expression) {
        this.expression = expression;
    }

    public T evaluate(final Map<String, Double> values) throws IOException {
        currentValues = values;
        currentToken = PRINT;

        final PushbackInputStream pushbackInputStream = new PushbackInputStream(
                new ByteArrayInputStream(
                        expression.getBytes(StandardCharsets.UTF_8)));

        getToken(pushbackInputStream);

        if (currentToken == END) {
            error("no expression");
        }

        if (currentToken == PRINT) {
            error("empty expression");
        }

        return expr(pushbackInputStream,false);
    }

    private double error(final String errorMessage) {
        System.out.println("Error :" + errorMessage);
        return 1;
    }


    protected Token getToken(final PushbackInputStream inputStream) throws IOException {
        int ch;

        do {
            if (inputStream.available() == 0) {
                return (currentToken = END);
            }
            ch = inputStream.read();
        } while (ch != '\n' && isSpace(ch));

        if (ch == END_STREAM_SYMBOL) {
            return (currentToken = END);
        }
        if (ch == ';' || ch == '\n') {
            return (currentToken = PRINT);
        }

        if (isToken(ch)) {
            return (currentToken = getTokenFromShort(ch));
        }

        if (isNumber(ch)) {
            inputStream.unread(ch);

            numberValue = readNumberFromStream(inputStream);
            return (currentToken = NUMBER);
        }

        if (Character.isLetter(ch)) {
            final StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append((char)ch);
            while (inputStream.available() > 0 && Character.isLetterOrDigit( (ch = inputStream.read()))) {
                stringBuilder.append((char)ch);
            }
            if (!Character.isLetter(ch))
                inputStream.unread(ch);
            name = stringBuilder.toString();
            return (currentToken = NAME);
        }

        error("Bad Token");
        return (currentToken = PRINT);
    }

    abstract T prim(final PushbackInputStream inputStream, final boolean get) throws IOException;

    abstract T term(final PushbackInputStream inputStream, final boolean get) throws IOException;

    abstract T expr(final PushbackInputStream inputStream, final boolean get) throws IOException;


    protected double readNumberFromStream(final PushbackInputStream pushbackInputStream) throws IOException {
        int ch = pushbackInputStream.read();
        final StringBuilder stringBuilder = new StringBuilder();
        while (Character.isDigit(ch)) {
            stringBuilder.append((char)ch);
            ch = pushbackInputStream.read();
        }

        if (!Character.isDigit(ch))
            pushbackInputStream.unread(ch);

        return Double.parseDouble(stringBuilder.toString());
    }

    private boolean isSpace(final int ch) {
        return Character.isWhitespace(ch);
    }

}
