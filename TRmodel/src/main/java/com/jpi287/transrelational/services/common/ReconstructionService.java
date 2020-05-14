package com.jpi287.transrelational.services.common;

import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ReconstructionService {
    //алгоритм зигзага
    public int[] getValuesFromRRTable(RecordReconstructionTable recordReconstructionTable, int columnIndex, int cellIndex) {
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

    public List<String> getValuesFromFVTable(Table fieldValuesTable, int[] rowRR) {
        return IntStream.range(0, rowRR.length).boxed().map(i -> {
            int lineValue = rowRR[i];
            i = i == rowRR.length - 1 ? 0 : i + 1;
            return fieldValuesTable.getColumns().get(i).getCells().get(lineValue - 1).getValue();
        }).collect(Collectors.toList());
    }
}
