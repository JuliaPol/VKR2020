package com.jpi287.transrelational;

import com.jpi287.transrelational.services.CommandLineParser;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final CommandLineParser commandLineParser;
    private final Scanner scanner;

    @Override
    public void run(String... args) {

        System.out.println("What your name?");
        String name = null;
        if (scanner.hasNext()) {
            name = scanner.nextLine();
            commandLineParser.parseArguments(name);
            commandLineParser.printRecordReconstructionTable();
            System.out.println("                     ");
            commandLineParser.printFieldValuesTable();
        }
        System.out.println("Hello " + name);
    }
}
