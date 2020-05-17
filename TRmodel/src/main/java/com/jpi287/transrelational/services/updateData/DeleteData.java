package com.jpi287.transrelational.services.updateData;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionCell;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.common.ReconstructionService;
import com.jpi287.transrelational.sorting.CellComparator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;

@Component
@AllArgsConstructor
public class DeleteData {

    private final StorageConfiguration storageConfiguration;
    private final ReconstructionService reconstructionService;

    public boolean deleteRowByColumnNameAndValue(String tableName, String columnName, String value) {
        long start = System.nanoTime();
        Table fieldValuesTable = storageConfiguration.getFieldValuesTableByName(tableName);
        RecordReconstructionTable recordReconstructionTable = storageConfiguration.getRecordReconstructionTableeByName(tableName);
        Column foundColumn = fieldValuesTable.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(columnName)).findFirst().orElse(new Column());
        int columnId = foundColumn.getColumnId();
        Cell cell = new Cell(value, 0);
        int cellIndex = Collections.binarySearch(foundColumn.getCells(), cell, new CellComparator());
        RecordReconstructionCell[] recordReconstructionCells = reconstructionService.getValuesFromRRTable(recordReconstructionTable, columnId, cellIndex, true);
        reconstructionService.getValuesFromFVTable(storageConfiguration.getFieldValuesTableByName(tableName), recordReconstructionCells);
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("delete: " + timeElapsed);
        return true;
    }
}
