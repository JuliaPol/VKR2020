package com.jpi287.transrelational;

import com.jpi287.transrelational.importSQL.ImportToFVTable;
import com.jpi287.transrelational.importSQL.ImportToRRTable;
import com.jpi287.transrelational.model.Row;
import com.jpi287.transrelational.model.table.Table;
import com.jpi287.transrelational.services.CommandLineParser;
import com.jpi287.transrelational.services.GenerateData;
import com.jpi287.transrelational.services.select.JoinService;
import com.jpi287.transrelational.services.select.SelectByValueService;
import com.jpi287.transrelational.services.updateData.DeleteData;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

@Component
@AllArgsConstructor
public class Runner implements CommandLineRunner {

    private final CommandLineParser commandLineParser;
    private final Scanner scanner;
    private final ImportToFVTable importToFVTable;
    private final ImportToRRTable importToRRTable;
    private final GenerateData generateData;
    private final SelectByValueService selectByValueService;
    private final JoinService joinService;
    private final DeleteData deleteData;

    @Override
    public void run(String... args) {

        System.out.println("What your name?");
        String name = null;
        if (scanner.hasNext()) {
            name = scanner.nextLine();
            commandLineParser.parseArguments(name);
//            commandLineParser.printRecordReconstructionTable();
            System.out.println("                     ");
//            commandLineParser.printFieldValuesTable();
            int number = 1000;
            Table table = generateData.createTable10Column(number);
//            Table table2 = generateData.createTable10Column2(number);
            Table fvt = importToFVTable.convertToFieldValuesTable(table);
            importToRRTable.convertToRecordReconstructionTable(fvt);

//            Table fvt2 = importToFVTable.convertToFieldValuesTable(table2);
//            importToRRTable.convertToRecordReconstructionTable(fvt2);
            System.out.println("N: " + number/2);
            int size = fvt.getColumns().get(3).getCells().size();
            String index = fvt.getColumns().get(3).getCells().get(size /2).getValue();
//            List<Row> foundRow = selectByValueService.selectByLessThanOperator("testTable", "id", index, false);
            deleteData.deleteRowByColumnNameAndValue("testTable", "id", index);
//            System.out.println("count:"  + foundRow.size());
//            joinService.innerJoinByColumnName("testTable", "testTable2", "id");

        }
        System.out.println("Hello " + name);
    }
}
