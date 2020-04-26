package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.table.Table;
import com.jpi287.transrelational.table.rrt.RecordReconstructionTable;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ImportToRRTable {

    private final RecordReconstructionTable recordReconstructionTable;

    public RecordReconstructionTable convertToRecordReconstructionTable(Table sortedRelationalTable) {
        return null;
    }

    // for permutations started from 1
    public int[] getInversePermutation(int[] permutation) {
        int[] inversePermutation = new int[permutation.length];
        for (int i = 1; i <= permutation.length; i++) {
            inversePermutation[permutation[i - 1] - 1] = i;
        }
        return inversePermutation;
    }
}
