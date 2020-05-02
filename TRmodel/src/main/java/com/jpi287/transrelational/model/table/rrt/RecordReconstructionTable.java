package com.jpi287.transrelational.model.table.rrt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReconstructionTable {
    private String name;
    private List<RecordReconstructionColumn> columns;

    public RecordReconstructionTable(String name) {
        this.name = name;
    }
}
