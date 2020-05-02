package com.jpi287.transrelational.importSQL;

import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class ImportToFVTableTest {

    private Table fieldValuesTable = new Table("fieldValuesTable");

    private ImportToFVTable importToFVTable = new ImportToFVTable(fieldValuesTable);

    @Test
    public void testConvert() {
        Table table = new Table();
        table.setName("table1");
        Column column1 = new Column();
        column1.setCells(Arrays.asList(new Cell("aaa", 0),
                new Cell("ccc", 1), new Cell("bbbb", 2)));
        Column column2 = new Column();
        column2.setCells(Arrays.asList(new Cell("ccc", 0),
                (new Cell("bbbb", 1)), (new Cell("aaa", 2))));
        table.setColumns(Arrays.asList(column1, column2));

        Table tableExpected = new Table();
        tableExpected.setName("fieldValuesTable");
        Column column1Expected = new Column();
        column1Expected.setCells(Arrays.asList((new Cell("aaa", 0)),
                (new Cell("bbbb", 2)), (new Cell("ccc", 1))));
        Column column2Expected = new Column();
        column2Expected.setCells(Arrays.asList((new Cell("aaa", 2)),
                (new Cell("bbbb", 1)), (new Cell("ccc", 0))));
        tableExpected.setColumns(Arrays.asList(column1Expected, column2Expected));

        Table newTable = importToFVTable.convertToFieldValuesTable(table);
        Assert.assertEquals(tableExpected, newTable);

    }
}
