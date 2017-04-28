package technopolis.greping.recursicve.impl;

import lombok.AllArgsConstructor;
import technopolis.greping.recursicve.SimpleGrepFileVisitor;
import technopolis.greping.recursicve.interfaces.FileToLineParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

@AllArgsConstructor
public class FileToLineParserImpl implements FileToLineParser {
    private final FileParser fileParser;
    private final Set<String> fileSet;

    @Override
    public void directoryDetected(final Path path) {
        try {
            Files.walkFileTree(path,new SimpleGrepFileVisitor(fileParser,fileSet));
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fileDetected(final Path path) {
        try {
            final String fileName = path.toString();
            if (!fileSet.contains(fileName)) {
                fileParser.parse(path);
                fileSet.add(fileName);
            }
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
