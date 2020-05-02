package com.jpi287.transrelational.model.table;

import lombok.Data;

import java.util.List;

@Data
public class Column {
    private String name;
    private int columnId;
    private List<Cell> cells;
}
