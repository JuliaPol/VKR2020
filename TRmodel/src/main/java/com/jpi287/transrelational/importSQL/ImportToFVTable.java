package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.sorting.CellComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImportToFVTable {

    private final List<Table> fieldValuesTables;

    public Table convertToFieldValuesTable(Table relationalTable) {
        List<Column> columnsFVTable = relationalTable.getColumns().stream().map(this::mapColumn).collect(Collectors.toList());
        Table fieldValuesTable = new Table(relationalTable.getName());
        fieldValuesTable.setColumns(columnsFVTable);
        fieldValuesTables.add(fieldValuesTable);
        return fieldValuesTable;
    }

    private Column mapColumn(Column column) {
        Column columnFVT = new Column();
        columnFVT.setColumnId(column.getColumnId());
        columnFVT.setName(column.getName());
        List<Cell> cells = column.getCells();
        List<Cell> sortedFields = cells.stream().sorted(new CellComparator()).collect(Collectors.toList());
        columnFVT.setCells(sortedFields);
        return columnFVT;
    }
}
