package com.jpi287.transrelational.services.common;

import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionCell;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Component
public class ReconstructionService {
    //алгоритм зигзага
    public RecordReconstructionCell[] getValuesFromRRTable(RecordReconstructionTable recordReconstructionTable, int columnIndex, int cellIndex, boolean isDeleted) {
        int size = recordReconstructionTable.getColumns().size();
        RecordReconstructionCell[] row = new RecordReconstructionCell[size];
        RecordReconstructionCell currentCell = recordReconstructionTable.getColumns().get(columnIndex).getCells()[cellIndex];
        int currentCellInt = currentCell.getValue();
        if (isDeleted) {
            currentCell.setDeleted(true);
        }
        row[columnIndex] = currentCell;
        int i = columnIndex + 1;
        while (i != columnIndex) {
            currentCell = recordReconstructionTable.getColumns().get(i).getCells()[currentCellInt - 1];
            currentCellInt = currentCell.getValue();
            if (isDeleted) {
                currentCell.setDeleted(true);
            }
            row[i] = currentCell;
            i = i == size - 1 ? 0 : i + 1;
        }
        return row;
    }

    public List<String> getValuesFromFVTable(Table fieldValuesTable, RecordReconstructionCell[] rowRR) {
        return IntStream.range(0, rowRR.length).boxed().map(i -> {
            int lineValue = rowRR[i].getValue();
            i = i == rowRR.length - 1 ? 0 : i + 1;
            if (rowRR[i].isDeleted()) {
                fieldValuesTable.getColumns().get(i).getCells().get(lineValue - 1).setDeleted(true);
                return "";
            } else {
               return fieldValuesTable.getColumns().get(i).getCells().get(lineValue - 1).getValue();
            }
        }).collect(Collectors.toList());
    }
}
