package technopolis.greping.recursicve.impl;

import lombok.AllArgsConstructor;
import technopolis.greping.recursicve.SimpleGrepFileVisitor;
import technopolis.greping.recursicve.interfaces.FileToLineParser;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

@AllArgsConstructor
public class FileToLineParserImpl implements FileToLineParser {
    private final FileParser fileParser;

    @Override
    public void directoryDetected(final Path path) {
        try {
            Files.walkFileTree(path,new SimpleGrepFileVisitor(fileParser));
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void fileDetected(final Path path) {
        try {
            fileParser.parse(path);
        } catch (final IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
