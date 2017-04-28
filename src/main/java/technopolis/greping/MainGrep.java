package technopolis.greping;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Polytech
 * Created by igor on 21.04.17.
 */
public class MainGrep {
    public static void main(final String[] args) {

        if (args.length != 2) {
            System.out.println("Вы ввели неправильное колиество аргументов");
            return;
        }

        try {
            final Path path = Paths.get(args[1]);

            if (Files.exists(path)) {
                Files.walkFileTree(path,new GrepFileVisitor(args[0]));
            } else {
                System.out.println("Нет такой директории");
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
