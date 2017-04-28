package technopolis.greping;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Polytech
 * Created by igor on 21.04.17.
 */

@Data
@AllArgsConstructor
public class GrepFileVisitor extends SimpleFileVisitor<Path> {
    private String string;

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        final String allFIle = FileUtils.readFileToString(new File(file.toUri()), "UTF-8");

        if (allFIle.contains(string)) {
            System.out.println("Файл с именем " + file.getFileName() + " содержит то, что вы ищете");
        }

        return FileVisitResult.CONTINUE;
    }
}
