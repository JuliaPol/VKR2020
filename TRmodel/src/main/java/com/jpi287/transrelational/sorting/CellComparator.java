package com.jpi287.transrelational.sorting;

import com.jpi287.transrelational.model.table.Cell;

import java.util.Comparator;

public class CellComparator implements Comparator<Cell> {
    @Override
    public int compare(Cell o1, Cell o2) {
        return Comparator.comparing(Cell::getValue)
                .compare(o1, o2);
    }
}
