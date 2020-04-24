package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.table.Column;
import com.jpi287.transrelational.table.Table;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class ImportToFVTable {

    private final Table fieldValuesTable;

    public Table convertToFieldValuesTable(Table relationalTable) {
        Column[] columnsFVTable = Stream.of(relationalTable.getColumns()).map(this::mapColumn).toArray(Column[]::new);
        fieldValuesTable.setColumns(columnsFVTable);
        return fieldValuesTable;
    }

    private Column mapColumn(Column column) {
        Column columnFVT = new Column();
        columnFVT.setColumnId(column.getColumnId());
        columnFVT.setName(column.getName());
        String[] fields = column.getFields();
        String[] sortedFields = Stream.of(fields).sorted().toArray(String[]::new);
        columnFVT.setFields(sortedFields);
        return columnFVT;
    }
}
