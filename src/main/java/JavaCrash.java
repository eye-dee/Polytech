import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Polytech
 * Created by igor on 17.04.17.
 */
public class JavaCrash {
    public static void main(final String[] args) {
        final List[] arr = new List[100];

        Arrays.setAll(arr,ArrayList::new);
    }
}
