package technopolis.recursive.descent.enumeration;

/**
 * Polytech
 * Created by igor on 02.04.17.
 */
public enum Number {
    NUM0('0'),
    NUM1('1'),
    NUM2('2'),
    NUM3('3'),
    NUM4('4'),
    NUM5('5'),
    NUM6('6'),
    NUM7('7'),
    NUM8('8'),
    NUM9('9'),
    NUMP('.');

    private final char number;

    Number(final char number) {
        this.number = number;
    }

    public char getNumber() {
        return number;
    }

    static public boolean isNumber(final int symbol) {
        return ((symbol >= '0' && symbol <= '9') || (symbol == '.'));
    }
}
