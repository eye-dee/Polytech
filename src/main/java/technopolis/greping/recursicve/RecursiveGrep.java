package technopolis.greping.recursicve;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import technopolis.greping.recursicve.impl.FileParser;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Set;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */
public class RecursiveGrep {
    public static void main(final String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Неверное количество аргументов");
        }

        final ApplicationContext applicationContext = new AnnotationConfigApplicationContext(MainRecursiveGrepConfig.class);

        final InputFileParser inputFileParser = applicationContext.getBean("inputFileParser", InputFileParser.class);
        inputFileParser.setPath(args[0]);

        final FileParser fileParser = applicationContext.getBean("fileParser", FileParser.class);
        fileParser.setSearchPhrase(args[2]);

        inputFileParser.parse();

        try (final PrintWriter printWriter = new PrintWriter(args[1]);){
            final Set resultObjects = applicationContext.getBean("resultObjects", Set.class);
            resultObjects.forEach(printWriter::println);

            final Set fileSet = applicationContext.getBean("fileSet",Set.class);
            fileSet.forEach(System.out::println);
        } catch (final FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }
}
