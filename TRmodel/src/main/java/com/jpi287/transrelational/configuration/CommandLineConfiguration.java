package com.jpi287.transrelational.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Scanner;

@Configuration
public class CommandLineConfiguration {
    @Bean
    public Scanner scanner() {
        return new Scanner(System.in);
    }
}
