package com.jpi287.transrelational.configuration;

import com.jpi287.transrelational.model.DummyTables;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfiguration {
    @Bean("fieldValuesTable")
    public Table fieldValuesTable() {
//        return new Table("fieldValuesTable");
        return DummyTables.FIELD_VALUES_TABLE;
    }

    @Bean("recordReconstructionTable")
    public RecordReconstructionTable recordReconstructionTable() {
//        return new RecordReconstructionTable("recordReconstructionTable");
        return DummyTables.RECORD_RECONSTRUCTION_TABLE;
    }
}
