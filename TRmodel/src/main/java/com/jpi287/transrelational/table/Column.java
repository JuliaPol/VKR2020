package com.jpi287.transrelational.table;

import lombok.Data;

import java.util.List;

@Data
public class Column {
    private String name;
    private String columnId;
    private List<Cell> cells;
}
