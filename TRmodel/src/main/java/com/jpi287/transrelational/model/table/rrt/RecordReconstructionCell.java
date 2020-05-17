package com.jpi287.transrelational.model.table.rrt;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RecordReconstructionCell {
    private int value;
    private boolean isDeleted;
}
