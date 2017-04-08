package technopolis.recursive.descent.enumeration;

/**
 * Polytech
 * Created by igor on 02.04.17.
 */
public enum Token {
    NAME,
    NUMBER,
    END,
    PLUS('+'),
    MINUS('-'),
    MUL('*'),
    DIV('/'),
    PRINT(';'),
    ASSIGN('='),
    LP('('),
    RP(')');

    private final int symbol;
    Token() {
        this.symbol = 'u';
    }
    Token(final int symbol) {
        this.symbol = symbol;
    }

    public int getSymbol() {
        return symbol;
    }

    public static Token getTokenFromShort(final int symbol) {
        if (symbol == '+')
            return PLUS;
        if (symbol == '-')
            return MINUS;
        if (symbol == '*')
            return MUL;
        if (symbol == '/')
            return DIV;
        if (symbol == ';')
            return PRINT;
        if (symbol == '=')
            return ASSIGN;
        if (symbol == '(')
            return LP;
        if (symbol == ')')
            return RP;
        return NAME;
    }

    public static boolean isToken(final int symbol) {
        return symbol == '+' ||
                symbol == '-' ||
                symbol == '*' ||
                symbol == '/' ||
                symbol == ';' ||
                symbol == '=' ||
                symbol == '(' ||
                symbol == ')';
    }
}
