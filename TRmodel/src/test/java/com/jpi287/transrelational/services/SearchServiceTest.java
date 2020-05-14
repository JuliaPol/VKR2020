package com.jpi287.transrelational.services;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.DummyTables;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import com.jpi287.transrelational.services.common.ReconstructionService;
import com.jpi287.transrelational.services.select.SearchService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class SearchServiceTest {

    private Table fieldValuesTable = DummyTables.FIELD_VALUES_TABLE;
    private RecordReconstructionTable recordReconstructionTable = DummyTables.RECORD_RECONSTRUCTION_TABLE;
    @Mock
    private StorageConfiguration storageConfiguration = new StorageConfiguration();


    private SearchService searchService = new SearchService(storageConfiguration, new ReconstructionService());

    @Test
    public void testFindByValue() {
        Row expected = new Row(Arrays.asList("Smith", "20", "London", "S1"));
        Row actual = searchService.getRowByValueAndColumnName(fieldValuesTable, recordReconstructionTable,"Number", "S1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectByEqualOperator() {
        Mockito.when(storageConfiguration.getFieldValuesTableByName("table1")).thenReturn(fieldValuesTable);
        Mockito.when(storageConfiguration.getRecordReconstructionTableeByName("table1")).thenReturn(recordReconstructionTable);
        List<Row> expected =  Arrays.asList(new Row(Arrays.asList("Smith", "20", "London", "S1")));
        List<Row> actual = searchService.selectByEqualOperator("table1","Number", "S1");
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectByGreaterThanOperator() {
        Mockito.when(storageConfiguration.getFieldValuesTableByName("table1")).thenReturn(fieldValuesTable);
        Mockito.when(storageConfiguration.getRecordReconstructionTableeByName("table1")).thenReturn(recordReconstructionTable);
        List<Row> expected =  Arrays.asList(new Row(Arrays.asList("Smith", "20", "London", "S1")),
                new Row(Arrays.asList("Jones", "10", "Paris", "S2")));
        List<Row> actual = searchService.selectByGreaterThanOperator("table1","Number", "S3", false);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSelectByLessThanOperator() {
        Mockito.when(storageConfiguration.getFieldValuesTableByName("table1")).thenReturn(fieldValuesTable);
        Mockito.when(storageConfiguration.getRecordReconstructionTableeByName("table1")).thenReturn(recordReconstructionTable);
        List<Row> expected =  Arrays.asList(new Row(Arrays.asList("Adams", "30", "Athens", "S5")),
                new Row(Arrays.asList("Clark", "20", "London", "S4")));
        List<Row> actual = searchService.selectByLessThanOperator("table1","Number", "S3", false);
        Assert.assertEquals(expected, actual);
    }
}
