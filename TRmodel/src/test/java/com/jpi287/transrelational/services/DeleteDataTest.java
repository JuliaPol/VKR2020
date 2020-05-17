package com.jpi287.transrelational.services;

import com.jpi287.transrelational.configuration.StorageConfiguration;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.services.common.ReconstructionService;
import com.jpi287.transrelational.services.updateData.DeleteData;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
public class DeleteDataTest {

    private StorageConfiguration storageConfiguration = new StorageConfiguration();

    private DeleteData deleteData = new DeleteData(storageConfiguration, new ReconstructionService());

    @Test
    public void testDeleteRow() {
        Assert.assertTrue(deleteData.deleteRowByColumnNameAndValue("table1", "Number", "S1"));
    }

}
