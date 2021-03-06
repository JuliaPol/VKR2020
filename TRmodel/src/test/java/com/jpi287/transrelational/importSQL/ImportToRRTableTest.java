package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionCell;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionColumn;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;

@RunWith(SpringRunner.class)
public class ImportToRRTableTest {

    private RecordReconstructionTable recordReconstructionTable = new RecordReconstructionTable("recordReconstructionTable");

    private ImportToRRTable importToRRTable = new ImportToRRTable(new StorageConfiguration());

    @Test
    public void testInversePermutation() {
        int[] permutation = {4, 3, 5, 1, 2};
        int[] expected = {4, 5, 2, 1, 3};
        int[] inversePermutation = importToRRTable.getInversePermutation(permutation);
        for (int i = 0; i <= permutation.length - 1; i++) {
            Assert.assertEquals(expected[i], inversePermutation[i]);
        }
    }

    @Test
    public void testConvertToRecordReconstructionTable(){
        Table sortedTable = new Table("test");
        Column column1 = new Column();
        column1.setCells(Arrays.asList(new Cell("S1", 4),
                new Cell("S2", 3), new Cell("S3", 5),
                new Cell("S4", 1), new Cell("S4", 2)));

        Column column2 = new Column();
        column2.setCells(Arrays.asList(new Cell("Adams", 2),
                new Cell("Blake", 5), new Cell("Clark", 1),
                new Cell("Jones", 3), new Cell("Smith", 4)));

        Column column3 = new Column();
        column3.setCells(Arrays.asList(new Cell("10", 3),
                new Cell("20", 1), new Cell("20", 4),
                new Cell("30", 2), new Cell("30", 5)));

        Column column4 = new Column();
        column4.setCells(Arrays.asList(new Cell("Athens", 2),
                new Cell("London", 1), new Cell("London", 4),
                new Cell("Paris", 3), new Cell("Paris", 5)));
        sortedTable.setColumns(Arrays.asList(column1, column2, column3, column4));


        RecordReconstructionTable expectedTable = new RecordReconstructionTable("test");
        RecordReconstructionColumn rColumn1 = new RecordReconstructionColumn();
        rColumn1.setCells(new RecordReconstructionCell[]{new RecordReconstructionCell(5, false),
                new RecordReconstructionCell(4, false),
                new RecordReconstructionCell(2, false),
                new RecordReconstructionCell(3, false),
                new RecordReconstructionCell(1, false)});

        RecordReconstructionColumn rColumn2 = new RecordReconstructionColumn();
        rColumn2.setCells(new RecordReconstructionCell[]{new RecordReconstructionCell(4, false),
                new RecordReconstructionCell(5, false),
                new RecordReconstructionCell(2, false),
                new RecordReconstructionCell(1, false),
                new RecordReconstructionCell(3, false)});

        RecordReconstructionColumn rColumn3 = new RecordReconstructionColumn();
        rColumn3.setCells(new RecordReconstructionCell[]{new RecordReconstructionCell(4, false),
                new RecordReconstructionCell(2, false),
                new RecordReconstructionCell(3, false),
                new RecordReconstructionCell(1, false),
                new RecordReconstructionCell(5, false)});

        RecordReconstructionColumn rColumn4 = new RecordReconstructionColumn();
        rColumn4.setCells(new RecordReconstructionCell[]{new RecordReconstructionCell(5, false),
                new RecordReconstructionCell(4, false),
                new RecordReconstructionCell(1, false),
                new RecordReconstructionCell(2, false),
                new RecordReconstructionCell(3, false)});
        expectedTable.setColumns(Arrays.asList(rColumn1, rColumn2, rColumn3, rColumn4));

        RecordReconstructionTable actualTable = importToRRTable.convertToRecordReconstructionTable(sortedTable);
        Assert.assertEquals(expectedTable, actualTable);
    }
}
