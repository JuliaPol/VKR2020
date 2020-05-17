package com.jpi287.transrelational.configuration;

import com.jpi287.transrelational.model.DummyTables;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class StorageConfiguration {

    public StorageConfiguration() {
        fieldValuesTables = new ArrayList<>();
        fieldValuesTables.add(DummyTables.FIELD_VALUES_TABLE);
        recordReconstructionTables = new ArrayList<>();
        recordReconstructionTables.add(DummyTables.RECORD_RECONSTRUCTION_TABLE);
    }

    private List<Table> fieldValuesTables;
    private List<RecordReconstructionTable> recordReconstructionTables;

    public Table getFieldValuesTableByName(String name) {
        return fieldValuesTables.stream().filter(table -> table.getName().equalsIgnoreCase(name))
                .findFirst().orElse(new Table());
    }

    public RecordReconstructionTable getRecordReconstructionTableeByName(String name) {
        return recordReconstructionTables.stream().filter(table -> table.getName().equalsIgnoreCase(name))
                .findFirst().orElse(new RecordReconstructionTable());
    }

    public void addValueToFVT(Table table) {
        fieldValuesTables.add(table);
    }

    public void addValueToRRT(RecordReconstructionTable table) {
        recordReconstructionTables.add(table);
    }
}
