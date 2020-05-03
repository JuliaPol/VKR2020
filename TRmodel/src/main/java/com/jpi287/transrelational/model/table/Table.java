package com.jpi287.transrelational.model.table;

import com.jpi287.transrelational.model.table.rrt.RecordReconstructionColumn;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private String name;
    private List<Column> columns;

    public Table(String name) {
        this.name = name;
    }

    public Object[] getRowByNumber(int number) {
        String[] result = new String[columns.get(0).getCells().size() - 1];
        for (int i = 0; i < columns.size(); i++) {
            result[i] = columns.get(i).getCells().get(number).getValue();
        }
        return result;
    }

    public Object[] getColumnNames() {
        return columns.stream().map(Column::getName).toArray(String[]::new);
    }
}
