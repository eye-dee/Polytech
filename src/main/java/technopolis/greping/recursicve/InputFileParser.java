package technopolis.greping.recursicve;

import org.apache.commons.io.FileUtils;
import technopolis.greping.recursicve.interfaces.FileToLineParser;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

public class InputFileParser {
    private final FileToLineParser fileToLineParser;
    private Path path;

    public InputFileParser(final FileToLineParser fileToLineParser) {
        this.fileToLineParser = fileToLineParser;
    }

    public void setPath(final String path) {
        this.path = Paths.get(path);

        if (!Files.isRegularFile(this.path)) {
            this.path = null;
            throw new IllegalArgumentException("Input file is not regular file");
        }
    }

    public void parse() {
        try {
            final List<String> files = FileUtils.readLines(new File(path.toUri()));

            files
                    .stream()
                    .map(
                            file -> Paths.get(file)
                    )
                    .forEach(file -> {
                        if (Files.isDirectory(file)) {
                            fileToLineParser.directoryDetected(file);
                        } else if (Files.isRegularFile(file)) {
                            fileToLineParser.fileDetected(file);
                        } else {
                            System.out.println(file.toUri() + " is not file or firectory");
                        }
                    });
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
