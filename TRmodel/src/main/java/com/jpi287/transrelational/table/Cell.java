package com.jpi287.transrelational.table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cell implements Comparable<Cell> {
    private String value;
    private int positionInColumn;

    @Override
    public int compareTo(Cell o) {
        return Comparator.comparing(Cell::getValue)
                .compare(this, o);
    }
}
