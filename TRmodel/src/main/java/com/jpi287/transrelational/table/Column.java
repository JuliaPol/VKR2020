package com.jpi287.transrelational.table;

import lombok.Data;

@Data
public class Column {
    private String name;
    private String columnId;
    private String[] fields;
}
