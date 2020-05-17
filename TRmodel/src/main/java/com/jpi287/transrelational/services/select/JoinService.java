package com.jpi287.transrelational.services.select;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionCell;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.common.ReconstructionService;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component
@AllArgsConstructor
public class JoinService {

    private final StorageConfiguration storageConfiguration;
    private final ReconstructionService reconstructionService;

    public Table innerJoinByColumnName(String tableName1, String tableName2, String matchedColumnName) {
        long start = System.nanoTime();
        Table fieldValuesTable1 = storageConfiguration.getFieldValuesTableByName(tableName1);
        RecordReconstructionTable recordReconstructionTable1 = storageConfiguration.getRecordReconstructionTableeByName(tableName1);

        Table fieldValuesTable2 = storageConfiguration.getFieldValuesTableByName(tableName2);
        RecordReconstructionTable recordReconstructionTable2 = storageConfiguration.getRecordReconstructionTableeByName(tableName2);

        Column foundColumn = fieldValuesTable1.getColumns().stream()
                .filter(column -> column.getName().equalsIgnoreCase(matchedColumnName)).findFirst().orElse(new Column());
        int columnId = foundColumn.getColumnId();

        List<Pair<Integer, Integer>> foundPairs = mergeJoin(fieldValuesTable1, fieldValuesTable2, columnId);
        List<Row> result = new ArrayList<>();
        for (Pair<Integer, Integer> pair : foundPairs) {
            RecordReconstructionCell[] rowRR1 = reconstructionService.getValuesFromRRTable(recordReconstructionTable1, columnId, pair.getKey(), false);
            RecordReconstructionCell[] rowRR2 = reconstructionService.getValuesFromRRTable(recordReconstructionTable2, columnId, pair.getValue(), false);
            List<String> rows1 = reconstructionService.getValuesFromFVTable(fieldValuesTable1, rowRR1);
            List<String> rows2 = reconstructionService.getValuesFromFVTable(fieldValuesTable2, rowRR2);
            rows1.addAll(rows2);
            Row row = new Row(rows1);
            result.add(row);
        }
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        System.out.println("join: " + timeElapsed / 1000000);
        Table resultedTable = new Table("newTable");
        return resultedTable;
    }

    public List<Pair<Integer, Integer>> mergeJoin(Table fieldValuesTable1, Table fieldValuesTable2, int columnId) {
        Iterator<Cell> leftIter = fieldValuesTable1.getColumns().get(columnId).getCells().listIterator();
        Iterator<Cell> rightIter = fieldValuesTable2.getColumns().get(columnId).getCells().listIterator();
        Cell leftPair = leftIter.next();
        Cell rightPair = rightIter.next();

        int rightCurrentCellIndex = 0;
        int leftCurrentCellIndex = 0;
        List<Pair<Integer, Integer>> result = new ArrayList<>();

        while (true)  {
            int compare = leftPair.getValue().compareTo(rightPair.getValue());
            if (compare < 0) {
                if (leftIter.hasNext()) {
                    leftPair = leftIter.next();
                    leftCurrentCellIndex++;
                } else {
                    break;
                }
            } else if (compare > 0) {
                if (rightIter.hasNext()) {
                    rightPair = rightIter.next();
                    rightCurrentCellIndex++;
                } else {
                    break;
                }
            } else {
                result.add(new MutablePair<>(rightCurrentCellIndex, leftCurrentCellIndex));
                if (leftIter.hasNext() && rightIter.hasNext()) {
                    leftPair = leftIter.next();
                    rightPair = rightIter.next();
                    rightCurrentCellIndex++;
                    leftCurrentCellIndex++;
                } else {
                    break;
                }
            }
        }
        return result;
    }
}
