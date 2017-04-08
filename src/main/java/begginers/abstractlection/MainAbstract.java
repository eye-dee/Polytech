package begginers.abstractlection;

/**
 * Polytech
 * Created by igor on 13.03.17.
 */

//функциональные интрефейсы
//@FunctionalInterface - аннотация, которая делает интерфейс функциональным
//у функционального интерфейса всего один метод
public class MainAbstract {
    public static void main(final String[] args) {
        final AbstractClassExample b = new ChildOfAbstractClassExample();

        b.print();
    }
}
