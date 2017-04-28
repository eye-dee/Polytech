package technopolis.greping.recursicve;

import technopolis.greping.recursicve.impl.FileParser;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

public class SimpleGrepFileVisitor extends SimpleFileVisitor<Path> {
    private final FileParser fileParser;

    public SimpleGrepFileVisitor(final FileParser fileParser) {
        super();
        this.fileParser = fileParser;
    }

    @Override
    public FileVisitResult visitFile(final Path file, final BasicFileAttributes attrs) throws IOException {
        fileParser.parse(file);
        return FileVisitResult.CONTINUE;
    }
}
