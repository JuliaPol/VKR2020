package com.jpi287.transrelational.model;

import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionColumn;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;

import java.util.Arrays;

public class DummyTables {
    public static final Table FIELD_VALUES_TABLE = getFieldValuesTable();

    public static final RecordReconstructionTable RECORD_RECONSTRUCTION_TABLE = getRecordReconstructionTable();

    private static Table getFieldValuesTable() {
        Table sortedTable = new Table("table1");
        Column column1 = new Column();
        column1.setCells(Arrays.asList(new Cell("S1", 4),
                new Cell("S2", 3), new Cell("S3", 5),
                new Cell("S4", 1), new Cell("S5", 2)));
        column1.setColumnId(0);
        column1.setName("Number");

        Column column2 = new Column();
        column2.setCells(Arrays.asList(new Cell("Adams", 2),
                new Cell("Blake", 5), new Cell("Clark", 1),
                new Cell("Jones", 3), new Cell("Smith", 4)));
        column2.setColumnId(1);
        column2.setName("Name");

        Column column3 = new Column();
        column3.setCells(Arrays.asList(new Cell("10", 3),
                new Cell("20", 1), new Cell("20", 4),
                new Cell("30", 2), new Cell("30", 5)));
        column3.setColumnId(2);
        column3.setName("Status");

        Column column4 = new Column();
        column4.setCells(Arrays.asList(new Cell("Athens", 2),
                new Cell("London", 1), new Cell("London", 4),
                new Cell("Paris", 3), new Cell("Paris", 5)));
        column4.setColumnId(3);
        column4.setName("City");
        sortedTable.setColumns(Arrays.asList(column1, column2, column3, column4));
        return sortedTable;
    }

    private static RecordReconstructionTable getRecordReconstructionTable() {
        RecordReconstructionTable expectedTable = new RecordReconstructionTable("table1");
        RecordReconstructionColumn rColumn1 = new RecordReconstructionColumn();
        rColumn1.setCells(new int[]{5, 4, 2, 3, 1});
        rColumn1.setName("Number");

        RecordReconstructionColumn rColumn2 = new RecordReconstructionColumn();
        rColumn2.setCells(new int[]{4, 5, 2, 1, 3});
        rColumn2.setName("Name");

        RecordReconstructionColumn rColumn3 = new RecordReconstructionColumn();
        rColumn3.setCells(new int[]{4, 2, 3, 1, 5});
        rColumn3.setName("Status");

        RecordReconstructionColumn rColumn4 = new RecordReconstructionColumn();
        rColumn4.setCells(new int[]{5, 4, 1, 2, 3});
        expectedTable.setColumns(Arrays.asList(rColumn1, rColumn2, rColumn3, rColumn4));
        rColumn4.setName("City");
        return expectedTable;
    }
}
