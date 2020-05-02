package com.jpi287.transrelational.services;

import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.sorting.CellComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class SearchService {

    private final Table fieldValuesTable;
    private final RecordReconstructionTable recordReconstructionTable;

    public Row getRowByValueAndColumnName(String columnName, String value) {
        Column foundColumn = fieldValuesTable.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName)).findFirst().orElse(new Column());
        int columnId = foundColumn.getColumnId();
        Cell cell = new Cell(value, 0);
        int cellIndex = Collections.binarySearch(foundColumn.getCells(), cell, new CellComparator());
        int[] rowRR = getValuesFromRRTable(columnId, cellIndex);
        List<String> valuesInRow = IntStream.range(0, rowRR.length).boxed().map(i -> {
            int lineValue = rowRR[i];
            i = i == rowRR.length - 1 ? 0 : i + 1;
            return fieldValuesTable.getColumns().get(i).getCells().get(lineValue - 1).getValue();
        }).collect(Collectors.toList());
        return new Row(valuesInRow);
    }

    private int[] getValuesFromRRTable(int columnIndex, int cellIndex) {
        int size = recordReconstructionTable.getColumns().size();
        int[] row = new int[size];
        int currentCell = recordReconstructionTable.getColumns().get(columnIndex).getCells()[cellIndex];
        row[columnIndex] = currentCell;
        int i = columnIndex + 1;
        while (i != columnIndex) {
            currentCell = recordReconstructionTable.getColumns().get(i).getCells()[currentCell - 1];
            row[i] = currentCell;
            i = i == size - 1 ? 0 : i + 1;
        }
        return row;
    }
}
