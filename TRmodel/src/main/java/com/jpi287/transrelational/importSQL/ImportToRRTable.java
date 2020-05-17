package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionCell;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionColumn;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImportToRRTable {

    private final StorageConfiguration storageConfiguration;

    public RecordReconstructionTable convertToRecordReconstructionTable(Table sortedRelationalTable) {
        long start = System.nanoTime();
        List<RecordReconstructionColumn> inversePermutationColumns = sortedRelationalTable.getColumns().stream()
                .map(this::mapColumn).collect(Collectors.toList());
        RecordReconstructionTable recordReconstructionTable = new RecordReconstructionTable(sortedRelationalTable.getName());
        recordReconstructionTable.setColumns(buildRRColumnsFromInversePermutationColumns(inversePermutationColumns));
        long finish = System.nanoTime();
        long timeElapsed = finish - start;
        storageConfiguration.addValueToRRT(recordReconstructionTable);
        System.out.println("RRT: " + timeElapsed / 1000000);
        return recordReconstructionTable;
    }

    private RecordReconstructionColumn mapColumn(Column column) {
        RecordReconstructionColumn recordReconstructionColumn = new RecordReconstructionColumn();
        recordReconstructionColumn.setName(column.getName());
        recordReconstructionColumn.setColumnId(column.getColumnId());
        int[] permutation = column.getCells().stream().mapToInt(Cell::getPositionInColumn).toArray();
        recordReconstructionColumn.setCells(getInversePermutationCells(permutation));
        return recordReconstructionColumn;
    }

    // for permutations started from 1
    int[] getInversePermutation(int[] permutation) {
        int[] inversePermutation = new int[permutation.length];
        for (int i = 1; i <= permutation.length; i++) {
            inversePermutation[permutation[i - 1] - 1] = i;
        }
        return inversePermutation;
    }

    // for permutations started from 1
    RecordReconstructionCell[] getInversePermutationCells(int[] permutation) {
        RecordReconstructionCell[] inversePermutation = new RecordReconstructionCell[permutation.length];
        for (int i = 1; i <= permutation.length; i++) {
            inversePermutation[permutation[i - 1] - 1] = new RecordReconstructionCell(i, false);
        }
        return inversePermutation;
    }

    private List<RecordReconstructionColumn> buildRRColumnsFromInversePermutationColumns(List<RecordReconstructionColumn> inversePermutationColumns) {
        List<RecordReconstructionColumn> finalColumns = new ArrayList<>();
        for (int i = 0; i < inversePermutationColumns.size(); i++) {
            RecordReconstructionColumn inversePermutation = inversePermutationColumns.get(i);
            RecordReconstructionColumn recordReconstructionColumn = mapColumnFromInversePermutation(inversePermutation);
            for (int j = 0; j < inversePermutation.getCells().length; j++) {
                int value = inversePermutation.getCells()[j].getValue();
                int nextColumn = i == inversePermutationColumns.size() - 1 ? 0 : i + 1;
                int valueFromRightColumn = inversePermutationColumns.get(nextColumn).getCells()[j].getValue();
                recordReconstructionColumn.getCells()[value - 1] = new RecordReconstructionCell(valueFromRightColumn, false);
            }
            finalColumns.add(recordReconstructionColumn);
        }
        return finalColumns;
    }

    private RecordReconstructionColumn mapColumnFromInversePermutation(RecordReconstructionColumn column) {
        RecordReconstructionColumn recordReconstructionColumn = new RecordReconstructionColumn();
        recordReconstructionColumn.setName(column.getName());
        recordReconstructionColumn.setColumnId(column.getColumnId());
        RecordReconstructionCell[] cells = new RecordReconstructionCell[column.getCells().length];
        recordReconstructionColumn.setCells(cells);
        return recordReconstructionColumn;
    }
}
