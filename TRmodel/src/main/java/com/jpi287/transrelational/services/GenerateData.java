package com.jpi287.transrelational.services;

import com.jpi287.transrelational.model.table.Cell;
import com.jpi287.transrelational.model.table.Column;
import com.jpi287.transrelational.model.table.Table;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Component
public class GenerateData {

    public Table createTable5Column(int rows) {
        Table table = new Table("testTable");
        List<Column> columns = Arrays.asList(getColumn("name", 0, rows),
                getColumn("number", 1, rows),
                getColumn("value", 2, rows),
                getColumn("id", 3, rows),
                getColumn("city", 4, rows));
        table.setColumns(columns);
        return table;
    }

    public Table createTable10Column(int rows) {
        Table table = new Table("testTable");
        List<Column> columns = Arrays.asList(getColumn("name", 0, rows),
                getColumn("number", 1, rows),
                getColumn("value", 2, rows),
                getColumn("id", 3, rows),
                getColumn("city", 4, rows),
                getColumn("pass", 5, rows),
                getColumn("aaaa", 6, rows),
                getColumn("bbb", 7, rows),
                getColumn("qw", 8, rows),
                getColumn("cdggd", 9, rows));
        table.setColumns(columns);
        return table;
    }

    public Table createTable5Column2(int rows) {
        Table table = new Table("testTable2");
        List<Column> columns = Arrays.asList(getColumn("name_1", 0, rows),
                getColumn("number_1", 1, rows),
                getColumn("value_1", 2, rows),
                getColumn("id", 3, rows),
                getColumn("city_1", 4, rows));
        table.setColumns(columns);
        return table;
    }

    public Table createTable10Column2(int rows) {
        Table table = new Table("testTable2");
        List<Column> columns = Arrays.asList(getColumn("name_1", 0, rows),
                getColumn("number_1", 1, rows),
                getColumn("value_1", 2, rows),
                getColumn("id", 3, rows),
                getColumn("city_1", 4, rows),
                getColumn("pass_1", 5, rows),
                getColumn("aaaa_1", 6, rows),
                getColumn("bbb_1", 7, rows),
                getColumn("qw_1", 8, rows),
                getColumn("cdggd_1", 9, rows));
        table.setColumns(columns);
        return table;
    }
    private Column getColumn(String columnName, int columnId, int rows) {
        Column column = new Column();
        column.setColumnId(columnId);
        column.setName(columnName);
        List<Cell> cells = new ArrayList<>();
        for (int i = 1; i <= rows; i++) {
            Cell cell = new Cell(columnName + i ,i);
            cells.add(cell);
        }
        Collections.shuffle(cells);
        column.setCells(cells);
        return column;
    }

}
