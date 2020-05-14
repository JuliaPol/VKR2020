package com.jpi287.transrelational.configuration;

import com.jpi287.transrelational.model.DummyTables;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.List;

@Configuration
public class StorageConfiguration {
    @Bean("fieldValuesTable")
    public List<Table> fieldValuesTables() {
//        return new Table("fieldValuesTable");
        return Arrays.asList(DummyTables.FIELD_VALUES_TABLE);
    }

    @Bean("recordReconstructionTable")
    public List<RecordReconstructionTable> recordReconstructionTables() {
//        return new RecordReconstructionTable("recordReconstructionTable");
        return Arrays.asList(DummyTables.RECORD_RECONSTRUCTION_TABLE);
    }

    public Table getFieldValuesTableByName(String name) {
        return fieldValuesTables().stream().filter(table -> table.getName().equalsIgnoreCase(name))
                .findFirst().orElse(new Table());
    }

    public RecordReconstructionTable getRecordReconstructionTableeByName(String name) {
        return recordReconstructionTables().stream().filter(table -> table.getName().equalsIgnoreCase(name))
                .findFirst().orElse(new RecordReconstructionTable());
    }
}
