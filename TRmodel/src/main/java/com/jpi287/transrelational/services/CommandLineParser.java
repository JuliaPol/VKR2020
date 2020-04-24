package com.jpi287.transrelational.services;

import com.jpi287.transrelational.importSQL.ImportService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineParser {

    private final ImportService importService;

    private static final String IMPORT = "IMPORT";

    public void parseArguments(String line) {
        System.out.println("I'm here" + line);
        if (StringUtils.containsIgnoreCase(line, IMPORT)) {
            //TODO: exceptions
            String fileName = line.replaceFirst(IMPORT, "").trim();
//            importService.createModelFromImport(fileName);
        }

    }

}
