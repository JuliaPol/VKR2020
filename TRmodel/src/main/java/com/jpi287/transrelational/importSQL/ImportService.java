package com.jpi287.transrelational.importSQL;

import org.springframework.stereotype.Component;

import javax.imageio.IIOException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
public class ImportService {
    public void createModelFromImport(String fileName) throws IIOException {
    }

    private Stream<String> getLinesFromFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.lines(path);
    }
}
