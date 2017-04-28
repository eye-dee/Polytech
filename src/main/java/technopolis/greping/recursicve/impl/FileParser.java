package technopolis.greping.recursicve.impl;

import org.apache.commons.io.FileUtils;
import technopolis.greping.recursicve.ResultObject;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

public class FileParser {
    private Set<ResultObject> resultObjects;
    private String searchPhrase;

    public void setSearchPhrase(final String searchPhrase) {
        this.searchPhrase = searchPhrase;
    }

    public FileParser(final Set<ResultObject> resultObjects) {
        this.resultObjects = resultObjects;
    }

    public void parse(final Path path) throws IOException {
        final String fileName = path.toString();
        final List<String> lines = FileUtils.readLines(new File(path.toUri()));

        final Iterator<String> iterator = lines.iterator();
        long stringNumber = 0;
        while (iterator.hasNext()) {
            final String line = iterator.next();

            if (line.contains(searchPhrase)) {
                resultObjects.add(new ResultObject(stringNumber,fileName));
            }
            ++stringNumber;
        }
    }
}
