package com.jpi287.transrelational.model.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell {
    private String value;
    private int positionInColumn;
    private boolean isDeleted;

    public Cell(String value, int positionInColumn) {
        this.value = value;
        this.positionInColumn = positionInColumn;
    }
}
