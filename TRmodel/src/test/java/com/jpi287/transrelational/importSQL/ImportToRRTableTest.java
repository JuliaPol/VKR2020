package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.table.Table;
import com.jpi287.transrelational.table.rrt.RecordReconstructionTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
public class ImportToRRTableTest {

    private RecordReconstructionTable recordReconstructionTable = new RecordReconstructionTable("recordReconstructionTable");

    private ImportToRRTable importToRRTable = new ImportToRRTable(recordReconstructionTable);

    @Test
    public void testInversePermutation() {
        int[] permutation = {4, 3, 5, 1, 2};
        int[] expected = {4, 5, 2, 1, 3};
        int[] inversePermutation = importToRRTable.getInversePermutation(permutation);
        for (int i = 0; i <= permutation.length - 1; i++) {
            Assert.assertEquals(expected[i], inversePermutation[i]);
        }
    }
}
