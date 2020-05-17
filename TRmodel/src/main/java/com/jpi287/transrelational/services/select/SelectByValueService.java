package com.jpi287.transrelational.services.select;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.common.ReconstructionService;
import com.jpi287.transrelational.sorting.CellComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
@AllArgsConstructor
public class SelectByValueService {

    private final StorageConfiguration storageConfiguration;
    private final ReconstructionService reconstructionService;

    public List<Row> selectByEqualOperator(String tableName, String columnName, String value) {
        long start = System.nanoTime();
        Table fieldValuesTable = storageConfiguration.getFieldValuesTableByName(tableName);
        RecordReconstructionTable recordReconstructionTable = storageConfiguration.getRecordReconstructionTableeByName(tableName);
        Row row = getRowByValueAndColumnName(fieldValuesTable, recordReconstructionTable, columnName, value);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("search equal: " + timeElapsed);
        return Arrays.asList(row);
    }


    public List<Row> selectByGreaterThanOperator(String tableName, String columnName, String value, boolean orEqual) {
        Table fieldValuesTable = storageConfiguration.getFieldValuesTableByName(tableName);
        RecordReconstructionTable recordReconstructionTable = storageConfiguration.getRecordReconstructionTableeByName(tableName);
        List<Row> result = new ArrayList<>();
        Column foundColumn = fieldValuesTable.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName)).findFirst().orElse(new Column());
        String currentValue = foundColumn.getCells().get(0).getValue();
        int currentCell = 0;
        while (!isResult(currentValue, value) && currentCell < foundColumn.getCells().size() - 2) {
            int[] rowRR = reconstructionService.getValuesFromRRTable(recordReconstructionTable, foundColumn.getColumnId(), currentCell);
            List<String> valuesInRow = reconstructionService.getValuesFromFVTable(fieldValuesTable, rowRR);
            result.add(new Row(valuesInRow));
            currentCell++;
            currentValue = foundColumn.getCells().get(currentCell).getValue();
        }
        return result;
    }


    public List<Row> selectByLessThanOperator(String tableName, String columnName, String value, boolean orEqual) {
        long start = System.nanoTime();
        Table fieldValuesTable = storageConfiguration.getFieldValuesTableByName(tableName);
        RecordReconstructionTable recordReconstructionTable = storageConfiguration.getRecordReconstructionTableeByName(tableName);
        List<Row> result = new ArrayList<>();
        Column foundColumn = fieldValuesTable.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName)).findFirst().orElse(new Column());
        String currentValue = foundColumn.getCells().get(foundColumn.getCells().size() - 1).getValue();
        int currentCell = foundColumn.getCells().size() - 1;
        while (!isResult(currentValue, value) && currentCell >= 0) {
            int[] rowRR = reconstructionService.getValuesFromRRTable(recordReconstructionTable, foundColumn.getColumnId(), currentCell);
            List<String> valuesInRow = reconstructionService.getValuesFromFVTable(fieldValuesTable, rowRR);
            result.add(new Row(valuesInRow));
            currentCell--;
            currentValue = foundColumn.getCells().get(currentCell).getValue();
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("searchLess equal: " + timeElapsed / 1000000);
        return result;
    }

    public Row getRowByValueAndColumnName(Table fieldValuesTable, RecordReconstructionTable recordReconstructionTable,
                                          String columnName, String value) {
        Column foundColumn = fieldValuesTable.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName)).findFirst().orElse(new Column());
        int columnId = foundColumn.getColumnId();
        Cell cell = new Cell(value, 0);
        int cellIndex = Collections.binarySearch(foundColumn.getCells(), cell, new CellComparator());
        int[] rowRR = reconstructionService.getValuesFromRRTable(recordReconstructionTable, columnId, cellIndex);
        List<String> valuesInRow = reconstructionService.getValuesFromFVTable(fieldValuesTable, rowRR);
        return new Row(valuesInRow);
    }

    private boolean isResult(String currentValue, String conditional) {
        return currentValue.equals(conditional);
    }

}
