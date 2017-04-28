package technopolis.greping.recursicve.impl;

import technopolis.greping.recursicve.ResultObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
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
//        final List<String> lines = FileUtils.readLines(new File(path.toUri()));

        final File file = new File(path.toUri());
        try (final BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {

            long stringNumber = 0;

            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.contains(searchPhrase)) {
                    resultObjects.add(new ResultObject(stringNumber, fileName));
                }
                ++stringNumber;
            }
        }
    }
}
