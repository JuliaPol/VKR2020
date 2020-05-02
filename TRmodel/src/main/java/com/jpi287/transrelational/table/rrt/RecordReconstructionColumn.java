package com.jpi287.transrelational.table.rrt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReconstructionColumn {
    private String name;
    private String columnId;
    private int[] cells;
}
