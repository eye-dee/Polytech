package technopolis.greping.recursicve.interfaces;

import java.nio.file.Path;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */
public interface FileToLineParser {
    void directoryDetected(Path path);
    void fileDetected(Path path);
}
