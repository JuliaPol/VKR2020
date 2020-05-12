package com.jpi287.transrelational.services;

import com.jpi287.transrelational.model.DummyTables;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.select.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

@RunWith(SpringRunner.class)
public class SearchServiceTest {

    private Table fieldValuesTable = DummyTables.FIELD_VALUES_TABLE;
    private RecordReconstructionTable recordReconstructionTable = DummyTables.RECORD_RECONSTRUCTION_TABLE;

    private SearchService searchService = new SearchService(fieldValuesTable, recordReconstructionTable);

    @Test
    public void testFindByValue() {
        Row expected = new Row(Arrays.asList("Smith", "20", "London", "S1"));
        Row actual = searchService.getRowByValueAndColumnName("Number", "S1");
        Assert.assertEquals(expected, actual);
    }
}
