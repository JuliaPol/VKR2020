package com.jpi287.transrelational.table;

import lombok.Data;

@Data
public class Table {
    private String name;
    private Column[] columns;

    public Table(String name) {
        this.name = name;
    }
}
