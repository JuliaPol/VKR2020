package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.table.Cell;
import com.jpi287.transrelational.table.Column;
import com.jpi287.transrelational.table.Table;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Component
@AllArgsConstructor
public class ImportToFVTable {

    private final Table fieldValuesTable;

    public Table convertToFieldValuesTable(Table relationalTable) {
        List<Column> columnsFVTable = relationalTable.getColumns().stream().map(this::mapColumn).collect(Collectors.toList());
        fieldValuesTable.setColumns(columnsFVTable);
        return fieldValuesTable;
    }

    private Column mapColumn(Column column) {
        Column columnFVT = new Column();
        columnFVT.setColumnId(column.getColumnId());
        columnFVT.setName(column.getName());
        List<Cell> cells = column.getCells();
        List<Cell> sortedFields = cells.stream().sorted().collect(Collectors.toList());
        columnFVT.setCells(sortedFields);
        return columnFVT;
    }
}
