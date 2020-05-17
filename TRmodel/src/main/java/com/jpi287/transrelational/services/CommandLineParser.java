package com.jpi287.transrelational.services;

import com.jpi287.transrelational.importSQL.ImportService;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.model.table.rrt.RecordReconstructionTable;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class CommandLineParser {

    private final ImportService importService;
//    private final Table fieldValuesTable;
//    private final RecordReconstructionTable recordReconstructionTable;

    private static final String IMPORT = "IMPORT";

    public void parseArguments(String line) {
        System.out.println("I'm here" + line);
        if (StringUtils.containsIgnoreCase(line, IMPORT)) {
            //TODO: exceptions
            String fileName = line.replaceFirst(IMPORT, "").trim();
//            importService.createModelFromImport(fileName);
        }

    }

    public void printRecordReconstructionTable() {
//        int columnNumber = recordReconstructionTable.getColumns().size();
//        StringBuilder lineFormat = printHeaderAndReturnLineFormat(recordReconstructionTable.getColumnNames(),
//                columnNumber);
//
//        StringBuilder leftAlignFormat = new StringBuilder("|");
//        for (int i = 0; i < columnNumber; i++) {
//            leftAlignFormat.append(" %-10d |");
//        }
//        leftAlignFormat.append("%n");
//
//        for (int i = 0; i < recordReconstructionTable.getColumns().get(0).getCells().length; i++) {
//            System.out.format(leftAlignFormat.toString(), recordReconstructionTable.getRowByNumber(i));
//        }
//        System.out.format(lineFormat.toString());
    }

    public void printFieldValuesTable() {
//        int columnNumber = fieldValuesTable.getColumns().size();
//        StringBuilder lineFormat = printHeaderAndReturnLineFormat(fieldValuesTable.getColumnNames(),
//                columnNumber);
//
//        StringBuilder leftAlignFormat = new StringBuilder("|");
//        for (int i = 0; i < columnNumber; i++) {
//            leftAlignFormat.append(" %-10s |");
//        }
//        leftAlignFormat.append("%n");
//
//        for (int i = 0; i < fieldValuesTable.getColumns().get(0).getCells().size(); i++) {
//            System.out.format(leftAlignFormat.toString(), fieldValuesTable.getRowByNumber(i));
//        }
//        System.out.format(lineFormat.toString());
    }

    private StringBuilder printHeaderAndReturnLineFormat(Object[] columnNames, int size) {
        StringBuilder columnNamesFormat = new StringBuilder("|");
        StringBuilder lineFormat = new StringBuilder("+");
        for (int i = 0; i < size; i++) {
            columnNamesFormat.append(" %-10s |");
            lineFormat.append("------------+");
        }
        columnNamesFormat.append("%n");
        lineFormat.append("%n");
        System.out.format(lineFormat.toString());
        System.out.format(columnNamesFormat.toString(), columnNames);
        System.out.format(lineFormat.toString());
        return lineFormat;
    }
}
