package technopolis.greping.recursicve;

import technopolis.greping.recursicve.impl.FileParser;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Set;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

public class SimpleGrepFileVisitor extends SimpleFileVisitor<Path> {
    private final FileParser fileParser;
    private final Set<String> fileSet;

    public SimpleGrepFileVisitor(final FileParser fileParser, final Set<String> fileSet) {
        super();
        this.fileParser = fileParser;
        this.fileSet = fileSet;
    }

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        final String fileName = file.toString();
        if (!fileSet.contains(fileName)) {
            fileParser.parse(file);
            fileSet.add(fileName);
        }

        return FileVisitResult.CONTINUE;
    }
}
