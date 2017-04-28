package technopolis.greping.recursicve;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import technopolis.greping.recursicve.impl.FileParser;
import technopolis.greping.recursicve.impl.FileToLineParserImpl;
import technopolis.greping.recursicve.interfaces.FileToLineParser;

import java.util.HashSet;
import java.util.Set;

/**
 * Polytech
 * Created by igor on 26.04.17.
 */

@Configuration
public class MainRecursiveGrepConfig {
    @Bean
    public Set<ResultObject> resultObjects() {
        return new HashSet<>();
    }

    @Bean
    public FileToLineParser fileToLineParser() {
        return new FileToLineParserImpl(fileParser());
    }

    @Bean
    public InputFileParser inputFileParser() {
        return new InputFileParser(fileToLineParser());
    }

    @Bean
    public FileParser fileParser() {
        return new FileParser(resultObjects());
    }
}
