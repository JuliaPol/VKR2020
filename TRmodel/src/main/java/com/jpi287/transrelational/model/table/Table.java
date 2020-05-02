package com.jpi287.transrelational.model.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Table {
    private String name;
    private List<Column> columns;

    public Table(String name) {
        this.name = name;
    }
}
