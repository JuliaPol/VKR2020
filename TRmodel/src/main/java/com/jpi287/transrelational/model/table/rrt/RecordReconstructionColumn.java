package com.jpi287.transrelational.model.table.rrt;

import com.jpi287.transrelational.model.table.Cell;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReconstructionColumn {
    private String name;
    private int columnId;
    private RecordReconstructionCell[] cells;
}
