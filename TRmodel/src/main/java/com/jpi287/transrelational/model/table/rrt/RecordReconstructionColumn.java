package com.jpi287.transrelational.model.table.rrt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReconstructionColumn {
    private String name;
    private int columnId;
    private int[] cells;
}
