package com.jpi287.transrelational.services;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.importSQL.ImportToRRTable;
import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.common.ReconstructionService;
import com.jpi287.transrelational.services.select.JoinService;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class JoinServiceTest {

    private StorageConfiguration storageConfiguration = new StorageConfiguration();

    private JoinService joinService = new JoinService(storageConfiguration, new ReconstructionService());

    private ImportToRRTable importToRRTable = new ImportToRRTable(storageConfiguration);

    @Test
    public void testMergeJoin() {
        Table table1 = storageConfiguration.getFieldValuesTableByName("table1");
        Table table2 = createTestTable();
        List<Pair<Integer, Integer>> expected = Arrays.asList(new MutablePair<>(0,0), new MutablePair<>(1,1), new MutablePair<>(2,2),
                new MutablePair<>(3,3), new MutablePair<>(4,4));
        List<Pair<Integer, Integer>> actualPairs = joinService.mergeJoin(table1, table2, 0);
        Assert.assertEquals(expected, actualPairs);
    }

    @Test
    public void testInnerJoinByColumnName() {
        createTestTable();
        Table expected = new Table("newTable");
        Table resultedTable = joinService.innerJoinByColumnName("table1","testTable2", "Number");
        Assert.assertEquals(expected, resultedTable);
    }

    private Table createTestTable() {
        Table table2 = new Table("testTable2");

        Column column1 = new Column();
        column1.setCells(Arrays.asList(new Cell("S1", 4),
                new Cell("S2", 3), new Cell("S3", 5),
                new Cell("S4", 1), new Cell("S5", 2)));
        column1.setColumnId(0);
        column1.setName("Number");

        Column column2 = new Column();
        column2.setCells(Arrays.asList(new Cell("Adams4", 2),
                new Cell("Blake4", 5), new Cell("Clark4", 1),
                new Cell("Jones4", 3), new Cell("Smith4", 4)));
        column2.setColumnId(1);
        column2.setName("Name");

        Column column3 = new Column();
        column3.setCells(Arrays.asList(new Cell("104", 3),
                new Cell("204", 1), new Cell("204", 4),
                new Cell("304", 2), new Cell("304", 5)));
        column3.setColumnId(2);
        column3.setName("Status");

        Column column4 = new Column();
        column4.setCells(Arrays.asList(new Cell("Athens5", 2),
                new Cell("London4", 1), new Cell("London4", 4),
                new Cell("Paris4", 3), new Cell("Paris4", 5)));
        column4.setColumnId(3);
        column4.setName("City");
        table2.setColumns(Arrays.asList(column1, column2, column3, column4));
        storageConfiguration.addValueToFVT(table2);
        RecordReconstructionTable recordReconstructionTable = importToRRTable.convertToRecordReconstructionTable(table2);
        storageConfiguration.addValueToRRT(recordReconstructionTable);
        return table2;
    }
}
