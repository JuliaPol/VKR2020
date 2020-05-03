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

    public Object[] getRowByNumber(int number) {
        Integer[] result = new Integer[columns.get(0).getCells().length - 1];
        for (int i = 0; i < columns.size(); i++) {
            result[i] = columns.get(i).getCells()[number];
        }
        return result;
    }

    public Object[] getColumnNames() {
        return columns.stream().map(RecordReconstructionColumn::getName).toArray(String[]::new);
    }
}
