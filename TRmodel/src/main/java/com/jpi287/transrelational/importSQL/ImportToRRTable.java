package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.table.Cell;
import com.jpi287.transrelational.table.Column;
import com.jpi287.transrelational.table.Table;
import com.jpi287.transrelational.table.rrt.RecordReconstructionColumn;
import com.jpi287.transrelational.table.rrt.RecordReconstructionTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ImportToRRTable {

    private final RecordReconstructionTable recordReconstructionTable;

    public RecordReconstructionTable convertToRecordReconstructionTable(Table sortedRelationalTable) {
        List<RecordReconstructionColumn> inversePermutationColumns = sortedRelationalTable.getColumns().stream()
                .map(this::mapColumn).collect(Collectors.toList());
        recordReconstructionTable.setColumns(buildRRColumnsFromInversePermutationColumns(inversePermutationColumns));
        return recordReconstructionTable;
    }

    private RecordReconstructionColumn mapColumn(Column column) {
        RecordReconstructionColumn recordReconstructionColumn = new RecordReconstructionColumn();
        recordReconstructionColumn.setName(column.getName());
        recordReconstructionColumn.setColumnId(column.getColumnId());
        int[] permutation = column.getCells().stream().mapToInt(Cell::getPositionInColumn).toArray();
        recordReconstructionColumn.setCells(getInversePermutation(permutation));
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

    private List<RecordReconstructionColumn> buildRRColumnsFromInversePermutationColumns(List<RecordReconstructionColumn> inversePermutationColumns) {
        List<RecordReconstructionColumn> finalColumns = new ArrayList<>();
        for (int i = 0; i < inversePermutationColumns.size(); i++) {
            RecordReconstructionColumn inversePermutation = inversePermutationColumns.get(i);
            RecordReconstructionColumn recordReconstructionColumn = mapColumnFromInversePermutation(inversePermutation);
            for (int j = 0; j < inversePermutation.getCells().length; j++) {
                int value = inversePermutation.getCells()[j];
                int nextColumn = i == inversePermutationColumns.size() - 1 ? 0 : i + 1;
                int valueFromRightColumn = inversePermutationColumns.get(nextColumn).getCells()[j];
                recordReconstructionColumn.getCells()[value - 1] = valueFromRightColumn;
            }
            finalColumns.add(recordReconstructionColumn);
        }
        return finalColumns;
    }

    private RecordReconstructionColumn mapColumnFromInversePermutation(RecordReconstructionColumn column) {
        RecordReconstructionColumn recordReconstructionColumn = new RecordReconstructionColumn();
        recordReconstructionColumn.setName(column.getName());
        recordReconstructionColumn.setColumnId(column.getColumnId());
        int[] cells = new int[column.getCells().length];
        recordReconstructionColumn.setCells(cells);
        return recordReconstructionColumn;
    }

    public static void main(String[] args) {
        int[] permutation = {4, 3, 2, 5, 1};
        int[] p = (new ImportToRRTable(null)).getInversePermutation(permutation);
        System.out.println(p);
    }
}
