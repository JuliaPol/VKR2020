package com.jpi287.transrelational.configuration;

import com.jpi287.transrelational.table.Table;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {
    @Bean("fieldValuesTable")
    public Table fieldValuesTable() {
        return new Table("fieldValuesTable");
    }
}